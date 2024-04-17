package checkers_multiplayer;

import java.io.Serializable;

public class Move implements Serializable
{
	private int turn;
	private int id;
	
	private int currentposX, currentposY, newposX, newposY;
	
	public Move(int turn, int currentposX, int currentposY, int newposX, int newposY)
	{
		this.turn = turn;
		this.newposX = newposX;
		this.newposY = newposY;
		this.currentposX = currentposX;
		this.currentposY = currentposY;
	}
	
	public int getTurn()
	{
		return this.turn;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public int[] getNewPos()
	{
		int[] temp = {newposX, newposY};
		return temp;
	}
	
	public int[] getCurrentPos()
	{
		int[] temp = {currentposX, currentposY};
		return temp;
	}
}
