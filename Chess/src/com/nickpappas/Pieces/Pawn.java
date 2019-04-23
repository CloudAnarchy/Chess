package com.nickpappas.Pieces;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.nickpappas.Board;
import com.nickpappas.Cordinates;

import javax.swing.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public JLabel pawnLabel = new JLabel();
    private final ImageIcon whitePawnIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whitePawn.png"));
    private final ImageIcon blackPawnIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackPawn.png"));

    public Pawn(boolean isWhite, String name, String pieceSumbol, Cordinates pieceCords, Board theBoard) {
        super(isWhite, name, pieceSumbol, pieceCords, theBoard);
        if (isWhite) {
            this.pawnLabel.setIcon(this.resizePieceLabel(this.whitePawnIcon));
        } else {
            this.pawnLabel.setIcon(this.resizePieceLabel(this.blackPawnIcon));
        }

    }

    public void setPawn(boolean isWhite) {
        if (isWhite) {
            this.pawnLabel.setIcon(this.resizePieceLabel(this.whitePawnIcon));
        } else {
            this.pawnLabel.setIcon(this.resizePieceLabel(this.blackPawnIcon));
        }

    }

    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getPieceValidMoves(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();
        boolean pieceAhead = false;

        /*
         TESTING
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                validMoves.add(new Cordinates(i,j));

        */


        /////////////////////////////////////////////////////////////////////////////////////
        // TODO  1. Add for the pawn when it starts to be able to go 2 moves ahead for the first time.
        //      3. Check when you move your piece if your King it getting threatened
        //
        /////////////////////////////////////////////////////////////////////////////////////

        // If the pawn is white you can look
        // to move forward to lower numbers.
        // else you can move to higher numbers.
        // (thats because pawns only look forward to where they are looking)
        if(getAlliance()){
            for(int j = pieceCords.getY()-1;j<=pieceCords.getY()+1;j++){
                desiredCords = new Cordinates(pieceCords.getX()-1, j);
                if(!checkStandardVars(pieceCords, desiredCords)) continue;
                if(getTheBoard().isTileOccupied(desiredCords)){
                    if(checkAlliances(desiredCords)){
                        continue; // Ama einai apo to idio Alliance skip.
                    }else{
                        // Ama to antipalo pionaki DEN brisketai mprosta tou tote mporeis na pas
                        // se auta ta cordinates.
                        if(pieceCords.getY() != j){
                            validMoves.add(desiredCords);
                        }else{
                            pieceAhead = true; // Keep track that there is a enemy piece ahead
                        }
                    }
                }
                if(pieceCords.getY() == j && !pieceAhead) validMoves.add(desiredCords);

                if(pieceCords.getX() == 6){
                    // An einai sto x=6
                    // perasei ta standard checks
                    // kai den einai occupied to block einai valid move.
                    desiredCords = new Cordinates(pieceCords.getX()-2, pieceCords.getY());
                    if(checkStandardVars(pieceCords, desiredCords)){
                        if(!getTheBoard().isTileOccupied(desiredCords))
                            validMoves.add(desiredCords);
                    }
                }
            }
        }else{
            for(int j = pieceCords.getY()+1;j>=pieceCords.getY()-1;j--){
                desiredCords = new Cordinates(pieceCords.getX()+1, j);
                if(!checkStandardVars(pieceCords, desiredCords)) continue;
                if(getTheBoard().isTileOccupied(desiredCords)){
                    if(checkAlliances(desiredCords)){
                        continue; // Ama einai apo to idio Alliance skip.
                    }else{
                        // Ama to antipalo pionaki DEN brisketai mprosta tou tote mporeis na pas
                        // se auta ta cordinates.
                        if(pieceCords.getY() != j){
                            validMoves.add(desiredCords);
                        }else{
                            pieceAhead = true; // Keep track that there is a enemy piece ahead
                        }
                    }
                }
                if(pieceCords.getY() == j && !pieceAhead) validMoves.add(desiredCords);
                if(pieceCords.getX() == 1){
                    // An einai sto x=1
                    // perasei ta standard checks
                    // kai den einai occupied to block einai valid move.
                    desiredCords = new Cordinates(pieceCords.getX()+2, pieceCords.getY());
                    if(checkStandardVars(pieceCords, desiredCords)){
                        if(!getTheBoard().isTileOccupied(desiredCords))
                            validMoves.add(desiredCords);
                    }
                }
            }
        }




        return validMoves;
    }

    @Override
    public ArrayList<Cordinates> getMovesForKing(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates pieceCords = getPieceCords();


        if(this.getAlliance()) {
            validMoves.add(new Cordinates(pieceCords.getX() - 1, pieceCords.getY() - 1));
            validMoves.add(new Cordinates(pieceCords.getX() - 1, pieceCords.getY() + 1));
        }else{
            validMoves.add(new Cordinates(pieceCords.getX() + 1, pieceCords.getY() - 1));
            validMoves.add(new Cordinates(pieceCords.getX() + 1, pieceCords.getY() + 1));
        }
        return validMoves;
    }






    @Override
    public JLabel getPieceLabel() {
        return this.pawnLabel;
    }
}
