import javax.sound.sampled.*;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class startMenu{

  public static boolean GO=false;
  public static int[] score=new int[4];

  public static void main(String[] args)
  {
    StdDraw.setCanvasSize(1000,900);
    StdDraw.setXscale(0, 10);
    StdDraw.setYscale(0, 10);
    int level=0;
    Clip wav=audio.loop("medly.wav");
    StdDraw.setPenRadius(0.01);
    StdDraw.setFont(new Font("Serif", Font.BOLD, 26));


    while(true){
      if(audio.silence())
        wav=audio.loop("medly.wav");

      StdDraw.picture(5, 5, "background.jpg", 10, 10);
      StdDraw.setPenColor(StdDraw.BLACK);

      if(level==-1)
        StdDraw.text(5, 7, "Bossfight");
      else if(level==0)
        StdDraw.text(5, 7, "campaign");
      else  StdDraw.text(5, 7, "level " + level);

      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.text(8, 5, "S for scores");
      StdDraw.text(2, 5, "C for controls");

      if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && level<30){
        level++;
        StdDraw.show(100);}
      if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && level>-1){
        level--;
        StdDraw.show(100);}

      if(StdDraw.isKeyPressed(KeyEvent.VK_C))
        display.control();
      if(StdDraw.isKeyPressed(KeyEvent.VK_S))
        display.leaderBoard(score[1], score[2], score[3]);


      if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
        score[0]=0;

        if (level>0){
          //normal mode
          score[0]=0;
          do{
            score[0]=score[0]+lazerEye.level(level);
            level++;
          }while(!GO);
          audio.stop(wav);
          display.gameOver();

        }else if(level==0){
          //campaign
          display.story();
          for(int i=1; i<=10;i++){
            score[0] = score[0]+lazerEye.level(i);
            if (GO){
              audio.stop(wav);
              display.gameOver();
              break;
            }
          }
          if(!GO) bossFight(wav);
        }

        else if(level==-1)
          //bossfight
          bossFight(wav);

        display.sortScores(score);
      }
      GO=false;
      StdDraw.show(30);
    }
  }

  private static void bossFight(Clip wav){

    audio.stop(wav);
    wav=audio.loop("boss.wav");
    display.bossStory();
    score[0] += lazerEye.BossFight();
    audio.stop(wav);
    if (GO)
      display.gameOver();
    else
      display.victory();
  }
}
