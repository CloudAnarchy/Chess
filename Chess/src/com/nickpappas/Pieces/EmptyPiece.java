package com.nickpappas.Pieces;

import com.nickpappas.Cordinates;

import javax.swing.*;
import java.util.ArrayList;

public class EmptyPiece extends Piece{


    private final ImageIcon emptyPieceIcon = new ImageIcon(this.getClass().getResource("Resources/emptyPiece.png"));
    private final JLabel emptyPieceLabel = new JLabel(emptyPieceIcon);


    public EmptyPiece(String name, String pieceSymbol, Cordinates pieceCords){
          super(name, pieceSymbol, pieceCords);
    }

    @Override
    public JLabel getPieceLabel(){

        return emptyPieceLabel;
    }


    @Override
    public ArrayList<Cordinates> getMovesForKing(){
        return null;
    }

    @Override
    public ArrayList<Cordinates> getPieceValidMoves(){
        return null;
    }
}
