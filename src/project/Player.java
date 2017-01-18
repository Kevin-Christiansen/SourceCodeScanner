package project;

public class Player {
	int team_id;
	int win;
	int loss;
	String Favorite_Champion;
	String Player_Name;
	
	Player(int team_id, int win, int loss, String Favorite_Champion, String Player_Name){
		this.team_id = team_id;
		this.win = win;
		this.loss = loss;
		this.Favorite_Champion = Favorite_Champion;
		this.Player_Name = Player_Name;
		
	}

}
