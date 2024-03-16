
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InsertFalkinburg
 */
@WebServlet("/InsertFalkinburg")
public class InsertFalkinburg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFalkinburg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Display the insertion form
        String insert_falkinburg = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "<meta charset=\"ISO-8859-1\">\n" +
        "<title>Add Tasks Page</title>\n" +
        "<link rel=\"stylesheet\" href=\"css/format.css\">\n" +
        "</head>\n" +
        "<body>\n" +
        "	<header>\n" +
        "		Add Tasks\n" +
        "	</header>\n" +
        "	<div class=container>\n" +
        "		<div class=\"left-box\">\n" +
        "			<a href=\"search_falkinburg.html\">Search Data</a>\n" +
        "		</div>\n" +
        "		<main class=\"right-box\">\n" +
        "			<h1>Task Details</h1>\n" +
        "			<form action=\"InsertFalkinburg\" method=\"post\">\n" +
        "				Title: <input type=\"text\" name=\"title\"><br>\n" +
        "				Description: <input type=\"text\" name=\"description\"><br>\n" +
        "				Due Date: <input type=\"text\" name=\"due_date\"><br>\n" +
        "				Status: <input type=\"text\" name=\"status\"><br>\n" +
        "				<input type=\"submit\" value=\"Submit\">\n" +
        "			</form>\n" +
        "		</main>\n" +
        "	</div>\n" +
        "</body>\n" +
        "</html>\n";
        
        response.getWriter().println(insert_falkinburg);   
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try (Connection connection = DBConnectionFalkinburg.getConnection()) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String due_date = request.getParameter("due_date");
			String status = request.getParameter("status");
			
			String insertSQL = "INSERT INTO MyTableFalkinburg0315 (TITLE, DESCRIPTION, DUE_DATE, STATUS) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, due_date);
			preparedStatement.setString(4, status);
			
			int rowsAffected = preparedStatement.executeUpdate();
			response.getWriter().println("<html><body>");
	        response.getWriter().println("<h1>Data Insertion Success</h1>");
	        response.getWriter().println("<p>" + rowsAffected + " row(s) inserted successfully.</p>");
	        response.getWriter().println("<a href='insert_falkinburg.html'>Back to Task Creation");
			response.getWriter().println("</body></html>");
		} catch(SQLException e) {
			e.printStackTrace();
			response.getWriter().println("<html><body>");
	        response.getWriter().println("<h1>Errors</h1>");
	        response.getWriter().println("<p> An error occured while inserting data.</p>");
	        response.getWriter().println("<a href='insert_falkinburg.html'>Back to Task Creation");
			response.getWriter().println("</body></html>");

		}
	}


}