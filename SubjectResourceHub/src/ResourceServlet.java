import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResourceServlet")
public class ResourceServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_hub";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "15Oct2005@"; 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("view")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                
                String sql = "SELECT * FROM resources ORDER BY upvotes DESC";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                request.setAttribute("resourcesData", rs);
                request.getRequestDispatcher("board.jsp").forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/html");
                response.getWriter().println("<h3>Error connecting to database: " + e.getMessage() + "</h3>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                
                if ("add".equals(action)) {
                    String subject = request.getParameter("subject");
                    String description = request.getParameter("description");
                    String linkUrl = request.getParameter("link_url");

                    String sql = "INSERT INTO resources (subject, description, link_url, upvotes) VALUES (?, ?, ?, 0)";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, subject);
                        stmt.setString(2, description);
                        stmt.setString(3, linkUrl);
                        stmt.executeUpdate();
                    }
                } 
                else if ("upvote".equals(action)) {
                    int resourceId = Integer.parseInt(request.getParameter("resource_id"));

                    String sql = "UPDATE resources SET upvotes = upvotes + 1 WHERE id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, resourceId);
                        stmt.executeUpdate();
                    }
                }
            }
            
            response.sendRedirect("ResourceServlet?action=view");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3>Database Error: " + e.getMessage() + "</h3>");
        }
    }
}