public class Boss extends Enemies{
  private double v=0.1; 
  private boolean vulnerable;
  private int counter=0;
  public double hp=1;
  private double h=1;
  
  public Boss(double x, double y){
    coord[0]=x;
    coord[1]=y;
  }
  
  public boolean alive(){
    return(hp>0);
  }
  
  public boolean vulnerable(){
    return(vulnerable);
  }
  
  public void draw(){
    if(!vulnerable)
      StdDraw.picture(coord[0]+0.05, coord[1]-0.5, "boss.png", 1.9, 3);
    else StdDraw.picture(coord[0]+0.05, coord[1]-0.3, "bossOpen.png", 3.3, 2.6);
  }  
  
  public boolean open(){
    counter++;
    if (counter==200){
      vulnerable=true;
      return true;
    }
    else if (counter==255){
      vulnerable=false;
      counter=0;      
    }
    return false;
  }
  
  public void move(){
    coord[0]+=v;  
    if (coord[0]>8 || coord[0]<2)
      v=-v;
  }
  
  public void bars(){
    if(h>hp) h=h-0.05;
    StdDraw.setPenColor(255, 0, 0);
    if(h>0)
      StdDraw.filledRectangle(coord[0],coord[1]+0.95, h*2, 0.1);
  }
}
