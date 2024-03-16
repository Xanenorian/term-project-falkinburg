

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchFalkinburg
 */
@WebServlet("/SearchFalkinburg")
public class MyServletFalkinburg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletFalkinburg() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (Connection connection = DBConnectionFalkinburg.getConnection()) {
			String selectSQL = "SELECT * FROM MyTableFalkinburg0315 order by case when status = 'Completed' then 1 else 0 end, priority;"; // Might need ;
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			String searchFalkinburgStart = "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Search Page</title>\r\n"
					+ "<link rel=\"stylesheet\" href=\"css/format.css\">\r\n"
					+ "\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "	<header>\r\n"
					+ "		To-Do List\r\n"
					+ "	</header>\r\n"
					+ "	\r\n"
					+ "	\r\n"
					+ "	<div class=\"container\">\r\n"
					+ "		<div class=\"left-box\">\r\n"
					+ "			<a href=\"InsertFalkinburg\">Add Tasks</a>\r\n"
					+ "		</div>\r\n"
					+ "		<main class=\"right-box\">\r\n";
	        response.getWriter().println(searchFalkinburgStart);   

					  
			response.getWriter().println("<html><body>");
			response.getWriter().println("<h2>Task List:</h2>");
			response.getWriter().println("<table border=\"1\">");
			response.getWriter().println("<tr><th>Title</th><th>Description</th><th>Due Date</th><th>Status</th><th>Priority</th></tr>");
			
			 

			while (rs.next()) {
				response.getWriter().println("<tr>");
				response.getWriter().println("<td>" + rs.getString("TITLE") + "</td>");
				response.getWriter().println("<td>" + rs.getString("DESCRIPTION") + "</td>");
				response.getWriter().println("<td>" + rs.getString("DUE_DATE") + "</td>");
				response.getWriter().println("<td>" + rs.getString("STATUS") + "</td>");
				response.getWriter().println("<td>" + rs.getString("PRIORITY") + "</td>");
				response.getWriter().println("</tr>");
			}
			response.getWriter().println("</table>");
			String searchFalkinburgEnd = "</main>\r\n"
					+ "	</div>\r\n"
					+ "</body>\r\n"
					+ "</html>";
			        response.getWriter().println(searchFalkinburgEnd);  
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
