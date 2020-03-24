/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
/**
 *
 * @author Siva
 */
public class CONNECTION {
    public Connection createConnection()
    {
        Connection conn=null;
        MysqlDataSource mds=new MysqlDataSource();
        mds.setServerName("localhost");
        mds.setPortNumber(3306);
        mds.setUser("root");
        mds.setPassword("");
        mds.setDatabaseName("hotel management");
        try
        {
            conn=(Connection) mds.getConnection();
        }catch(Exception e)
        {
            
        }
        return conn;
    }
}
