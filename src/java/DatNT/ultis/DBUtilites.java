/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.ultis;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nguye
 */
public class DBUtilites implements Serializable {

    public static Integer totalRowPerPage = 20;

    public static Timestamp getTime() {
        Date date = new Date();
        long dateTime = date.getTime();
        Timestamp ts = new Timestamp(dateTime);
        return ts;
    }

    public static Connection makeConnection()
            throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatCtx = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatCtx.lookup("DS007");
        Connection con = ds.getConnection();
        return con;

//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=ListLogin";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "momasa123");
//        return con;
    }
}
