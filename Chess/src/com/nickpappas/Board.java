package com.nickpappas;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.nickpappas.Pieces.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Board extends JFrame implements MouseListener, MouseMotionListener {

    private final int ROWS = 8;
    private final int COLS = 8;
    private JPanel pnl = new JPanel(new GridLayout(8, 8, 0, 0));
    private JPanel[] pnl1 = new JPanel[64];
    private ArrayList<Tile> boardTiles = new ArrayList<>();
    private boolean isWhite = false;
    private final Color darkerClr = new Color(156, 112, 42, 196);   // Dark Color for board
    private final Color lighterClr = new Color(224, 212, 164, 237); // Lighter Color for board
    private final Color clrForHighlight = new Color(168, 184, 58, 237); // Color for highligting
    private final int[][] gameData= new int[8][8];
    private ArrayList<Rook> rook = new ArrayList<>();
    private ArrayList<Knight> knight = new ArrayList<>();
    private ArrayList<Bishop> bishop = new ArrayList<>();
    private ArrayList<Queen> queen = new ArrayList<>();
    private ArrayList<King> king = new ArrayList<>();
    private ArrayList<Pawn> pawn = new ArrayList<>();
    private final ImageIcon boardIcon= new ImageIcon(this.getClass().getResource("Pieces/BoardResources/board.png"));
    private final ImageIcon emptyPieceIcon = new ImageIcon(this.getClass().getResource("Pieces/Resources/emptyPiece.png"));

    // King stuff
    private Cordinates[] KINGS_CORDS = {new Cordinates(7, 4), new Cordinates(0,4)};
    private int[] KING_STATE = {0,0}; // 0 natural , 1 roua, 2 roua-mat


    // Gui stuff
    private JLabel movingLabel;
    private JPanel sourcePanel;
    private JPanel oldPanel;
    private boolean isThereHighlight = false;
    private Color oldClr;
    private int tileClicked = -1;
    private int oldTileClicked = -1;


    public Board() {
        /*
        super("Chess");
        setDefaultCloseOperation(3);
        createPieces();
        //drawCheckBoard();
        createAndDrawTiles();
       // this.moves = new Move(boardTiles);
        pack();
        add(pnl);

         */
    }
    public Board getBoard(){
        return this;
    }

    public void createBoard(){
        setTitle("Chess");
        setDefaultCloseOperation(3);
        createPieces();
        //drawCheckBoard();
        createAndDrawTiles();
        //this.KINGS_CORDS[0] = new Cordinates(7,4);
        //this.KINGS_CORDS[1] = new Cordinates(0,4);
        // this.moves = new Move(boardTiles);
        pack();
        add(pnl);
    }


    private ImageIcon resizePieceLabel(ImageIcon pieceIcon) {
        ImageIcon pieceImg = new ImageIcon(pieceIcon.getImage().getScaledInstance(700, 700, 4));
        return pieceImg;
    }

    private void createAndDrawTiles(){
        Color newColor;
        int count = 0;
        for(int i=0;i<(ROWS * COLS);i++){
            count++;
            if(count % 2 == 0 && i % ROWS == 0){
                newColor = darkerClr;
                count++;
            }else if(i % ROWS == 0){
                newColor = lighterClr;
                count++;
            }else if(count % 2 == 0){
                newColor = lighterClr;
            }else{
                newColor = darkerClr;
            }
            boardTiles.add(i, new Tile(newColor, new JPanel(), new EmptyPiece("empty"," _", new Cordinates(i/8,i % 8))));
            //System.out.println("("+i/8+","+i%8+")");
            this.pnl.add(getTile(i).getTilePanel());
        }

        // Giving to the tiles names/position indentifiers.
        char A = '`'; // One char before "a" is " ` "
        int counter = 0;
        for(int i=0;i<ROWS;i++){
            A++;

            // Gia na onomastous swsta ta tiles prepei
            // na mpoun ta grammata san sthles kai oxi grammes

            /*
                TILES NAMED AS SHOWN BELOW
                    0    1    2    3    4    5    6    7
                0 [a8] [b8] [c8] [d8] [e8] [f8] [g8] [h8]
                1 [a7] [b7] [c7] [d7] [e7] [f7] [g7] [h7]
                2 [a6] [b6] [c6] [d6] [e6] [f6] [g6] [h6]
                3 [a5] [b5] [c5] [d5] [e5] [f5] [g5] [h5]
                4 [a4] [b4] [c4] [d4] [e4] [f4] [g4] [h4]
                5 [a3] [b3] [c3] [d3] [e3] [f3] [g3] [h3]
                6 [a2] [b2] [c2] [d2] [e2] [f2] [g2] [h2]
                7 [a1] [b1] [c1] [d1] [e1] [f1] [g1] [h1]

                                            CORDINATES OF EACH TILE
                0-> (0,0) 1-> (0,1) 2-> (0,2) 3-> (0,3) 4-> (0,4) 5-> (0,5) 6-> (0,6) 7-> (0,7)
                8-> (1,0) 9-> (1,1) 10-> (1,2) 11-> (1,3) 12-> (1,4) 13-> (1,5) 14-> (1,6) 15-> (1,7)
                16-> (2,0) 17-> (2,1) 18-> (2,2) 19-> (2,3) 20-> (2,4) 21-> (2,5) 22-> (2,6) 23-> (2,7)
                24-> (3,0) 25-> (3,1) 26-> (3,2) 27-> (3,3) 28-> (3,4) 29-> (3,5) 30-> (3,6) 31-> (3,7)
                32-> (4,0) 33-> (4,1) 34-> (4,2) 35-> (4,3) 36-> (4,4) 37-> (4,5) 38-> (4,6) 39-> (4,7)
                40-> (5,0) 41-> (5,1) 42-> (5,2) 43-> (5,3) 44-> (5,4) 45-> (5,5) 46-> (5,6) 47-> (5,7)
                48-> (6,0) 49-> (6,1) 50-> (6,2) 51-> (6,3) 52-> (6,4) 53-> (6,5) 54-> (6,6) 55-> (6,7)
                56-> (7,0) 57-> (7,1) 58-> (7,2) 59-> (7,3) 60-> (7,4) 61-> (7,5) 62-> (7,6) 63-> (7,7)
            */

            // GIVING TILES CORDS
            for(int j=0;j<COLS;j++){
               getTile((8*i) + j).setTileCords(new Cordinates(i, j));

            }
            // NAMING THEM
            for(int j=(56+i);j>=i;j=j-8){
                if(counter == 8) counter = 0;
                counter++;
                getTile(j).setTilePosName(A+""+(counter));
            }
        }
        // ADDING PIECES
        // FIRST THE BLACKS THEN THE WHITES

        ////////////////////////////////////////////////////////////////////////////////////
        // Adding Blacks
        int j = 8;
        for(int i = 8; i < 16; ++i) {
            getTile(i).setPiece(this.pawn.get(j));
            ++j;
        }
        getTile(0).setPiece(this.rook.get(2));
        getTile(1).setPiece(this.knight.get(2));
        getTile(2).setPiece(this.bishop.get(2));
        getTile(3).setPiece(this.queen.get(1));
        getTile(4).setPiece(this.king.get(1));
        getTile(5).setPiece(this.bishop.get(3));
        getTile(6).setPiece(this.knight.get(3));
        getTile(7).setPiece(this.rook.get(3));


        // Adding whites
        j = 0;
        for(int i = 48; i < 56; ++i) {
            getTile(i).setPiece(this.pawn.get(j));
            ++j;
        }
        getTile(56).setPiece(this.rook.get(0));
        getTile(57).setPiece(this.knight.get(0));
        getTile(58).setPiece(this.bishop.get(0));
        getTile(59).setPiece(this.queen.get(0));
        getTile(60).setPiece(this.king.get(0));
        getTile(61).setPiece(this.bishop.get(1));
        getTile(62).setPiece(this.knight.get(1));
        getTile(63).setPiece(this.rook.get(1));
        ////////////////////////////////////////////////////////////////////////////////////

        // Adding mouse listeners
        for(int i=0;i<(ROWS * COLS);i++){
            getTile(i).getTilePanel().addMouseListener(new MouseHandler());
        }

    }

    public void updateBoardState(){

        this.validate();
        this.repaint();

        System.out.println("\n\n\tNEW GAME STATE");
        for(int i = 0; i < (ROWS * COLS);i++){
           if(i % 8 == 0) System.out.println();
           System.out.print(getTile(i).getPiece().getPieceSymbol()+" ");
        }
        System.out.println("\n");

    }

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

    public void swapPieces(){


        // Krata to palio piece pou pathses
        // Krata to piece pou 8a pas
        Piece firstPiece  = getTile(oldTileClicked).getPiece();

        Piece secondPiece = getTile(tileClicked).getPiece();
        Cordinates tempCords = firstPiece.getPieceCords();   // Krata ta cords tou prwtou Piece
        firstPiece.updatePiecePos(secondPiece.getPieceCords());  // ala3e ta cords tou prwtou Piece me tou deuterou.

        // An to piece einai o basilias kane update to position
        if(firstPiece.getPieceName().equals("White King")){
            KINGS_CORDS[0] = firstPiece.getPieceCords();
            // Unique cases aka Castling
            if(KINGS_CORDS[0].getX() == 7 && KINGS_CORDS[0].getY() == 2){
                getTile(59).setPiece(getTile(56).getPiece());
                getTile(59).getPiece().updatePiecePos(new Cordinates(7, 3));
                getTile(56).setPiece(new EmptyPiece("empty"," _", new Cordinates(7, 0)));
            }else if(KINGS_CORDS[0].getX() == 7 && KINGS_CORDS[0].getY() == 6){
                getTile(61).setPiece(getTile(63).getPiece());
                getTile(61).getPiece().updatePiecePos(new Cordinates(7, 5));
                getTile(63).setPiece(new EmptyPiece("empty"," _", new Cordinates(7, 7)));
            }
        }else if(firstPiece.getPieceName().equals("Black King")){
            KINGS_CORDS[1] = firstPiece.getPieceCords();

            // Unique cases aka Castling
            if(KINGS_CORDS[1].getX() == 0 && KINGS_CORDS[1].getY() == 2){
                getTile(3).setPiece(getTile(0).getPiece());
                getTile(3).getPiece().updatePiecePos(new Cordinates(0, 3));
                getTile(0).setPiece(new EmptyPiece("empty"," _", new Cordinates(0, 0)));
            }else if(KINGS_CORDS[1].getX() == 0 && KINGS_CORDS[1].getY() == 6){
                getTile(5).setPiece(getTile(7).getPiece());
                getTile(5).getPiece().updatePiecePos(new Cordinates(0, 5));
                getTile(7).setPiece(new EmptyPiece("empty"," _", new Cordinates(0, 7)));
            }
        }else{
            // An den einai King to piece prepei na doume se ti
            // state meta apo ka8e khnhsh brisketai o King mas.
            updateKingsStates(firstPiece.getAlliance());
        }

        // Sto palio tile dimiourgise ena new EmptyPiece sthn palia 8esh tou prwtou Piece kai san cords dwstou ta Cords tou paliou
        getTile(oldTileClicked).setPiece(new EmptyPiece("empty"," _", tempCords));
        // Sto neo tile bale to Piece tou prwtou tile pou pathses
        getTile(tileClicked).setPiece(firstPiece);

    }
    public void test(){
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        if (x == 1) {
            System.out.println("\nLol");
            this.pnl1[0].remove((this.rook.get(0)).getPieceLabel());
            this.pnl1[23].add((this.rook.get(0)).getPieceLabel());
        }

    }

    // TODO maybe impliment a bitMap
    public boolean isTileOccupied(Cordinates desiredCords){




        if(getTile(getTileNumber(desiredCords))
                .getPiece().getPieceName().equals("empty")) return false;

        //String ep = getTile(getTileNumber(desiredCords)).getPiece().getPieceName();
        //System.out.print("\nOCCUPIED: ("+desiredCords.getX()+","+desiredCords.getY()+") Name of tile: "+ep);
        return true;
    }


    public Tile getTile(int i){
        return this.boardTiles.get(i);
    }

    public Tile getTile(Cordinates tileCords){
        return this.boardTiles.get(getTileNumber(tileCords));
    }


    public int getTileNumber(Cordinates tileCords){
        return ((8 * tileCords.getX()) + tileCords.getY());
    }

    // TODO Reset somehow your king_state to natural
    //      Maybe move KING_STATE to king.
    private void updateKingsStates(boolean isWhite){
        PossibleMoves movesGetter = new PossibleMoves(this);
        Piece king;
        if(isWhite) king = getTile(KINGS_CORDS[0]).getPiece();
        else king = getTile(KINGS_CORDS[1]).getPiece();

        // Check if your king is getting threatened
        ArrayList<Cordinates> enemyCords = movesGetter.getEnemyMoves(king);
        for(Cordinates enCords : enemyCords)
            if (king.getPieceCords().getX() == enCords.getX() && king.getPieceCords().getY() == enCords.getY())
                if (king.getAlliance()) KING_STATE[0] = 1;
                else KING_STATE[1] = 1;


        // TODO kai den exei tropo na to stamathsei SOS!!
        // An den exei validMoves KAI apileite einai mat.
        if(KING_STATE[0] == 1 && king.getPieceValidMoves().isEmpty()) { // Kai den exei kanenan na ton "swsei"
            ArrayList<Cordinates> allyMoves = movesGetter.getProctiveMoves(king);
            if(allyMoves.isEmpty()){
                for(int i=0;i < 40;i++) System.out.println("WHITE LOST");
                KING_STATE[0] = 2;
            }
        }else if(KING_STATE[1] == 1 && king.getPieceValidMoves().isEmpty()) {
            ArrayList<Cordinates> allyMoves = movesGetter.getProctiveMoves(king);
            System.out.println("GGGGGGGGGGGGGGGGGGGGGG");
            if(allyMoves.isEmpty()){
                for(int i=0;i < 40;i++) System.out.println("BLACK LOST");
                KING_STATE[1] = 2;
            }else{
                System.out.println("HEHE XDDDDDDDDDDDDDD");
            }
        }
    }

    public int getKING_STATE(boolean isWhite){
        if(isWhite) return KING_STATE[0];
        else        return KING_STATE[1];
    }
    public Cordinates getKingsCords(boolean isWhite){
        if(isWhite) return KINGS_CORDS[0];
        else        return KINGS_CORDS[1];
    }



    private void createPieces() {
        this.rook.add(0, new Rook(true, "White Rook", " r", new Cordinates(7, 0),this));
        this.rook.add(1, new Rook(true, "White Rook", " r", new Cordinates(7, 7),this));
        this.rook.add(2, new Rook(false, "Black Rook", ".r", new Cordinates(0, 0),this));
        this.rook.add(3, new Rook(false, "Black Rook", ".r", new Cordinates(0, 7),this));
        this.knight.add(0, new Knight(true, true, "White Knight Right"," k", new Cordinates(7, 1),this));
        this.knight.add(1, new Knight(true, false, "White Knight Left", " k", new Cordinates(7, 6),this));
        this.knight.add(2, new Knight(false, true, "Black Knight Right", ".k", new Cordinates(0, 1),this));
        this.knight.add(3, new Knight(false, false, "Black Knight Left", ".k", new Cordinates(0, 6),this));
        this.bishop.add(0, new Bishop(true, "White Bishop", " b", new Cordinates(7, 2),this));
        this.bishop.add(1, new Bishop(true, "White Bishop", " b", new Cordinates(7, 5),this));
        this.bishop.add(2, new Bishop(false, "Black Bishop", ".r", new Cordinates(0, 2),this));
        this.bishop.add(3, new Bishop(false, "Black Bishop", ".r", new Cordinates(0, 5),this));
        this.queen.add(0, new Queen(true, "White Queen", " Q", new Cordinates(7, 3),this));
        this.queen.add(1, new Queen(false, "Black Queen", ".Q", new Cordinates(0, 3),this));
        this.king.add(0, new King(true, "White King", " K", new Cordinates(7, 4),this));
        this.king.add(1, new King(false, "Black King", ".K", new Cordinates(0, 4),this));

        int j = 0, k = 0;
        for(int i = 0; i < 16; ++i) {
            if (i < 8) {
                this.pawn.add(i, new Pawn(true, "White Pawn", " p", new Cordinates(6, j++),this));
            } else {
                this.pawn.add(i, new Pawn(false, "Black Pawn", ".p", new Cordinates(1, k++),this));
            }
        }

    }















    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
