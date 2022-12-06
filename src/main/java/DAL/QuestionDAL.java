/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.QuestionModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class QuestionDAL extends DatabaseConnection {

    public QuestionDAL() {
        super();
        this.connectDB();
    }

    public int addQuest(QuestionModel us) throws SQLException {
        String query = "INSERT INTO quest ( question, answerA, answerB, answerC, answerD, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, us.getQuestion());
        p.setString(2, us.getAnswerA());
        p.setString(3, us.getAnswerB());
        p.setString(4, us.getAnswerC());
        p.setString(5, us.getAnswerD());
        p.setBoolean(6, us.isStatus());
        int result = p.executeUpdate();
        return result;
    }
    
    public int addQuestDB(String Question,String AnswerA,String AnswerB,String AnswerC,String AnswerD) throws SQLException {
        String sql ="UPDATE `question` SET `Question`='"+Question+"',`AnswerA`='"+AnswerA+"',`AnswerB`='"+AnswerB+"',`AnswerC`='"+AnswerC+"',`AnswerD`='"+AnswerD+"' WHERE IdTimer =1";
        PreparedStatement p = this.getConnection().prepareStatement(sql);
        int rs = p.executeUpdate();
        return rs;
    }

    
    public int updateQuest(QuestionModel us) throws SQLException {
        String query = "UPDATE quest SET question = ?, answerA = ?, answerB = ?, answerC = ?, answerD = ?, status = ? WHERE idquest = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, us.getQuestion());
        p.setString(2, us.getAnswerA());
        p.setString(3, us.getAnswerB());
        p.setString(4, us.getAnswerC());
        p.setString(5, us.getAnswerD());
        p.setBoolean(6, us.isStatus());
        int result = p.executeUpdate();
        return result;
    }

    public ArrayList loadQuestion() throws SQLException {
        String query = "SELECT * FROM quest";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        ArrayList<QuestionModel> questionList = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                QuestionModel us = new QuestionModel();
                us.setIdquest(rs.getInt("Question ID"));
                us.setQuestion(rs.getString("Question :"));
                us.setAnswerA(rs.getString("Answer A"));
                us.setAnswerB(rs.getString("Answer B"));
                us.setAnswerC(rs.getString("Answer C"));
                us.setAnswerD(rs.getString("Answer D"));
                questionList.add(us);
            }
        }
        return questionList;
    }
    public Boolean checkDuplicate(int idquest) throws SQLException {
        String query = "SELECT * FROM quest WHERE idquest = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, idquest);
        ResultSet rs = p.executeQuery();
        return rs.next();
    }
    public int setOnlOff(int idquest, boolean status) throws SQLException{
        String query = "UPDATE quest SET status = ? Where idquest = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setBoolean(1, status);
        p.setInt(2, idquest);
        int rs = p.executeUpdate();
        return rs;
    }

    

   
}
