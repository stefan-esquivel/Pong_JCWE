
public class Player 
{
	
	private int player1Score = 0;
	private int player2Score = 0;
	int playerAmount = 0;
	
	public int playerChoose(int choice)
	{
		if(choice == 1)
		{
			playerAmount = 1;
		}
		else if(choice == 2)
		{
			playerAmount = 2;
		}
		return playerAmount;
	}
	
	/*
	public void  player1ScoreAdd()
	{
		player1Score += 1;
	}
	
	public void  player2ScoreAdd()
	{
		player2Score += 1;
	}
	
	public void  player1ScoreMinus()
	{
		player1Score -= 1;
	}
	
	public void  player2ScoreMinus()
	{
		player2Score -= 1;
	}
	
	public int getPlayer1Score()
	{
		return player1Score;
	}
	
	public int getPlayer2Score()
	{
		return player2Score;
	}
	*/
}