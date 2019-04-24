package com.nickpappas;

import com.nickpappas.Pieces.EmptyPiece;
import com.nickpappas.Pieces.Piece;
import java.util.ArrayList;

public class PossibleMoves {

    private Piece piece;
    private ArrayList<Cordinates> validMoves;
    private Board theBoard;

    public PossibleMoves(Board theBoard){
        this.theBoard = theBoard;
    }

    public ArrayList<Cordinates> getEnemyMoves(Piece piece){

        ArrayList<Cordinates> enemyMoves = new ArrayList<>();

        // Kai gia ta 16 antipala pieces.
        for(int i = 0; i < 64; i++){
            Piece enemyPiece = theBoard.getTile(i).getPiece();
            if(enemyPiece.getPieceName().equals("empty") ||  piece.getAlliance() == enemyPiece.getAlliance())  continue;  // Mhn checkareis empty kai idio alliance.
            /*
            for(Cordinates cord : theBoard.getTile(i).getPiece().getPieceValidMoves()){
                System.out.println(theBoard.getTile(i).getPiece().getPieceName()+"("+theBoard.getTile(i).getPiece().getPieceCords().getX()+","+theBoard.getTile(i).getPiece().getPieceCords().getY()+") - ("+cord.getX()+","+cord.getY()+")");
            }

            */
            enemyMoves.addAll(enemyPiece.getMovesForKing());
        }

        return enemyMoves;
    }

    public ArrayList<Cordinates> getProctiveMoves(Piece piece){

        ArrayList<Cordinates> allyMoves = new ArrayList<>();

        // Kai gia ta 16 antipala pieces.

        for(int i = 0; i < 64; i++){
            Piece allyPiece = theBoard.getTile(i).getPiece();
            if(allyPiece.getPieceName().equals("empty") ||  piece.getAlliance() != allyPiece.getAlliance())  continue;  // Mhn checkareis empty kai idio alliance.
            allyMoves.addAll(allyPiece.getPieceValidMoves());
        }

        return allyMoves;
    }

    public boolean simulate(Cordinates pieceCords, Cordinates desiredCords, Cordinates kingCords){

        Piece pieceCordsPiece = theBoard.getTile(pieceCords).getPiece();
        Piece desiredCordsPiece = theBoard.getTile(desiredCords).getPiece();
        theBoard.getTile(pieceCords).setPiece(new EmptyPiece("empty"," _", pieceCords));
        theBoard.getTile(desiredCords).setPiece(pieceCordsPiece);
        // GET THE MOVES AFTER YOU SWAP THE PIECES DUMMY
        ArrayList<Cordinates> moves = getEnemyMoves(theBoard.getTile(kingCords).getPiece());


        for(Cordinates enemyMove : moves){
            // An brei oti ontws den ginetai na kouni8eis ftia3e
            // ftia3e ta cords pali kai gurna false
            if(kingCords.getX() == enemyMove.getX() && kingCords.getY() == enemyMove.getY()){
                //System.out.println("KING IN POSSIBLE DANGER IF YOU MOVE");
                theBoard.getTile(desiredCords).setPiece(desiredCordsPiece);
                theBoard.getTile(pieceCords).setPiece(pieceCordsPiece);
                return false;
            }
        }


        // SOS!!! prepei na to ftiaxneis pisw
        theBoard.getTile(desiredCords).setPiece(desiredCordsPiece);
        theBoard.getTile(pieceCords).setPiece(pieceCordsPiece);
        return true;
    }




}


