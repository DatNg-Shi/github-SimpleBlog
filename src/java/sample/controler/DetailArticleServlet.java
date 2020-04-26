/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DatNT.articles.ArticlesDAO;
import DatNT.articles.ArticlesDTO;
import DatNT.comment.CommentDAO;
import DatNT.comment.CommentDTO;
import DatNT.userInfo.UserInfoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
@WebServlet(name = "DetailArticleServlet", urlPatterns = {"/DetailArticleServlet"})
public class DetailArticleServlet extends HttpServlet {

    private final String ERROR_PAGE = "articleSearch.jsp";
    private final String INFORARTICLE_PAGE = "detailsArticle.jsp";

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

        String tittle = request.getParameter("pk");
        String status = request.getParameter("txtStatusAr");
        System.out.println(status + ": status");
        String email = " ";
        //System.out.println(tittle + " - is tittle");
        String url = INFORARTICLE_PAGE;
        try {
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("EMAIL");
            if (user != null) {
                email = user.split(" ")[0].trim();
                System.out.println(email + " - admin");
                UserInfoDAO dao = new UserInfoDAO();
                if(dao.checkAdmin(email)) {
                    request.setAttribute("USERROLE", "Admin");
                }
                
            }
            //System.out.println(email + " - is email");
            if (!tittle.equals("")) {
                ArticlesDAO daoArticle = new ArticlesDAO();
                daoArticle.viewInforArticle(tittle, status);
                ArticlesDTO viewResult = daoArticle.getDTO();
                request.setAttribute("VIEWRESULT", viewResult);

                CommentDAO daoComment = new CommentDAO();
                daoComment.searchListComment(tittle, email);
                List<CommentDTO> listC = daoComment.getListComment();
                request.setAttribute("LISTCOMMENTRESULT", listC);

            }
//            String tittle = dto.getTittle();
//            String shorDescription = dto.getShortDescription();
//            String content = dto.getContent();
//            String author = dto.getAuthor();
//            Timestamp dateOfPost = dto.getDateOfPost();
//            String status = dto.getStatus();
        } catch (SQLException ex) {
            log("DetailArticleServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DetailArticleServlet _ NamingException: " + ex.getMessage());
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
