package com.nickpappas.Pieces;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.nickpappas.Board;
import com.nickpappas.Cordinates;
import com.nickpappas.PossibleMoves;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Piece {
    private int width = 50;
    private int height = 50;
    private boolean alliance;
    private String name;
    private JLabel pieceLabel = null;
    private ArrayList<Cordinates> validMoves = new ArrayList<>();
    private Board theBoard;
    private String pieceSymbol;
    protected Cordinates pieceCords;


    public Piece(boolean alliance, String name, String pieceSymbol, Cordinates pieceCords, Board board) {
        this.alliance = alliance;
        this.name = name;
        this.theBoard = board;
        this.pieceSymbol = pieceSymbol;
        this.pieceCords = pieceCords;
    }

    public Piece(String name, String pieceSymbol, Cordinates pieceCords){
        this.name = name;
        this.pieceSymbol = pieceSymbol;
        this.pieceCords = pieceCords;
    }


    public abstract ArrayList<Cordinates> getPieceValidMoves();

    // TODO: Check for
    //          - Complete the standard checks that
    //            every piece have to check to
    //            render the move valid:
    //       - Implement the the king is getting threatend logic DONE
    //       - DO Castle AND (If the King its getting threatened and cant move its GAME OVER)
    //          the King has to check every tile he wants to get to if its possible for an enemy piece to attack there.
    //       -
    //
    //          if( King's desiredCords == enemies(Queen,King,Pawns,Bishops,Knights,Rooks) validMoves ){
    //              then the King cant move to that tile.
    //          }
    //
    //        DONE:
    //        YOU HAVE TO IMPLEMENT THE LOGIC AND USE IT ON THE GUI
    //                  (once you finish the queen)
    public boolean checkStandardVars(Cordinates pieceCords, Cordinates desiredCords){

        // Get Kings Cords tou IDIOU Alliance!!! SOS
        Cordinates kingCords = theBoard.getKingsCords(getAlliance());
        PossibleMoves moves = new PossibleMoves(theBoard);
        int kingState = theBoard.getKING_STATE(getAlliance());


        if(desiredCords.getX() == pieceCords.getX() && desiredCords.getY() == pieceCords.getY()) return false;  // Dont include the move.
        if(desiredCords.getX() < 0 || desiredCords.getX() > 7 || desiredCords.getY() < 0 || desiredCords.getY() > 7) return false;

        // 0 natural
        // 1 roua , 2 roua-mat
        boolean simulatedMove = moves.simulate(pieceCords, desiredCords, kingCords);
        switch (kingState){

            case 0:
                // An kanei return false den einai valid move
                //System.out.println("King State 0 -- "+kingState+" -- ("+desiredCords.getX()+","+desiredCords.getY()+")");
                return simulatedMove;
            case 1:
                // An kanei return false sto case 1 paei na pei
                // oti stamtaei to roua h kinish ara einai valid
                return simulatedMove;
            case 2:
                return false;
        }

        /*
        if(getAlliance()){

        }
         */

        return true;
    }

    public void updatePiecePos(Cordinates newCords){
        this.pieceCords = newCords;
    }


    public boolean checkOutBounds(Cordinates pieceCords, Cordinates desiredCords){
        if(desiredCords.getX() == pieceCords.getX() && desiredCords.getY() == pieceCords.getY()) return false;  // Dont include the move.
        if(desiredCords.getX() < 0 || desiredCords.getX() > 7 || desiredCords.getY() < 0 || desiredCords.getY() > 7) return false;
        return true;
    }

    public Cordinates getPieceCords(){
        return pieceCords;
    }

    public String getPieceSymbol(){
        return pieceSymbol;
    }



/*



            THERE IS AN EASIER WAY THAN THIS....gamw tsampa mou bghke h pisth :(

            // Cords for "boxing" the piece
             private final Cordinates NO_BOX = new Cordinates(-1,-1);
             private Cordinates boxXminus;
             private Cordinates boxXplus;
             private Cordinates boxYminus;
             private Cordinates boxYplus;
             private Cordinates boxYminus1Upper;
             private Cordinates boxYplus1Upper;
             private Cordinates boxYminus1Lower;
             private Cordinates boxYplus1Lower;

        // EXAMPLE OF THE BOXING:
        //      0    1    2     3   4         Let's Assume:
        //    0  []  []   []    []  []      1.We have a Queen in cords (2,2).
        //    1  []  [p]  [p]  [p]  []      2.There is a pawn in pos   (1,2).
        //    2  []  [p]  [Q]  [p]  []        This means that for every possible
        //    3  []  [p]  [p]  [p]  []        move for the SAME axis (axis Y in this case)
        //    4  []  []   []    []  []        you cant go to a cordinate with the same Y
        //                                    but with a lower X since you have encounted
        //                                    a piece in that direction.
        //
        if(desiredPos.getY() == boxYminus.getY() && desiredPos.getX() < boxYminus.getX()) return false;
        if(desiredPos.getY() == boxYplus.getY() && desiredPos.getX() > boxYplus.getX()) return false;
        if(desiredPos.getY() < boxYminus1Upper.getY() && desiredPos.getX() < boxYplus1Upper.getX()) return false;
        if(desiredPos.getY() > boxYplus1Upper.getY()
                && desiredPos.getX() < boxYplus1Upper.getX()
                && desiredPos.getY() != pieceCords.getY()
                && desiredPos.getX() != pieceCords.getX()) return false;

        if(desiredPos.getY() > boxYplus1Lower.getY() && desiredPos.getX() > boxYminus1Lower.getX()) return false;
        if(desiredPos.getY() < boxYminus1Lower.getY() && desiredPos.getX() > boxYminus1Lower.getX()
                && desiredPos.getX() != pieceCords.getX()
                && desiredPos.getY() != pieceCords.getY()) return false;

        public Cordinates boxingPiece(Cordinates pieceCords){
        int x = pieceCords.getX();
        int y = pieceCords.getY();


        // Checks in axis Y trying to find a piece.
        for(int i = x;i >= 0;i--){
            if(i == 0){
                boxYminus = new Cordinates(NO_BOX);
            }
            if(isTileOccupied(new Cordinates(i, y))){
                boxYminus = new Cordinates(i, y);
                break;
            }
        }
       }

*/

    public boolean checkAlliances(Cordinates desiredCords){
        if(getAlliance() != getTheBoard()
                .getTile(getTheBoard().getTileNumber(desiredCords))
                .getPiece()
                .getAlliance())
            return false;
        return true;
    }


    public boolean doubleCheck(Cordinates desiredCords){

        if(getTheBoard().isTileOccupied(desiredCords)){
            if(!checkAlliances(desiredCords)) return true;
        }
        return false;
    }


    public ImageIcon resizePieceLabel(ImageIcon pieceIcon) {
        ImageIcon pieceImg = new ImageIcon(pieceIcon.getImage().getScaledInstance(this.width, this.height, 4));
        return pieceImg;
    }

    public void setTheBoard(Board board){
        this.theBoard = board;
    }

    public boolean getAlliance(){
        return alliance;
    }

    public String getPieceName(){
        return name;
    }

    public Board getTheBoard() {
        return theBoard;
    }

    // todo impliment for all the classes.
    public abstract ArrayList<Cordinates> getMovesForKing();

    public abstract JLabel getPieceLabel();

}
