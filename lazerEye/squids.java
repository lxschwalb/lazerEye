public class squids extends Enemies{
  private double trajectory=0;
  
  public squids(){
    coord[0]=-1;
    coord[1]=-1;
    dead=true;
  }
  
  public void reset(double[] spawn){
    coord[0]=spawn[0];
    coord[1]=spawn[1]-0.5;
    trajectory=0;
    dead=false;
  }
  
  public void draw(){
    if(!dead)
      StdDraw.picture(coord[0], coord[1], "squid.png", 0.5, 1.5, trajectory*-57.296);
    else  StdDraw.picture(coord[0], coord[1], "calamari.png", 1, 0.5);
  }
  
  public void move(double[] target){
    if(Math.atan((target[0]-coord[0])/(target[1]-coord[1]))<trajectory && trajectory>-1.3)
      trajectory-=0.025;
    if(Math.atan((target[0]-coord[0])/(target[1]-coord[1]))>trajectory && trajectory<1.3)
      trajectory+=0.025;    
    coord[0]-=Math.sin(trajectory)*0.25;
    coord[1]-=Math.cos(trajectory)*0.25;
    
  }
}
