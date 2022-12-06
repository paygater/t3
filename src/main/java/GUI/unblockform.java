/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.blockedBLL;
import DAL.user;
import static GUI.blockform.blockBll;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class unblockform extends JFrame {
    	private JPanel contentPane;
	JScrollPane scrollPane;
	private JTextField textField;
        int waitID = 0;
	JTable jTable;
	blockedBLL block = new blockedBLL();
	public unblockform() {
		setTitle("Unblock Form");
		// TODO Auto-generated constructor stub
		init();
		try {
//			listKQ();
//			listCourseID();
//			listStudentID();
                        listBlock();
		} catch (SQLException ex) {
			Logger.getLogger(blockform.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static void main (String [] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(unblockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(unblockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(unblockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(unblockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		unblockform f = new unblockform();
		f.setVisible(true);
	}
	
	public void init () {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
                JLabel lblNewLabel_1 = new JLabel("BANNED USER LIST");
		lblNewLabel_1.setBounds(100, 5, 300, 80);
                lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
                lblNewLabel_1.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("UNBLOCK");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setForeground(new Color(0, 100, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton.setBounds(41, 348, 133, 50);
		btnNewButton.addActionListener((ActionEvent e) -> {
                        btnUnBlock_Click(e);
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setForeground(new Color(0, 100, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton_1.setBounds(319, 348, 133, 50);
		contentPane.add(btnNewButton_1);

		jTable = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 450 , 100);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(jTable);
	}
        public void ClickMouseClicked(MouseEvent evt) throws SQLException {
		int row = jTable.getSelectedRow();
                waitID = (int) jTable.getValueAt(row, 0);

	}
        private void btnUnBlock_Click (ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(this, "Sure? You want to unblock this user?", "Unblock User", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                if (waitID==0) {
                    JOptionPane.showMessageDialog(this, "You did not choose any user to unblock!", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                    if (blockBll.UnBlockUser(waitID) > 0) {
			JOptionPane.showMessageDialog(this, "Block user success!", "Message", JOptionPane.INFORMATION_MESSAGE);
			listBlock();
                        waitID =0;
                    } else {
			JOptionPane.showMessageDialog(this, "Error", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                    } catch (SQLException ex) {
                    Logger.getLogger(blockform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
		} else if (result == JOptionPane.NO_OPTION) {	
		}
        }
        private void listBlock() throws SQLException {
		List list = block.LoadListBlock();
		DefaultTableModel model = convertTable(list);
		jTable.setModel(model);
                jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				try {
					ClickMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}	
        private DefaultTableModel convertTable(List list) {
		String[] columnNames = {"UserID","UserName", "Name", "Status"};
		Object[][] data = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			user u = (user) list.get(i);
			data[i][0] = u.getUserid();
                        data[i][1] = u.getUsername();
			data[i][2] = u.getName();
                        data[i][3] = u.getStatus();
		}
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		return model;
	}
}
