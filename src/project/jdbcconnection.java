package project;
import java.sql.*;
public class jdbcconnection {

	public static void main(String[] args){

		try
		{
		
		//Connecting to our Database
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","team11");
		Statement st = con.createStatement();
		String sql = "ALTER SESSION SET CURRENT_SCHEMA = LEAGUE";// not needed
		ResultSet rs = st.executeQuery(sql);
		rs = st.executeQuery(sql);
		
		//Basic SQL Statements
		sql = "select * from PLAYERS"; //
		rs = st.executeQuery(sql);
		
		//Display Table
		while(rs.next()){
			System.out.println(rs.getString(1) +" " + rs.getInt(2) + " " //be able to identify column 2 changes to varchar
		+ rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
		
		
		sql = "CALL addplayer('80','76','5','ALLISON','COMPUTER')";	//stored procedure	
		rs = st.executeQuery(sql);
		sql = "commit";
		rs = st.executeQuery(sql);
		
		String UserInputPartial = UserInput.Ask(); //incomplete SQL statement
		rs = st.executeQuery(UserInputPartial);
		sql = "select * from TEAMS";
		rs= st.executeQuery(sql);
		
		
		
		
		////////////////
		con.close();
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		//SimpleUI.display();

	}

}
