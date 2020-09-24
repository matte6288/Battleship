public class Move{
   private int row;
   private int col;
   /**
      Create a move object from two ints
   */
   public Move(int row, int col){
      this.row=row;
      this.col=col;
      
   }
   /**
      Create a move object from a string
   */
   public Move(String move){
      row=move.charAt(0)-65;
      col=move.charAt(1)-49;
      if (move.length()==3){
         if(move.charAt(1)=='1'&&move.charAt(2)=='0'){
            col=9;
         }
         
         
      }
   }
   /**
      Returns the integer row of the move object
      @return the row in int form
   */
   public int row(){
      return row;
   }
   /**
      Returns the integer col of the move object
      @return the col in int form
   */
   public int col(){
      return col;
   }
   /**
      Return the string form of the move using the first letter to represent row
      and the number to represent col
      @return the string of the move object
   */
   @Override 
   public String toString(){
      return(""+(char)(row+65)+(col+1));
   }
}