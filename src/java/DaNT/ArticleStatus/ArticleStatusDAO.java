/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaNT.ArticleStatus;

import DatNT.ultis.DBUtilites;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author nguye
 */
public class ArticleStatusDAO implements Serializable {

    public int getArticleStatusID(String status) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtilites.makeConnection();
            String sql = "Select statusID "
                    + "From ArticleStatus "
                    + "Where statusAr = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, status);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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
}
