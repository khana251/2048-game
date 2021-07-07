/**
 * @File: View.java
 * @Author: Amaan Ahmad Khan - khana251
 * @Date: April.12th, 2021
 * @Description: A View module to Display the game as it goes
 */
import java.util.Scanner;

public class View {

    /**
     * @brief Main method
     * @details The part of the code that is run and handles user IO
     */
    public static void main(String[] args) {
        Model game = new Model();
        game.displayBoard();
        while(!game.youLoose() || !game.youWin()){
            System.out.println("Enter direction (w,a,s,d) or e(exit) or r(restart)");
            Scanner sc = new Scanner(System.in);
            String direction = sc.nextLine();
            if (direction.equalsIgnoreCase("w")){
                game.moveUp();
                game.displayBoard();
            }
            else if (direction.equalsIgnoreCase("a")){
                game.moveLeft();
                game.displayBoard();
            }
            else if (direction.equalsIgnoreCase("s")){
                game.moveDown();
                game.displayBoard();
            }
            else if (direction.equalsIgnoreCase("d")){
                game.moveRight();
                game.displayBoard();
            }
            else if (direction.equalsIgnoreCase("r")){
                game.restart();
                game.displayBoard();
            }
            else if (direction.equalsIgnoreCase("e")){
                System.exit(0);
            }
            else{
                System.out.println("Please enter one of w,a,s,d or r to restart or e to exit  ");
            }
        }
    }

}
