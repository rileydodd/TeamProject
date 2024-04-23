package checkers_multiplayer;

import java.util.ArrayList;

public class CheckersEngine {
    private Piece[][] board;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    public CheckersEngine(Piece[][] board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public boolean movePiece(Move move) {
        int[] startPos = move.getCurrentPos();
        int[] endPos = move.getNewPos();

        int startX = startPos[0];
        int startY = startPos[1];
        int endX = endPos[0];
        int endY = endPos[1];

      
        if (validate(move)) {
            Piece piece = board[startX][startY];
            board[endX][endY] = piece;
            board[startX][startY] = new Piece("Empty", new int[]{startX, startY});

            if (Math.abs(endX - startX) == 2 && Math.abs(endY - startY) == 2) {
                int middleX = (startX + endX) / 2;
                int middleY = (startY + endY) / 2;
                Piece middlePiece = board[middleX][middleY];
                if (middlePiece != null && middlePiece.getTeam() != currentPlayer.getTeam()) {
                    board[middleX][middleY] = new Piece("Empty", new int[]{middleX, middleY});
                }
            }

            switchPlayer();
            return true;
        }
        return false;
    }

    private void checkForCaptures(int startX, int startY, int endX, int endY) {
        if (Math.abs(endX - startX) == 2 && Math.abs(endY - startY) == 2) {
            int middleX = (startX + endX) / 2;
            int middleY = (startY + endY) / 2;
            Piece middlePiece = board[middleX][middleY];
            if (middlePiece != null && middlePiece.getTeam() != currentPlayer.getTeam()) {
                board[middleX][middleY] = new Piece("Empty", new int[]{middleX, middleY});
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean validate(Move move) {
        int[] startPos = move.getCurrentPos();
        int[] endPos = move.getNewPos();
        int startX = startPos[0];
        int startY = startPos[1];
        int endX = endPos[0];
        int endY = endPos[1];
        
        if (!isValidPosition(startX, startY) || !isValidPosition(endX, endY)) {
            return false;
        }

        Piece piece = board[startX][startY];
        if (piece == null || piece.getTeam() != currentPlayer.getTeam()) {
            return false;
        }

        if (Math.abs(endX - startX) != Math.abs(endY - startY)) {
            return false;
        }

        if (!board[endX][endY].getName().equals("Empty")) {
            return false;
        }

        return true;
    }
    
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
