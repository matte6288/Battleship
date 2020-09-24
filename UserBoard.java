import java.io.*; 
import java.util.*;

public class UserBoard extends Board{
  private ArrayList<Move> moves;
  private Random r;
  /**
   Creates UserBoard calling the super constructor and 
   creting an arraylist with all possible moves as well as a random object
   @param fileName the name of the file to be turned into the layout
   
  */
  public UserBoard (String fileName){
     super(fileName);
     moves = new ArrayList<Move> ();
     r= new Random();
     for (int i=0;i<10;i++){
         for (int j=0;j<10;j++){
            moves.add(new Move(i,j));
         }
     }
  }
  
  /**
   Returns a string array of size 2
   The first string being a readable form of the move
   The second informing if a ship is sunk
   @return a string array showing a readable verison of the selected move and informing if a ship ws sunk
  */
  public String[] makeComputerMove(){
      String [] readable =new String[2];
      Move m = pickRandomMove();
      readable[0]=m.toString();
      CellStatus target= applyMoveToLayout(m);
      readable[1]= null;
      switch(target){
         case AIRCRAFT_CARRIER:
            if(getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER))
            {
               updateLayout(CellStatus.AIRCRAFT_CARRIER_HIT, CellStatus.AIRCRAFT_CARRIER_SUNK);
               readable[1]= ("You sunk my Aircraft Carrier!");
            }
            break;
         case BATTLESHIP:
            if(getFleet().updateFleet(ShipType.ST_BATTLESHIP)){
               updateLayout(CellStatus.BATTLESHIP_HIT, CellStatus.BATTLESHIP_SUNK);
               readable[1]= ("You sunk my Battleship!");
            }
            break;
         case CRUISER:
            if(getFleet().updateFleet(ShipType.ST_CRUISER)){
               updateLayout(CellStatus.CRUISER_HIT, CellStatus.CRUISER_SUNK);
               readable[1]= ("You sunk my Cruiser!");
            }
            break;
         case DESTROYER:
            if(getFleet().updateFleet(ShipType.ST_DESTROYER)){
               updateLayout(CellStatus.DESTROYER_HIT, CellStatus.DESTROYER_SUNK);
               readable[1]= ("You sunk my Destroyer!");
            }
            break;
         case SUB:
            if(getFleet().updateFleet(ShipType.ST_SUB)){
               updateLayout(CellStatus.SUB_HIT, CellStatus.SUB_SUNK);
               readable[1]= ("You sunk my Sub!");
            }
            break;
      
      }
      return readable;
  }
  
  /**
   selects and returns a random move
   @return a random move
  */
  public Move pickRandomMove(){
      return moves.remove(r.nextInt((moves.size())));
  }
  
  /**
   returns a string form of the layout of the  board
   @return a strign veriosn of the layout 
  */
  public String toString(){
    String boardString="  1 2 3 4 5 6 7 8 9 10\n";
    String [] col=new String [] {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
    for (int i=0;i<getLayout().size();i++){
         boardString+=col[i];
         for (int j=0;j<this.getLayout().get(i).size();j++){
            boardString+=this.getLayout().get(i).get(j).toString().charAt(1)+" ";
         }
         boardString+="\n";
      }
      return boardString;
   }
   
   
   
  
}