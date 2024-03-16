
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
        "			<a href=\"SearchFalkinburg\">Search Data</a>\n" +
        "		</div>\n" +
        "		<main class=\"right-box\">\n" +
        "			<h1>Task Details</h1>\n" +
        "			<form action=\"InsertFalkinburg\" method=\"post\">\n" +
        "				Title: <input type=\"text\" name=\"title\"><br>\n" +
        "				Description: <input type=\"text\" name=\"description\"><br>\n" +
        "				Due Date: <input type=\"text\" name=\"due_date\"><br>\n" +
        "				Status: <select name=\"status\">\r\n"
        + "					<option value=\"Incomplete\">Incomplete</option>\r\n"
        + "					<option value=\"In_Progress\">In Progress</option>\r\n"
        + "					<option value=\"Complete\">Complete</option>\r\n"
        + "				</select><br>\r\n"
        + "				Priority: <select name=\"priority\">\r\n"
        + "					<option value=\"1\">1 - Highest</option>\r\n"
        + "					<option value=\"2\">2</option>\r\n"
        + "					<option value=\"3\">3</option>\r\n"
        + "					<option value=\"4\">4</option>\r\n"
        + "					<option value=\"5\">5</option>\r\n"
        + "					<option value=\"6\">6</option>\r\n"
        + "					<option value=\"7\">7</option>\r\n"
        + "					<option value=\"8\">8</option>\r\n"
        + "					<option value=\"9\">9</option>\r\n"
        + "					<option value=\"10\">10 - Lowest</option>\r\n"
        + "				</select><br>\r\n" +
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
			String priority = request.getParameter("priority");

			String insertSQL = "INSERT INTO MyTableFalkinburg0315 (TITLE, DESCRIPTION, DUE_DATE, STATUS, PRIORITY) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, due_date);
			preparedStatement.setString(4, status);
			preparedStatement.setString(5, priority);
			
			int rowsAffected = preparedStatement.executeUpdate();
			response.getWriter().println("<html><body>");
	        response.getWriter().println("<h1>Data Insertion Success</h1>");
	        response.getWriter().println("<p>" + rowsAffected + " row(s) inserted successfully.</p>");
	        response.getWriter().println("<a href='InsertFalkinburg'>Back to Task Creation");
			response.getWriter().println("</body></html>");
		} catch(SQLException e) {
			e.printStackTrace();
			response.getWriter().println("<html><body>");
	        response.getWriter().println("<h1>Errors</h1>");
	        response.getWriter().println("<p> An error occured while inserting data.</p>");
	        response.getWriter().println("<a href='InsertFalkinburg'>Back to Task Creation");
			response.getWriter().println("</body></html>");

		}
	}


}