/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.blockDAL;
import DAL.user;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class blockedBLL {
    blockDAL block;
    
    public blockedBLL () {
        block = new blockDAL();
    }
    //hien thi
    public List LoadList() throws SQLException {
        ArrayList list = block.readUserList();
        return list;
    }
    //hien thi danh sach block
    public List LoadListBlock () throws SQLException {
        ArrayList list = block.readBlockList();
        return list;
    }
    //block user
    public int BlockUser (int userid) throws SQLException {
    	int result = block.BlockUser(userid);
    	return result;
    }
    //unblock 
    public int UnBlockUser (int userid) throws SQLException {
    	int result = block.UnBlockUser(userid);
    	return result;
    }
}
