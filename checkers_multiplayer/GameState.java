package checkers_multiplayer;

import java.io.Serializable;

public class GameState implements Serializable
{
	private int turn;
	private Player[] players;
	private Piece[][] board;
	
	public GameState(Piece[][] board, Player player1, Player player2)
	{
		this.turn = 0;
		this.players = new Player[2];
		players[0] = player1;
		players[1] = player2;
	}
	
	public int getTurn()
	{
		return turn;
	}
	
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	
	public Piece[][] getBoard()
	{
		return this.board;
	}
	
	public void setBoard(Piece[][] board)
	{
		this.board = board;
	}
	
	public Player getPlayer(int i)
	{
		return players[i];
	}
	

	
	

}
