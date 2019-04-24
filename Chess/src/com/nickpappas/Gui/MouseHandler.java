package com.nickpappas.Gui;

import com.nickpappas.Board;
import com.nickpappas.Cordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class MouseHandler extends MouseAdapter {

    private final int ROWS = 8, COLS = 8;
    private Board board;

    public MouseHandler(Board board){
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Board board = startBoard.getBoard();
        board.setSourcePanel((JPanel)e.getComponent());
        board.setTileClicked(matchPanels());

        // Kanei to component JPanel gia na tre3ei thn .getComponent me (int index)
        // Epeita to kanei cast se JLabel giati to .getComponent gurnaei profanws Component
        if(isLeftMouseButton(e)){

            // Ama uparxei highlighted hdh kai pathsame pali sto idio panel tote
            // alla3e to xrwma sto palio xrwma kai kanei to highlight false.
            // Alliws an uparxei highlight alla to panel pou pathsame den einai to idio
            // tote mhn kaneis tpt/typwse sfalma
            // kai den yparxei highlight tote apla krata to palio xrwma kai alla3e xrwma
            // kai to highlight kanto true.
            if(board.isThereHighlight() && board.getSourcePanel() == board.getOldPanel()) {
                board.getSourcePanel().setBackground(board.getOldClr());
                board.setThereHighlight(false);
            }else if(board.isThereHighlight()){
                System.out.println("Invalid click.");
            }else{
                // Save which panel was clicked
                board.setOldTileClicked(matchPanels());
                leftMouseCickUpdates();
            }
        }else if(isRightMouseButton(e)){
            if(board.isThereHighlight()){
                // Kanei remove ola comps pou uparxoun sto panel
                // kai epeita pros8eth to moving label.
                makeMove();
            }

        }

    }

    public void makeMove(){
        ArrayList<Cordinates> validMoves = board.getTile(board.getOldTileClicked()).getPiece().getPieceValidMoves();
        int y = board.getTile(board.getTileClicked()).getTileCords().getY();
        int x = board.getTile(board.getTileClicked()).getTileCords().getX();

        System.out.println("\n\t\t\t\tCLICKED ("+x+","+y+")");
        System.out.println("VALID MOVES ");

        for(Cordinates cord : validMoves){
            System.out.println("("+cord.getX()+","+cord.getY()+")");
            if(cord.getY() == y && cord.getX() == x){
                rightMouseClickUpdates();
                break;
            }
        }
    }

    // keep track which panel got clicked
    private int matchPanels(){
        for(int i=0;i<(ROWS * COLS);i++)
            if(board.getTile(i).getTilePanel().equals(board.getSourcePanel()))
                return  i;
        return -1;
    }

    private void leftMouseCickUpdates(){
        board.setOldClr(board.getSourcePanel().getBackground());
        board.getSourcePanel().setBackground(board.getClrForHighlight());
        board.setOldPanel(board.getSourcePanel());
        board.setThereHighlight(true);
        board.setMovingLabel((JLabel)board.getSourcePanel().getComponent(0));
        board.updateBoardState();
    }

    private void rightMouseClickUpdates(){
        board.swapPieces(board.getOldTileClicked(), board.getTileClicked()); // Ama DEN einai occupied to tile apla kanto swap
        board.getSourcePanel().removeAll();
        board.getSourcePanel().add(board.getMovingLabel());
        board.getOldPanel().setBackground(board.getOldClr());
        board.getOldPanel().add(new JLabel(board.getEmptyPieceIcon()));
        board.setThereHighlight(false);
        board.updateBoardState();
    }

    // Old MouseHandler class that was inside Board
    /*
    public class MouseHandler extends MouseAdapter {

            @Override
            public void mousePressed(MouseEvent e) {
                sourcePanel =(JPanel)e.getComponent();

                // keep track which panel got clicked
                for(int i=0;i<(ROWS * COLS);i++) {
                    if(getTile(i).getTilePanel().equals(sourcePanel)){

                        //int y = boardTiles.get(i).getTileCords().getY();
                        //int x = boardTiles.get(i).getTileCords().getX();
                        //System.out.println("Panel Cords: ("+x+","+y+")");
                        tileClicked = i;
                        break;
                    }
                }

                // Kanei to component JPanel gia na tre3ei thn .getComponent me (int index)
                // Epeita to kanei cast se JLabel giati to .getComponent gurnaei profanws Component
                if(isLeftMouseButton(e)){

                    // Ama uparxei highlighted hdh kai pathsame pali sto idio panel tote
                    // alla3e to xrwma sto palio xrwma kai kanei to highlight false.
                    // Alliws an uparxei highlight alla to panel pou pathsame den einai to idio
                    // tote mhn kaneis tpt/typwse sfalma
                    // kai den yparxei highlight tote apla krata to palio xrwma kai alla3e xrwma
                    // kai to highlight kanto true.

                    if(isThereHighlight && sourcePanel == oldPanel) {
                        sourcePanel.setBackground(oldClr);
                        isThereHighlight = false;
                    }else if(isThereHighlight && sourcePanel != oldPanel){
                        System.out.println("Invalid click.");
                    }else if(!isThereHighlight){

                        // Save which panel was clicked
                        for(int i=0;i<(ROWS * COLS);i++) {
                            if(getTile(i).getTilePanel().equals(sourcePanel)){
                                oldTileClicked = i;
                                break;
                            }
                        }
                        //PossibleMoves allMoves = new PossibleMoves(getBoard());
                        //allMoves.getEnemyMoves(true);

                        oldClr = sourcePanel.getBackground();
                        sourcePanel.setBackground(clrForHighlight);
                        oldPanel = sourcePanel;
                        isThereHighlight = true;

                        movingLabel = (JLabel)sourcePanel.getComponent(0);
                        updateBoardState();

                    }
                }else if(isRightMouseButton(e)){

                    if(isThereHighlight){
                        // Kanei remove ola comps pou uparxoun sto panel
                        // kai epeita pros8eth to moving label.

                        ArrayList<Cordinates> validMoves = getTile(oldTileClicked)
                                                                .getPiece()
                                                                .getPieceValidMoves();

                        int y = getTile(tileClicked).getTileCords().getY();
                        int x = getTile(tileClicked).getTileCords().getX();
                        System.out.println("\n\t\t\t\tCLICKED ("+x+","+y+")");
                        System.out.println("VALID MOVES ");
                        for(Cordinates cord : validMoves){
                            System.out.println("("+cord.getX()+","+cord.getY()+")");
                            if(cord.getY() == y && cord.getX() == x){

                                 swapPieces(); // Ama DEN einai occupied to tile apla kanto swap
                                 sourcePanel.removeAll();
                                 sourcePanel.add(movingLabel);
                                 oldPanel.setBackground(oldClr);
                                 oldPanel.add(new JLabel(emptyPieceIcon));
                                 isThereHighlight = false;
                                 updateBoardState();
                                 break;
                            }
                        }


                    }

                }

            }

    }

     */
}
