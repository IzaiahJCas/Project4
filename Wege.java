import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Collections;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.geometry.Pos;
import java.util.NoSuchElementException;

/**
 * This class represents the board game Wege
 * @Author Izaiah Caston
 */
public class Wege extends Application{
  
  /** button for placing card on board */
  private WegeButton wegebutton;
  
  /** button for drawing next card */
  private WegeButton nextCardButton;
  
  /** current card being played */
  private WegeCard currentCard;
  
  /** boolean indicating if current card is placed on board */
  private boolean isPlaced;
  
  /** number of rows in the game */
  private static int numRow;
  
  /** number of columns in the game */
  private static int numCol;
  
  /** row index of the current card on board */
  private int row;
  
  /** column index of the current card on board */
  private int col;
  
  /** represents a water card */
  private static int water;
  
  /** represents a land card */
  private static int land;
  
  /** represents a cossack card */
  private static int cossack;
  
  /** represents a bridge card */
  private static int bridge;
  
  /** represents a water card with gnome on the stream */
  private static int gnomeStream;
  
  /** represents a land card with gnome on the path */
  private static int gnomePath;
  
  /** represents a water card with the gnome in the corner */
  private static int gnomeWaterCorner;
  
  /** represents a land card with the gnome in the corner */
  private static int gnomeLandCorner;
  
  /** list of cards to draw */
  ArrayList<WegeCard> draw = new ArrayList<WegeCard>();
  
  /** linked list for the cards in the deck */
  LinkedList<WegeCard> deck = new LinkedList<>();
  
  /** border pane to contain the game elements */
  BorderPane border = new BorderPane();
  
  /** grid pane to contain the board */
  GridPane gridPane = new GridPane();
  
  /** 2D array representing the board */
  WegeButton[][] board = new WegeButton[6][6];
  
  /** button to draw the next card */
  WegeButton drawButton = new WegeButton(50, 50);
  
  /** label to indicate whose turn it is */
  Label turnLabel = new Label("Turn");
  
  /** label to indicate the current player */
  Label currentPlayerLabel = new Label("Player 1 turn");
  
  /** text to be displayed at the start of the game */
  String startText = "";
  
  /** array containing the current player */
  int[] player = {0};
  
  /** array containing the number of turns played so far */
  int count[] = {0};
  
  /**
   * Sets the number of rows
   * @param x the new number of rows
   */
  public static void setNumRow(int x) {
    numRow = x; 
  }
  
  /**
   * Sets the number of columns
   * @param x the new number of columns
   */
  public static void setNumCol(int x) {
    numCol = x; 
  }
  
  /**
   * Sets the number of cossacks 
   * @param x the new number of cossacks 
   */
  public static void setCossack(int x) {
    cossack = x; 
  }
  
  /**
   * Sets the number of water cards
   * @param x the new number of water cards
   */
  public static void setWater(int x) {
    water = x;
  }
  
  /**
   * Sets the number of land cards
   * @param x the new number of land cards
   */
  public static void setLand(int x) {
    land = x;
  }
  
  /**
   * Sets the number of bridges cards
   * @param x the new number of bridges cards
   */
  public static void setBridge(int x) {
    bridge = x;
  }
  
  /**
   * Sets the number of water cards with a gnome in the stream
   * @param x the new number of cards with a gnome in the stream
   */
  public static void setGnomeStream(int x) {
    gnomeStream = x;
  }
  
  /**
   * Sets the number of land cards with a gnome on the path
   * @param x the new number of cards with a gnome on the path
   */
  public static void setGnomePath(int x) {
    gnomePath = x;
  }
  
  /**
   * Sets the number of water cards with a gnome in the corner
   * @param x the new number of water cards with a gnome in the corner
   */
  public static void setGnomeWaterCorner(int x) {
    gnomeWaterCorner = x;
  }
  
  /**
   * Sets the number of land cards with a gnome in the corner
   * @param x the new number of land cards with a gnome in the corner
   */
  public static void setGnomeLandCorner(int x) {
    gnomeLandCorner = x;
  }
  
  /**
   * gets the number of land cards with gnomes in the corner
   * @return the number of land cards with gnomes in the corner
   */
  public int getGnomeLandCorner() {
    return gnomeLandCorner;
  }
  
