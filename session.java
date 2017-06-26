package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class session
 */
@WebServlet("/session")
public class session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();		
		
		out.println("<a href='/Servlets/session'>[Reload]</a>");
		out.println("<a href='/Servlets/session?a=destroy'>[Destroy Session]</a>");
		
		//Create Session
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(5); //Set max interval of 5 sec for session
		
		//Check if destroyed is clicked, so invalidate the session
		if(request.getParameter("a") != null){
			session.invalidate();
			out.println("Session Destroyed");
		} else {
		out.println("<h1> Session Created Successfully</h1><br>");
		out.println("<h3> Session Data</h3><br>");
		out.println("New Session: " + session.isNew());
		out.println("<br>Session ID: " + session.getId());
		
		//Convert from millisecond to readable format
		long x = session.getCreationTime();
		Date date = new Date(x);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		out.println("<br>Creation Time: " + date);
		
		out.println("<br>Last Accessed Time: " + session.getLastAccessedTime());
		
		//Check if session varibale is set or not, if yes then set to +1 value, else create a new session variable.
		Integer param = (Integer) session.getAttribute("MySessionVariable");
		if (param != null) {
            session.setAttribute("MySessionVariable", new Integer(param.intValue() + 1));
            param = (Integer) session.getAttribute("MySessionVariable");
            
        } else {
            param = new Integer(1);
            session.setAttribute("MySessionVariable", param);
            
        }
		out.println("<br> Number of Access: " + param.intValue());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
