import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet implements CometProcessor {

    private static final long serialVersionUID = 1L;

    private static final String CHARSET = "UTF-8";

    protected final ArrayList<HttpServletResponse> connections = new ArrayList<HttpServletResponse>();
    protected MessageHistory history;
    protected transient MessageQueue messageQueue = null;

    @Override
    public void init() throws ServletException {
        history = new MessageHistory(getServletContext());
        messageQueue = new MessageQueue(connections,history);
    }

    @Override
    public void destroy() {
        connections.clear();
        messageQueue.stop();
        messageQueue = null;
    }

    //Processes Comet event
    @Override
    public void event(CometEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getHttpServletRequest();
        HttpServletResponse response = event.getHttpServletResponse();

        if (event.getEventType() == CometEvent.EventType.BEGIN) {
            String action = request.getParameter("action");
            if (action != null) {
                if ("login".equals(action)) {
                    String nickname = request.getParameter("nickname");
                    request.getSession(true).setAttribute("nickname", nickname);
                    response.sendRedirect("index.jsp");
                    event.close();
                    return;
                }
                if("post".equals(action)){
                    String nickname = (String) request.getSession(true).getAttribute("nickname");
                    String message = request.getParameter("message");
                    messageQueue.add(nickname, message);
                    response.sendRedirect("post.jsp");
                    event.close();
                    return;
                }
                if("history".equals(action)){
                    response.setContentType("text/html; charset=" + CHARSET);
                    String format = request.getParameter("format");
                    history.export(format);
                    PrintWriter writer = response.getWriter();
                    writer.println("History ready.<a href=\"history.txt\" download>Click here to download.</a>");
                    writer.flush();
                    return;
                }
            }
            if (request.getSession(true).getAttribute("nickname") == null) {
                // Redirect to "login"
                log("Redirect to login for session: " + request.getSession(true).getId());
                response.sendRedirect("login.jsp");
                event.close();
                return;
            }
            begin(event, request, response);
        } else if (event.getEventType() == CometEvent.EventType.ERROR) {
            error(event, request, response);
        } else if (event.getEventType() == CometEvent.EventType.END) {
            end(event, request, response);
        } else if (event.getEventType() == CometEvent.EventType.READ) {
            read(event, request, response);
        }
    }

    protected void begin(@SuppressWarnings("unused") CometEvent event, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log("Begin for session: " + request.getSession(true).getId());

        response.setContentType("text/html; charset=" + CHARSET);

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        writer.println("<html><head><title>Chat</title></head><body bgcolor=\"#FFFFFF\">");
        writer.println("<div>Welcome to the chat.</div>");
        writer.flush();

        synchronized(connections) {
            connections.add(response);
        }

        messageQueue.add("Tomcat", request.getSession(true).getAttribute("nickname") + " joined the chat.");
    }

    protected void end(CometEvent event, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log("End for session: " + request.getSession(true).getId());
        synchronized(connections) {
            connections.remove(response);
        }

        PrintWriter writer = response.getWriter();
        writer.println("</body></html>");

        event.close();
    }

    protected void error(CometEvent event, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log("Error for session: " + request.getSession(true).getId());
        synchronized(connections) {
            connections.remove(response);
        }
        event.close();
    }

    //For logging purposes only
    protected void read(CometEvent event, HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream is = request.getInputStream();
        byte[] buf = new byte[512];
        while (is.available() > 0) {
            log("Available: " + is.available());
            int n = is.read(buf);
            if (n > 0) {
                log("Read " + n + " bytes: " + new String(buf, 0, n) + " for session: " + request.getSession(true).getId());
            } else if (n < 0) {
                log("End of file: " + n);
                end(event, request, response);
                return;
            }
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // In case there is no Comet processing enable, display error message
        response.setContentType("text/html; charset=" + CHARSET);
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        writer.println("<html><head><title>JSP Chat</title></head><body bgcolor=\"#FFFFFF\">");
        writer.println("This chat only supports Comet processing, change connector to one that supports it. ");
        writer.println("</body></html>");
    }
}
