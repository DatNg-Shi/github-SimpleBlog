/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_SERVLET = "SearchArticleServlet";
    private final String CREATE_NEW_ACCOUNT = "createNewAccount.jsp";
    private final String CHANGE_PAGE_SERVLET = "ChangePageServlet";
    private final String STARTUS_SERVLET = "StartusServlet";
    private final String DETAIL_ARTICLE_SERVLET = "DetailArticleServlet";
    private final String CHECK_LOGIN_SERVLET = "CheckLoginServlet";
    private final String WELLCOME_ADMIN_PAGE = "WellComeAdminServlet";
    private final String WELLCOME_PAGE_SERVLET = "WellcomePageServlet";
    private final String COMMENT_SERVLET = "CommentServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String CREATE_NEW_ARTICLE_SERVLET = "CreateNewArticleServlet";
    private final String CREATE_NEW_ACCOUNT_SERVLET = "CreateNewAccountServlet";
    private final String UPDATE_ARTICLE_STATUS_SERVLET = "UpdateArticleStatusServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //what do you eat?
        String button = request.getParameter("btAction");

        String url = LOGIN_PAGE;
        try {
            if (button == null) {
                url = STARTUS_SERVLET;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("changePage")) {
                url = CHANGE_PAGE_SERVLET;
            } else if (button.equals("LoginPage")) {
                url = LOGIN_PAGE;
            } else if (button.equals("tittle")) {
                url = DETAIL_ARTICLE_SERVLET;
            } else if (button.equals("showAll")) {
                url = WELLCOME_PAGE_SERVLET;
            } else if (button.equals("showAllAdminPage")) {
                url = WELLCOME_ADMIN_PAGE;
            } else if (button.equals("Cancel")) {
                url = WELLCOME_PAGE_SERVLET;
            } else if (button.equals("Sign Up")) {
                url = CREATE_NEW_ACCOUNT;
            } else if (button.equals("Post")) {
                url = CREATE_NEW_ARTICLE_SERVLET;
            } else if (button.equals("Comment")) {
                url = COMMENT_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("CheckLogin")) {
                url = CHECK_LOGIN_SERVLET;
            } else if (button.equals("Create New Account")) {
                url = CREATE_NEW_ACCOUNT_SERVLET;
            } else if (button.equals("Approval")) {
                url = UPDATE_ARTICLE_STATUS_SERVLET;
            } else if (button.equals("Delete")) {
                url = UPDATE_ARTICLE_STATUS_SERVLET;
            } else if (button.equals("Restore")) {
                url = UPDATE_ARTICLE_STATUS_SERVLET;
            }

            System.out.println(button + " button");
        } finally {
            System.out.println(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
