/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DaNT.ArticleStatus.ArticleStatusDAO;
import DatNT.articles.ArticlesDAO;
import DatNT.articles.PostArticleError;
import DatNT.ultis.DBUtilites;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "CreateNewArticleServlet", urlPatterns = {"/CreateNewArticleServlet"})
public class CreateNewArticleServlet extends HttpServlet {

    private final String ARTICLE_PAGE = "WellcomePageServlet";
    private final String ERROR_POST = "createNewArticle.jsp";

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
        String url = ERROR_POST;
        String email= "";
        
        String tittle = request.getParameter("txtInputTittle");
        String shortDescription = request.getParameter("txtInputShortDes");
        String content = request.getParameter("txtInputContent");
        String status = request.getParameter("txtStatus");
        Timestamp date = DBUtilites.getTime();

        boolean foundErr = false;
        PostArticleError errors = new PostArticleError();
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (tittle.isEmpty()) {
                    foundErr = true;
                    errors.setTittleEmptyErr("The tittle must not empty!!!");
                }
                if (shortDescription.isEmpty()) {
                    foundErr = true;
                    errors.setShortDescriptionEmptyErr("The Short Description must not empty!!!");
                }
                if (content.isEmpty()) {
                    foundErr = true;
                    errors.setContentEmptyErr("The content must not empty!!!");
                }
                if (foundErr) {
                    request.setAttribute("POSTERRORS", errors);
                    url = ERROR_POST;
                } else {
                    String user = (String) session.getAttribute("EMAIL");
                    email = user.split(" ")[0].trim();
                    if (email != null) {
                        ArticlesDAO dao = new ArticlesDAO();
                        ArticleStatusDAO dt = new ArticleStatusDAO();
                        int statusID = dt.getArticleStatusID(status);
                        int result = dao.createArticle(tittle, shortDescription, content, email, date, statusID);
                        
                        if (result > 0) {
                            url = ARTICLE_PAGE;
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            log("CreateNewArticleServlet _ SQLException " + ex.getMessage());
            if (ex.getMessage().contains("duplicate")) {
                errors.setTittleIsExisted(tittle + " is existed!!!");
                request.setAttribute("POSTERRORS", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewArticleServlet _ NamingException " + ex.getMessage());
        } finally {
            System.out.println(errors.getTittleEmptyErr());
            System.out.println(errors.getShortDescriptionEmptyErr());
            System.out.println(errors.getContentEmptyErr());
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
