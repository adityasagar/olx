package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=(String)request.getParameter("flag");
		if(flag.trim().equalsIgnoreCase("register")){
		UserVO newUser= new UserVO();
		newUser.setName(((String)request.getParameter("name")).trim());
		newUser.setContact((String)request.getParameter("contact"));
		newUser.setEmail((String)request.getParameter("email"));
		newUser.setContact((String)request.getParameter("number"));
		newUser.setPwd((String)request.getParameter("pwd"));	
		//newUser.setJoiningDate(new java.sql.Date(new java.util.Date()));
		boolean check=RegisterUtility.signUp(newUser);
		System.out.println(newUser.getName());
		if(check){
			/*UserVO fullUser= RegisterUtility.getUserByEmail(newUser.getEmail());
			request.setAttribute("name", fullUser.getName());
			request.setAttribute("userid", fullUser.getUserId());
			request.setAttribute("user", fullUser);
			ArrayList<ProductVO> mostViewList= ProductUtility.getMostViewedResults("10");
			ArrayList<ProductVO> recentList= ProductUtility.getRecentResults("10");
			request.setAttribute("mostViewList", mostViewList);
			request.setAttribute("recentList", recentList);*/
			request.getRequestDispatcher("index.jsp").include(request, response);
		//request.getRequestDispatcher("Succes.html").include(request, response);
		}else{ 
			request.getRequestDispatcher("Error.html").include(request, response);	
		}
		}
		else if(flag.trim().equalsIgnoreCase("login")){
			String email=(String)request.getParameter("email");
			String pwd=(String)request.getParameter("pwd");
		UserVO  user=	RegisterUtility.verify(email, pwd);
		if(user!=null){
			//boolean loginFlag= true;
			String userName=user.getName();
			Long userid=user.getUserId();
			UserVO fullUser= RegisterUtility.getUser(userid);
			request.setAttribute("name", userName);
			request.setAttribute("userid", userid);
			request.setAttribute("user", fullUser);
			ArrayList<ProductVO> mostViewList= ProductUtility.getMostViewedResults("10");
			ArrayList<ProductVO> recentList= ProductUtility.getRecentResults("10");
			request.setAttribute("mostViewList", mostViewList);
			request.setAttribute("recentList", recentList);
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
			else {
				request.getRequestDispatcher("Error.html").include(request, response);
		}
		}
			//newUser.setUserId(userId);
		//String name=;
		//String password=request.getParameter("password");
		/*String email=;
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String addressLine=request.getParameter("addressLine");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String contact=request.getParameter("contact");
		*/
		
		// TODO Auto-generated method stub
	}

}
