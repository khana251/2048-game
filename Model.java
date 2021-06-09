/**
 * @File: Model.java
 * @Author: Amaan Ahmad Khan - khana251
 * @Date: April.12th, 2021
 * @Description: A Model module to store the game and game status
 */

import java.util.ArrayList;
import java.util.Random;

public class Model{



  ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<>();

  /**
   * @brief constructor
   * @details generates a board with 0s in all positions and 2 or 4 in two random positions
   */
  public Model(){
    for(int i=0; i<4; i++){
      ArrayList<Integer> row = new ArrayList<>();
      for(int j=0; j<4; j++){
        row.add(0);
      }
      gameBoard.add(row);
    }

    addTwoOrFour();
    addTwoOrFour();
  }

  /**
   * @brief displayBoard
   * @details Displays the game board in a stylized form
   */

  public void displayBoard() {
    System.out.println("- - - - - - - - - -");
    for(int i=0; i<4; i++){
      for(int j=0; j<4; j++){
        System.out.print(gameBoard.get(i).get(j)+" " + " | ");
      }
      System.out.println("");
      System.out.println("- - - - - - - - - -");
    }
  }

  /**
   * @brief Add 2s or 4s or a 2 and a 4
   * @details Everytime it is called a pair or 2s a pair of 4s or a 2 and a 4 is added in a 0 spot
   */

  public void addTwoOrFour(){
    Random rand = new Random();
    int randomI = rand.nextInt(4);
    int randomJ = rand.nextInt(4);
    while(gameBoard.get(randomI).get(randomJ)!=0){
      randomI = rand.nextInt(4);
      randomJ = rand.nextInt(4);
    }
    if (rand.nextInt(100) < 90){
      gameBoard.get(randomI).set(randomJ, 2);
    }
    else{
      gameBoard.get(randomI).set(randomJ, 4);
    }

  }

  /**
   * @brief restart game
   * @details restarts the game by setting all to 0 and calling addTwoOrFour twice
   */

  public void restart(){
    for (int i = 0; i < 4; i++){
      for (int j = 0; j < 4; j++){
        gameBoard.get(i).set(j,0);
      }
    }

    addTwoOrFour();
    addTwoOrFour();
  }


  /**
   * @brief Win condition
   * @details Checks if 2048 is reached meaning you win
   */

  public boolean youWin(){
    for (int i = 0; i < 4; i++){
      for (int j = 0; j < 4; j++){
        if (gameBoard.get(i).get(j) == 2048){
          System.out.println("YOU WIN!");
          System.exit(0);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @brief Loose condition
   * @details If all the spots are full before reaching 2048, you looose
   */

  public boolean youLoose(){
    int full = 0;
    for (int i = 0; i < 4; i++){
      for (int j = 0; j < 4; j ++){
        if (gameBoard.get(i).get(j) != 0){
          full ++;
        }
      }
    }
    if (full == 16){
      System.out.println("You loose");
      System.exit(0);
      return true;
    }
    return false;
  }

  /**
   * @brief move right
   * @details Does the moving right operation on the board
   */

  public void moveRight(){
    for (int i = 0; i <= 3; i++){
      for (int j = 0; j <= 2; j++){
        if (gameBoard.get(i).get(j) == gameBoard.get(i).get(j+1)){
          gameBoard.get(i).set(j+1,gameBoard.get(i).get(j) + gameBoard.get(i).get(j+1));
          gameBoard.get(i).set(j, 0);

        }
        else while(gameBoard.get(i).get(j+1) == 0){
          gameBoard.get(i).set(j+1,gameBoard.get(i).get(j));
          gameBoard.get(i).set(j,0);
        }
      }
    }

    addTwoOrFour();
  }

  /**
   * @brief move left
   * @details Does the moving left operation on the board
   */

  public void moveLeft(){
    for (int i = 3; i >= 0; i--){
      for (int j = 2; j >= 0; j--){
        if (gameBoard.get(i).get(j) == gameBoard.get(i).get(j+1)){
          gameBoard.get(i).set(j,gameBoard.get(i).get(j) + gameBoard.get(i).get(j+1));
          gameBoard.get(i).set(j+1, 0);

        }
        else while(gameBoard.get(i).get(j) == 0){
          gameBoard.get(i).set(j,gameBoard.get(i).get(j+1));
          gameBoard.get(i).set(j+1,0);
        }
      }
    }
    addTwoOrFour();
  }

  /**
   * @brief move up
   * @details Does the moving up operation on the board
   */

//  public void moveUp()
//  {
//    for(int i = 3; i > 0; i--)
//    {
//      for(int j = 3; j > 0; j--)
//      {
//        if(gameBoard.get(i).get(j) == gameBoard.get(i-1).get(j))
//        {
//          gameBoard.get(i-1).set(j,gameBoard.get(i).get(j));
//          gameBoard.get(i).set(j, 0);
//        }
//        else if(gameBoard.get(i).get(j) == 0)
//        {
//          gameBoard.get(i-1).set(j, gameBoard.get(i).get(j));
//          gameBoard.get(i).set(j,0);
//        }
//      }
//    }
//    addTwoOrFour();
//  }

  public void transpose(){
    for (int i = 0; i < 4; i++){
      for (int j = i+1; j < 4; j++){
//        int temp = gameBoard.get(j).get(i);
//        gameBoard.get(j).set(i, gameBoard.get(i).get(j));
//        gameBoard.get(i).set(j, temp);
        int temp = gameBoard.get(j).get(i);
        gameBoard.get(j).set(i, gameBoard.get(i).get(j));
        gameBoard.get(i).set(j, temp);
      }
    }
  }

  public void flip(){
    for (int i = 0; i < 4; i++){
      for (int j = 0; j < 2; j++){
        int temp = gameBoard.get(i).get(j);
        gameBoard.get(i).set(j, gameBoard.get(i).get(4-j-1));
        gameBoard.get(i).set(4-j-1, temp);
      }
    }
  }

  public void moveUp(){
    transpose();
    displayBoard();
    flip();
    for (int i = 0; i <= 3; i++){
      for (int j = 0; j <= 2; j++){
        if (gameBoard.get(i).get(j) == gameBoard.get(i).get(j+1)){
          gameBoard.get(i).set(j+1,gameBoard.get(i).get(j) + gameBoard.get(i).get(j+1));
          gameBoard.get(i).set(j, 0);

        }
        else while(gameBoard.get(i).get(j+1) == 0){
          gameBoard.get(i).set(j+1,gameBoard.get(i).get(j));
          gameBoard.get(i).set(j,0);
        }
      }
    }
    flip();
    transpose();
    addTwoOrFour();
  }

  /**
   * @brief move down
   * @details Does the moving down operation on the board
   */

  public void moveDown(){
    transpose();
    flip();
    for (int i = 3; i >= 0; i--){
      for (int j = 2; j >= 0; j--){
        if (gameBoard.get(i).get(j) == gameBoard.get(i).get(j+1)){
          gameBoard.get(i).set(j,gameBoard.get(i).get(j) + gameBoard.get(i).get(j+1));
          gameBoard.get(i).set(j+1, 0);

        }
        else while(gameBoard.get(i).get(j) == 0){
          gameBoard.get(i).set(j,gameBoard.get(i).get(j+1));
          gameBoard.get(i).set(j+1,0);
        }
      }
    }
    flip();
    transpose();
    addTwoOrFour();
  }

}
