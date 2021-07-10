package login;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class forgetPassword extends HttpServlet{

	
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
		{
		  String email = req.getParameter("email");
		  System.out.println(email);
		  try {
			  Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kj","root","krrish"); 
				PreparedStatement pst=con.prepareStatement("select * from user where email= ?");
				pst.setString(1,email);
				ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
			  sendMail mail = new sendMail();
			  SecureRandom random = new SecureRandom();
			  int num = random.nextInt(100000);
			  String str = String.format("%05d", num);
			  PreparedStatement pst1=con.prepareStatement("UPDATE user set otp=? where email=?");
		  		
			  	pst1.setInt(1,num);
				pst1.setString(2,email);
				int i=pst1.executeUpdate();
				System.out.println(i);
				  mail.send(email, "Forget Passowrd Email", "Your Otp is "+str);
				  RequestDispatcher dispatcher = req.getRequestDispatcher("updatepassword.jsp");
				  dispatcher.forward(req, res);
			}else
			{
				PrintWriter out = res.getWriter();
				out.print("email is not found");          
				System.out.println("yaha pe a gaya h");
				RequestDispatcher dispatcher=req.getRequestDispatcher("forget.jsp");
				dispatcher.include(req, res);
			
			}
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		}
		
}
		

	

