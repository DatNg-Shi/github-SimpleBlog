///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package sample.controler;
//
//import DatNT.userInfo.UserInfoDAO;
//import DatNT.userInfo.UserInfoDTO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.List;
//import javax.naming.NamingException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author nguye
// */
//@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
//public class SearchLastnameServlet extends HttpServlet {
//    
////    private final String SEARCH_PAGE = "search.html";
////    private final String VIEW_PAGE = "ViewSearchServlet";
//    private final String SEARCH_PAGE = "search.jsp";
//    private final String VIEW_PAGE = "search.jsp";
//    
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        String searchValue = request.getParameter("txtSearchValue");
//        String url = SEARCH_PAGE;
//        try {
//            if (!searchValue.equals("")) {
//                UserInfoDAO dao = new UserInfoDAO();
//                dao.searchLastname(searchValue);
//                
//                List<UserInfoDTO> result = dao.getListAccounts();
//                request.setAttribute("SEARCHRESULT", result);
//                
//                url = VIEW_PAGE;
//            }// end if search Value is not " "
//        }catch (NamingException ex) {
//            ex.printStackTrace();
//        }catch (SQLException ex) {
//            ex.printStackTrace();
//        }finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
