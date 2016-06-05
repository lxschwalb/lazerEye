import java.awt.event.KeyEvent;

public class display{
  
  public static void gameOver(){
    audio.play("game over.wav");
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.picture(5, 7.5, "octopi.png", 7, 7);
    for(int i=0; i<255; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 5, "GAME OVER");
      StdDraw.show(15);
    }
  }
  
  public static void victory(){
    audio.play("win.wav");
    StdDraw.clear(StdDraw.BLACK);
    for(int i=0; i<255; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 5, "VICTORY");
      StdDraw.show(15);
    }
  }
  
  public static void pause(int shots, int kills, int level){
    
    if(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)){
      boolean pause=true;
      while(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)){
        StdDraw.show(30);
      }      
      while(pause)
      {
        StdDraw.textLeft(0.5, 9.7, "paused");
        
        if(level==0)
          StdDraw.textLeft(0.5, 9.3, "Boss Fight");
        else{
          StdDraw.textLeft(0.5, 9.3, "level " + level);
          StdDraw.textLeft(0.5, 8.9, kills + " kills");
          StdDraw.textLeft(0.5, 8.5, shots + " shots");
          StdDraw.textLeft(0.5, 8.1, "wasted shots: " + (shots - kills));
        }
        
        StdDraw.textLeft(0.5, 7.5, "Press q to quit");
        
        while(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE))
          pause=false;
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
          startMenu.GO=true;
          pause=false;
        }
        StdDraw.show(30);
      }
    }
  }
  
  public static boolean crash(double[] A, double[] B, double R, boolean exist){
    double r=Math.sqrt(Math.pow(A[0]-B[0],2)+Math.pow(A[1]-B[1],2));
    if(r<R && exist){
      for(int i=0; i<20; i++){
        if(Math.random()<0.7) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(B[0]-0.5+Math.random(), B[1]-0.5+Math.random(), Math.random());
      }return true;
    }else return false;
  }
  
  public static void story(){
    StdDraw.clear(StdDraw.BLACK);    
    for(int i=0; i<255; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 5, "It has been nine years since the first octopus took to the sky.");
      StdDraw.show(15);
    }
    StdDraw.show(1000);
    
    StdDraw.clear(StdDraw.BLACK);
    for(int i=0; i<200; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 5.5, "Ever since, humans lived in a dystopia under");
      StdDraw.text(5, 5, "strict laws of the eight legged oppressors.");
      StdDraw.show(15);
    }
    StdDraw.show(700);
    
    for(int i=0; i<255; i+=2){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 4, "But there is hope yet...");
      StdDraw.show(10);
    }
    StdDraw.show(1000);
    
    StdDraw.clear(StdDraw.BLACK);
    for(int i=0; i<200; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 6.5, "A marine officer discovered one day");
      StdDraw.text(5, 6, "that he could shoot laser beams");
      StdDraw.text(5, 5.5, "from his eyes.");
      StdDraw.show(30);
    }
    for(int i=0; i<200; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 4, "He swore to use his power to overthrow the octopus empire.");
      StdDraw.show(30);
    }
    StdDraw.show(1000);
  }
  
  public static void bossStory(){
    StdDraw.clear(StdDraw.BLACK);
    for(int i=0; i<200; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 6, "King Neptune noticed that his octopi stood no chance");
      StdDraw.text(5, 5.5, "so he came to take care of business himself.");
      StdDraw.show(30);
    }
    for(int i=0; i<200; i++){
      StdDraw.setPenColor(i, i, i);
      StdDraw.text(5, 4, "He was certain he could take down the marine officer,");
      StdDraw.text(5, 3.5, "because, as we all know, his beard is impervious to bullets.");
      StdDraw.show(30);
    }
    StdDraw.show(1000);
  }
  
  public static void control(){
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.text(5, 7, "Rotate arm with A and S");
    StdDraw.text(5, 6, "Move with Z and X");
    StdDraw.text(5, 5, "Shoot laser with C if power is full");
    StdDraw.text(5, 4, "Shoot bullets with Spacebar");
    StdDraw.text(5, 3, "While pressing SHIFT Curve bullets with A and S");
  }
  
  public static void sortScores(int[] scores){
    int hold;
    for(int i=0; i<scores.length; i++)
      for(int j=1; j<scores.length; j++)
      if(scores[j-1]>scores[j]){
      hold=scores[j-1];
      scores[j-1]=scores[j];
      scores[j]=hold;
    }        
  }
  
  public static void leaderBoard(int one, int two, int three){
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.text(5, 7, "1.........."+three);
    StdDraw.text(5, 6, "2.........."+two);
    StdDraw.text(5, 5, "3.........."+one);
  }
}