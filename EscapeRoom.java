/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{
  /* TO-DO: Process game commands from user input:
      right, left, up, down: move player size of move, m, if player try to go off grid or bump into wall, score decreases
      jump over 1 space: player cannot jump over walls
      pick up prize: score increases, if there is no prize, penalty
      help: display all possible commands
      end: reach the far right wall, score increase, game ends, if game ends without reaching far right wall, penalty
      replay: shows number of player steps and resets the board, player or another player can play the same board
        
      if player land on a trap, spring a trap to increase score: the program must first check if there is a trap, if none exists, penalty
      Note that you must adjust the score with any method that returns a score
      Optional: create a custom image for player - use the file player.png on disk
    */

  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpright", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?"};
  
    // set up game
    boolean play = true;
    while (play)
    {

      
      if (game.x > 430 && game.y > 250){
        game.endGame();
        break;

      }
      // get user command and validate
      System.out.print("Enter command:");
      String input = UserInput.getValidInput(validCommands);
	    /* process user commands*/
      if (input.equals("right") || input.equals("r")){
        game.movePlayer(60, 0);
      }
      else if (input.equals("left") || input.equals("l")){
        game.movePlayer(-60, 0);
      }
      else if (input.equals("up") || input.equals("u")){
        game.movePlayer(0, -60);
      }
      else if (input.equals("down") || input.equals("d")){
        game.movePlayer(0, 60);
      }
      else if (input.equals("jumpr") || input.equals("jr")){
        game.movePlayer(120, 0);
      }
      else if (input.equals("jumpl") || input.equals("jl")){
        game.movePlayer(-120, 0);
      }
      else if (input.equals("jumpu") || input.equals("ju")){
        game.movePlayer(0, -120);
      }
      else if (input.equals("jumpd") || input.equals("jd")){
        game.movePlayer(0, 120);
      }
      else if (input.equals("pickup") || input.equals("p")){
        game.pickupPrize();
      }
      else if (input.equals("replay")){
        game.replay();
      }
      else if (input.equals("help") || input.equals("?")){
        System.out.println("These are the controls for the Escape Room");
        System.out.println("Type right (r), left (l), up (u), down (d) to move one unit");
        System.out.println("Type jumpr (jr), jumpl (jl), jumpu (ju), jumpd (jd) to jump over one unit");
        System.out.println("Type pickup (p) to pickup prize ");
        System.out.println("Type replay to repla the game");
        System.out.println("Your goal is to reach the bottom right corner ");
        System.out.println("Type help (?) to repeat these instructions ");
      }

      
      /* uncomment when user quits */
      else if (input.equals("quit") || input.equals("q")){
        play = false;
      }
      else{
        System.out.println("INVALID INPUT");
        score -= 5;
      }
    

    }

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}

        