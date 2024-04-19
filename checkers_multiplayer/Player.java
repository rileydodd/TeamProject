/*

PLAYER CLASS (THIS IS THE USERS IN THE SOFTWARE)
ONLY TWO ALLOWED FOR ONE GAME

*/
package checkers_multiplayer;

import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable{
	
	private int id;
	private int score;
	private String name;
	private int team;
	private int color; // 0 - red, 1 -black 
	private Piece[][] checkersBoard;
	private ArrayList<Piece> pieces;
	private boolean staleMate;
	
	public Player(String name, int id, Piece[][] checkersBoard, ArrayList<Piece> pieces, int team) {
		this.name = name;
		this.id = id;
		this.checkersBoard = checkersBoard;
		this.score = 0;
		this.team = team;
		this.staleMate = false;
		this.pieces = pieces;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPiece(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}
	
	public void staleMated() {
		this.staleMate = true;
	}

	public Piece[][] getBoard() {
		return this.checkersBoard;
	}

	// GETTERS
	public int getID() {
		return this.id;
	}

	public int getScore() {
		return this.score;
	}
	
	public int getTeam() {
		return this.team;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Piece> get_pieces() {
		return this.pieces;
	}
	
	public boolean is_stalemate() {
		return this.staleMate;
	}


}
