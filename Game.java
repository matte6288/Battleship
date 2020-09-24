import java.io.*; 

public class Game{

   private ComputerBoard computer;
   private UserBoard user;
   /**
      Creates a new game populating computer and user boards
   */
   public Game(){
      try{
         computer = new ComputerBoard("compFleet.txt");
         user = new UserBoard("userFleet.txt");
      }
      catch(IOException e)
      {
      
      }
   }
   /**
      returns the string array of makeComputerMove
      @return the string array from makeComputerMove
   */
   public String [] makeComputerMove(){
      
      return user.makeComputerMove();
   }
   /**
      returns the string of makeUserMove
      @return the string from makeUserMove
   */
   public String makePlayerMove(String entry){
      return computer.makePlayerMove(new Move(entry));
   }
  /**
      returns if the computer is defeated
      @return true if computer is defeated
   */
   public boolean computerDefeated(){
      return computer.gameOver();
   }
   /**
      returns if the user is defeated
      @return true if user is defeated
   */
   public boolean userDefeated(){
      return user.gameOver();
   }
   /**
      returns a readle string verison of the game
      @return a readble string veriosn of the game
   */
   @Override
   public String toString(){
      String gameboard="";
      gameboard+="COMPUTER \n"+computer.toString();
      gameboard+="\n";
      gameboard+="USER \n"+user.toString();
      return gameboard;
   }
   /**
      returns cellstatus of computer at given col and row
      @param col the col of the cell status
      @param row the row of the cell status
      @return cell status at given row and col
   */
   public CellStatus getComputerStatus(int row, int col)
   {
      return computer.getStatus(row,col);
   }
  /**
      returns cellstatus of user at given col and row
      @param col the col of the cell status
      @param row the row of the cell status
      @return cell status at given row and col
   */
   public CellStatus getUserStatus(int row, int col)
   {
      return user.getStatus(row,col); 
   }
   
   

}