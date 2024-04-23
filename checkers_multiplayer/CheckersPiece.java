package checkers_multiplayer;

import java.io.Serializable;

public class CheckersPiece extends Piece implements Serializable{
	
	 public CheckersPiece(String name, int[] pos) {
	        super(name, pos);
	    }

	    public boolean isValidMove(int[] newPos, Piece[][] board) {
	        int startX = this.getPos()[0];
	        int startY = this.getPos()[1];
	        int endX = newPos[0];
	        int endY = newPos[1];

	        // Check if the new position is within the bounds of the board
	        if (endX < 0 || endX >= board.length || endY < 0 || endY >= board[0].length) {
	            return false;
	        }

	        // Check if the destination position is empty
	        if (!board[endX][endY].getName().equals("Empty")) {
	            return false;
	        }

	        // Check if the move is diagonal
	        if (Math.abs(endX - startX) != 1 || Math.abs(endY - startY) != 1) {
	            return false;
	        }

	        // Check if the move direction corresponds to the piece's team
	        if (this.getTeam() == 0 && endX > startX) {
	            return false; // Red pieces can only move upwards
	        }
	        if (this.getTeam() == 1 && endX < startX) {
	            return false; // Black pieces can only move downwards
	        }

	        return true;
	    }
}
