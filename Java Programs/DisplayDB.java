

import java.io.*;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayDB
 */
@WebServlet("/DisplayDB")
public class DisplayDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	       response.setContentType("text/html"); 
    	       PrintWriter out = response.getWriter(); 
    	       try{ 
    	          Class.forName("com.mysql.jdbc.Driver"); 
    	          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project?characterEncoding=latin1","root","mysql"); 
    	          
    	          
    	          
    	          PreparedStatement ps=con.prepareStatement("select * from data"); 
    	          Statement stmt=con.createStatement();
    	          
    	          
    	          out.print("<center>"); 
    	          out.print("<table width=50% border=1>"); 
    	          out.print("<caption><b>DataBase</b></caption>"); 
    	          ResultSet rs=ps.executeQuery(); 
    	         /* Printing column names */ 
    	          ResultSetMetaData rsmd=rs.getMetaData(); 
    	          int total=rsmd.getColumnCount(); 
    	          out.print("<tr>"); 
    	          for(int i=1;i<=total;i++) 
    	          { 
    	              out.print("<th>"+rsmd.getColumnName(i)+"</th>"); 
    	              if(i==total)
    	              {
    	            	  out.print("<th>"+"WareHouse Status"+"</th>");
    	              }
    	          } 
    	          //out.print("<th>Status</th></tr>"); 
    	         /* Printing result */ 
    	         while(rs.next()) 
    	         { 
    	        	 String s=rs.getString("returnReason");
    	        	 String s1=rs.getString("productCondition");
    	        	 if(s.equals("excess_supply")||s.equals("wrong_delivery")||s.equals("customer_dissatisfaction"))
    	        	 {
    	        	 out.println("<tr><td>" + rs.getString("productID") + "</td><td>" + rs.getString("returnReason") + "</td><td>" + rs.getString("returndetails") + "</td><td>"+ rs.getString("productCondition") + "</td><td>"+"Normal/Undamaged Warehouse"+"</td></tr>");
    	        	 }
    	        	 else if(s.equals("other"))
    	        	 {
    	        		 if(s1.equals("heavily_damaged")||s1.equals("Unusable"))
    	        		 {
    	        			 out.println("<tr><td>" + rs.getString("productID") + "</td><td>" + rs.getString("returnReason") + "</td><td>" + rs.getString("returndetails") + "</td><td>"+ rs.getString("productCondition") + "</td><td>"+"Damaged Warehouse"+"</td></tr>");
    	        		 }
    	        	 }
    	        	 else
    	        	 {
    	        		 out.println("<tr><td>" + rs.getString("productID") + "</td><td>" + rs.getString("returnReason") + "</td><td>" + rs.getString("returndetails") + "</td><td>"+ rs.getString("productCondition") + "</td><td>"+"Damaged Warehouse"+"</td></tr>");
    	        	 }
    	        	 //out.println("<tr><td>" + rs.getString("productID") + "</td><td>" + rs.getString("returnReason") + "</td><td>" + rs.getString("returndetails") + "</td><td>"+ rs.getString("productCondition") + "</td><td>"+"Damaged Warehouse"+"</td></tr>");

    	         } 
    	out.print("</table>"); 
    	out.print("</center>"); 
    	}catch (Exception e2) {e2.printStackTrace();} 
    	finally{out.close();} 
    	       
    	       
    	       
    	       
   	} 
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


