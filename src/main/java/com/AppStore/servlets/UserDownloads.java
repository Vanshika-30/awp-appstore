/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AppStore.servlets;

import com.AppStore.data.AppDao;
import com.AppStore.data.DataConnection;
import com.AppStore.domain.Application;
import com.AppStore.domain.Downloads;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author HP
 */
@WebServlet(name = "UserDownloads", urlPatterns = {"/download.html"})
public class UserDownloads extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int appId = Integer.parseInt(request.getParameter("appId"));
        AppDao appData = DataConnection.getAppDao();
        HttpSession session = request.getSession();
        if (session.getAttribute("LoggedIn") == null) {
            session.setAttribute("LoggedIn", false);
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/index.html");
            dispatch.forward(request, response);
        }
        String userName = String.valueOf(session.getAttribute("uname"));
        System.out.println("USERNAME!!!!!!!!!!!!!!!!!!!!!!!!!" + userName);
        appData.getUserDownloads(userName);
        if (appId != -1) {
            Application app = appData.getItem(appId);
            appData.addToDownloads(userName, app, app.getVersion());
        }
        Downloads userDownloads = appData.getUserDownloads(userName);
        System.out.println(userDownloads.getApplications().toString());
        session.setAttribute("appLs", userDownloads.getApplications());
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/downloadsPage.jsp");
        dispatch.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
