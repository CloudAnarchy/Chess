package com.nickpappas;

import com.nickpappas.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Tile {

    private Color tileClr;  // Dark Color for board new Color(56, 147, 52, 237);
                            // For Lighter Color.White
                            // For Highlight Color.Orange
    private String tilePosName;
    private Piece piece;
    private JPanel tilePanel;
    private Cordinates tileCords;
   // private Piece piece;


    public Tile(Color tileClr, JPanel tilePanel){

        this.tilePanel = tilePanel;
        // When you add a tile you color it.
        this.tileClr = tileClr;
        this.tilePanel.setBackground(tileClr);

        // When you adding a tile you give it a piece.
        // this.piece = piece;
        // this.tilePanel.add(this.piece.getPieceLabel());

        //setSize(new Dimension());
    }

    public Tile(Color tileClr, JPanel tilePanel, Piece piece){

        this.tilePanel = tilePanel;
        // When you add a tile you color it.
        this.tileClr = tileClr;
        this.tilePanel.setBackground(tileClr);

        // When you adding a tile you give it a piece.
        this.piece = piece;
        this.tilePanel.add(this.piece.getPieceLabel());

        //setSize(new Dimension());
    }

    public Tile(String tilePosName){
        this.tilePosName = tilePosName;
    }
/*
    public boolean checkIfTileOccupied(){
        if(this.piece.getPieceLabel() != null){
            return true;
        }else{
            return false;
        }
    }
*/
    public void removePieceFromTile(){
        this.tilePanel.remove(this.tilePanel.getComponent(0));
        this.tilePanel.revalidate();
        this.tilePanel.repaint();
    }
    public void setTileCords(Cordinates tileCords) {
        this.tileCords = tileCords;
    }
    public void setTileClr(Color tileClr) {
        this.tileClr = tileClr;
        this.tilePanel.setBackground(tileClr);
    }
    public void setTilePosName(String tilePosName) {
        this.tilePosName = tilePosName;
    }
    public void setTilePanel(JPanel tilePanel) {
        this.tilePanel = tilePanel;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
        // You have to remove the empty Jlabel firt
        // then add the piece you want
        this.tilePanel.removeAll();
        this.tilePanel.add(piece.getPieceLabel());
    }

    public Cordinates getTileCords() {
        return tileCords;
    }
    /*
    public int getTileNumberWithOutCords(){
        int tileN = (8 * this.tileCords.getX()) + this.tileCords.getY();
        return tileN;
    }

     */

    public Piece getPiece() {
        return piece;
    }

    public JPanel getTilePanel() {
        return tilePanel;
    }


    public JLabel getPieceLabelOnTile(){
        //JLabel label = (JLabel)getComponent(0);
        if(this.piece.getPieceLabel() != null){
            return piece.getPieceLabel();
        }else{
            return null;
        }
    }

    public String getTilePosName(){
        return tilePosName;
    }
    public Color getTileClr(){
        return tileClr;
    }
}
