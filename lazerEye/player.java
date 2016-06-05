import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class player{
  
  public double hp=1;
  private double h=1;
  private double power=1;
  public double[] coord={5, 0};
  private double rocket = 0;
  public boolean l=false; //laser
  public int kills;
  public int shots;  
  private ArrayList<bullets> b=new ArrayList<bullets>();
  private int reloadTime=0;
  
  private class bullets{
    double angle;
    double[] coord=new double[2];
    
    public bullets(double a, double x, double y){
      angle=a;
      coord[0]=x;
      coord[1]=y;
    }
  }
  
  public void regen(){
    if(hp>0.85){
      hp=1;
      h=1;
    }
    else{
      h+=0.15;
      hp+=0.15;
    }
    
  }
  
  public int bulletAmount(){
    return b.size();
  }  
  public double[] getBullet(int n){
    bullets temp=b.get(n);
    return temp.coord;
  }  
  public void loseBullet(int n){
    b.remove(n);
  }
  
  
  public int score(){
    if (shots==0) return 0;
    return (int)((double)Math.pow(kills, 2)/(double)shots*1000);
  }
  
  public boolean bars(double x, double y){
    if(h>hp)h=h-0.05;
    StdDraw.setPenColor(255, 0, 0);
    if(h>0)
      StdDraw.filledRectangle(x-1+h,y, h, 0.2);
    else return true;
    
    if(power>0){
      StdDraw.setPenColor((int)((1-power)*(255)), (int)((1-power)*(255)), (int)(power*255));
      StdDraw.filledRectangle(x-1+power,y-0.5, power, 0.2);
    }    
    StdDraw.setPenColor(255, 255, 255);
    StdDraw.rectangle(x,y, 1, 0.2);
    StdDraw.rectangle(x,y-0.5, 1, 0.2);
    if(power<1)
      power+=0.008;
    if(power>1)
      power=1;
    return false;
  }
  
  public void move(){
    if (StdDraw.isKeyPressed(KeyEvent.VK_X) && coord[0]<=9)
      coord[0]+=0.3;
    if (StdDraw.isKeyPressed(KeyEvent.VK_Z)&& coord[0]>=1)
      coord[0]-=0.3;
    if (StdDraw.isKeyPressed(KeyEvent.VK_S) && rocket<=1.5)
      rocket+=0.1;
    if (StdDraw.isKeyPressed(KeyEvent.VK_A) && rocket>=-1.5)
      rocket-=0.1;    
  }
  public void draw(){
    StdDraw.picture(coord[0], coord[1]+0.5, "player.png",2, 1.5);
    StdDraw.picture(coord[0]-0.8, coord[1],"arm.png", 0.5, 5, rocket*-57.296);
  }
  
  
  public void laser(){
    if(StdDraw.isKeyPressed(KeyEvent.VK_C) && power==1){
      l=true;
      shots=shots+2;
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.line(coord[0]-0.15, 1, coord[0]-0.15, 10);
      StdDraw.line(coord[0]+0.15, 1, coord[0]+0.15, 10);
      power-=1;
    }else l=false;
  }  
  public void shoot(){    
    if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && power>0.2 && reloadTime>5){
      b.add(new bullets(rocket, coord[0]-0.8+2*Math.sin(rocket), coord[1] + 2*Math.cos(rocket)));
      shots++;
      power-=0.2;
      reloadTime=0;
    }
    reloadTime++;
    
    if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)&&power>0.05){
      for(int i=0; i<b.size(); i++){
        bullets temp =b.get(i);
        b.set(i, new bullets(rocket, temp.coord[0]+0.1*Math.sin(rocket), temp.coord[1]+0.1*Math.cos(rocket)));
        power-=0.03;
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(temp.coord[0], temp.coord[1], 0.1);
      }
    }else {
      for(int i=0; i<b.size(); i++){
        bullets temp =b.get(i);
        b.set(i, new bullets(temp.angle, temp.coord[0]+0.5*Math.sin(temp.angle), temp.coord[1]+0.5*Math.cos(temp.angle)));
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(temp.coord[0], temp.coord[1], 0.1);
        if(temp.coord[0]>10 || temp.coord[0]<0 || temp.coord[1]>11)
          b.remove(i);
      }
    } 
  }
}