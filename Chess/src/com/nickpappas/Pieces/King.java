package com.nickpappas.Pieces;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.nickpappas.Board;
import com.nickpappas.Cordinates;
import com.nickpappas.PossibleMoves;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.ArrayList;

public class King extends Piece {
    private JLabel kingLabel = new JLabel();
    private final ImageIcon whiteKingIcon = new ImageIcon(this.getClass().getResource("Resources/WhitePieces/whiteKing.png"));
    private final ImageIcon blackKingIcon = new ImageIcon(this.getClass().getResource("Resources/BlackPieces/blackKing.png"));
    private boolean KING_MOVED = false;
    private int KING_STATE = 0;


    public King(boolean isWhite, String name, String pieceSymbol, Cordinates pieceCords, Board theBoard) {
        super(isWhite, name, pieceSymbol, pieceCords,theBoard);

        if (isWhite) {
            this.kingLabel.setIcon(this.resizePieceLabel(this.whiteKingIcon));
        } else {
            this.kingLabel.setIcon(this.resizePieceLabel(this.blackKingIcon));
        }

    }

    public void setKing(boolean isWhite) {
        if (isWhite) {
            this.kingLabel.setIcon(this.resizePieceLabel(this.whiteKingIcon));
        } else {
            this.kingLabel.setIcon(this.resizePieceLabel(this.blackKingIcon));
        }

    }


    @Override
    public ArrayList<Cordinates> getPieceValidMoves(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();


        for(int i = pieceCords.getX()-1;i<=pieceCords.getX()+1;i++){

            for(int j = pieceCords.getY()-1;j<=pieceCords.getY()+1;j++){
                desiredCords = new Cordinates(i, j);

                if(!checkOutBounds(pieceCords, desiredCords)) continue;

                if(getTheBoard().isTileOccupied(desiredCords))
                    if(checkAlliances(desiredCords)) continue; // An occupied == true kai einai apo to idio Alliance skip.

                // Ama to mporeiRousa == true tote mhn mpeis mesa
                if(mporeiRoua(desiredCords)) continue;

                validMoves.add(desiredCords);
            }

        }

        ////////////////////
        //  KING CASLTE  //
        ///////////////////
        if(!KING_MOVED){
            boolean addMove = true;
            // Castling for the whiteKing
            if(getAlliance()){
                // Ama den kounh8hke o basilias kane check gia castling

                // Left castling or white King
                if(getTheBoard().getTile(56).getPiece().getPieceName().equals("White Rook")){
                    Rook rook = (Rook)getTheBoard().getTile(56).getPiece();
                    if(!rook.didRookMoved()){
                        for(int j = 3; j > 0; j--){
                            desiredCords = new Cordinates(7, j);
                            if(getTheBoard().isTileOccupied(desiredCords)){
                                addMove = false;
                                break;
                            }    // An occupied == true tote break.
                            if(mporeiRoua(desiredCords)){
                                addMove = false;
                                break;
                            }                      // Ama to mporeiRousa == true tote mhn mpeis mesa
                        }
                        if(addMove) validMoves.add(new Cordinates(7, 2));
                    }
                }


                addMove = true;
                // Right castling for white King
                if(getTheBoard().getTile(63).getPiece().getPieceName().equals("White Rook")){
                    Rook rook = (Rook)getTheBoard().getTile(63).getPiece();
                    if(!rook.didRookMoved()) {
                        for (int j = 5; j < 7; j++){
                            desiredCords = new Cordinates(7, j);

                            if (getTheBoard().isTileOccupied(desiredCords)){
                                addMove = false;
                                break;
                            }   // An occupied == true tote break.
                            if (mporeiRoua(desiredCords)){
                                addMove = false;
                                break;
                            }                     // Ama to mporeiRousa == true tote mhn mpeis mesa
                        }
                        if(addMove) validMoves.add(new Cordinates(7, 6));
                    }
                }

            }else{  // for the blackKing

                // Left castling for black King
                if(getTheBoard().getTile(0).getPiece().getPieceName().equals("Black Rook")){
                    Rook rook = (Rook)getTheBoard().getTile(0).getPiece();
                    if(!rook.didRookMoved()){
                        for(int j = 3; j > 0; j--){
                            desiredCords = new Cordinates(0, j);

                            if(getTheBoard().isTileOccupied(desiredCords)){
                                addMove = false;
                                break;
                            }    // An occupied == true tote break.
                            if(mporeiRoua(desiredCords)){
                                addMove = false;
                                break;
                            }                      // Ama to mporeiRousa == true tote mhn mpeis mesa
                        }
                        if(addMove) validMoves.add(new Cordinates(0, 2));
                    }
                }

                addMove = true;
                // Right castling for black King
                if(getTheBoard().getTile(7).getPiece().getPieceName().equals("Black Rook")){
                    Rook rook = (Rook)getTheBoard().getTile(7).getPiece();
                    if(!rook.didRookMoved()){
                        for(int j = 5; j < 7; j++){
                            desiredCords = new Cordinates(0, j);

                            // An occupied == true tote break.
                            if(getTheBoard().isTileOccupied(desiredCords)){
                                addMove = false;
                                break;
                            }

                            // Ama to mporeiRousa == true tote mhn mpeis mesa
                            if(mporeiRoua(desiredCords)){
                                addMove = false;
                                break;
                            }
                        }
                        if(addMove) validMoves.add(new Cordinates(0, 6));
                    }
                }
            }
        }

        return validMoves;
    }

