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

public class Bishop extends Piece {
    public JLabel bishopLabel = new JLabel();
    private final ImageIcon whiteBishopIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whiteBishop.png"));
    private final ImageIcon blackBishopIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackBishop.png"));

    public Bishop(boolean isWhite, String name, String pieceSymbol, Cordinates pieceCords,Board theBoard) {
        super(isWhite, name, pieceSymbol, pieceCords,theBoard);
        if (isWhite) {
            this.bishopLabel.setIcon(this.resizePieceLabel(this.whiteBishopIcon));
        } else {
            this.bishopLabel.setIcon(this.resizePieceLabel(this.blackBishopIcon));
        }

    }

    public void setBishop(boolean isWhite) {
        if (isWhite) {
            this.bishopLabel.setIcon(this.resizePieceLabel(this.whiteBishopIcon));
        } else {
            this.bishopLabel.setIcon(this.resizePieceLabel(this.blackBishopIcon));
        }

    }


    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getPieceValidMoves(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();

        // Diagonal movements.
        // Also bishop movement.
        // UpperLeft.

        int j = pieceCords.getY();
        for(int i = pieceCords.getX()-1;i>=0;i--){
            j--;
            desiredCords = new Cordinates(i, j);

            if(desiredCords.getY() == pieceCords.getY() && desiredCords.getX() == pieceCords.getX()) continue;
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
        // LowerRight.
        j = pieceCords.getY();
        for(int i = pieceCords.getX()+1;i<8;i++){
            j++;
            desiredCords = new Cordinates(i, j);

            if(!checkStandardVars(pieceCords, desiredCords)) continue;
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
        // LowerLeft.
        j = pieceCords.getY();
        for(int i = pieceCords.getX()+1;i<8;i++){
            j--;
            desiredCords = new Cordinates(i, j);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
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
        // UpperRight.
        j = pieceCords.getY();
        for(int i = pieceCords.getX()-1;i>=0;i--){
            j++;
            desiredCords = new Cordinates(i, j);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
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

        return validMoves;
    }





    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getMovesForKing(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();

        // Diagonal movements.
        // Also bishop movement.
        // UpperLeft.

        int j = pieceCords.getY();
        for(int i = pieceCords.getX()-1;i>=0;i--){
            j--;
            desiredCords = new Cordinates(i, j);

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        // LowerRight.
        j = pieceCords.getY();
        for(int i = pieceCords.getX()+1;i<8;i++){
            j++;
            desiredCords = new Cordinates(i, j);

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        // LowerLeft.
        j = pieceCords.getY();
        for(int i = pieceCords.getX()+1;i<8;i++){
            j--;
            desiredCords = new Cordinates(i, j);

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!getTheBoard().getTile(desiredCords).getPiece().getPieceName().endsWith("King")){
                    validMoves.add(desiredCords);
                    break;
                }
            }
            validMoves.add(desiredCords);
        }
        // UpperRight.
        j = pieceCords.getY();
        for(int i = pieceCords.getX()-1;i>=0;i--){
            j++;
            desiredCords = new Cordinates(i, j);

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
    public JLabel getPieceLabel() {
        return this.bishopLabel;
    }
}
