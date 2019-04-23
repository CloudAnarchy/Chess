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

public class Queen extends Piece {
    public JLabel queen = new JLabel();
    private final ImageIcon whiteQueenIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whiteQueen.png"));
    private final ImageIcon blackQueenIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackQueen.png"));

    public Queen(boolean isWhite, String name, String pieceSymbol, Cordinates pieceCords, Board theBoard) {
        super(isWhite, name, pieceSymbol, pieceCords,theBoard);
        if (isWhite) {
            this.queen.setIcon(this.resizePieceLabel(this.whiteQueenIcon));
        } else {
            this.queen.setIcon(this.resizePieceLabel(this.blackQueenIcon));
        }

    }

    public void setQueen(boolean isWhite) {
        if (isWhite) {
            this.queen.setIcon(this.resizePieceLabel(this.whiteQueenIcon));
        } else {
            this.queen.setIcon(this.resizePieceLabel(this.blackQueenIcon));
        }

    }



    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getPieceValidMoves(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();


        // Horizontal Movement
        // Also rook movement
        for(int i=pieceCords.getX()-1;i>=0;i--){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkStandardVars(pieceCords,desiredCords)) continue;  // An den perasei ta standard gia auth thn kinhsh prepei na sunexisei
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else break;
            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getX()+1;i<8;i++){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else break;

            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getY()-1;i>=0;i--){
            desiredCords = new Cordinates(pieceCords.getX(), i);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else break;

            }
            validMoves.add(desiredCords);
        }
        for(int i=pieceCords.getY()+1;i<8;i++){
            desiredCords = new Cordinates(pieceCords.getX(), i);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else break;

            }
            validMoves.add(desiredCords);
        }
        /////////////////////////////////////////////////

        // Diagonal movements.
        // Also bishop movement.
        // UpperLeft.

        int j = pieceCords.getY();
        for(int i = pieceCords.getX()-1;i>=0;i--){
            j--;
            desiredCords = new Cordinates(i, j);

            if(!checkStandardVars(pieceCords,desiredCords)) continue;
            // Check if the desiredCords are Occupied then check if the pieces are from the same alliance
            // if they are dont add the cords if they are NOT add the cords but dont add any more in that direction.
            if(getTheBoard().isTileOccupied(desiredCords)){
                if(!checkAlliances(desiredCords)) {
                    validMoves.add(desiredCords);
                    break;
                }else break;

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
                }else break;

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
                }else break;

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
                }else break;

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

        // Horizontal Movement
        for(int i = pieceCords.getX()-1; i >= 0;i--){
            desiredCords = new Cordinates(i, pieceCords.getY());

            if(!checkOutBounds(pieceCords, desiredCords)) continue;
            if(getTheBoard().isTileOccupied(desiredCords)){
                // Ama to tile auto panw DEN exei king tote mono kane add break.
                // gia na mhn mporeis o king na kanei backward kinhsh.
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
                // Ama to tile auto panw DEN exei king tote mono kane add break.
                // gia na mhn mporeis o king na kanei backward kinhsh.
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
                // Ama to tile auto panw DEN exei king tote mono kane add break.
                // gia na mhn mporeis o king na kanei backward kinhsh.
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
        return this.queen;
    }
}