  /**
   * gets the number of water cards with gnomes in the corner
   * @return the number of water cards with gnomes in the corner
   */
  public int getGnomeWaterCorner() {
    return gnomeWaterCorner;
  }
  
  /**
   * Gets the number of rows in the game
   * @return the number of rows in the game
   */
  public int getNumRow() {
    return numRow; 
  }
  
  /**
   * Gets the number of columns in the game
   * @return the number of columns in the game
   */
  public int getNumCol() {
    return numCol; 
  }
  
  /**
   * Gets the number of cossacks cards in the game
   * @return the number of cossacks cards in the game
   */
  public int getCossack() {
    return cossack; 
  }
  
  /**
   * Gets the number of water cards in the game
   * @return the number of water cards in the game
   */
  public int getWater() {
    return water;
  }
  
  /**
   * Gets the number of land cards in the game
   * @return the number of land cards in the game
   */
  public int getLand() {
    return land;
  }
  
  /**
   * Gets the number of bridge cards in the game
   * @return the number of bridge cards in the game
   */
  public int getBridge() {
    return bridge;
  }
  
  /**
   * Gets the number of water cards with a gnome in the stream
   * @return the number of water cards with a gnome in the stream
   */
  public int getGnomeStream() {
    return gnomeStream;
  }
  
  /**
   * gets the number of land cards with gnomes on the path
   * @return the number of cards with gnomes on the path
   */
  public int getGnomePath(){
    return gnomePath; 
  }
  
  /**
   * Returns the LinkedList with the deck
   */
  public LinkedList<WegeCard> getDeck(){
    return deck; 
  }
  
