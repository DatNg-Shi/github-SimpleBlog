/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.comment;

import DatNT.ultis.DBUtilites;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author nguye
 */
public class CommentDAO implements Serializable {

    private List<CommentDTO> listComment;

    public List<CommentDTO> getListComment() {
        return listComment;
    }

    public boolean searchListComment(String tittle, String userEmail) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtilites.makeConnection();
            String sql = "Select id, comment, name, emailUser, tittleComment "
                    + "From Comment, UserInfo, Articles "
                    + "Where UserInfo.email = Comment.emailUser "
                    + "And Articles.tittle = Comment.tittleComment "
                    + "And Comment.tittleComment = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, tittle);

            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("comment");
                String name = rs.getString("name");
                String email = rs.getString("emailUser");
                String userName = email.matches(userEmail) ? "You":name;
                String tittleComment = rs.getString("tittleComment");

                CommentDTO dto = new CommentDTO(id, comment, userName, tittleComment);
                if (this.listComment == null) {
                    this.listComment = new ArrayList<>();
                }
                this.listComment.add(dto);
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

    public int doComment(String comment, String email, String tittle) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilites.makeConnection();
            String sql = "INSERT INTO Comment(comment, emailUser, tittleComment) "
                    + "VALUES(?,?,?) ";
            stm = con.prepareStatement(sql);
            stm.setString(1, comment);
            stm.setString(2, email);
            stm.setString(3, tittle);

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

}
