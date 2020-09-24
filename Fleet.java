public class Fleet{
   private Ship battleShip;
   private Ship aircraftCarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   /**
      Creates fleet with every ship type
   */
   public Fleet(){
      battleShip=new Battleship();
      aircraftCarrier= new AircraftCarrier();
      cruiser= new Cruiser();
      sub=new Sub();
      destroyer=new Destroyer();
   }
   /**
      Hits ship and check if ship is sunk 
      @returns true if ship is sunk false if not
   */
   public boolean updateFleet(ShipType ship){
      boolean shipSunk = false;
      switch (ship){
         case ST_AIRCRAFT_CARRIER:
            shipSunk = aircraftCarrier.hit();
          //  =aircraftCarrier.getSunk();
            break;
        case ST_BATTLESHIP:
            shipSunk= battleShip.hit();
           // shipSunk=battleShip.getSunk();
            break;
        case ST_CRUISER:
            shipSunk=cruiser.hit();
          //  shipSunk=cruiser.getSunk();
            break;
       case ST_SUB:
           shipSunk= sub.hit();
          //  shipSunk=sub.getSunk();
            break;
       case ST_DESTROYER:
           shipSunk= destroyer.hit();
          //  shipSunk=destroyer.getSunk();
            break;
      }
      return shipSunk;
   }
   /**
      Checks to see if all ships are sunk
      @return true if all ships are sunk
   */
   public boolean gameOver(){
      return(battleShip.getSunk() && aircraftCarrier.getSunk() && cruiser.getSunk() && sub.getSunk() && destroyer.getSunk());
               
   }

}