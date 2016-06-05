import java.awt.event.KeyEvent;
import javax.sound.sampled.*;

public class Attack{

    public static int level(int level){
      
      player player1=new player();
      Invader[][] invaders =new Invader [5][3];
      double[][] drop=new double[5][2];
      for(int i=0; i<5; i++)
        drop[i][1]=-2;
      
      for(int i=0; i<5; i++)
        for(int j=0; j<3; j++){
        invaders[i][j]=new Invader();
        invaders[i][j].coord[0]=1+i;
        invaders[i][j].coord[1]=6.5+j;
      }
      
      while(player1.hp>0 && player1.killed<15){
        StdDraw.clear(StdDraw.BLACK);
                
        for(int i=0; i<5; i++){
          for(int j=0; j<3; j++){
            //hit
            if(display.crash(player1.b, invaders[i][j].coord, 0.7, invaders[i][j].dead==false)){
              invaders[i][j].dead=true;
              player1.b[1]=11;
              player1.killed++;
            }
            
            double[] temp ={player1.l, invaders[i][j].coord[1]};
            if(display.crash(temp, invaders[i][j].coord, 0.3, invaders[i][j].dead==false)){
              invaders[i][j].dead=true;
              player1.killed++;
            }
            
            //invaders
            StdDraw.setPenColor(StdDraw.BLUE);
            Invader.draw(invaders[i][j].coord, invaders[i][j].dead);
            invaders[i][j].move();
          
          if(invaders[i][j].dead==false && invaders[i][j].coord[1]<0.5)
              return 0;
          }
          //dropping bombs
          if(drop[i][1]<-2 && Math.random()<0.01){
            if (invaders[i][0].dead == false){
              drop[i][0]= invaders[i][0].coord[0];
              drop[i][1]= invaders[i][0].coord[1];
            }else if (invaders[i][1].dead == false){
              drop[i][0]= invaders[i][1].coord[0];
              drop[i][1]= invaders[i][1].coord[1];
            }else if(invaders[i][2].dead == false){
              drop[i][0]= invaders[i][2].coord[0];
              drop[i][1]= invaders[i][2].coord[1];
            }
          }
          
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.filledCircle( drop[i][0], drop[i][1], 0.1);
          
          if(display.crash(player1.coord, drop[i], 1, true)){
            player1.hp=player1.hp-(1-Math.abs(player1.coord[0]-drop[i][0]))/1.5;
            drop[i][1]= -3;
          }
          drop[i][1]= drop[i][1]-0.3;
        }
        
        //player 1
        player1.move();
        player1.draw();
        player1.shoot();      
        player1.bars(8.5, 9.5);     
        
        while(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE))
        {
          StdDraw.text(1, 9.7, "paused");
          StdDraw.text(1, 8.5, player1.shots + " shots");
          StdDraw.text(1, 8.9, player1.killed + " killed");
          StdDraw.text(1, 9.3, "level " + level);
          StdDraw.text(1.5, 8.1, "wasted shots: " + (player1.shots - player1.killed));
          StdDraw.show(30);
        }
        StdDraw.show(30-level*3);
        
    }
      if (player1.shots>0 && player1.hp>0) return player1.score();
      else return 0;
}
    
    public static void main(String[] args)
    {    
      StdDraw.setXscale(0, 10);
      StdDraw.setYscale(0, 10);
      int level=1, score=0, total=0;
      Clip wav=audio.loop("medly.mp3");
      boolean silence=false;
      StdDraw.setPenRadius(0.005);
      
      while(true){  
        if(silence==true){
          wav=audio.loop("medly.mp3");
          silence = false;}
        
        StdDraw.picture(5, 5, "background.jpg", 10, 10);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(1, 5, "GAME MODE");
        if(level<11 && level>0)
          StdDraw.text(1, 4.5, "level " + level);
        if(level==11)
          StdDraw.text(1, 4.5, "Bossfight"); 
        if(level==12)
          StdDraw.text(1, 4.5, "crazy mode");
        if(level==13)
          StdDraw.text(1, 4.5, "campaign");
        StdDraw.text(8, 5, "last score: " + score);
        StdDraw.text(8, 4.5, "total score: " + total);
        
        if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && level<13){
          level++;
          StdDraw.show(100);}
        if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && level>1){
          level--;
          StdDraw.show(100);}     
        
        if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && level<=10){
          score=level(level);
          if(score==0){
            silence = display.gameOver(wav);
          }
          total = total+score;
        }
        
        if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && level==13){
          score=0;
          for(int i=1; i<=10;i++){
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(5, 5, "LEVEL " + i);
            StdDraw.show(1000);
            int x=level(i);
            if (x==0){
              silence = display.gameOver(wav);
              break;
            }
            score = score+x;
          } 
          total=total+score;
        }
        StdDraw.show(30);
      }
    }
  }
