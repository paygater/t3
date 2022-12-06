/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.Question;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserDAL extends DatabaseConnection {

    public UserDAL() {
        super();
        this.connectDB();
    }

    public int addUser(User us) throws SQLException {
        String query = "INSERT INTO user (Username, Password, Nickname, Sex, Birthday) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, us.getUserName());
        p.setString(2, us.getPassword());
        p.setString(3, us.getNickname());
        p.setInt(4, us.getSex());
        p.setDate(5, us.getBirthday());
        int result = p.executeUpdate();
        return result;
    }

    public int updateInfo(User us) throws SQLException {
        String query = "UPDATE user SET Nickname = ?, Sex = ?, Birthday = ? WHERE UserId = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, us.getNickname());
        p.setInt(2, us.getSex());
        p.setDate(3, us.getBirthday());
        p.setInt(4, us.getUserId());
        int result = p.executeUpdate();
        return result;
    }

    public int updatePassword(User us) throws SQLException {
        String query = "UPDATE user SET Password = ? WHERE UserId = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, us.getPassword());
        p.setInt(2, us.getUserId());
        int result = p.executeUpdate();
        return result;
    }

    public ArrayList loadUser() throws SQLException {
        String query = "SELECT * FROM User";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        ArrayList<User> userList = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                User us = new User();
                us.setUserId(rs.getInt("UserId"));
                us.setUserName(rs.getString("Username"));
                us.setPassword(rs.getString("Password"));
                us.setNickname(rs.getString("Nickname"));
                us.setSex(rs.getInt("Sex"));
                us.setBirthday(rs.getDate("Birthday"));
                us.setGrade(rs.getInt("Grade"));
                us.setWinMatch(rs.getInt("WinMatch"));
                us.setLoseMatch(rs.getInt("LoseMatch"));
                us.setDrawMatch(rs.getInt("DrawMatch"));
                us.setCurrentWinStreak(rs.getInt("CurrentWinStreak"));
                us.setMaxWinStreak(rs.getInt("MaxWinStreak"));
                us.setCurrentLoseStreak(rs.getInt("CurrentLoseStreak"));
                us.setMaxLoseStreak(rs.getInt("MaxLoseStreak"));
                us.setIsBlocked(rs.getInt("isBlocked"));
                userList.add(us);
            }
        }
        return userList;
    }

    public User findUser(int id) throws SQLException {
        String query = "SELECT * FROM User WHERE UserId = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        User us = new User();
        if (rs != null) {
            while (rs.next()) {
                us.setUserId(rs.getInt("UserId"));
                us.setUserName(rs.getString("Username"));
                us.setPassword(rs.getString("Password"));
                us.setNickname(rs.getString("Nickname"));
                us.setSex(rs.getInt("Sex"));
                us.setBirthday(rs.getDate("Birthday"));
                us.setGrade(rs.getInt("Grade"));
                us.setWinMatch(rs.getInt("WinMatch"));
                us.setLoseMatch(rs.getInt("LoseMatch"));
                us.setDrawMatch(rs.getInt("DrawMatch"));
                us.setCurrentWinStreak(rs.getInt("CurrentWinStreak"));
                us.setMaxWinStreak(rs.getInt("MaxWinStreak"));
                us.setCurrentLoseStreak(rs.getInt("CurrentLoseStreak"));
                us.setMaxLoseStreak(rs.getInt("MaxLoseStreak"));
                us.setIsBlocked(rs.getInt("isBlocked"));
            }
        }
        return us;
    }

    public ArrayList findUserOnline() throws SQLException {
        String sql = "Select UserId, Nickname, Status From user";
        ArrayList<User> list = new ArrayList();
        PreparedStatement p = this.getConnection().prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                User us = new User();
                us.setUserId(rs.getInt("UserId"));
                us.setNickname(rs.getString("Nickname"));
                us.setStatus(rs.getInt("Status"));
                list.add(us);
            }
        }
        return list;
    }

    public Boolean checkDuplicate(String Username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, Username);
        ResultSet rs = p.executeQuery();
        return rs.next();
    }

    public User verifyUser(User user) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ? AND password = ? AND isBlocked = 1";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, user.getUserName());
        p.setString(2, user.getPassword());
        ResultSet rs = p.executeQuery();
        User us = new User();
        if (rs != null) {
            while (rs.next()) {
                us.setUserId(rs.getInt("UserId"));
                us.setUserName(rs.getString("Username"));
                us.setPassword(rs.getString("Password"));
                us.setNickname(rs.getString("Nickname"));
                us.setSex(rs.getInt("Sex"));
                us.setBirthday(rs.getDate("Birthday"));
                us.setGrade(rs.getInt("Grade"));
                us.setWinMatch(rs.getInt("WinMatch"));
                us.setLoseMatch(rs.getInt("LoseMatch"));
                us.setDrawMatch(rs.getInt("DrawMatch"));
                us.setCurrentWinStreak(rs.getInt("CurrentWinStreak"));
                us.setMaxWinStreak(rs.getInt("MaxWinStreak"));
                us.setCurrentLoseStreak(rs.getInt("CurrentLoseStreak"));
                us.setMaxLoseStreak(rs.getInt("MaxLoseStreak"));
                us.setIsBlocked(rs.getInt("isBlocked"));
            }
        }
        return us;
    }

    public User getGrade(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE UserId = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        User gr = new User();
        if (rs != null) {
            while (rs.next()) {
                gr.setUserId(rs.getInt("UserId"));
                gr.setGrade(rs.getInt("Grade"));
                gr.setWinMatch(rs.getInt("WinMatch"));
                gr.setLoseMatch(rs.getInt("LoseMatch"));
                gr.setDrawMatch(rs.getInt("DrawMatch"));
                gr.setCurrentWinStreak(rs.getInt("CurrentWinStreak"));
                gr.setMaxWinStreak(rs.getInt("MaxWinStreak"));
                gr.setCurrentLoseStreak(rs.getInt("CurrentLoseStreak"));
                gr.setMaxLoseStreak(rs.getInt("MaxLoseStreak"));
            }
        }
        return gr;
    }

    public int getMatch(int id) throws SQLException {
        String query = "SELECT WinMatch, LoseMatch, DrawMatch FROM user where UserID=?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        int all = 0;
        if (rs != null) {
            while (rs.next()) {
                all = rs.getInt("WinMatch") + rs.getInt("LoseMatch") + rs.getInt("DrawMatch");
            }
        }
        return all;
    }

    public float getWinRate(int id) throws SQLException {
        String query = "SELECT WinMatch FROM user where UserID=?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        float rate = 0;
        if (rs != null) {
            while (rs.next()) {
                rate = (float) rs.getInt("WinMatch") / getMatch(id);
            }
        }
        return (rate * 100);
    }

    public int updateWinMatch(int id) throws SQLException {
        User gr = getGrade(id);
        String query = "";
        int rs = 0;
        if (gr.getCurrentWinStreak() <= gr.getMaxWinStreak()) {
            query = "UPDATE user SET `WinMatch` = ?, `CurrentWinStreak` = ? , `CurrentLoseStreak` = 0, `Grade` = ? WHERE `UserId` = ?";
            PreparedStatement p = this.getConnection().prepareStatement(query);
            int value1 = gr.getWinMatch() + 1;
            int value2 = gr.getCurrentWinStreak() + 1;
            int grade = gr.getGrade() + 3;
            p.setInt(1, value1);
            p.setInt(2, value2);
            p.setInt(3, grade);
            p.setInt(4, id);
            rs = p.executeUpdate();
        } else if ((gr.getCurrentWinStreak() > gr.getMaxWinStreak())) {
            query = "UPDATE user SET `WinMatch` = ?, `CurrentWinStreak` = ?, `MaxWinStreak` = ?, `CurrentLoseStreak` = 0, `Grade` = ? WHERE `UserId` = ?";
            PreparedStatement p = this.getConnection().prepareStatement(query);
            int value1 = gr.getWinMatch() + 1;
            int value2 = gr.getCurrentWinStreak() + 1;
            int grade = gr.getGrade() + 3;
            p.setInt(1, value1);
            p.setInt(2, value2);
            p.setInt(3, value2);
            p.setInt(4, grade);
            p.setInt(5, id);
            rs = p.executeUpdate();
        }
        return rs;
    }

    public int updateLoseMatch(int id) throws SQLException {
        User gr = getGrade(id);
        String query = "";
        int rs = 0;
        if (gr.getCurrentLoseStreak() < gr.getMaxLoseStreak()) {
            query = "UPDATE user SET `LoseMatch` = ?, `CurrentLoseStreak` = ? , `CurrentWinStreak` = 0, `Grade` = ? WHERE `UserId` = ?";
            PreparedStatement p = this.getConnection().prepareStatement(query);
            int value1 = gr.getLoseMatch() + 1;
            int value2 = gr.getCurrentLoseStreak() + 1;
            int grade = gr.getGrade() - 2;
            if (grade < 1) {
                grade = 0;
            }
            p.setInt(1, value1);
            p.setInt(2, value2);
            p.setInt(3, grade);
            p.setInt(4, id);
            rs = p.executeUpdate();
        } else if ((gr.getCurrentLoseStreak() >= gr.getMaxLoseStreak())) {
            query = "UPDATE user SET `LoseMatch` = ?, `CurrentLoseStreak` = ?, `MaxLoseStreak` = ?, `CurrentWinStreak` = 0, `Grade` = ? WHERE `UserId` = ?";
            PreparedStatement p = this.getConnection().prepareStatement(query);
            int value1 = gr.getLoseMatch() + 1;
            int value2 = gr.getCurrentLoseStreak() + 1;
            int grade = gr.getGrade() - 2;
            if (grade < 1) {
                grade = 0;
            }
            p.setInt(1, value1);
            p.setInt(2, value2);
            p.setInt(3, value2);
            p.setInt(4, grade);
            p.setInt(5, id);
            rs = p.executeUpdate();
        }
        return rs;
    }

    public int updateDrawMatch(int id) throws SQLException {
        User gr = getGrade(id);
        String query = "UPDATE user SET `DrawMatch` = ?, `CurrentWinStreak` = 0, `CurrentLoseStreak` = 0, `Grade` = ? WHERE `UserId` = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        int value = gr.getDrawMatch() + 1;
        int grade = gr.getGrade() + 1;
        p.setInt(1, value);
        p.setInt(2, grade);
        p.setInt(3, id);
        int rs = p.executeUpdate();
        return rs;
    }

    public int updateWin(int id) throws SQLException {
        User gr = getGrade(id);
        String query = "UPDATE user SET `WinMatch` = ?, `CurrentWinStreak` = ?, `CurrentLoseStreak` = 0 WHERE `UserId` = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        int value = gr.getWinMatch() + 1;
        int value1 = gr.getCurrentWinStreak() + 1;
        p.setInt(1, value);
        p.setInt(2, value1);
        p.setInt(3, id);
        int rs = p.executeUpdate();
        return rs;
    }

    public int updateLose(int id) throws SQLException {
        User gr = getGrade(id);
        String query = "UPDATE user SET `LoseMatch` = ?, `CurrentLoseStreak` = ?, `CurrentWinStreak` = 0 WHERE `UserId` = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        int value = gr.getLoseMatch() + 1;
        int value1 = gr.getCurrentLoseStreak() + 1;
        p.setInt(1, value);
        p.setInt(2, value1);
        p.setInt(3, id);
        int rs = p.executeUpdate();
        return rs;
    }

    public ArrayList getRank() throws SQLException {
        String query = "SELECT * from user WHERE Grade > 15 ORDER BY Grade DESC";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        ArrayList<User> gradeList = new ArrayList<>();
        if (rs != null) {
            while (rs.next()) {
                User grade = new User();
                grade.setUserId(rs.getInt("UserID"));
                grade.setGrade(rs.getInt("Grade"));
                grade.setWinMatch(rs.getInt("WinMatch"));
                grade.setLoseMatch(rs.getInt("LoseMatch"));
                grade.setDrawMatch(rs.getInt("DrawMatch"));
                grade.setCurrentWinStreak(rs.getInt("CurrentWinStreak"));
                grade.setMaxWinStreak(rs.getInt("MaxWinStreak"));
                grade.setCurrentLoseStreak(rs.getInt("CurrentLoseStreak"));
                grade.setMaxLoseStreak(rs.getInt("MaxLoseStreak"));
                gradeList.add(grade);
            }
        }
        return gradeList;
    }

    public int setStatus(int id, int status) throws SQLException {
        String query = "UPDATE user SET Status = ? Where UserID = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, status);
        p.setInt(2, id);
        int rs = p.executeUpdate();
        return rs;
    }

    public User getInfo(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE userID = ? AND isBlocked = 1";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        User us = new User();
        if (rs != null) {
            while (rs.next()) {
                us.setUserId(rs.getInt("UserId"));
                us.setUserName(rs.getString("Username"));
                us.setPassword(rs.getString("Password"));
                us.setNickname(rs.getString("Nickname"));
                us.setSex(rs.getInt("Sex"));
                us.setBirthday(rs.getDate("Birthday"));
                us.setGrade(rs.getInt("Grade"));
                us.setWinMatch(rs.getInt("WinMatch"));
                us.setLoseMatch(rs.getInt("LoseMatch"));
                us.setDrawMatch(rs.getInt("DrawMatch"));
                us.setCurrentWinStreak(rs.getInt("CurrentWinStreak"));
                us.setMaxWinStreak(rs.getInt("MaxWinStreak"));
                us.setCurrentLoseStreak(rs.getInt("CurrentLoseStreak"));
                us.setMaxLoseStreak(rs.getInt("MaxLoseStreak"));
                us.setIsBlocked(rs.getInt("isBlocked"));
            }
        }
        return us;
    }

    public int blockUser(int id) throws SQLException {
        String query = "UPDATE user SET `isBlocked` = 0 WHERE `UserId` = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rs = p.executeUpdate();
        return rs;
    }

    public int unblockUser(int id) throws SQLException {
        String query = "UPDATE user SET `isBlocked` = 1 WHERE `UserId` = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rs = p.executeUpdate();
        return rs;
    }

    public ArrayList listUserOnline() throws SQLException {
        String sql = "Select UserId, Nickname, Status, isBlocked From user where Status = 1 or Status = 2";
        ArrayList<User> list = new ArrayList();
        PreparedStatement p = this.getConnection().prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                User us = new User();
                us.setUserId(rs.getInt("UserId"));
                us.setNickname(rs.getString("Nickname"));
                us.setStatus(rs.getInt("Status"));
                us.setIsBlocked(rs.getInt("isBlocked"));
                list.add(us);
            }
        }
        return list;
    }
    
        public ArrayList<Question> getListQuestion() throws SQLException {
//        String query = "SELECT * FROM question";
//        PreparedStatement p = this.getConnection().prepareStatement(query);
//        ResultSet rs = p.executeQuery();
//        ArrayList<Question> questionList = new ArrayList<>();
//        int count = 0;
//        if (rs != null) {
//            while (rs.next() && count < 5) {
//                Question qu = new Question();
//                qu.setQuestion(rs.getString("Question"));
//                qu.setAnswerA(rs.getString("AnswerA"));
//                qu.setAnswerB(rs.getString("AnswerB"));
//                qu.setAnswerC(rs.getString("AnswerC"));
//                qu.setAnswerD(rs.getString("AnswerD"));
//                qu.setAnswerOfQuestion(rs.getString("AnswerOfQuestion"));
//                questionList.add(qu);
//                count++;
//            }
//        }
        String query = "SELECT * FROM question ORDER BY RAND()";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        //PreparedStatement p = getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        ArrayList<Question> questionList = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                Question qu = new Question();
                qu.setQuestion(rs.getString("Question"));
                qu.setAnswerA(rs.getString("AnswerA"));
                qu.setAnswerB(rs.getString("AnswerB"));
                qu.setAnswerC(rs.getString("AnswerC"));
                qu.setAnswerD(rs.getString("AnswerD"));
                qu.setAnswerOfQuestion(rs.getString("AnswerOfQuestion"));
                questionList.add(qu);
            }
        }
        return questionList;
    }
    
    public Question getQuestion() throws SQLException {
        String query = "SELECT * FROM question";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        Question qu = new Question();
        if (rs != null) {
            if (rs.next()) {
               
                qu.setQuestion(rs.getString("Question"));
                qu.setAnswerA(rs.getString("AnswerA"));
                qu.setAnswerB(rs.getString("AnswerB"));
                qu.setAnswerC(rs.getString("AnswerC"));
                qu.setAnswerD(rs.getString("AnswerD"));
                qu.setAnswerOfQuestion(rs.getString("AnswerOfQuestion"));
                
            }
        }
        return qu;
    }
    

    public ArrayList topUser() throws SQLException {
        String sql = "SELECT UserId, Nickname, WinMatch FROM user WHERE WinMatch = (SELECT MAX(WinMatch) from user);";
        ArrayList<User> list = new ArrayList();
        PreparedStatement p = this.getConnection().prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                User us = new User();
                us.setUserId(rs.getInt("UserId"));
                us.setNickname(rs.getString("Nickname"));
                us.setStatus(rs.getInt("WinMatch"));
                list.add(us);
            }
        }
        return list;
    }
}
