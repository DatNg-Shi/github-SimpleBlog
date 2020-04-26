/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.userInfo;

import DaNT.more.Encrypt;
import DatNT.articles.ArticlesDTO;
import DatNT.ultis.DBUtilites;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
public class UserInfoDAO implements Serializable {

    private UserInfoDTO fullName;

    public boolean checkLogin(String email, String password)
            throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Make connection
            con = DBUtilites.makeConnection();
            if (con != null) {
                Encrypt encrypt = new Encrypt();
                String passAfterEncypt = encrypt.encryptPassword(password);
                //2. Create Sql String
                String sql = "Select email, name, password, role, status "
                        + "From UserInfo "
                        + "Where email = ? And password = ?";
                //3. Create Statement & assign parameter
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, passAfterEncypt);
                //4. Excute query
                rs = stm.executeQuery();
                //5. Process rs
                if (rs.next()) {
                    String name = rs.getString("name");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    this.fullName = new UserInfoDTO(email, name, password, role, status);

                    return true;
                }
            } //end if con is null
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
        return false;
    }

    private List<UserInfoDTO> listAccounts;

    public List<UserInfoDTO> getListAccounts() {
        return listAccounts;
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtilites.makeConnection();
            if (con != null) {
                String sql = "SELECT UserRoles.roleInfo"
                        + "FROM UserRoles, UserInfo "
                        + "Where username = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkAdmin(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBUtilites.makeConnection();
            if (con != null) {

                String sql = "SELECT UserRoles.roleInfo "
                        + "FROM UserRoles, UserInfo "
                        + "Where UserRoles.roleID = UserInfo.role "
                        + "AND email = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();

                if (rs.next()) {
                    String role = "admin";
                    if (rs.getString(1).matches(role)) {
                        return true;
                    }
                }
            } //end if con is null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(String email, String name, String password, int role, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            con = DBUtilites.makeConnection();
            if (con != null) {
                //2. Create Sql String
                String sql = "Insert Into UserInfo(email, name, password, role, status ) "
                        + "Values(?,?,?,?,?) ";
                //3. Create Statement & assign parameter
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, name);
                stm.setString(3, password);
                stm.setInt(4, role);
                stm.setInt(5, status);

                //4. Excute query
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            } //end if con is null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public UserInfoDTO getFullName() {
        return fullName;
    }

}
