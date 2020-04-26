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
@WebServlet(name = "ChangePageServlet", urlPatterns = {"/ChangePageServlet"})
public class ChangePageServlet extends HttpServlet {

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
        String status = request.getParameter("txtStatusAr");
        String tittle = request.getParameter("txtSearchTittle");
        String page = request.getParameter("page").trim();
        String pages[] = new String[2];
        String option = request.getParameter("option");
        int numRowPerPage = DBUtilites.totalRowPerPage;
        String email = "";
        String url = ARTICLE_PAGE;

        try {
            if (page != null) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    String user = (String) session.getAttribute("EMAIL");
                    if (user != null) {
                        email = user.split(" ")[0].trim();
                        UserInfoDAO dao = new UserInfoDAO();
                        if (dao.checkAdmin(email)) {
                            url = ADMIN_PAGE;
                        }
                    }
                }

                pages = page.split("/");
                int pageNum = Integer.parseInt(pages[0]);
                ArticlesDAO dao = new ArticlesDAO();
                int totalPage = dao.totalPage(numRowPerPage, searchArticle, status, tittle);
                if (totalPage > 0) {
                    if (option.matches("previous") && pageNum > 1) {
                        pageNum = pageNum - 1;
                    } else if (option.matches("next") && pageNum < totalPage) {
                        pageNum = pageNum + 1;
                    }
                    dao.searchArByContent(pageNum, numRowPerPage, searchArticle, status, tittle);
                    List<ArticlesDTO> result = dao.getListArticles();
                    request.setAttribute("SEARCHRESULT", result);
                    request.setAttribute("SHOWARTICLE", "show");
                    request.setAttribute("PAGE", pageNum + "/" + totalPage);

                 

                }
            }

        } catch (SQLException ex) {
            log("ChangePageServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ChangePageServlet _ NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //response.sendRedirect(url);
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
