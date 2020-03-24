/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Siva
 */
public class ROOMS {
    CONNECTION conn=new CONNECTION();
    public void fillRoomTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `room`";
        try
        {
        ps=(PreparedStatement) conn.createConnection().prepareStatement(selectQuery);
        rs=ps.executeQuery();
        DefaultTableModel dtm=(DefaultTableModel)table.getModel();
        Object[] row;
        while(rs.next())
        {
            row=new Object[4];
            row[0]=rs.getInt(1);
            row[1]=rs.getString(2);
            row[2]=rs.getString(3);
            row[3]=rs.getString(4);
            dtm.addRow(row);
        }
        }
        catch(SQLException e)
        {
            
        }
    }
    public void fillRoomCombo(JComboBox combo)
    {
       PreparedStatement ps;
       ResultSet rs;
       String selectQuery="SELECT * FROM `type`";
       try
       {
           ps=(PreparedStatement) conn.createConnection().prepareStatement(selectQuery);
           rs=ps.executeQuery();
           while(rs.next())
               combo.addItem(rs.getInt(1));
       }
       catch(Exception e)
       {
           
       }
    }
    public boolean addRoom(int number,int type,String phn,String reserved)
    {
        PreparedStatement ps;
        ResultSet rs;
        String addQuery="INSERT INTO `room`(`number`, `type`, `phn`,`reserved`) VALUES (?,?,?,?)";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(addQuery);
            ps.setInt(1,number);
            ps.setInt(2,type);
            ps.setString(3,phn);
            ps.setString(4,reserved);
            return (ps.executeUpdate()>0);
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    public boolean editRoom(int number,int type,String phn,String reserved)
    {
        PreparedStatement ps;
        ResultSet rs;
        String addQuery="UPDATE `room` SET `type`=?,`phn`=?,`reserved`=? WHERE `number`=?";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(addQuery);
            ps.setInt(1,type);
            ps.setString(2,phn);
            ps.setString(3,reserved);
            ps.setInt(4,number);
            return (ps.executeUpdate()>0);
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    public boolean removeRoom(int number)
    {
        PreparedStatement ps;
            String deleteQuery="DELETE FROM `room` WHERE `number`=?";
            try
            {
                ps=(PreparedStatement) conn.createConnection().prepareStatement(deleteQuery);
                ps.setInt(1,number);
                return (ps.executeUpdate()>0);
            }
            catch(SQLException e)
            {
                return false;
            }
    }
    public boolean setRoomToReserved(int number,String isReserved)
    {
        PreparedStatement ps;
        ResultSet rs;
        String addQuery="UPDATE `room` SET `reserved`=? WHERE `number`=?";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(addQuery);
            ps.setString(1,isReserved);
            ps.setInt(2,number);
            return (ps.executeUpdate()>0);
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    public boolean setRoomToUnreserved(int number,String isReserved)
    {
        PreparedStatement ps;
        ResultSet rs;
        String addQuery="UPDATE `room` SET `reserved`=? WHERE `number`=?";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(addQuery);
            ps.setString(1,isReserved);
            ps.setInt(2,number);
            return (ps.executeUpdate()>0);
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    public String isReserved(int room)
    {
        PreparedStatement ps;
        ResultSet rs;
        String addQuery="SELECT `reserved` FROM `room` WHERE `number`=?";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(addQuery);
            ps.setInt(1,room);
            rs=ps.executeQuery();
            if(rs.next())
            {
                return rs.getString(1);
            }
            else
                return "";
        }
        catch(SQLException e)
        {
            return "";
        }
    }
}