  /**
   * Determines if the first card is on the board
   * @return true if the first card is on the board, false otherwise
   */
  public boolean isFirstCardOnBoard() {
    for (int row = 0; row < getNumRow(); row++) {
      for (int col = 0; col < getNumCol(); col++) {
        if (board[row][col].getCard() != null) {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Gets the row of the specified Wege button.
   * @param button The Wege button.
   * @return The row of the button, or -1 if the button is not found.
   */
  public int getRow(WegeButton button) {
    for (int row = 0; row < getNumRow(); row++) {
      for (int col = 0; col < getNumCol(); col++) {
        if (board[row][col] == button) {
          return row;
        }
      }
    }
    return -1;
  }
  
  /**
   * Gets the column of the specified Wege button.
   * @param button The Wege button.
   * @return The column of the button, or -1 if the button is not found.
   */
  public int getCol(WegeButton button) {
    for (int row = 0; row < getNumRow(); row++) {
      for (int col = 0; col < getNumCol(); col++) {
        if (board[row][col] == button) {
          return col;
        }
      }
    }
    return -1;
  }
  
  /**
   * Determines whether a given card is adjacent and matching with the current card on the game board.
   * @param button the button that represents the card to be checked
   * @return true if the given card is adjacent and matching with the current card, false otherwise
   */
  public boolean isMatching(WegeButton button) {
    int row = getRow(button);
    int col = getCol(button);
    
    if (row > 0 && board[row - 1][col].getCard() != null) {      
      if(((currentCard.isWater(Pos.TOP_LEFT) == board[row-1][col].getCard().isWater(Pos.BOTTOM_LEFT)) 
            && (currentCard.isWater(Pos.TOP_RIGHT) == board[row-1][col].getCard().isWater(Pos.BOTTOM_RIGHT)))){
        System.out.println("Amazing move");
        return true; 
      }    
    }
    if (row < getNumRow() -1 && board[row + 1][col].getCard() != null) {
      if(((currentCard.isWater(Pos.BOTTOM_LEFT) == board[row+1][col].getCard().isWater(Pos.TOP_LEFT)) 
            && (currentCard.isWater(Pos.BOTTOM_RIGHT) == board[row+1][col].getCard().isWater(Pos.TOP_RIGHT)))){
        System.out.println("Awesome placement");
        return true; 
      }
    }
    if (col > 0 && board[row][col - 1].getCard() != null) {
      if(((currentCard.isWater(Pos.TOP_RIGHT) == board[row][col-1].getCard().isWater(Pos.TOP_LEFT))
            && (currentCard.isWater(Pos.BOTTOM_RIGHT) == board[row][col-1].getCard().isWater(Pos.BOTTOM_LEFT)))){
        System.out.println("Great move");
        return true; 
      }
    }
    if (col < getNumCol() -1 && board[row][col + 1].getCard() != null) {
      if(((currentCard.isWater(Pos.TOP_LEFT) == board[row][col+1].getCard().isWater(Pos.TOP_RIGHT))
            && (currentCard.isWater(Pos.BOTTOM_LEFT) == board[row][col+1].getCard().isWater(Pos.BOTTOM_RIGHT)))){
        System.out.println("Nice move");
        return true; 
      }
    }
    System.out.println("Not Matching");
    return false; 
  }
  
  /**
   * Determines whether the gnomes on a given card can form a combination with the gnomes on adjacent cards
   * @param x the button that represents the card to be checked
   * @return true if the gnomes on the given card can form a combination with the gnomes on adjacent cards,
   *         false otherwise
   */
  public boolean isMatchingGnomes(WegeButton x){
    int row = getRow(x);
    int col = getCol(x);
    
    if(board[row][col].getCard().hasGnome()){
      if (row < getNumRow() - 1 && board[row + 1][col].getCard() != null && col > 0 && board[row][col - 1].getCard() != null){      
        if(currentCard.getGnomePosition() == Pos.BOTTOM_RIGHT 
             && board[row + 1][col].getCard().getGnomePosition() == Pos.BOTTOM_LEFT && board[row][col - 1].getCard().getGnomePosition() == Pos.TOP_RIGHT){
          System.out.println("Gnome Gang");
          return true; 
        }    
      }
      
      if (row > 0 && board[row - 1][col].getCard() != null && col > 0 && board[row][col - 1].getCard() != null){
        if(currentCard.getGnomePosition() == Pos.BOTTOM_LEFT 
             && board[row - 1][col].getCard().getGnomePosition() == Pos.BOTTOM_RIGHT && board[row][col - 1].getCard().getGnomePosition() == Pos.TOP_LEFT){
          System.out.println("Gnome Attack");
          return true; 
        }
      }
      
      if (row < getNumRow() -1 && board[row + 1][col].getCard() != null && col < getNumCol()-1 && board[row][col + 1].getCard() != null) {
        if(currentCard.getGnomePosition() == Pos.TOP_RIGHT 
             && board[row + 1][col].getCard().getGnomePosition() == Pos.TOP_LEFT && board[row][col + 1].getCard().getGnomePosition() == Pos.BOTTOM_RIGHT){
          System.out.println("Gnome Mob");
          return true; 
        }
      }
      if (row > 0 && board[row - 1][col].getCard() != null && col < getNumCol()-1 && board[row][col + 1].getCard() != null) {
        if(currentCard.getGnomePosition() == Pos.TOP_LEFT 
             && board[row - 1][col].getCard().getGnomePosition() == Pos.TOP_RIGHT && board[row][col + 1].getCard().getGnomePosition() == Pos.BOTTOM_LEFT){
          System.out.println("Gnome Mafia");
          return true; 
        }
      }
      return false;
    }
    System.out.println("Not Matching Gnomes");
    return false; 
  }
  
  /**
   * Determines whether a bridge card can replace a given card on the game board.
   * @param x the button that represents the card to be replaced
   * @return true if the bridge card can replace the given card, false otherwise
   */
  public boolean bridgeReplace(WegeButton x){
    int row = getRow(x);
    int col = getCol(x);
    
    if(board[row][col].getCard() != null && isMatching(x) && currentCard.getCardType() == WegeCard.CardType.BRIDGE && board[row][col].getCard().getCardType() == WegeCard.CardType.WATER ||
       board[row][col].getCard() != null && isMatching(x) && currentCard.getCardType() == WegeCard.CardType.BRIDGE && board[row][col].getCard().getCardType() == WegeCard.CardType.LAND){
      System.out.println("Replaced");
      return true;
    }      
    else if(board[row][col].getCard() != null && currentCard.getCardType() == WegeCard.CardType.BRIDGE && board[row][col].getCard().getCardType() == WegeCard.CardType.COSSACK
              || board[row][col].getCard() != null && currentCard.getCardType() == WegeCard.CardType.BRIDGE && board[row][col].getCard().getCardType() == WegeCard.CardType.BRIDGE){
      System.out.println("Cant Replace");
      return false;
    }
    else if(board[row][col].getCard() != null && currentCard.getCardType() == WegeCard.CardType.BRIDGE && board[row][col].getCard().getCardType() == WegeCard.CardType.WATER && isMatchingGnomes(x)
              || board[row][col].getCard() != null && currentCard.getCardType() == WegeCard.CardType.BRIDGE && board[row][col].getCard().getCardType() == WegeCard.CardType.LAND && isMatchingGnomes(x)){
      System.out.println("The Gnomes have unionized");
      return false;
    }
    return false;
  }
  
  /**
   * Replaces the drawButton card with the card from the board
   * @param x the button that is clicked on in the board
   */
  public void replaceCard(WegeButton x){
    int row = getRow(x);
    int col = getCol(x);
    
    WegeButton temp = new WegeButton(50, 50);
    temp.setCard(board[row][col].getCard());
    board[row][col].setCard(currentCard);
    getDeck().addToFront(temp.getCard());
    nextCardButton.setCard(getDeck().removeFromFront());
    System.out.println("Card Returned");
  }
  
  /**
   * This method starts the Wege game and initializes the game board, draw pile, and UI components.
   * @param primaryStage The primary stage for the JavaFX application.
   */
  public void start(Stage primaryStage){
    draw = new ArrayList<>(); 
    
    /** Initialize the game board */
    for (int row = 0; row < getNumRow(); row++) {
      for (int col = 0; col < getNumCol(); col++) {
        WegeButton wegebutton = new WegeButton(50,50);
        wegebutton.setOnAction(new EventHandler<ActionEvent>() {
          /**
           * Handles the action event when a WegeButton is clicked.
           * @param event The action event.
           */
          public void handle(ActionEvent event) {
            if (currentCard != null) {
              if(currentCard.getCardType() == WegeCard.CardType.BRIDGE && bridgeReplace(wegebutton) == true || isFirstCardOnBoard()){
                replaceCard(wegebutton);
                if(player[0] == 1){
                  player[0] = 2;
                  currentPlayerLabel.setText("Player 1 turn");
                }
                else{
                  player[0] = 1;
                  currentPlayerLabel.setText("Player 2 turn"); 
                }
                count[0]++;
                turnLabel.setText("Turn" + " " + count[0]);
                draw.remove(0);
                if (draw.isEmpty()) {
                  currentCard = null;
                }
              }
              else if(currentCard.getCardType() == WegeCard.CardType.BRIDGE && bridgeReplace(wegebutton) != true){
                System.out.println("Cant Place Here"); 
                return;
              }
              
              if(isFirstCardOnBoard() || isMatching(wegebutton)){
                try {
                  wegebutton.setCard(currentCard);
                } catch (Exception e) {
                  System.out.println("Can't Place Here");
                }
                currentCard = null;
                if(player[0] == 1){
                  player[0] = 2;
                  currentPlayerLabel.setText("Player 1 turn");
                }
                else{
                  player[0] = 1;
                  currentPlayerLabel.setText("Player 2 turn"); 
                }
                count[0]++;
                turnLabel.setText("Turn" + " " + count[0]);
                draw.remove(0);
                if (draw.isEmpty()) {
                  currentCard = null;
                }
                drawButton.setCard(null);
              }            
            }
          }
        });
        board[row][col] = wegebutton;
        gridPane.add(wegebutton, row, col);
      }
    }
    
    /** Initialize the UI components for the draw pile and turn information */
    FlowPane flowPane = new FlowPane();
    flowPane.getChildren().addAll(drawButton, currentPlayerLabel, turnLabel);
    flowPane.setAlignment(Pos.CENTER);
    flowPane.setHgap(15);
    flowPane.setVgap(10);
    drawButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * Handles the action event when the draw button is clicked.
       * @param event The action event.
       */
      public void handle(ActionEvent event){
        if(!draw.isEmpty()){
          nextCardButton = drawButton;
          drawButton.setCard(draw.get(0));
          drawButton.rotate();
          currentCard = drawButton.getCard();
        }
        else if(drawButton.getCard() == null){
          nextCardButton = null;
        }
      }
    });
    
    /** Initialize the draw pile with WegeCards */
    for(int i = 0; i< getLand(); i++){
      draw.add(new WegeCard(WegeCard.CardType.LAND, false, false)); 
    }
    
    for(int i = 0; i< getWater(); i++){
      draw.add(new WegeCard(WegeCard.CardType.WATER, false, false)); 
    }
    
    for(int i = 0; i< getGnomePath(); i++){
      draw.add(new WegeCard(WegeCard.CardType.LAND, true, true)); 
    }
    
    for(int i = 0; i< getGnomeLandCorner(); i++){
      draw.add(new WegeCard(WegeCard.CardType.LAND, true, false)); 
    }
    
    for(int i = 0; i< getGnomeWaterCorner(); i++){
      draw.add(new WegeCard(WegeCard.CardType.WATER, true, false)); 
    }
    
    for(int i = 0; i< getGnomeStream(); i++){
      draw.add(new WegeCard(WegeCard.CardType.WATER, true, true)); 
    }
    
    for(int i = 0; i< getCossack(); i++){
      draw.add(new WegeCard(WegeCard.CardType.COSSACK, false, false)); 
    }
    
    for(int i = 0; i< getBridge(); i++){
      draw.add(new WegeCard(WegeCard.CardType.BRIDGE, false, false)); 
    }
    Collections.shuffle(draw);
    
    for (int i = 0; i < draw.size(); i++){
      getDeck().addToFront(draw.get(i)); 
    }
    
    /** Sets up the border and stage for the game */
    border.setTop(gridPane);
    border.setBottom(flowPane);
    Scene scene = new Scene(border);
    primaryStage.setTitle("Wege");
    primaryStage.setScene(scene);
    primaryStage.show();  
  }  
  
  /**
   * The main method of the Wege class. It launches the application.
   * The games properties are changed based on the arguments
   * @param args The amount of arguments changes the board in different ways.
   *             arg length 1 changes the amount of Cossack, Bridge, GnomeStream, GnomePath, GnomeWaterCorner, GnomeLandCorner cards
   *             arg length 2 changes the number of columns and rows on the game board respectively
   *             arg length 3 does what arg length 1 and 2 do at the same time
   */
  public static void main(String[] args) {    
    
    if(args.length == 1){
      Wege.setNumRow(6);
      Wege.setNumCol(6);
      Wege.setCossack(Integer.parseInt(args[0]));
      Wege.setBridge(Integer.parseInt(args[0]));
      Wege.setLand(12);
      Wege.setWater(12);
      Wege.setGnomeStream(Integer.parseInt(args[0]));
      Wege.setGnomePath(Integer.parseInt(args[0]));
      Wege.setGnomeWaterCorner(Integer.parseInt(args[0]));
      Wege.setGnomeLandCorner(Integer.parseInt(args[0]));   
    }
    
    else if(args.length == 2){
      Wege.setNumRow(Integer.parseInt(args[1]));
      Wege.setNumCol(Integer.parseInt(args[0]));
      Wege.setCossack(3);
      Wege.setBridge(3);
      Wege.setLand(12);
      Wege.setWater(12);
      Wege.setGnomeStream(3);
      Wege.setGnomePath(3);
      Wege.setGnomeWaterCorner(2);
      Wege.setGnomeLandCorner(2);  
    }
    
    else if(args.length == 3){
      Wege.setNumRow(Integer.parseInt(args[0]));
      Wege.setNumCol(Integer.parseInt(args[1]));
      Wege.setCossack(Integer.parseInt(args[2]));
      Wege.setBridge(Integer.parseInt(args[2]));
      Wege.setLand(12);
      Wege.setWater(12);
      Wege.setGnomeStream(Integer.parseInt(args[2]));
      Wege.setGnomePath(Integer.parseInt(args[2]));
      Wege.setGnomeWaterCorner(Integer.parseInt(args[2]));
      Wege.setGnomeLandCorner(Integer.parseInt(args[2]));  
    }
    
    else{
      Wege.setNumRow(6);
      Wege.setNumCol(6);
      Wege.setCossack(3);
      Wege.setBridge(3);
      Wege.setLand(12);
      Wege.setWater(12);
      Wege.setGnomeStream(3);
      Wege.setGnomePath(3);
      Wege.setGnomeWaterCorner(2);
      Wege.setGnomeLandCorner(2);   
    }
    Application.launch(args);
  }
}


