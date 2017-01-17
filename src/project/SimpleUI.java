package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class SimpleUI {

	private static class SimpleDisplay extends JPanel {
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);
	         g.drawString( "Would you like to know how many teams are in the database?", 20, 30 );
	      }
	   }
	
	 private static class ButtonHandler extends JPanel implements ActionListener {
		 
	      public void actionPerformed(ActionEvent e) {
	    	 try
	  		{
	    	int numPlayers = 0;
	    	int numTeams = 0;
	  		Class.forName("oracle.jdbc.driver.OracleDriver");
	  		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
	  		Statement st = con.createStatement();
	  		String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";
	  		ResultSet rs = st.executeQuery(sql);
	  		sql = "select * from PLAYERS";
	  		rs = st.executeQuery(sql);
	    	  
	    	  
	    	  while(rs.next()){
	  				numPlayers = numPlayers+1;
	  			}
	    	  
	    	  sql = "select * from TEAMS";
		  		rs = st.executeQuery(sql);
		  	  while(rs.next()){
	  				numTeams = numTeams+1;
	  			}
		  	  System.out.println("There are " + numPlayers+ " players in the database.");
		  	System.out.println("There are " + numTeams+ " teams in the database.");
		  	 con.close();
		  //	JTextField numberEnter = new JTextField("Enter numbers here", 20);

		  	System.exit(0);

	    	 
	    	  
			}
			
			catch(Exception f){
				System.out.println(f);
			}
	      }
	   }
	 
	public static void display(){
		
		  SimpleDisplay displayPanel = new SimpleDisplay();
	      JButton yesButton = new JButton("YES");
	      ButtonHandler listener = new ButtonHandler();
	      yesButton.addActionListener(listener);

	      JPanel content = new JPanel();
	      content.setLayout(new BorderLayout());
	      content.add(displayPanel, BorderLayout.CENTER);
	      content.add(yesButton, BorderLayout.SOUTH);

	      JFrame window = new JFrame("Simple UI");
	      window.setContentPane(content);
	      window.setSize(500,100);
	      window.setLocation(100,100);
	      window.setVisible(true);
	      
	}
	
	
	
}
