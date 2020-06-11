package com.AppStore.servlets;
import com.AppStore.data.LoginDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String type = request.getParameter("type");

        if(uname.isEmpty() || pass.isEmpty()){
            String msg = "Fields can't be empty...";
            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("login.jsp");
        }

        if(type.equals("admin")) {
            if(uname.equals("admin") && pass.equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("uname", uname);
                response.sendRedirect("admin.jsp");
            }
        }
        else {
//			System.out.println("hello in login");
            LoginDao dao = new LoginDao();

            try {
                if(dao.checkDetails(uname, pass)) {
//					System.out.println("granted");
                    HttpSession session = request.getSession();
                    session.setAttribute("uname", uname);
                    response.sendRedirect("index.jsp");
                }
                else {
                    String msg = "Incorrect username or password...";
                    request.getSession().setAttribute("msg", msg);
                    response.sendRedirect("login.jsp");
                }
            } catch (SQLException | IOException e) {

                e.printStackTrace();
            }
        }
    }


}
