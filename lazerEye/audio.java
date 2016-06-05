import javax.sound.sampled.*;
import java.io.*;

public class audio{
  
  private static boolean silence = true;
  
  public static Clip play(String filename)
  {
    Clip wav= null; 
    try
    {
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(filename)));
      clip.start();
      wav=clip;
    }
    catch (Exception exc)
    {
      exc.printStackTrace(System.out);
    }return wav;
  }
  
  public static Clip loop(String filename)
  {
    Clip wav= null; 
    silence=false;
    try
    {
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(filename)));
      clip.loop(100);
      wav=clip;      
    }
    
    catch (Exception exc)
    {
      exc.printStackTrace(System.out);
    }return wav;
  }
  
  public static boolean silence(){
    return silence;
  }
  
  public static void stop(Clip wav){
    wav.stop();
    silence=true;
  }
}