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
	 
	 JFrame frame2 = new JFrame();
	 JButton b2 = new JButton();
	 frame2.setSize(500,200);     
	 b2.setSize(400,400);
	 b2.setVisible(true);
	 b2.setText("Add No Name team to Teams Table via Preapared Statement");
	 frame2.add(b2);
	 frame2.setVisible(true);
	 b2.addActionListener(new BasicAction2());
	 
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
		                	JFrame frame = new JFrame("Select * from Players");
		        	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	 
		        	        //Create and set up the content pane.
		        	        BasicAction newContentPane = new BasicAction();
		        	        newContentPane.setOpaque(true); //content panes must be opaque
		        	        frame.setContentPane(newContentPane);
		        	 
		        	        //Display the window.
		        	        frame.pack();
		        	        frame.setVisible(true);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
    }

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	static class BasicAction2 extends JPanel implements ActionListener {
		BasicAction2() throws ClassNotFoundException, SQLException{
			 super(new GridLayout(1,0));
			 Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
				Statement st = con.createStatement();
				String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
				ResultSet rs = st.executeQuery(sql);
				rs = st.executeQuery(sql);
				
				PreparedStatement addBlankTeam = con.prepareStatement("INSERT INTO teams values (?,?,?,?)");
				
				addBlankTeam.setInt(1, 0);
				addBlankTeam.setString(2, "No Name");
				addBlankTeam.setInt(3, 0);
				addBlankTeam.setInt(4, 0);
				addBlankTeam.executeUpdate();
						
				
				sql = "SELECT column_name from all_tab_columns where table_name = 'TEAMS'";
				rs = st.executeQuery(sql);
				ArrayList<String> columnNames = new ArrayList<String>();
				while(rs.next()){
						columnNames.add(rs.getNString("COLUMN_NAME"));
					}
				 Object[] columnN = columnNames.toArray();
				 sql = "select count(*) from TEAMS";
				 rs = st.executeQuery(sql);
				 rs.next();
				 String numRows = rs.getNString("COUNT(*)");
				 int numR = Integer.parseInt(numRows); 
				 sql = "SELECT * from TEAMS";
				 rs = st.executeQuery(sql);
				 
				Object[][] dataTeams = new Object[numR][columnNames.size()];
				
				 for(int i = 0; i<numR; i++ ){
					 rs.next();
					 dataTeams[i][0] = rs.getInt(1);
					 dataTeams[i][1] = rs.getString(2);
					 dataTeams[i][2] = rs.getInt(3);	
					 dataTeams[i][3] = rs.getInt(4);
					 
				 }
			 
		        JTable table2 = new JTable(dataTeams, columnN);
		        table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
		        table2.setFillsViewportHeight(true);
		        JScrollPane scrollPane = new JScrollPane(table2);
		        add(scrollPane);
			        
				con.close();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
				
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	JFrame frame = new JFrame("Select * from TEAMS");
		        	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	 
		        	        //Create and set up the content pane.
		        	        BasicAction2 newContentPane = new BasicAction2();
		        	        newContentPane.setOpaque(true); //content panes must be opaque
		        	        frame.setContentPane(newContentPane);
		        	 
		        	        //Display the window.
		        	        frame.pack();
		        	        frame.setVisible(true);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
			
			
		}
		
		
	
	
}
	

