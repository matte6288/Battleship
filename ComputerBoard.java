import java.io.*; 
import java.util.*; 

public class ComputerBoard extends Board{
   /**
      Creates ComputerBoard using the super constructor 
      @param fileName name of file to load into layout
   */
   public ComputerBoard(String fileName) throws IOException{
      super(fileName);
   }
   
   /**
      Takes a move and Returns a string if a ship is sunk after applying the move, null if no ship is sunk
      @param m the move to apply
      @return a string if ship was sunk null if not
   */
   public String makePlayerMove(Move m){
      CellStatus target= applyMoveToLayout(m);
      switch(target){
         case AIRCRAFT_CARRIER:
            if(getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER))
            {
               updateLayout(CellStatus.AIRCRAFT_CARRIER_HIT, CellStatus.AIRCRAFT_CARRIER_SUNK);
               return ("You sunk my Aircraft Carrier!");
            }
            break;
         case BATTLESHIP:
            if(getFleet().updateFleet(ShipType.ST_BATTLESHIP)){
               updateLayout(CellStatus.BATTLESHIP_HIT, CellStatus.BATTLESHIP_SUNK);
               return ("You sunk my Battleship!");
            }
            break;
         case CRUISER:
            if(getFleet().updateFleet(ShipType.ST_CRUISER)){
               updateLayout(CellStatus.CRUISER_HIT, CellStatus.CRUISER_SUNK);
               return ("You sunk my Cruiser!");
            }
            break;
         case DESTROYER:
            if(getFleet().updateFleet(ShipType.ST_DESTROYER)){
               updateLayout(CellStatus.DESTROYER_HIT, CellStatus.DESTROYER_SUNK);
               return ("You sunk my Destroyer!");
            }
            break;
         case SUB:
            if(getFleet().updateFleet(ShipType.ST_SUB)){
               updateLayout(CellStatus.SUB_HIT, CellStatus.SUB_SUNK);
               return ("You sunk my Sub!");
            }
            break;
      
      }
      return null;
   
   }
   /**
      Returns a string verison of the board
      @return the string verison of the board
   */
   public String toString(){
      String boardString="  1 2 3 4 5 6 7 8 9 10\n";
      String [] col=new String [] {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
      for (int i=0;i<getLayout().size();i++){
         boardString+=col[i];
         for (int j=0;j<this.getLayout().get(i).size();j++){
            boardString+=this.getLayout().get(i).get(j).toString().charAt(0)+ " ";
         }
         boardString+="\n";
      }
      return boardString;
   }
   
   
}