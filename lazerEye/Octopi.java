public class Octopi extends Enemies{
  private static double v; 
  private static double h;
  private static boolean turn=false;
  public double[] drop={-2, -2};
  
  public static void initialise(double height, double level){
  h=height;
  v=Math.pow(1.1, level)*0.1;
  }
  
  public Octopi(double x){
    coord[0]=x;
    }
  
  public void draw(){
    if(dead==false)
      StdDraw.picture(coord[0], coord[1], "octopi.png", 1.2, 1.2);
  }
  
  public void move(double x){
    coord[0]+=v;
    coord[1]=h+x;
    if(!dead && coord[0]>=9.5 && !turn){
      turn=true;
      h-=0.3;
    }else if(!dead && coord[0]<=0.5 && !turn){
      turn=true; 
      h-=0.3;
    }    
  }
  
  public static void turn(){
    if(turn) v=-v;
    turn=false;
  }
     
   public void drop(double p){
     if(drop[1]<-2 && Math.random()<p && !dead){
       drop[0]=coord[0];
       drop[1]=coord[1];
     }
     
     StdDraw.setPenColor(StdDraw.BLUE);
     StdDraw.filledCircle( drop[0], drop[1], 0.1);
     
     drop[1]= drop[1]-0.3;
   }
   }
