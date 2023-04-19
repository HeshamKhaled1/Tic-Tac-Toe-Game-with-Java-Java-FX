package tic.tac.toe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Deebo
 */
public class TicTacToe extends Application {
    
    private ImageView background;
    private ImageView innerbackground;
    private MediaPlayer mediaplayer;
    
    
    // Indicate whose Turn 
   char ch;
   char Turn = ch;
  
   Square [][] Square=  new Square [3][3];
  
  // Game Status 
   Label Information = new Label(Turn +"'s turn to play");
      
   BorderPane borderPane1 = new BorderPane(); // The starting stage
   BorderPane borderPane2 = new BorderPane(); // The game stage
   GridPane GPane = new GridPane();


    @Override
      public void start(Stage primaryStage) { 
           Information.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
          Media media=null;
          try {
           media=new Media(new File("sound.mp3").toURI().toString());
          } catch (Exception ex){
             System.out.println("File Not Found");
          }
           mediaplayer=new MediaPlayer(media);
           //mediaplayer.play();
           
        Image image=null;
        try {
            image = new Image(new FileInputStream("outerbackground.jpeg"));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        background = new ImageView(image);
        background.setFitHeight(500);
        background.setFitWidth(500);
        
        
        Button btnStart = new Button("Start Playing");
        Button xplayer = new Button(" X ");
        Button oplayer = new Button(" O ");
        
        //Buttons Style
        xplayer.setFont(Font.font("Times new roman", 24.0));
        oplayer.setFont(Font.font("Times new roman", 24.0));
        btnStart.setFont(Font.font("Times new roman", 24.0));
        
        btnStart.setVisible(false); // To be unvisible until choosing X or O
        
        // Label Style
        Text l = new Text(160,230,"Start Playing With");
        l.setFont(Font.font("Times new roman", 24.0));
          
        HBox hb = new HBox(130);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(xplayer,oplayer);

        HBox hbox1 =new HBox();  
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(btnStart);

        HBox hbox2 =new HBox();  
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(l);
        
        borderPane1.setPadding(new Insets(150,10,30,10));
        borderPane1.getChildren().addAll(background,l);
        borderPane1.setCenter(hb);
        borderPane1.setBottom(hbox1);
        
        Image innerImage=null;
        try {
            innerImage=new Image(new FileInputStream("innerbackground.jpeg"));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        innerbackground = new ImageView(innerImage);
        
        
        
        xplayer.setOnAction(e->{
            ch = 'X';
        btnStart.setVisible(true);
        oplayer.setVisible(false);

        });
        
        oplayer.setOnAction(e->{
            ch = 'O';
        btnStart.setVisible(true);
        xplayer.setVisible(false);
        });
          
      GPane.setHgap(5);
      GPane.setVgap(5);
      
      for (int i = 0; i < 3; i++) 
        for (int j = 0; j < 3; j++) {
          Square [i][j] = new Square();
          GPane.add(Square [i][j], j, i); // column then row
        }
    
     
    Button btnExit = new Button("Exit");
    Button btnPlayAgain = new Button("Play again");
    
//    Buttons Style
    btnExit.setFont(Font.font("Times new roman", 18.0));
    btnPlayAgain.setFont(Font.font("Times new roman", 18.0));    
    
//    Label Style
    Information.setFont(Font.font("Times new roman", 18.0));
    
    HBox hb1 = new HBox(130);
    hb1.setPadding(new Insets(0,0,10,0));
    hb1.setAlignment(Pos.CENTER);
    hb1.getChildren().add(Information);
    
    HBox hb2 = new HBox(130);
    hb2.setPadding(new Insets(10,5,0,5));
    hb2.setAlignment(Pos.CENTER);
    hb2.getChildren().addAll(btnExit , btnPlayAgain);
    
    borderPane2.setPadding(new Insets(10,10,10,10));
    borderPane2.getChildren().add(innerbackground);
    borderPane2.setCenter(GPane);
    borderPane2.setTop(hb1);
    borderPane2.setBottom(hb2); 
    
    btnExit.setOnAction(e->{
         primaryStage.close();
    });
    
    
    btnPlayAgain.setOnAction(e->{
      str(ch);
    });
    
    
    btnStart.setOnAction(e->{
         
           str(ch);
            
    Scene s = new Scene(borderPane2, 500, 500);
    primaryStage.setTitle("TIC-TAC-TOC Game");
    primaryStage.setScene(s); 
    primaryStage.setResizable(false); //in order not to change dimensions
    primaryStage.show();
    });
    
    Scene s2 = new Scene(borderPane1, 500, 500);
    primaryStage.setTitle("TIC-TAC-TOC Game");
    primaryStage.setScene(s2); 
    primaryStage.setResizable(false); //in order not to change dimensions
    primaryStage.show();
   
  }
      
      
      
    //Method to remove all and start again
      public void str(char z){
        Turn = z;
        Information.setText( Turn + "'s turn to play");
      
      borderPane2.getChildren().removeAll(GPane);
      for (int i = 0; i < 3; i++) 
      for (int j = 0; j < 3; j++) {
          Square [i][j] = new Square ();
        GPane.add(Square [i][j], j, i);
        }
        borderPane2.setCenter(GPane);
        
    }
      
  
  public boolean isFull() { 
    for (int i = 0; i < 3; i++) 
      for (int j = 0; j < 3; j++) 
        if (Square [i][j].getchoosedChar() == ' ') 
          return false;  
    return true; 
  } 
  
   
  public boolean isWon(char c) {
    //Rows
    for (int i = 0; i < 3; i++) 
      if (Square [i][0].getchoosedChar() == c 
          && Square [i][1].getchoosedChar() == c 
          && Square [i][2].getchoosedChar() == c) { 
        return true; 
      } 
    //Columns
    for (int j = 0; j < 3; j++) 
      if (Square [0][j].getchoosedChar() ==  c 
          && Square [1][j].getchoosedChar() == c 
          && Square [2][j].getchoosedChar() == c) { 

        return true; 
      } 
  
    //Diagonals
    if (Square [0][0].getchoosedChar() == c  
        && Square [1][1].getchoosedChar() == c         
        && Square [2][2].getchoosedChar() == c) {

      return true; 
    } 
  
    if (Square [0][2].getchoosedChar() == c 
        && Square [1][1].getchoosedChar() == c 
        && Square [2][0].getchoosedChar() == c) { 
     
      return true; 
    } 
  
    return false; 
  }
  
  
  
  public class Square extends Pane { 
    private char choosedChar = ' ';
 
    public Square() {
    this.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    this.setPrefSize(157,157);
    this.setOnMouseClicked(e -> handleMouseClick()); 
    } 
    
   
    public char getchoosedChar() { 
      return choosedChar; 
    } 
  
    public void setchoosedChar(char c) { 
      choosedChar = c; 
      
      if (choosedChar == 'X') { 
        Line line1 = new Line(30, 30,  
          this.getWidth() - 30, this.getHeight() - 30); 
        
        Line line2 = new Line(30, this.getHeight() - 30,  
         this.getWidth() - 30, 30); 
      
        this.getChildren().addAll(line1, line2);  
      } 
      else if (choosedChar == 'O') {
        Circle c1 = new Circle (this.getWidth() / 2,  
        this.getHeight() / 2,  this.getWidth() / 2 - 30);
        c1.setFill(Color.SKYBLUE);
        c1.setStroke(Color.BLACK);
        getChildren().add(c1);  
      }

    }
  
    private void handleMouseClick() { 
      if (choosedChar == ' ' && Turn != ' ') { 
        setchoosedChar(Turn); 
  
       
        if (isWon(Turn)) { 
          Information.setText( Turn + " Player won! Game Over"); 
          Turn = ' '; 
        } 
        else if (isFull()) { 
          Information.setText(" Draw!  Game Over"); 
          Turn = ' ';  
        } 
        
        else { 
          Turn = (Turn == 'X') ? 'O' : 'X'; 
          Information.setText(Turn + "'s turn"); 
        } 
      } 
    } 
  } 
    
  
  public static void main(String[] args) { 
    launch(args); 
  } 
}