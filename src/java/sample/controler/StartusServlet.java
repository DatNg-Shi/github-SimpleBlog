/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DaNT.more.Encrypt;
import DatNT.userInfo.UserInfoDAO;
import DatNT.userInfo.UserInfoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
@WebServlet(name = "StartusServlet", urlPatterns = {"/StartusServlet"})
public class StartusServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "WellcomePageServlet";
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
        PrintWriter out = response.getWriter();
                
        String url = LOGIN_PAGE;  
        try  {
            //1 lay cookie
            Cookie[] cookies = request.getCookies();
            //2  check cookie
            if (cookies != null) {
                //3. duyet cookie de get username va password
                for (Cookie cookie : cookies) {
                    String email = cookie.getName();
                    String password = cookie.getValue();
                    
                    Encrypt encrypt = new Encrypt();
                    String decodeEmail = encrypt.decodeEmail(email);
                
                    UserInfoDAO dao = new UserInfoDAO();
                    boolean result = dao.checkLogin(decodeEmail, password);
                    
                    if (result) {
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
                        session.setAttribute("EMAIL", decodeEmail + welcome);
                        session.setAttribute("NAME", thisName.getFullName());
                        break;
                        
                    }
                }
            }//end cookie is not null
        }catch (NamingException ex) {
            log ("StartusServlet _ NamingException: " + ex.getMessage());
        }catch (SQLException ex) {
            log ("StartusServlet _ SQLException: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log ("StartusServlet _ NoSuchAlgorithmException: " + ex.getMessage());
        }finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
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
