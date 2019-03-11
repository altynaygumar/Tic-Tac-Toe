import javax.swing.*;

public class Main  extends JFrame {



    public static void main(String args[]){
        Main main= new Main();
        Game game =new Game();
        main.setTitle("Tik Tak Toe");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocation(400,400);
        main.setSize(500,500);
        main.add(game);
        main.setVisible(true);
        main.setResizable(false);





    }
}