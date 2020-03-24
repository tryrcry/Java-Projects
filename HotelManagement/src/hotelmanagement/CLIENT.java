/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Siva
 */
public class CLIENT {
    CONNECTION conn=new CONNECTION();
    public boolean addClient(String fname,String lname,String no,String mail)
    {
        int flag=0;
        PreparedStatement ps;
        ResultSet rs;
        String addQuery="INSERT INTO `client`(`fname`, `lname`, `no`, `mail`) VALUES (?,?,?,?)";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(addQuery);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,no);
            ps.setString(4,mail);
            if(ps.executeUpdate()>0)
            {
                flag=1;
            }
        }
        catch(SQLException e)
        {
            
        }
        return flag==1;
    }
    public void fillClientTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `client`";
        try
        {
        ps=(PreparedStatement) conn.createConnection().prepareStatement(selectQuery);
        rs=ps.executeQuery();
        DefaultTableModel dtm=(DefaultTableModel)table.getModel();
        Object[] row;
        while(rs.next())
        {
            row=new Object[5];
            row[0]=rs.getInt(1);
            row[1]=rs.getString(2);
            row[2]=rs.getString(3);
            row[3]=rs.getString(4);
            row[4]=rs.getString(5);
            dtm.addRow(row);
        }
        }
        catch(SQLException e)
        {
            
        }
    }
        public boolean editClient(int id,String fname,String lname,String no,String mail)
        {
            PreparedStatement ps;
            String updateQuery="UPDATE `client` SET `fname`=?,`lname`=?,`no`=?,`mail`=? WHERE `id`=?";
            try
            {
                ps=(PreparedStatement) conn.createConnection().prepareStatement(updateQuery);
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,no);
                ps.setString(4,mail);
                ps.setInt(5,id);
                return(ps.executeUpdate()>0);
            }
            catch(SQLException e)
            {
                return false;
            }
        }
        public boolean removeClient(int id)
        {
            PreparedStatement ps;
            String deleteQuery="DELETE FROM `client` WHERE `id`=?";
            try
            {
                ps=(PreparedStatement) conn.createConnection().prepareStatement(deleteQuery);
                ps.setInt(1,id);
                return (ps.executeUpdate()>0);
            }
            catch(SQLException e)
            {
                return false;
            }
        }
}
