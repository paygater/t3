/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class blockDAL extends  MyDatabaseManager{
    public blockDAL () {
        this.connectDB();
    }
    public ArrayList readUserList () throws SQLException {
	String query = "SELECT * FROM `user` WHERE userid NOT IN ( SELECT `userid` FROM `banned_user`)";
        ResultSet rs = blockDAL.doReadQuery(query);
	ArrayList list;
        list = new ArrayList();
	if (rs != null) {
            int i = 1;
            while (rs.next()) {
                user u = new user();
                    u.setUserid(rs.getInt("userid"));
                    u.setUsername(rs.getString("useremail"));
                    u.setName(rs.getString("username"));
                    if (rs.getInt("status")==0) {
                        u.setStatus("offline");
                    } else if (rs.getInt("status")==1) {
                        u.setStatus("online");
                    }
                    
                    list.add(u);
		}
	}
        return list;
    }
        public ArrayList readBlockList () throws SQLException {
	String query = "SELECT * FROM `user` WHERE userid IN ( SELECT `userid` FROM `banned_user`)";
        ResultSet rs = blockDAL.doReadQuery(query);
	ArrayList list;
        list = new ArrayList();
	if (rs != null) {
            int i = 1;
            while (rs.next()) {
                user u = new user();
                    u.setUserid(rs.getInt("userid"));
                    u.setUsername(rs.getString("useremail"));
                    u.setName(rs.getString("username"));
                    if (rs.getInt("status")==0) {
                        u.setStatus("offline");
                    } else if (rs.getInt("status")==1) {
                        u.setStatus("online");
                    }
                    
                    list.add(u);
		}
	}
        return list;
    }
    public int BlockUser (int userid) throws SQLException{
        String query = "INSERT INTO `banned_user`(`userid`) VALUES (?)";
        PreparedStatement p = blockDAL.getConnection().prepareStatement(query);
        p.setInt(1, userid);
        int result = p.executeUpdate();
        return result;
    }
     public int UnBlockUser (int userid) throws SQLException {
        String query = "DELETE FROM `banned_user` WHERE `userid`=?";
        PreparedStatement p = blockDAL.getConnection().prepareStatement(query);
        p.setInt(1, userid);
        int result = p.executeUpdate();
        return result;
     }
}
