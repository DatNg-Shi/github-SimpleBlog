/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DaNT.ArticleStatus.ArticleStatusDAO;
import DatNT.articles.ArticlesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
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
@WebServlet(name = "UpdateArticleStatusServlet", urlPatterns = {"/UpdateArticleStatusServlet"})
public class UpdateArticleStatusServlet extends HttpServlet {
    private final String ADMIN_PAGE = "WellComeAdminServlet";
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
        String url =ADMIN_PAGE;
        String confirm = request.getParameter("btAction");
        String tittle = request.getParameter("pk");
        System.out.println(confirm + " - confirm");
        String status = "Delete";
        System.out.println(tittle + " - tittle");
        try {
            if(confirm.trim().matches("Approval")) {
                status = "Active";
            }else if(confirm.trim().matches("Restore")) {
                status = "New";
            }
            ArticleStatusDAO stsDAO = new ArticleStatusDAO();
            int statusID = stsDAO.getArticleStatusID(status);
            System.out.println(statusID + " - statusID");
            ArticlesDAO dao = new ArticlesDAO();
            int result = dao.updateStatus(tittle, statusID);
            System.out.println(result + " -result");
        }catch (SQLException ex) {
            log ("UpdateArticleStatusServlet _ SQLException: " + ex.getMessage());
        }catch (NamingException ex) {
            log ("UpdateArticleStatusServlet _ NamingException: " + ex.getMessage());
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
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
