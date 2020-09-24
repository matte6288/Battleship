import java.io.*; 
import java.util.*; 

public class Board{
   private ArrayList<ArrayList<CellStatus>> layout;
   private Fleet fleet;
   private Scanner infile;
   public static final int SIZE=10;
  /**
   Creates new board and fills ArrayList layout with EMPTY CellStatus
  */
   public Board(String n){
      layout= new ArrayList<ArrayList<CellStatus>> ();
      fleet = new Fleet();
      for (int i=0;i<SIZE;i++){
         layout.add(new ArrayList<CellStatus> ());
         for (int j=0;j<SIZE;j++){
            layout.get(i).add(CellStatus.NOTHING);
         }
      }
         LoadFromFile(n);
   }
   /**
      Takes in a text files and populates layout using the information in it
      @param fileName the name of the file to populate layout from
   */
   private void LoadFromFile(String fileName){
      try{
         infile = new Scanner(new File(fileName));
      }
      catch(FileNotFoundException f){
      
      } 
      String workingLine;
      while (infile.hasNext()){
         workingLine=infile.nextLine();
         CellStatus workingCellStatus=CellStatus.NOTHING;
         switch(workingLine.charAt(0)){
            case 'A':
               workingCellStatus=CellStatus.AIRCRAFT_CARRIER;
               break;
            case 'B':
               workingCellStatus=CellStatus.BATTLESHIP;
               break;
            case 'C':
               workingCellStatus=CellStatus.CRUISER;
               break;
            case 'D':
               workingCellStatus=CellStatus.DESTROYER;
               break;
            case 'S':
               workingCellStatus=CellStatus.SUB;
               break;
          }
          if (workingLine.charAt(2)==workingLine.charAt(5)){
            for(int i=workingLine.charAt(3)-49;i<=workingLine.charAt(6)-49;i++){
               layout.get(workingLine.charAt(2)-65).set(i,workingCellStatus);
            } 
            
          }
          else if(workingLine.charAt(3)==workingLine.charAt(6)){
            for(int i=workingLine.charAt(2)-65;i<=workingLine.charAt(5)-65;i++){
               layout.get(i).set(workingLine.charAt(3)-49,workingCellStatus);
            }
          }
               
      }
   }
   /**
      takes a move and applies it to layout changing returns original cellstatus
      @param m the move to be applied
      @return the original cell status
   */
   public CellStatus applyMoveToLayout(Move m){
      CellStatus originalCell=CellStatus.NOTHING;
      CellStatus cell=layout.get(m.row()).get(m.col());
      switch (cell){
         case NOTHING:
            cell=CellStatus.NOTHING_HIT;
            originalCell=CellStatus.NOTHING;
            break;
         case AIRCRAFT_CARRIER:
            cell=CellStatus.AIRCRAFT_CARRIER_HIT;
            originalCell=CellStatus.AIRCRAFT_CARRIER;
            break;
         case BATTLESHIP:
            cell=CellStatus.BATTLESHIP_HIT;
            originalCell=CellStatus.BATTLESHIP;
            break;
         case SUB:
            cell=CellStatus.SUB_HIT;
            originalCell=CellStatus.SUB;
            break;
         case CRUISER:
            cell=CellStatus.CRUISER_HIT;
            originalCell=CellStatus.CRUISER;
            break;
         case DESTROYER:
            cell=CellStatus.DESTROYER_HIT;
            originalCell=CellStatus.DESTROYER;
            break;
      }
      layout.get(m.row()).set(m.col(),cell);
      return originalCell;
   }
   
   /**
      Takes a move and sees if the spot is available 
      @param m the move to be 
   */
   public boolean moveTaken(Move m){
      switch(layout.get(m.row()).get(m.col())){
         case AIRCRAFT_CARRIER_HIT:
         case AIRCRAFT_CARRIER_SUNK:
         case BATTLESHIP_HIT:
         case BATTLESHIP_SUNK:
         case CRUISER_HIT:
         case CRUISER_SUNK:
         case DESTROYER_HIT:
         case DESTROYER_SUNK:
         case SUB_HIT:
         case SUB_SUNK:
         case NOTHING_HIT:
            return false;
      }
      return true;
   }
   
   /**
      returns layout
      @return layout
   */
   public ArrayList<ArrayList<CellStatus>> getLayout(){
      return layout;
   }
   
   /**
      returns fleets
      @return fleet
   */
   
   public Fleet getFleet(){
      return fleet;
   }  
   /**
      returns true if game is over false other wise
      @return true if the game is over false other wise
   */
   public boolean gameOver(){
      return fleet.gameOver();
   }
   /**
      returns cellstatus at given col and row
      @param col the col of the cell status
      @param row the row of the cell status
      @return cell status at given row and col
   */
   public CellStatus getStatus(int row, int col){
      return layout.get(row).get(col); 
   }
   /**
      changes all cellstatuses of one type in layou to another type
      @param originalCellStatus the cellstatus to change
      @param newStatus the status to change to
   */
   public void updateLayout(CellStatus originalStatus, CellStatus newStatus){
      for (int i=0;i<SIZE;i++){
         for (int j=0;j<SIZE;j++){
            if (layout.get(i).get(j)==originalStatus){
               layout.get(i).set(j, newStatus);
            }
         }
      }

   }
}