package checkers_multiplayer;

import java.io.Serializable;

public class Piece implements Serializable
{
	String name;
	int[] pos;
	int id;
	int value;
	boolean movedOnce;
	
	public Piece(String name, int[] pos) {
        this.name = name;
        this.pos = pos;
        this.movedOnce = false;
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
	
	public boolean isMovedOnce() {
        return isMovedOnce();
    }

    public void moved() {
        this.movedOnce = true;
    }
	
	public int getID()
	{
		return this.id;
	}
}
