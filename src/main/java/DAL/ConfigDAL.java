/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.ConfigModel;
import GUI.config;
import javax.swing.JTextField;


/**
 *
 * @author Admin
 */
public class ConfigDAL extends DatabaseConnection {

    public ConfigDAL() {
        super();
        this.connectDB();
    }

    public int addNewConfig(ConfigModel us) throws SQLException {
        String query = "INSERT INTO config ( idTimer, Timer, breaktime, NumberQuestion) VALUES (?, ?, ?, ?)";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, us.getIdTimer());
        p.setInt(2, us.getTimer());
        p.setInt(3, us.getBreaktime());
        p.setInt(4, us.getNumberQuestion());
        int result = p.executeUpdate();
        return result;
    }
  public int updateTimer(String Timer, String breaktime, String NumberQuestion) throws SQLException {
       String sql ="UPDATE `timer` SET `Timer`='"+Timer+"',`breaktime`='"+breaktime+"',`NumberQuestion`='"+NumberQuestion+"' WHERE IdTimer =1";
       PreparedStatement p = this.getConnection().prepareStatement(sql);
        int rs = p.executeUpdate();
        return rs;
    }
    
    public int updateNewConfig(ConfigModel us) throws SQLException {
        String query = "UPDATE config SET  Timer = ?, breaktime = ?, NumberQuestion = ? WHERE idTimer = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, us.getTimer());
        p.setInt(2, us.getBreaktime());
        p.setInt(3, us.getNumberQuestion());
        int result = p.executeUpdate();
        return result;
    }

    public ArrayList loadNewConfig() throws SQLException {
        String query = "SELECT * FROM config";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        ArrayList<ConfigModel> questionConfigList = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                ConfigModel us = new ConfigModel();
                us.setIdTimer(rs.getInt("Question config ID"));
                us.setTimer(rs.getInt("Question :"));
                us.setBreaktime(rs.getInt("Answer A"));
                us.setNumberQuestion(rs.getInt("Answer B"));
                questionConfigList.add(us);
            }
        }
        return questionConfigList;
    }
    public Boolean checkDuplicate(int idTimer) throws SQLException {
        String query = "SELECT * FROM config WHERE idTimer = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
         p.setInt(1, idTimer);
        ResultSet rs = p.executeQuery();
        return rs.next();
    }
    
}
