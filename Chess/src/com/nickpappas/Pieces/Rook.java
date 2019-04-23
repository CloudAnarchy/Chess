package com.nickpappas.Pieces;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.nickpappas.Board;
import com.nickpappas.Cordinates;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

public class Rook extends Piece {
    public JLabel rookLabel = new JLabel();
    private final ImageIcon whiteRookIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whiteRook.png"));
    private final ImageIcon blackRookIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackRook.png"));
    private boolean ROOK_MOVED = false;

    public Rook(boolean isWhite,String name, String pieceSymbol, Cordinates pieceCords,Board theBoard) {
        super(isWhite, name, pieceSymbol, pieceCords, theBoard);
        if (isWhite) {
            this.rookLabel.setIcon(this.resizePieceLabel(this.whiteRookIcon));
        } else {
            this.rookLabel.setIcon(this.resizePieceLabel(this.blackRookIcon));
        }


    }

    public void setRook(boolean isWhite) {
        if (isWhite) {
            this.rookLabel.setIcon(this.resizePieceLabel(this.whiteRookIcon));
        } else {
            this.rookLabel.setIcon(this.resizePieceLabel(this.blackRookIcon));
        }

    }

    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getPieceValidMoves(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();
        //getTheBoard().getBoardTiles().get(getTheBoard().getTileNumber(pieceCords));

        // Also rook movement
        for(int i=pieceCords.getX()-1;i>=0;i--){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkStandardVars(pieceCords,desiredCords)) continue;  // An den perasei ta standard gia auth thn kinhsh prepei na sunexisei
            // Check if the desiredCords are Occupied then check if the pieces are from the same alliance
            // if they are dont add the cords if they are NOT add the cords but dont add any more in that direction.
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else{
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getX()+1;i<8;i++){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            // Check if the desiredCords are Occupied then check if the pieces are from the same alliance
            // if they are dont add the cords if they are NOT add the cords but dont add any more in that direction.
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else{
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getY()-1;i>=0;i--){
            desiredCords = new Cordinates(pieceCords.getX(), i);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            // Check if the desiredCords are Occupied then check if the pieces are from the same alliance
            // if they are dont add the cords if they are NOT add the cords but dont add any more in that direction.
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else{
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getY()+1;i<8;i++){
            desiredCords = new Cordinates(pieceCords.getX(), i);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            // Check if the desiredCords are Occupied then check if the pieces are from the same alliance
            // if they are dont add the cords if they are NOT add the cords but dont add any more in that direction.
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else{
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        /////////////////////////////////////////////////

        return validMoves;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getMovesForKing(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();

        for(int i = pieceCords.getX()-1; i >= 0;i--){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }

        for(int i=pieceCords.getX()+1;i<8;i++){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getY()-1;i>=0;i--){
            desiredCords = new Cordinates(pieceCords.getX(), i);

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getY()+1;i<8;i++){
            desiredCords = new Cordinates(pieceCords.getX(), i);

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }



        return validMoves;
    }


    @Override
    public void updatePiecePos(Cordinates newCords){
        ROOK_MOVED = true;
        super.pieceCords = newCords;
    }

    public boolean didRookMoved(){
        return ROOK_MOVED;
    }

    @Override
    public JLabel getPieceLabel() {
        return this.rookLabel;
    }
}
