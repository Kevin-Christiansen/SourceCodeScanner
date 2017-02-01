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
public class TestApplication {
	static JTextField maxNum = new JTextField();

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
	 JFrame SELECT_PLAYERS_FRAME = new JFrame();
	 SELECT_PLAYERS_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton SELECT_PLAYERS = new JButton();
	 SELECT_PLAYERS_FRAME.setSize(200,200);     
	 SELECT_PLAYERS.setSize(400,400);
	 SELECT_PLAYERS.setVisible(true);
	 SELECT_PLAYERS.setText("SELECT * from PLAYERS");
	 SELECT_PLAYERS_FRAME.add(SELECT_PLAYERS);
	 SELECT_PLAYERS_FRAME.setLocation(700, 100);
	 SELECT_PLAYERS_FRAME.setVisible(true);
	 SELECT_PLAYERS.addActionListener(new SelectPlayers());
	 
	 
	 JFrame frame_prepared_statement = new JFrame();
	 frame_prepared_statement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton prepared_statement = new JButton();
	 frame_prepared_statement.setSize(320,200);     
	 prepared_statement.setSize(400,400);
	 frame_prepared_statement.setLocation(50, 300);
	 prepared_statement.setVisible(true);
	 prepared_statement.setText("Add No Name team to Teams Table via Preapared Statement");
	 frame_prepared_statement.add(prepared_statement);
	 frame_prepared_statement.setVisible(true);
	 prepared_statement.addActionListener(new PreparedState());
	 
	 JFrame frame_delete = new JFrame();
	 frame_delete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton delete = new JButton();
	 frame_delete.setSize(220,200);     
	 delete.setSize(400,400);
	 frame_delete.setLocation(380, 300);
	 delete.setVisible(true);
	 delete.setText("Delete No Name Team");
	 frame_delete.add(delete);
	 frame_delete.setVisible(true);
	 delete.addActionListener(new DeleteTeam());
	 
	 JFrame frame_select_teams = new JFrame();
	 frame_select_teams.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton select_teams = new JButton();
	 frame_select_teams.setSize(200,200);     
	 select_teams.setSize(400,400);
	 frame_select_teams.setLocation(910, 100);
	 select_teams.setVisible(true);
	 select_teams.setText("Select * from Teams");
	 frame_select_teams.add(select_teams);
	 frame_select_teams.setVisible(true);
	 select_teams.addActionListener(new SelectTeams());
	 
	 JFrame frame_update_no_name = new JFrame();
	 frame_update_no_name.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton update_no_name = new JButton();
	 frame_update_no_name.setSize(200,200);     
	 update_no_name.setSize(400,400);
	 frame_update_no_name.setLocation(600, 300);
	 update_no_name.setVisible(true);
	 update_no_name.setText("Update No Name Teams");
	 frame_update_no_name.add(update_no_name);
	 frame_update_no_name.setVisible(true);
	 update_no_name.addActionListener(new UpdateNoNameTeam());
	 
	 
	 JFrame frame_insert_player = new JFrame();
	 frame_insert_player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton insert_player = new JButton();
	 frame_insert_player.setSize(200,200);     
	 insert_player.setSize(400,400);
	 frame_insert_player.setLocation(820, 300);
	 insert_player.setVisible(true);
	 insert_player.setText("Query Insert Player");
	 frame_insert_player.add(insert_player);
	 frame_insert_player.setVisible(true);
	 insert_player.addActionListener(new InsertPlayer());
	 
	 JFrame frame_stored_insert = new JFrame();
	 frame_stored_insert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton stored_insert = new JButton();
	 frame_stored_insert.setSize(250,200);     
	 stored_insert.setSize(400,400);
	 frame_stored_insert.setLocation(1030, 300);
	 stored_insert.setVisible(true);
	 stored_insert.setText("Stored Procedure Insert Player");
	 frame_stored_insert.add(stored_insert);
	 frame_stored_insert.setVisible(true);
	 stored_insert.addActionListener(new StoredProcedure());
	 
	 JFrame frame_Max = new JFrame();
	 frame_Max.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton display_max = new JButton(); 
	 maxNum.setSize(50,50);
	 maxNum.setLocation(1030, 660 );
	 frame_Max.setSize(200,220);     
	 display_max.setSize(200,100);
	 frame_Max.setLocation(1030, 500);
	 display_max.setVisible(true);
	 display_max.setText("Player Max Win");
	 
	 frame_Max.add(display_max);
	 frame_Max.add(maxNum);
	 frame_Max.setVisible(true);
	 display_max.addActionListener(new MaxFunction());
	 
