package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BLL.blockedBLL;
import DAL.user;

public class blockform extends JFrame {

	private JPanel contentPane;
	JScrollPane jScrollPane1;
	JTable jTable = new JTable();
	JButton jbtnFind;
	JTextField jtxtFind;
	JPanel panel_1;
        int waitID = 0;
	static blockedBLL blockBll = new blockedBLL();
        
        
	public blockform() throws SQLException {
		setTitle("Block Form");
		init();
		try {
			listUser();
		} catch (SQLException ex) {
			Logger.getLogger(blockform.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) throws SQLException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(blockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(blockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(blockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(blockform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		blockform f = new blockform();
		f.setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public void init() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 681);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 255));
		panel.setBounds(0, 0, 911, 118);
		contentPane.add(panel);
		panel.setLayout(null);


		JButton btnNewButton_1_1 = new JButton("Block User");
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(102, 153, 255));
		btnNewButton_1_1.addActionListener((ActionEvent e) -> {
                      btnBlock_Click(e);
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(150, 530, 120, 50);
		contentPane.add(btnNewButton_1_1);
                
                JButton btnNewButton_1 = new JButton("Unlock User");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(102, 153, 255));
		btnNewButton_1.addActionListener((ActionEvent e) -> {
			btnUnBlock_Click(e);
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(320, 530, 120, 50);
		contentPane.add(btnNewButton_1);
                
                JButton btnRefresh = new JButton("Refresh List");
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(102, 153, 255));
		btnRefresh.addActionListener((ActionEvent e) -> {
                    try {
                        btnRefresh_Click(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(blockform.class.getName()).log(Level.SEVERE, null, ex);
                    }
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.setBounds(500, 530, 120, 50);
		contentPane.add(btnRefresh);

		JButton btnNewButton_1_1_1 = new JButton("Back");
		btnNewButton_1_1_1.setForeground(new Color(128, 0, 0));
		btnNewButton_1_1_1.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIserver home = new GUIserver();
				home.show(true);
				dispose();
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1_1.setBounds(755, 571, 126, 50);
		contentPane.add(btnNewButton_1_1_1);

		JLabel lblNewLabel_1 = new JLabel("USER LIST");
		lblNewLabel_1.setBounds(370, 20, 300, 80);
                lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
                lblNewLabel_1.setForeground(Color.WHITE);
		panel.add(lblNewLabel_1);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 145, 846, 347);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(jTable);
			
		panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(29, 512, 846, 43);
		contentPane.add(panel_1);
		
		
	}

	private void listUser() throws SQLException {
		List list = blockBll.LoadList();
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

	public void ClickMouseClicked(MouseEvent evt) throws SQLException {
		int row = jTable.getSelectedRow();
                waitID = (int) jTable.getValueAt(row, 0);

	}
        public void btnUnBlock_Click (ActionEvent e ) {
            unblockform unblock = new unblockform();
            unblock.setVisible(true);
        }
        public void btnRefresh_Click(ActionEvent e) throws SQLException {
            listUser();
            panel_1.setVisible(true);
	}
        private void btnBlock_Click (ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(this, "Sure? You want to block this user?", "Bock User", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                if (waitID==0) {
                    JOptionPane.showMessageDialog(this, "You did not choose any user to block!", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                    if (blockBll.BlockUser(waitID) > 0) {
			JOptionPane.showMessageDialog(this, "Block user success!", "Message", JOptionPane.INFORMATION_MESSAGE);
			listUser();
                        waitID=0;
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

