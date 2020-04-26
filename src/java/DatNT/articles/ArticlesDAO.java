/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.articles;

import DatNT.ultis.DBUtilites;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author nguye
 */
public class ArticlesDAO implements Serializable {

    private List<ArticlesDTO> listArticles;

    public List<ArticlesDTO> getListArticles() {
        return listArticles;
    }

    private ArticlesDTO dtoo;

    public ArticlesDTO getDTO() {
        return dtoo;
    }

    public void searchArByContent(int pageNum, int rowPerPage, String searchContent, String status, String searchTittle) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int start = rowPerPage * (pageNum - 1) + 1;
        int end = pageNum * rowPerPage;

        try {
            con = DBUtilites.makeConnection();
            String sql = "Select tittle, shortDescription, content, name, dateOfPost, statusAr "
                    + "From "
                    + "(Select Articles.tittle, Articles.shortDescription, Articles.content, UserInfo.name, "
                    + "Articles.dateOfPost, ArticleStatus.statusAr, ROW_NUMBER() OVER (ORDER BY Articles.dateOfPost desc) AS RowNum "
                    + "From Articles, UserInfo, ArticleStatus "
                    + "Where Articles.author = UserInfo.email "
                    + "and Articles.status = ArticleStatus.statusID "
                    + "and ArticleStatus.statusAr = ? "
                    + "and Articles.content Like ? "
                    + "and Articles.tittle Like ?) AS list "
                    + "Where RowNum >= ? and RowNum <= ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, "%" + searchContent + "%");
            stm.setString(3, "%" + searchTittle + "%");
            stm.setInt(4, start);
            stm.setInt(5, end);
            rs = stm.executeQuery();
            while (rs.next()) {
                String tittle = rs.getString("tittle");
                String shortDescription = rs.getString("shortDescription");
                String content = rs.getString("content");
                String author = rs.getString("name");

                Timestamp date = rs.getTimestamp("dateOfPost");
                String statusAr = rs.getString("statusAr");

                ArticlesDTO dto = new ArticlesDTO(tittle, shortDescription, content, author, date, statusAr);
                if (this.listArticles == null) {
                    this.listArticles = new ArrayList<>();
                }
                this.listArticles.add(dto);

            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public int totalPage(int numRowArPerPage, String searchContent, String status, String searchTittle) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page = 0;

        try {
            con = DBUtilites.makeConnection();
            String sql = "Select count (RowNum) "
                    + "From "
                    + "(Select Articles.tittle, ROW_NUMBER() OVER (ORDER BY Articles.dateOfPost desc) AS RowNum "
                    + "From Articles, UserInfo, ArticleStatus "
                    + "Where Articles.author = UserInfo.email "
                    + "and Articles.status = ArticleStatus.statusID "
                    + "and ArticleStatus.statusAr = ? "
                    + "and Articles.content Like ? "
                    + "and Articles.tittle Like ?) AS list ";
            stm = con.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, "%" + searchContent + "%");
            stm.setString(3, "%" + searchTittle + "%");

            rs = stm.executeQuery();
            if (rs.next()) {
                page = (float) rs.getInt(1);
                System.out.println(page + " page");
                if (page > 0) {
                    return (int) Math.ceil(page / numRowArPerPage);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return 0;
    }

    public boolean viewInforArticle(String tittleKey, String statusAr) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtilites.makeConnection();
            String sql = "Select tittle, shortDescription, content, name, dateOfPost, statusAr "
                    + "From Articles, ArticleStatus, UserInfo "
                    + "Where Articles.status = ArticleStatus.statusID "
                    + "And Articles.author = UserInfo.email "
                    + "And ArticleStatus.statusAr = ? "
                    + "And Articles.tittle = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, statusAr);
            stm.setString(2, tittleKey);

            rs = stm.executeQuery();
            while (rs.next()) {
                String tittle = rs.getString("tittle");
                String shortDescription = rs.getString("shortDescription");
                String content = rs.getString("content");
                String author = rs.getString("name");
                System.out.println(author);
                Timestamp date = rs.getTimestamp("dateOfPost");
                String status = rs.getString("statusAr");

                this.dtoo = new ArticlesDTO(tittle, shortDescription, content, author, date, status);

//                if (this.listArticles == null) {
//                    this.listArticles = new ArrayList<>();
//                }
//
//                this.listArticles.add(dto);
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return false;
    }

    public void showAllArticle(String statusAr) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        System.out.println(statusAr + " - statusAr");
        try {
            con = DBUtilites.makeConnection();
            String sql = "SELECT tittle, shortDescription, content, author, dateOfPost, statusAr "
                    + "FROM Articles, ArticleStatus "
                    + "WHERE Articles.status = ArticleStatus.statusID "
                    + "and Articles.status Like ? ";

            stm = con.prepareStatement(sql);
            stm.setString(1, statusAr);
            rs = stm.executeQuery();

            while (rs.next()) {
                String tittle = rs.getString("tittle");
                String shortDescription = rs.getString("shortDescription");
                String content = rs.getString("content");
                String author = rs.getString("author");
                Timestamp dateOfPost = rs.getTimestamp("dateOfPost");
                String status = rs.getString("statusAr");

                ArticlesDTO dto = new ArticlesDTO(tittle, shortDescription, content, author, dateOfPost, status);
                if (this.listArticles == null) {
                    this.listArticles = new ArrayList<>();
                }

                this.listArticles.add(dto);
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public int createArticle(String tittle, String shortDescription, String content, String author, Timestamp postingDate, int status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtilites.makeConnection();
            String sql = "Insert into Articles(tittle, shortDescription, content, author, dateOfPost, status) "
                    + "Values(?,?,?,?,?,?) ";
            stm = con.prepareStatement(sql);
            stm.setString(1, tittle);
            stm.setString(2, shortDescription);
            stm.setString(3, content);
            stm.setString(4, author);
            stm.setTimestamp(5, postingDate);
            stm.setInt(6, status);

            int row = stm.executeUpdate();
            if (row > 0) {
                return row;
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }

        }
        return 0;
    }

    public int updateStatus(String tittle, int statusId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilites.makeConnection();
            String sql = "Update Articles "
                    + "Set status = ? "
                    + "Where tittle = ? ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setString(2, tittle);

            int row = stm.executeUpdate();
            System.out.println(row + " - row ");
            if (row > 0) {
                return row;
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return 0;
    }
}
