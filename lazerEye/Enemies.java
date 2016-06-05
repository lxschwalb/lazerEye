public class Enemies{
  public double[] coord=new double[2];
  public boolean dead=false;
   
  
  public boolean bottom(double[] player){
    if(dead==false && coord[1]<0.5) return true;
    if(display.crash(player, coord, 1.5, !dead)) return true;
    else return false;  
  }
}
