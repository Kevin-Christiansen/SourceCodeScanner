package project;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class jdbcconnection {
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
	 JFrame frame = new JFrame();
	 JButton b1 = new JButton();
	 frame.setSize(200,200);     
	 b1.setSize(400,400);
	 b1.setVisible(true);
	 b1.setText("SELECT * from PLAYERS");
	 frame.add(b1);
	 frame.setVisible(true);
	 b1.addActionListener(new BasicAction());
	 
	}
	
	static class BasicAction extends JPanel implements ActionListener {
		
		
		public BasicAction() throws ClassNotFoundException, SQLException {
	        super(new GridLayout(1,0));
	        Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
			Statement st = con.createStatement();
			String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			sql = "SELECT column_name from all_tab_columns where table_name = 'PLAYERS'";
			rs = st.executeQuery(sql);
			ArrayList<String> columnNames = new ArrayList<String>();
			while(rs.next()){
					columnNames.add(rs.getNString("COLUMN_NAME"));
				}
			 Object[] columnN = columnNames.toArray();
			 sql = "select count(*) from PLAYERS";
			 rs = st.executeQuery(sql);
			 rs.next();
			 String numRows = rs.getNString("COUNT(*)");
			 int numR = Integer.parseInt(numRows); 
			 sql = "SELECT * from PLAYERS";
			 rs = st.executeQuery(sql);
			 
			Object[][] dataT = new Object[numR][columnNames.size()];
			
			 for(int i = 0; i<numR; i++ ){
				 rs.next();
				dataT[i][0] = rs.getInt(1);
				dataT[i][1] = rs.getInt(2);	
				dataT[i][2] = rs.getInt(3);
				dataT[i][3] = rs.getString(4);
				dataT[i][4] = rs.getString(5);
				 
			 }
		 
	        JTable table = new JTable(dataT, columnN);
	        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	        table.setFillsViewportHeight(true);
	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane);
		        
				        
			con.close();
		        
		 }
		@Override
		public void actionPerformed(ActionEvent e) {
			
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                try {
							createAndShowGUI();
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
    }
	
	 private static void createAndShowGUI() throws ClassNotFoundException, SQLException {
	        //Create and set up the window.
	        JFrame frame = new JFrame("SimpleTableDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Create and set up the content pane.
	        BasicAction newContentPane = new BasicAction();
	        newContentPane.setOpaque(true); //content panes must be opaque
	        frame.setContentPane(newContentPane);
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

}