	 JFrame frame_replace = new JFrame();
	 frame_replace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JButton replace = new JButton();
	 frame_replace.setSize(250,200);     
	 replace.setSize(400,400);
	 frame_replace.setLocation(600, 550);
	 replace.setVisible(true);
	 replace.setText("Replace Stored Players");
	 frame_replace.add(replace);
	 frame_replace.setVisible(true);
	 replace.addActionListener(new ReplaceFunction());
	 
	}
	
	static class SelectPlayers extends JPanel implements ActionListener {
		
		public SelectPlayers() throws ClassNotFoundException, SQLException {
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
	        table.setPreferredScrollableViewportSize(new Dimension(columnN.length*120,numR*17 ));
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
		        	       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	 
		        	        //Create and set up the content pane.
		        	        SelectPlayers newContentPane = new SelectPlayers();
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
	
	static class PreparedState extends JPanel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                
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
		    			        
		    				con.close();

		        	
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
			
		}
	
	static class DeleteTeam extends JPanel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                
		                	Class.forName("oracle.jdbc.driver.OracleDriver");
		     				
		     				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
		     				Statement st = con.createStatement();
		     				String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
		     				ResultSet rs = st.executeQuery(sql);
		     				rs = st.executeQuery(sql);
		     				sql = "DELETE FROM TEAMS WHERE TEAM_NAME = 'No Name'";
		     				rs = st.executeQuery(sql);		
		        	        
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
			
		
	}
	
	static class SelectTeams extends JPanel implements ActionListener{
		
		public SelectTeams() throws ClassNotFoundException, SQLException {
	        super(new GridLayout(1,0));
	        Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
			Statement st = con.createStatement();
			String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
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
			 
			Object[][] dataT = new Object[numR][columnNames.size()];
			
			 for(int i = 0; i<numR; i++ ){
				 rs.next();
				dataT[i][0] = rs.getInt(1);
				dataT[i][1] = rs.getString(2);	
				dataT[i][2] = rs.getInt(3);
				dataT[i][3] = rs.getInt(4);
				 
			 }
		 
	        JTable table = new JTable(dataT, columnN);
	       // table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	        table.setPreferredScrollableViewportSize(new Dimension(columnN.length*120,numR*17 ));
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
		                	JFrame frame = new JFrame("Select * from Teams");
		     //   	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	 
		        	        //Create and set up the content pane.
		        	        SelectTeams newContentPane = new SelectTeams();
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

	static class UpdateNoNameTeam extends JPanel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	Class.forName("oracle.jdbc.driver.OracleDriver");
		     				
		     				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
		     				Statement st = con.createStatement();
		     				String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
		     				ResultSet rs = st.executeQuery(sql);
		     				rs = st.executeQuery(sql);
		     				sql = "UPDATE TEAMS SET TEAM_NAME = 'Updated Team' WHERE TEAM_NAME = 'No Name'";
		     				rs = st.executeQuery(sql);	
		        	        
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
	}
	
	static class InsertPlayer extends JPanel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
						
						
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
							Statement st = con.createStatement();
							String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
							ResultSet rs = st.executeQuery(sql);
							rs = st.executeQuery(sql);
							
							sql = "INSERT INTO PLAYERS (TEAM_ID, WIN, LOSS, FAVORITE_CHAMPION, PLAYER_NAME) VALUES ('1', '137', '0', 'Renekton', 'Inserted Player')";
							rs = st.executeQuery(sql);
						
						        
							con.close();
		            	}catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		            }
		        });
			
		}
			
	}

	static class StoredProcedure extends JPanel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	 try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
						
		 				
			 				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
			 				Statement st = con.createStatement();
			 				String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
			 				ResultSet rs = st.executeQuery(sql);
			 				rs = st.executeQuery(sql);
			 				
			 				sql = "CALL ADDPLAYER('0','0','0','STORED CHAMPION','STORED PROCEDURE')";
			 				rs = st.executeQuery(sql);
			 				sql = "commit";
			 				rs = st.executeQuery(sql);
			 			
			 			        
			 				con.close();
		            	 }catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		            	
		            	
		            	
		            }
		        });
			
		}
			
	}
	
	static class MaxFunction extends JPanel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	 try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
		 				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
		 				Statement st = con.createStatement();
		 				String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
		 				ResultSet rs = st.executeQuery(sql);
		 				rs = st.executeQuery(sql);
		 				
		 				sql = "SELECT MAX(WIN) FROM PLAYERS";
		 				rs = st.executeQuery(sql);
		 				
		 				if(rs.next()){
		 					maxNum.setText(""+rs.getInt(1));
		 					
		 					
		 				}
		 			
		 			        
		 				con.close();
	            	 }catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	
	            	
	            	
	            }
	        });
		}
		
	}

	static class ReplaceFunction extends JPanel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			  javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                
		                	Class.forName("oracle.jdbc.driver.OracleDriver");
		     				
		     				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
		     				Statement st = con.createStatement();
		     				String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
		     				ResultSet rs = st.executeQuery(sql);
		     				//sql = "UPDATE PLAYERS SET PLAYER_NAME = REPLACE (PLAYERS.PLAYER_NAME, 'STORED PROCEDURE', 'REPLACED')";
		     				sql = "UPDATE players SET PLAYER_NAME = REPLACE (PLAYERS.PLAYER_NAME, 'STORED PROCEDURE', 'REPLACED')";
		     				st.executeQuery(sql);	
		     				sql = "commit";
		     				st.executeQuery(sql);
		     				con.close();
		     				
		        	        
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			
		}
		
	}
}
	

