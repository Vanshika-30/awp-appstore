/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AppStore.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AppStore.data.AppDao;
import com.AppStore.data.DataConnection;
import com.AppStore.data.MysubscriptionDao;
import com.AppStore.domain.AppCategory;
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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            throws ServletException, IOException, SQLException {
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
        ArrayList<Application> appList = (ArrayList<Application>)session.getAttribute("appLs");
        if(appList == null){
            appList = new ArrayList<Application>();
        }
        String userName = String.valueOf(session.getAttribute("uname"));
        appData.getDownloadsN(userName);
        Application app = null;
        
        MysubscriptionDao msdb = new MysubscriptionDao();
        
        if (appId != -1) {
            app = appData.getItem(appId);
            request.setAttribute("app",app);
            appList.add(app);
            try {
                appData.addAppToDownloads(userName, app);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        session.setAttribute("appLs",appList);
        String username =(String)session.getAttribute("uname");
        ServletContext context = getServletContext();
        RequestDispatcher dispatch;
        if(app !=null && app.getCategory()==AppCategory.BETA){
            msdb.addtomysubscription(appId,username,"pending...");
            session.setAttribute("DDD", appId);
            dispatch = context.getRequestDispatcher("/individualPage.html?id="+String.valueOf(appId));   
        }
        else{
            dispatch = context.getRequestDispatcher("/order_status.jsp");
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserDownloads.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserDownloads.class.getName()).log(Level.SEVERE, null, ex);
        }
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
