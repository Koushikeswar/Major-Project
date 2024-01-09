

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/EmployeeServelt")
public class EmployeeServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static DriverManager out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try 
		{
			Class.forName("com.mysql.jdbc.Driver"); 
		    Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project?characterEncoding=latin1","root","mysql"); 
		    String uname=request.getParameter("username");
		    String pwd=request.getParameter("password");
		    PreparedStatement ps=con.prepareStatement("select username from employee where username=? and password=?");
		    ps.setString(1, uname);
		    ps.setString(2, pwd);
		    ResultSet rs=ps.executeQuery();
		    PrintWriter out = response.getWriter();
		    if(rs.next())
		    {
		    	RequestDispatcher rd=request.getRequestDispatcher("DisplayDB");
		    	rd.forward(request, response);
		    }
		    else
		    {
//		    	out.print("Sorry username or password error");  
//		        RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
//		        rd.include(request,response); 
		    	response.setContentType("text/html");  
		    	out.println("<script type=\"text/javascript\">");  
		    	out.println("alert('Wrong password');");  
		    	out.println("window.location.assign('EmployeeLogin.html');");  
		    	out.println("</script>");
		    	
		    }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}






