

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{ 
		    Class.forName("com.mysql.jdbc.Driver"); 
		    Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project?characterEncoding=latin1","root","mysql"); 
		    PreparedStatement st = con.prepareStatement("insert into data values(?, ?, ?, ?)");
		     st.setString(1, request.getParameter("productID"));
		     st.setString(2, request.getParameter("returnReason"));
		     st.setString(3, request.getParameter("returndetails"));
		     st.setString(4, request.getParameter("productCondition"));
		    
		     st.executeUpdate();
		     st.close();
		     con.close(); 
		     PrintWriter out = response.getWriter();
//		     out.println("<html><body><b>Successfully stored in Database"
//		     + "</b></body></html>");
		     response.setContentType("text/html");  
		    	out.println("<script type=\"text/javascript\">");  
		    	out.println("alert('Thank You. Happy Shopping.');");  
		    	out.println("window.location.assign('Home.html');");  
		    	out.println("</script>");
		}catch(Exception e){
		      System.out.println(e);} 
		}
}


