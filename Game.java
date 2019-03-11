import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public  class Game extends JPanel implements ActionListener {

    JPanel tpanel = new JPanel();
    JPanel cpanel = new JPanel();
    JPanel bpanel = new JPanel();

    JButton[][] buttonArray = new JButton[3][3];

    JButton buttonPX = new JButton("X");
    JButton buttonP0 = new JButton("0");
    JButton buttonRS = new JButton("Restart");

    JLabel label =new JLabel("Choose player or start play");



    public Point computerMove;

    public String startPlayer = "X";

    public String a[][] = new String[3][3];
    //int min = Integer.MAX_VALUE;
    //int max = Integer.MIN_VALUE;

    public static final String NO_PLAYER = "";//TAKE X
    public static  String PLAYER_X = "X";
    public static  String PLAYER_O = "0";
    public boolean booleanPX =false ;
    public boolean booleanP0 =false;
    public boolean flagfill = false;
    public int counterX=0;
    public int counterY=0;
    String[][] arrays =new String[3][3];


    public Game() {
        setBackground(Color.black);
        setLayout(new BorderLayout());
        tpanel.setBackground(Color.yellow);
        cpanel.setBackground(Color.green);
        bpanel.setBackground(Color.blue);

        add(tpanel, BorderLayout.NORTH);
        add(cpanel, BorderLayout.CENTER);
        add(bpanel, BorderLayout.SOUTH);

        tpanel.setLayout(new GridLayout(2, 2));
        tpanel.add(buttonPX);
        tpanel.add(buttonP0);
        tpanel.add(label);
        buttonPX.addActionListener(this);
        buttonP0.addActionListener(this);
        cpanel.setLayout(new GridLayout(3, 3));
        String s = "" ;//TAKE X

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttonArray[i][j] = new JButton(s);
                buttonArray[i][j].addActionListener(this);
                cpanel.add(buttonArray[i][j]);
            }
        }

        bpanel.add(buttonRS);
        buttonRS.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==buttonRS){
            restart();}

        if(e.getSource()==buttonPX){
            booleanPX = true;
            PLAYER_X ="0";
            PLAYER_O ="X";
            buttonP0.setEnabled(false);
            buttonPX.setBackground(Color.blue);
            label.setText("You choose - X, Computer - 0 ");
        }

        if(e.getSource()==buttonP0){
            booleanP0 = true;
            PLAYER_X ="X";
            PLAYER_O ="0";
            buttonPX.setEnabled(false);
            buttonP0.setBackground(Color.blue);
            label.setText("You choose - 0, Computer - X ");}




        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(e.getSource()==buttonArray[i][j]){
                    if(booleanP0 == false  && booleanPX == false){
                        PLAYER_X = "0";
                        PLAYER_O = "X";
                        buttonPX.setEnabled(false);
                        buttonP0.setEnabled(false);
                        label.setText("You - X, Computer - 0 ");

                    }


                    buttonArray[i][j].setText(PLAYER_O);
                    copyArray();
                    gamefiled(0,PLAYER_X);
                    buttonArray[computerMove.x][computerMove.y].setText(PLAYER_X);
                    if (playerWonB("X")){
                        JOptionPane.showMessageDialog(null, "Player - X is WIN!");
                        counter("X");
                        clear();}
                    // System.out.println("Player X is WIN!");
                    else if (playerWonB("0")){
                        JOptionPane.showMessageDialog(null, "Player - 0 is WIN!");
                        // System.out.println("Player 0 is WIN!");
                        counter("0");
                        clear();
                    }
                    else if (getAvailableCellsB().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "DRAW");
                        // System.out.println("DRAW");
                        clear();
                    }

                } }}



    }

    public void counter(String player){
        if (player=="X"){
            counterX++;
            buttonPX.setText("X - "+counterX);
        }

        if (player == "0") {
            counterY++;
            buttonP0.setText("0 - "+counterY);}
    }


    public void clear(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttonArray[i][j].setText(""); }}//TAKE ?


    }


    public void restart(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttonArray[i][j].setText(""); }}//TAKE ?

        buttonPX.setEnabled(true);
        buttonP0.setEnabled(true);
        label.setText("Choose player or start play");
        buttonPX.setText("X");
        buttonP0.setText("0");
        buttonPX.setBackground(null);
        buttonP0.setBackground(null);
        booleanPX = false;
        booleanP0 =false;
        counterY=0;
        counterX=0;
    }


    public void copyArray(){
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                arrays[i][j] =buttonArray[i][j].getText();
                //System.out.print(arrays[i][j]+" ");
            }
          //  System.out.println();
        }

    }
    public java.util.List<Point> getAvailableCells(){
        List<Point> availableCells = new ArrayList<>();

        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (arrays[i][j] == "") {//TAKE ?
                    availableCells.add(new Point(i,j));
                }
            }

        }
        return availableCells;
    }
    public java.util.List<Point> getAvailableCellsB(){
        List<Point> availableCellsB = new ArrayList<>();

        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (buttonArray[i][j].getText().equals("")) {//TAKE ?
                    availableCellsB.add(new Point(i,j));
                }
            }

        }
        return availableCellsB;
    }


    public boolean playerWonB(String player){
      //  System.out.println(startPlayer +" =playerWonB  Entered");

        if ((buttonArray[0][0].getText().equals(buttonArray[1][1].getText())
                && buttonArray[0][0].getText().equals(buttonArray[2][2].getText())
                && buttonArray[0][0].getText().equals(player)) ||
                (buttonArray[0][2].getText().equals(buttonArray[1][1].getText())
                        && buttonArray[0][2].getText().equals(buttonArray[2][0].getText())
                        && buttonArray[0][2].getText().equals(player) )){
            return true;  }

        for (int i = 0; i < 3; i++){
            if ((buttonArray[i][0].getText().equals(buttonArray[i][1].getText()) && buttonArray[i][0].getText().equals(buttonArray[i][2].getText())
                    && buttonArray[i][0].getText().equals(player)) ||
                    (buttonArray[0][i].getText().equals(buttonArray[1][i].getText()) && buttonArray[0][i].getText().equals(buttonArray[2][i].getText()) && buttonArray[0][i].getText().equals(player))) {
                //JOptionPane.showMessageDialog(null, "Player - " + player + " is WIN!");
                return true;

            }}
        //   System.out.println(startPlayer +" = Game NOT WON");

        return false; }

    public boolean playerWon(String player){
       // System.out.println(startPlayer +" = Entered");

        if ((arrays[0][0]==arrays[1][1]
                && arrays[0][0]==arrays[2][2]
                && arrays[0][0]==player) ||
                (arrays[0][2]==arrays[1][1]
                        && arrays[0][2]==arrays[2][0]
                        && arrays[0][2]==player )){
            return true;  }

        for (int i = 0; i < 3; i++){
            if ((arrays[i][0]==arrays[i][1] && arrays[i][0]==arrays[i][2] && arrays[i][0]==player) ||
                    (arrays[0][i]==arrays[1][i] && arrays[0][i]==arrays[2][i] && arrays[0][i]==player)) {
                //JOptionPane.showMessageDialog(null, "Player - " + player + " is WIN!");
                return true;

            }}
        //   System.out.println(startPlayer +" = Game NOT WON");

        return false; }



    public int gamefiled(int depth, String turn){
        if (playerWon(PLAYER_O)){ return -1; }
        if (playerWon(PLAYER_X)){ return 1;}
        List<Point> availableCells = getAvailableCells();
        if (availableCells.isEmpty()){
            return 0;}

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i<availableCells.size(); i++) {
            Point point = availableCells.get(i);
           // System.out.println(" point = " + point);

            if(turn == PLAYER_X){
                arrays[point.x][point.y]=PLAYER_X;
                //buttonArray[point.x][point.y].setText("0");
              //  display();

                int currentScore = gamefiled(depth+1, PLAYER_O);
                max = Math.max(currentScore, max);

                if (depth == 0){
                   // System.out.println(" 1 Computer score for position " + point + " = " + currentScore);
                    }

                if (currentScore >= 0){
                    if (depth == 0) {
                        computerMove = point;
                     //   System.out.println("2 ENDED");



                    }}
                if ( currentScore == 1) {
                    arrays[point.x][point.y] = NO_PLAYER;
                   // System.out.println("3 ENDED");

                    break;

                }
                if (i == availableCells.size()-1 && max<0){
                    if (depth == 0) {
                       // System.out.println("4 ENDED");

                        computerMove = point;
                    }}
            }

            if(turn==PLAYER_O){
                arrays[point.x][point.y]=PLAYER_O;
               // display();
                int currentScore = gamefiled(depth + 1, PLAYER_X);
                min = Math.min(currentScore, min);

                if (min == -1){
                    arrays[point.x][point.y] = NO_PLAYER;
                   // System.out.println("5 ENDED");

                    break;
                }
            }
            arrays[point.x][point.y] = NO_PLAYER;
            //  System.out.println( "availableCells = " + arrays[point.x][point.y]);

        }
        return turn == PLAYER_X ? max : min;
    }



//    public void   display() {
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//               // System.out.print(arrays[i][j] + " ");
//            }
//          //  System.out.println();
//
//        }
//
//    }

}