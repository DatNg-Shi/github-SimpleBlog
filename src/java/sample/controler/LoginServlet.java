/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DaNT.more.Encrypt;
import DatNT.userInfo.LoginError;
import DatNT.userInfo.UserInfoDAO;
import DatNT.userInfo.UserInfoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class LoginServlet extends HttpServlet {

    private final String RETURN_COMMENT_PAGE = "DetailArticleServlet";
    private final String LOGIN_ERROR = "login.jsp";
    private final String ADMIN_PAGE = "WellComeAdminServlet";
    private final String SEARCH_PAGE = "WellcomePageServlet";

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
        String clickComment = request.getParameter("btAction");

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        boolean foundErr = false;
        LoginError error = new LoginError();

        String url = LOGIN_ERROR;
        try {
            if (!email.matches(regex)) {
                foundErr = true;
                error.setEmailNotCorrect("Email wrong format!!!");
            }
            UserInfoDAO dao = new UserInfoDAO();
            boolean result = dao.checkLogin(email, password);

            if (result) {
                if (clickComment.matches("Comment")) {
                    url = RETURN_COMMENT_PAGE;
                } else {
                    UserInfoDTO thisName = dao.getFullName();
                    String welcome = null;
                    if (thisName.getStatus() == 0) {
                        welcome = " (member)";
                        url = SEARCH_PAGE;
                    } else {
                        welcome = " (Admin)";
                        url = ADMIN_PAGE;
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("EMAIL", email + welcome);
                    System.out.println(email);
                    session.setAttribute("NAME", thisName.getFullName());

                    Encrypt encrypt = new Encrypt();
                    String encyptEmail = encrypt.encryptEmail(email);

                    Cookie cookie = new Cookie(encyptEmail, password);
                    cookie.setMaxAge(60 * 60);
                    response.addCookie(cookie);
                }
            } else {
                foundErr = true;
                error.setAccountNotFound("This account not existed or Invalid email or password!!!");
                System.out.println(error.getAccountNotFound());
            }

            if (foundErr) {
                request.setAttribute("LOGINERROR", error);
            }

        } catch (NamingException ex) {
            log("LoginServlet _ NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet _ SQLException: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("LoginServlet _ NoSuchAlgorithmException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
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
