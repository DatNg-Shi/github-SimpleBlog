/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DatNT.articles.ArticlesDAO;
import DatNT.articles.ArticlesDTO;
import DatNT.ultis.DBUtilites;
import DatNT.userInfo.UserInfoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SearchArticleServlet", urlPatterns = {"/SearchArticleServlet"})
public class SearchArticleServlet extends HttpServlet {

    private final String ADMIN_PAGE = "adminPage.jsp";
    private final String ARTICLE_PAGE = "articleSearch.jsp";
    private final String VIEW_SEARCH_PAGE = "articleSearch.jsp";

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
        //PrintWriter out = response.getWriter();

        String searchArticle = request.getParameter("txtSearchArticle");
        
        int numRowPerPage = DBUtilites.totalRowPerPage;
        String status = request.getParameter("txtStatusAr");
        String tittle = request.getParameter("txtSearchTittle");
        String email = "";
        String url = ARTICLE_PAGE;
        
        System.out.println(status + " = statusAr");
        try {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("EMAIL");
            if (role != null) {
                email = role.split(" ")[0].trim();
                UserInfoDAO dao = new UserInfoDAO();
                if (dao.checkAdmin(email)) {
                    url = ADMIN_PAGE;
                }
            }

            //if (!searchArticle.equals("")) {}
            ArticlesDAO dao = new ArticlesDAO();
            int totalPage = dao.totalPage(numRowPerPage, searchArticle, status, tittle);
            System.out.println(totalPage + " totalPage");
            if (totalPage > 0) {
                dao.searchArByContent(1, numRowPerPage, searchArticle, status, tittle);
                List<ArticlesDTO> result = dao.getListArticles();
                request.setAttribute("SEARCHRESULT", result);
                request.setAttribute("SHOWARTICLE", "show");
                request.setAttribute("PAGE", ("1/" + totalPage));

            }

        } catch (SQLException ex) {
            log("SearchArticleServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchArticleServlet _ NamingException: " + ex.getMessage());
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
