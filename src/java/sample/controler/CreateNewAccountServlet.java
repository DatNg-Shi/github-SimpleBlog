/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controler;

import DaNT.more.Encrypt;
import DatNT.userInfo.UserInfoCreateError;
import DatNT.userInfo.UserInfoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
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
        
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String comfirm = request.getParameter("txtComfirm");
        String fullName = request.getParameter("txtFullname");
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        int role = 0;
        int status = 0;
        
        
        boolean foundErr = false;
        UserInfoCreateError errors = new UserInfoCreateError();
        
        String url = ERROR_PAGE;
        try {
            //1.Check 4 er users
            if (!email.trim().matches(regex)) {
               foundErr = true; 
               errors.setEmailFormatErr("Email must match format chars@example.com!!!");
            }
            
            if (password.trim().length() < 6 || password.trim().length() > 20) {
               foundErr = true; 
               errors.setPasswordLengthErr("Password length requires from 6 to 30 chars!!!");
            } else if (!comfirm.trim().equals(password.trim())) {
               foundErr = true; 
               errors.setConfirmNotMatchPassword("Confirm must match password!!!");
            }
            
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
               foundErr = true; 
               errors.setFullNameLengthErr("Full name length requires from 2 to 50 chars!!!");
            }
            
            if (foundErr) {
                request.setAttribute("CREATEERRORS", errors);
            } else {
                Encrypt encrypt = new Encrypt();
                String passAfterEncypt = encrypt.encryptPassword(password);
                UserInfoDAO dao = new UserInfoDAO();
                boolean result = dao.createNewAccount(email, fullName, passAfterEncypt, role, status);
                
                if (result) {
                    url = LOGIN_PAGE;
                }//end if result 
            }
        }catch (SQLException ex) {
            log("CreateNewAccount _ SQLException " + ex.getMessage());
            if (ex.getMessage().contains("duplicate")) {
                errors.setEmailIsExisted(email + " is existed!!!");
                request.setAttribute("CREATEERRORS", errors);
            }
        }catch (NamingException ex) {
            log("CreateNewAccount _ NamingException " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("CreateNewAccount _ NoSuchAlgorithmException " + ex.getMessage());
        }finally {
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
