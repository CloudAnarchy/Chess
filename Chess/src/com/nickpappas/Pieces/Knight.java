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

public class Knight extends Piece {
    public JLabel knightLabel = new JLabel();
    private final ImageIcon whiteKnightRightIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whiteKnightRight.png"));
    private final ImageIcon whiteKnightLeftIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whiteKnightLeft.png"));
    private final ImageIcon blackKnightRightIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackKnightRight.png"));
    private final ImageIcon blackKnightLeftIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackKnightLeft.png"));

    public Knight(boolean isWhite, boolean isRight, String name, String pieceSymbol,Cordinates pieceCords, Board theBoard) {
        super(isWhite, name, pieceSymbol, pieceCords, theBoard);
        if (isWhite) {
            if (isRight) {
                this.knightLabel.setIcon(this.resizePieceLabel(this.whiteKnightRightIcon));
            } else {
                this.knightLabel.setIcon(this.resizePieceLabel(this.whiteKnightLeftIcon));
            }
        } else if (isRight) {
            this.knightLabel.setIcon(this.resizePieceLabel(this.blackKnightRightIcon));
        } else {
            this.knightLabel.setIcon(this.resizePieceLabel(this.blackKnightLeftIcon));
        }

    }

    public void setKnight(boolean isWhite, boolean isRight) {

        if (isWhite) {
            if (isRight) {
                this.knightLabel.setIcon(this.resizePieceLabel(this.whiteKnightRightIcon));
            } else {
                this.knightLabel.setIcon(this.resizePieceLabel(this.whiteKnightLeftIcon));
            }
        } else if (isRight) {
            this.knightLabel.setIcon(this.resizePieceLabel(this.blackKnightRightIcon));
        } else {
            this.knightLabel.setIcon(this.resizePieceLabel(this.blackKnightLeftIcon));
        }

    }

    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getPieceValidMoves(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();

        for(int i=pieceCords.getX()-2;i<=pieceCords.getX()+2;i++){
            if(Math.abs(i - pieceCords.getX()) == 2){
                for (int j = -1; j <= 1; j++) {
                    if(j == 0) continue;
                    desiredCords = new Cordinates(i, pieceCords.getY() + j);
                    if (!checkStandardVars(pieceCords, desiredCords)) continue;  // An den perasei ta standard gia auth thn kinhsh prepei na sunexisei
                    if (getTheBoard().isTileOccupied(desiredCords)) {
                            if (!checkAlliances(desiredCords)) validMoves.add(desiredCords);
                            else continue;
                    }
                    validMoves.add(desiredCords);
                }
            }else if(Math.abs(i - pieceCords.getX()) == 1){
                for (int j = -2; j <= 2; j++) {
                    if(j != 2 && j != -2) continue;
                    desiredCords = new Cordinates(i, pieceCords.getY() + j);
                    if (!checkStandardVars(pieceCords, desiredCords)) continue;  // An den perasei ta standard gia auth thn kinhsh prepei na sunexisei
                    if (getTheBoard().isTileOccupied(desiredCords)) {
                        if (!checkAlliances(desiredCords)) validMoves.add(desiredCords);
                        else continue;
                    }
                    validMoves.add(desiredCords);
                }
            }
        }

        return validMoves;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public ArrayList<Cordinates> getMovesForKing(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();

        for(int i=pieceCords.getX()-2;i<=pieceCords.getX()+2;i++){
            if(Math.abs(i - pieceCords.getX()) == 2){
                for (int j = -1; j <= 1; j++) {
                    if(j == 0) continue;
                    desiredCords = new Cordinates(i, pieceCords.getY() + j);

                    if(!checkOutBounds(pieceCords, desiredCords)) continue;
                    validMoves.add(desiredCords);
                }
            }else if(Math.abs(i - pieceCords.getX()) == 1){
                for (int j = -2; j <= 2; j++) {
                    if(j != 2 && j != -2) continue;
                    desiredCords = new Cordinates(i, pieceCords.getY() + j);

                    if(!checkOutBounds(pieceCords, desiredCords)) continue;
                    validMoves.add(desiredCords);
                }
            }
        }

        return validMoves;
    }








    @Override
    public JLabel getPieceLabel() {
        return this.knightLabel;
    }
}