    private boolean mporeiRoua(Cordinates desiredCords){
        PossibleMoves posMoves = new PossibleMoves(getTheBoard());
        ArrayList<Cordinates> allEnemyMoves = new ArrayList<>(posMoves.getEnemyMoves(this));


        for(Cordinates enemyCords : allEnemyMoves)
            //System.out.println("("+enemyCords.getX()+","+enemyCords.getY()+")");
            if (enemyCords.getX() == desiredCords.getX() && enemyCords.getY() == desiredCords.getY())
                return true;
        return false;
    }



    @Override
    public ArrayList<Cordinates> getMovesForKing(){
        ArrayList<Cordinates> validMoves = new ArrayList<>();
        Cordinates desiredCords, pieceCords = getPieceCords();

        for(int i = pieceCords.getX()-1;i<=pieceCords.getX()+1;i++){

            for(int j = pieceCords.getY()-1;j<=pieceCords.getY()+1;j++){
                // Dont include the kings position as a move.
                desiredCords = new Cordinates(i, j);
                if(!checkOutBounds(pieceCords, desiredCords)) continue;

                validMoves.add(desiredCords);
            }

        }
        return validMoves;
    }

    @Override
    public void updatePiecePos(Cordinates newCords){
        KING_MOVED = true;
        super.pieceCords = newCords;
    }

    // TODO Reset somehow your king_state to natural
    //      Maybe move KING_STATE to king.
    public int updateKingState(){
        PossibleMoves helpingMoves = new PossibleMoves(getTheBoard());
        ArrayList<Cordinates> enemyCords = helpingMoves.getEnemyMoves(this);


        for(Cordinates enCords : enemyCords){
            if(this.getPieceCords().getX() == enCords.getX() && this.getPieceCords().getY() == enCords.getY()) {
                if (this.getAlliance()) KING_STATE = 1;
                else KING_STATE = 1;
            }
        }

        // TODO kai den exei tropo na to stamathsei SOS!!
        // An den exei validMoves , apileite KAI den uparxei savior einai mat.
        if(KING_STATE == 1 && this.getPieceValidMoves().isEmpty()) {
            ArrayList<Cordinates> allyMoves = helpingMoves.getProctiveMoves(this);
            if(allyMoves.isEmpty()){
                if(getAlliance()) for(int i=0;i < 40;i++) System.out.println("WHITE LOST");
                else for(int i=0;i < 40;i++) System.out.println("BLACK LOST");
                KING_STATE = 2;
            }
        }
        return KING_STATE;
    }

    @Override
    public JLabel getPieceLabel() {
        return this.kingLabel;
    }
}
