import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
public class CellPane extends HBox{
   private CellStatus cell;
   private int row;
   private int col;
   private boolean belongsToUser;
   private String letter;
   
   /**
      creates new cell pane
      @param cell the CellStatus of the cell pane
      @param row the row of the cell pane
      @param belongsToUser a boolean to indicate if the cell pane belongs to the user
   */
   public CellPane(CellStatus cell, int row, int col, boolean belongsToUser){
      this.cell=cell;
      this.row=row;
      this.col=col;
      this.belongsToUser=belongsToUser;
      this.setPrefSize(30,30);
      letter=" ";
      this.setAlignment(Pos.CENTER);
      switch(cell){
         case AIRCRAFT_CARRIER:
         case BATTLESHIP:
         case CRUISER:
         case DESTROYER:
         case SUB:
         case NOTHING:
            this.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
            break;
         case AIRCRAFT_CARRIER_HIT:
         case BATTLESHIP_HIT:
         case CRUISER_HIT:
         case DESTROYER_HIT:
         case SUB_HIT:
            this.setStyle("-fx-background-color: red; -fx-border-color: black;");
            break;
         case AIRCRAFT_CARRIER_SUNK:
         case BATTLESHIP_SUNK:
         case CRUISER_SUNK:
         case DESTROYER_SUNK:
         case SUB_SUNK:
            this.setStyle("-fx-background-color: yellow; -fx-border-color: black;");
            break;
         case NOTHING_HIT:
            this.setStyle("-fx-background-color: grey; -fx-border-color: black;");
            break;
      }
      switch (cell){
         case AIRCRAFT_CARRIER_SUNK:
            letter="A";
            break;
         case BATTLESHIP_SUNK:
            letter="B";
            break;
         case CRUISER_SUNK:
            letter="C";
            break;
         case DESTROYER_SUNK:
            letter="D";
            break;
         case SUB_SUNK:
            letter="S";
            break;
      }
      if(belongsToUser){
         switch(cell){
            case AIRCRAFT_CARRIER:
            case AIRCRAFT_CARRIER_HIT:
               letter="A";
               break;
            case BATTLESHIP:
            case BATTLESHIP_HIT:
               letter="B";
               break;
            case CRUISER:
            case CRUISER_HIT:
               letter="C";
               break;
            case DESTROYER:
            case DESTROYER_HIT:
               letter="D";
               break;
            case SUB:
            case SUB_HIT:
               letter="S";
               break;
         }
      
      }
      Text t=new Text(letter);
      this.getChildren().add(t);   
      
      
   }
   
   /**
      returns row of cell pane
      @return row of cell pane
   */
   public int getRow(){
      return row;
   }
   /**
      returns col of cell pane
      @return row of cell pane
   */
   public int getCol(){
      return col;
   }
   /**
      returns cell status of cell pane
      @return cell status of cell pane
   */
   public CellStatus getCellStatus(){
      return cell;
   }
}