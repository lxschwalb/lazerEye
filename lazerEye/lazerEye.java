import java.awt.event.KeyEvent;

public class lazerEye{
  
  public static int level(int level){
    
    player player1=new player();
    Octopi[][] invaders =new Octopi [5][3];
    Octopi.initialise(7, level);
    for(int i=0; i<5; i++)
      for(int j=0; j<3; j++){
      invaders[i][j]=new Octopi(1+i);
    }
    
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.text(5, 5, "LEVEL " + level);
    StdDraw.show(1000);
    
    
    while(!startMenu.GO && player1.kills<15){
      StdDraw.clear(StdDraw.BLACK);
      
      for(int i=0; i<5; i++){
        for(int j=0; j<3; j++){
          
          //hit
          for(int k=0; k<player1.bulletAmount(); k++)
            if(display.crash(player1.getBullet(k), invaders[i][j].coord, 0.5, invaders[i][j].dead==false)){
            invaders[i][j].dead=true;
            player1.loseBullet(k);
            player1.kills++;
          }
          //laser hit
          if(player1.l &&  Math.abs(invaders[i][j].coord[0]-player1.coord[0])<0.5 && !invaders[i][j].dead){
            invaders[i][j].dead=true;
            player1.kills++;
          }
          
          //invaders        
          invaders[i][j].move(j);
          invaders[i][j].draw();               
          if(invaders[i][j].bottom(player1.coord))
            startMenu.GO=true;
          
          invaders[i][j].drop(level*0.0015);
          if(display.crash(player1.coord, invaders[i][j].drop, 1, true)){
            player1.hp-=(1-Math.abs(player1.coord[0]-invaders[i][j].drop[0]))/1.5;
            invaders[i][j].drop[1]= -3;
            
          }}}
      Octopi.turn();
      
      //player 1
      player1.move();
      player1.draw();
      player1.shoot(); 
      player1.laser();
      if(player1.bars(8.5, 9.5)) startMenu.GO=true;
      
      StdDraw.text(5, 9.7, "score " + (startMenu.score[0] + player1.score()));
      
      if(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) display.pause(player1.shots, player1.kills, level);
      StdDraw.show(30); 
    }
    return player1.score();
  }
  
  public static int BossFight(){
    player player1=new player();
    Boss neptune=new Boss(5, 9);
    squids squid= new squids();
    
    
    while(!startMenu.GO && neptune.alive()){
      StdDraw.clear(StdDraw.BLACK);
      
      //player 1
      player1.move();      
      player1.shoot(); 
      player1.laser();
      if(player1.bars(8.5, 1)) startMenu.GO=true;
      player1.draw();
      
      //squids
      if(!squid.dead) squid.move(player1.coord);
      squid.draw();
      if(squid.bottom()) squid.dead=true;
      
      if(squid.dead && (Math.abs(squid.coord[0]-player1.coord[0])<0.5)){
        squid.coord[0]=-1;
        player1.regen();
      }
      if(display.crash(player1.coord, squid.coord, 1.5, !squid.dead)){
        player1.hp-=0.6;
        squid.coord[0]=-1;
        squid.dead=true;
      }

        
        //boss
        neptune.move();
        neptune.draw(); 
        neptune.bars();
        if(neptune.open())
          squid.reset(neptune.coord);   
        
        if(neptune.vulnerable() && player1.l && Math.abs(neptune.coord[0]-player1.coord[0])<0.7){
          neptune.hp-=0.35;
          player1.kills+=3;
        }
        
        StdDraw.setPenColor(255, 255, 255);     
        StdDraw.text(5, 9.7, "score " + (startMenu.score[0] + player1.score()));      
        if(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) display.pause(player1.shots, player1.kills, 0);
        StdDraw.show(30); 
      }
      return player1.score();
    }
  }
