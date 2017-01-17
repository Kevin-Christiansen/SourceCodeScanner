package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
	public static String Ask(){
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
		System.out.println();
		System.out.println("Lets ADD A NEW TEAM to the DATABASE!!");
        System.out.print("Enter new Team ID: ");
        int team_id = Integer.parseInt(br.readLine());
        
        System.out.print("Enter new Team Name: ");
        String teamName = br.readLine();
       
        System.out.print("Enter number of Wins: ");
        int wins = Integer.parseInt(br.readLine());
		
          
        System.out.print("Enter number of Losses: ");
       	int losses = Integer.parseInt(br.readLine());
       	
       	System.out.print("YOU ENTERED NEW TEAM");
       	
       	String finalCommand = UserInputCommand(team_id, teamName, wins, losses);
       	return finalCommand;
       	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("INVALID FORMAT BRO");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
        
        
	}
	
	//User Input team_id, team_Name. numberOfWins, numberOfLosses
	static String UserInputCommand(int team_id, String team_Name, int numberOfWins, int numberOfLosses){
		String SQLcommand = null;
			SQLcommand = "INSERT INTO teams values('" 
									+ team_id+"', '"
									+ team_Name+"', '"
									+ numberOfWins+"', '"
									+ numberOfLosses+"')";
			
		return SQLcommand;
		//prepared statement ** 
	}
}




