public class Boss extends Enemies{
  private double v=0; 
  private boolean vulnerable=false;
  private int counter=0;
  public double hp=1;
  
  public boss(double x, double y){
    coord[0]=x;
    coord[1]=y;
  }
  
  public void draw(){
    if(!vulnerable)
      StdDraw.picture(coord[0], coord[1], "boss.png", 2, 2);
    else StdDraw.picture(coord[0], coord[1], "bossOpen.png", 2, 2);
  }
  
  public void move(double x){
    coord[0]+=v;
    v+=(Math.random()-0.5);
    if (coord[0]>9 || (coord[0]<1)
          v=-v;
  }  
}
