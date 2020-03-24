/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import com.mysql.jdbc.PreparedStatement;
import javax.swing.JTable;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Siva
 */
public class RESERVE {
    CONNECTION conn=new CONNECTION();
    ROOMS rooms=new ROOMS();
    public void fillTableReserve(JTable tableReserve)
    {
        PreparedStatement ps;
        ResultSet rs;
        String query="SELECT * FROM `reservation`";
        try
        {
            ps=(PreparedStatement)conn.createConnection().prepareStatement(query);
            rs=ps.executeQuery();
            DefaultTableModel dtm=(DefaultTableModel)tableReserve.getModel();
            while(rs.next())
            {
                Object row[]=new Object[5];
                row[0]=rs.getInt(1);
                row[1]=rs.getInt(2);
                row[2]=rs.getInt(3);
                row[3]=rs.getDate(4);
                row[4]=rs.getDate(5);
                dtm.addRow(row);
            }
        }
        catch(Exception e)
        {
            
        }
    }
    public boolean addReserve(int client,int room,String in,String out)
    {
        PreparedStatement ps;
        ResultSet rs;
        String query="INSERT INTO `reservation`(`client_id`,`room_no`,`date_in`,`date_out`) VALUES (?,?,?,?)";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(query);
            ps.setInt(1,client);
            ps.setInt(2,room);
            ps.setString(3,in);
            ps.setString(4,out);
            if(rooms.isReserved(room).equals("No"))
            {
            if(ps.executeUpdate()>0)
            {
                rooms.setRoomToReserved(room, "Yes");
                return true;
            }
            else
                return false;  
            }
            else
            {
                JOptionPane.showMessageDialog(null,"This room is already reserved","Room Reserved",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean editReserve(int id,int client,int room,String in,String out)
    {
        PreparedStatement ps;
        ResultSet rs;
        String query="UPDATE `reservation` SET `client_id`=?,`room_no`=?,`date_in`=?,`date_out`=? WHERE `id`=?";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(query);
            ps.setInt(1,client);
            ps.setInt(2, room);
            ps.setString(3,in);
            ps.setString(4,out);
            ps.setInt(5,id);
            return (ps.executeUpdate()>0);
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean removeReserve(int id,int room)
    {
        PreparedStatement ps;
        ResultSet rs;
        String query="DELETE FROM `reservation` WHERE `id`=?";
        try
        {
            ps=(PreparedStatement) conn.createConnection().prepareStatement(query);
            ps.setInt(1,id);
            if(ps.executeUpdate()>0)
            {
                rooms.setRoomToUnreserved(room,"No");
                return true;
            }
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
