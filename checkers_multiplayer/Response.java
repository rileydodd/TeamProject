package checkers_multiplayer;

import java.io.Serializable;

public class Response implements Serializable
{
	public String[][] pieces;
	public int[][] teams;
	
	public Response(String[][] pieces, int[][] teams)
	{
		this.pieces = pieces;
		this.teams = teams;
	}
	
	
}