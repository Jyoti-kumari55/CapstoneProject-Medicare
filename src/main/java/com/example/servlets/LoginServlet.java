package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.controller.FactoryProvider;
import com.example.dao.UserDao;
import com.example.entities.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //coding area
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //validations
            //authenticating user
            UserDao userDao = new UserDao(FactoryProvider.getFactory());
            User user = userDao.getUserByEmailAndPassword(email, password);

            //System.out.println(user);
            HttpSession httpSession = request.getSession();
            if (user == null) {
                httpSession.setAttribute("message", "Invalid Details !! Try with another one");
                response.sendRedirect("login.jsp");
                return;
            } else {
                out.println("<h1>Welcome " + user.getUserName() + " </h1>");

                //login
                httpSession.setAttribute("current-user", user);

                if (user.getUserType().equals("admin")) {
                    //admin:-admin.jsp
                    response.sendRedirect("admin.jsp");
                } else if (user.getUserType().equals("normal")) {
                    //normal :normal.jsp
                    response.sendRedirect("index.jsp");
                }else
                {
                    out.println("We have not identified user type");
                }

            }

        }
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
		doGet(request, response);
	}
	 @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>
}
