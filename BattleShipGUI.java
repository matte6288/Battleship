import javafx.scene.layout.BorderPane;
import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BattleShipGUI extends Application{
   private Game game;
   private BorderPane mainPane;
   boolean player;
   private GridPane middle;
   private GridPane playerBoard;
   private GridPane computerBoard;
   private HBox buttonHolder;
   private Button bComputerTurn;
   public void start(Stage primaryStage){
      game = new Game();
      player=true;
      //create pane
      mainPane= new BorderPane();
      //create title box
      HBox top=new HBox();
      top.setStyle("-fx-background-color: lightgrey;");
      Text title = new Text("Battleship");
      title.setFont(Font.font ("Verdana", 36));
      top.getChildren().add(title);
      top.setAlignment(Pos.CENTER);
      mainPane.setTop(top);
      
      //make middle
      middle=new GridPane();
      middle.setHgap(10);
      middle.setVgap(10);
      
      //mak userBoard
      playerBoard=new GridPane();
      displayUserBoard();
      middle.add(playerBoard,0,0);
      
      //make computerBoard
      computerBoard=new GridPane();
      displayComputerBoard(true);
      middle.add(computerBoard,1,0);
      
      //label for Player Board
      HBox playerBoardTextBox=new HBox();
      Text playerBoardText = new Text("Player Board");
      playerBoardText.setFont(Font.font ("Verdana", 18));
      playerBoardTextBox.getChildren().add(playerBoardText);
      playerBoardTextBox.setAlignment(Pos.CENTER);
      middle.add(playerBoardTextBox ,0,1);
      
      //label for User Board
      HBox computerBoardTextBox=new HBox();
      Text computerBoardText = new Text("Computer Board");
      computerBoardText.setFont(Font.font ("Verdana", 18));
      computerBoardTextBox.getChildren().add(computerBoardText);
      computerBoardTextBox.setAlignment(Pos.CENTER);
      middle.add(computerBoardTextBox,1,1);
      
      
      mainPane.setCenter(middle);
      
      //button holder for computer turn
      buttonHolder=new HBox();
      mainPane.setRight(buttonHolder);
      buttonHolder.setPrefWidth(120);
      bComputerTurn = new Button("Computers Turn");
      
      
      
      //setTitle and Show
      primaryStage.setTitle("Battleship");
      Scene scene = new Scene(mainPane);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   //updates playerboard with a random move displaying an alert if a ship has been sunk
   //also checks if game is over
   public void updatePlayerBoard(){
      String move=game.makeComputerMove()[1];
      if( move != null){
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setHeaderText(move);
          alert.showAndWait();
      }
      playerBoard.getChildren().clear();
      displayUserBoard();
      displayComputerBoard(true);
      buttonHolder.getChildren().clear();
      if(game.userDefeated()){
         
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("You Lost!");
            alert.showAndWait();
            newGame();
       }
   }
   
   
   //updates playerboard with a move based on the cell pane clicked displaying an alert if a ship has sunk
   //also checks if game is over
   public void updateComputerBoard(MouseEvent event)
   {
      
      CellPane cellClicked = (CellPane)event.getSource();
      Move m=new Move(cellClicked.getRow(),cellClicked.getCol());
      
      String move=game.makePlayerMove(m.toString());
      buttonHolder.getChildren().add(bComputerTurn);
      if( move != null){
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setHeaderText(move);
          alert.showAndWait();
      }
      computerBoard.getChildren().clear();
      displayUserBoard();
      displayComputerBoard(false);
      bComputerTurn.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle (ActionEvent e)
         {
            updatePlayerBoard();
         }
       });
       if(game.computerDefeated()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("You Won!");
            alert.showAndWait();
            newGame();
       }
       
      
   }
   //displays computer board
   public void displayComputerBoard(boolean clickable){
      for (int i=0;i<10;i++){
         for (int j=0;j<10;j++){
            CellPane cp=new CellPane(game.getComputerStatus(j, i),j,i, false);
            playerBoard.add(cp,j,i);
            if (clickable){
               switch(cp.getCellStatus()){
                   case AIRCRAFT_CARRIER:
                   case BATTLESHIP:
                   case CRUISER:
                   case DESTROYER:
                   case SUB:
                   case NOTHING:
                     cp.setOnMouseClicked(this::updateComputerBoard);
               }
            }
            computerBoard.add(cp,i,j);
            
         }
      }
   }
   //display user board
   public void displayUserBoard(){
      for (int i=0;i<10;i++){
         for (int j=0;j<10;j++){
            playerBoard.add((new CellPane(game.getUserStatus(i, j),j,i, true)),j,i);
         }
      }
   }
   //restarts the game, reassigning the game variable to a new verison and redisplaying the board
   public void newGame(){
      Button bNewGame=new Button("New Game");
      bNewGame.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle (ActionEvent e)
         {
            buttonHolder.getChildren().clear();
            game=new Game();
            displayUserBoard();
            displayComputerBoard(true);
            
         }
       });
      buttonHolder.getChildren().clear();
      buttonHolder.getChildren().add(bNewGame);
      
   }
   
   
   //launches java fx
   
   public static void main (String [] args){
      launch(args);
   }
   
   
}