

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
			String selectSQL = "SELECT * FROM MyTableFalkinburg0315;"; // Might need ;
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			response.getWriter().println("<html><body>");
			response.getWriter().println("<h2>Task List:</h2>");
			response.getWriter().println("<table border=\"1\">");
			response.getWriter().println("<tr><th>Title</th><th>Description</th><th>Due Date</th><th>Status</th></tr>");
			
			
			while (rs.next()) {
				response.getWriter().println("<tr>");
				response.getWriter().println("<td>" + rs.getString("TITLE") + "</td>");
				response.getWriter().println("<td>" + rs.getString("DESCRIPTION") + "</td>");
				response.getWriter().println("<td>" + rs.getString("DUE_DATE") + "</td>");
				response.getWriter().println("<td>" + rs.getString("STATUS") + "</td>");
				response.getWriter().println("</tr>");
			}
			response.getWriter().println("</table>");
			response.getWriter().println("<a href='search_falkinburg.html'>Back to Search Page</a>");
			response.getWriter().println("</body></html>");
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
