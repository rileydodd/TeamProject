package checkers_multiplayer;

import java.io.Serializable;

public class Piece implements Serializable
{
	String name;
	int[] pos;
	int id;
	int value;
	boolean moved_once;
	
	public Piece(String name, int type, int id, int value, int[] pos)
	{
		this.name = name;
		this.id = id;
		this.value = value;
		this.pos = pos;
		this.moved_once = false;
	}
	
	public boolean exists()
	{
		return false;
	}
	
	public int getTeam()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int[] getPos()
	{
		return this.pos;
	}
	
	public void setPos(int[] pos)
	{
		this.pos = pos;
	}
	
	public boolean movePiece(int[] new_pos, Piece[][] board)
	{
		return false;
	}
	
	public boolean isMovedOnce()
	{
		return moved_once;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void moved()
	{
		this.moved_once = true;
	}
}
