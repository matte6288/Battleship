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
public class BattleShipGUI1 extends Application{
   private Game game;
   private BorderPane mainPane;
   private Text title;
   private Text playerBoardText;
   private Text computerBoardText;
   boolean player;
   private HBox top;
   private HBox playerBoardTextBox;
   private HBox computerBoardTextBox;
   private GridPane middle;
   private GridPane playerBoard;
   private GridPane computerBoard;
   public void start(Stage primaryStage){
      game = new Game();
      player=true;
      
      mainPane= new BorderPane();
      
      top=new HBox();
      top.setStyle("-fx-background-color: lightgrey;");
      title = new Text("Battleship");
      title.setFont(Font.font ("Verdana", 36));
      top.getChildren().add(title);
      top.setAlignment(Pos.CENTER);
      mainPane.setTop(top);
      
      middle=new GridPane();
      middle.setHgap(10);
      middle.setVgap(10);
      
      playerBoard=new GridPane();
      for (int i=0;i<10;i++){
         for (int j=0;j<10;j++){
            playerBoard.add((new CellPane(game.getComputerStatus(j, i),j,i, true)),j,i);
         }
      }
      middle.add(playerBoard,0,0);
      
      computerBoard=new GridPane();
      
      for (int i=0;i<10;i++){
         for (int j=0;j<10;j++){
            computerBoard.add((new CellPane(CellStatus.BATTLESHIP,i,j, false)),i,j);
         }
      }
      middle.add(computerBoard,1,0);
      
      playerBoardTextBox=new HBox();
      playerBoardText = new Text("Player Board");
      playerBoardText.setFont(Font.font ("Verdana", 18));
      playerBoardTextBox.getChildren().add(playerBoardText);
      playerBoardTextBox.setAlignment(Pos.CENTER);
      middle.add(playerBoardTextBox ,0,1);
      
      computerBoardTextBox=new HBox();
      computerBoardText = new Text("Computer Board");
      computerBoardText.setFont(Font.font ("Verdana", 18));
      computerBoardTextBox.getChildren().add(computerBoardText);
      computerBoardTextBox.setAlignment(Pos.CENTER);
      middle.add(computerBoardTextBox,1,1);
      
      
      mainPane.setCenter(middle);
      
      
      
      primaryStage.setTitle("Battleship");
      Scene scene = new Scene(mainPane);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   
   public static void main (String [] args){
      launch(args);
   }
   
   
}