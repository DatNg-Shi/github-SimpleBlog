/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DatNT.articles.ArticlesDAO;
import DatNT.articles.ArticlesDTO;
import DatNT.ultis.DBUtilites;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "WellcomePageServlet", urlPatterns = {"/WellcomePageServlet"})
public class WellcomePageServlet extends HttpServlet {

    private final String ARTICLE_PAGE = "articleSearch.jsp";

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
        String url = ARTICLE_PAGE;

        int totalRow = DBUtilites.totalRowPerPage;
        String status = "Active";
        try {
            ArticlesDAO dao = new ArticlesDAO();
            int totalPage = dao.totalPage(totalRow, "", status, "");
            if (totalPage > 0) {
                dao.searchArByContent(1, totalRow, "", status, "");
           
                List<ArticlesDTO> result = dao.getListArticles();
                request.setAttribute("SEARCHRESULT", result);
                request.setAttribute("SHOWARTICLE", "show");
                request.setAttribute("PAGE", ("1/" + totalPage));

            }
            url = ARTICLE_PAGE;
        } catch (SQLException ex) {
            log("WellComePageServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("WellComePageServlet _ NamingException: " + ex.getMessage());
        } finally {
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
