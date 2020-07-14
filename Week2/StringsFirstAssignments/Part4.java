/**
 * Write a description of Part1 here.
 * Finding  a Gene in DNA
 * 
 * @author Ahmed AbdulAzeem 
 * @version 7/10/2020
 */
import edu.duke.*;
import java.io.File;

public class Part4 {
    public void printYoutubeURL()
    {
        FileResource fr = new FileResource();
        int index;
        int startQoute, stopQoute;
        for(String line : fr.lines())
        {
            index=line.toLowerCase().indexOf("youtube.com");
            if(index != -1)
            {
                startQoute = line.lastIndexOf("\"",index);
                stopQoute = line.indexOf("\"", startQoute + 1);
                System.out.println(line.substring(startQoute, stopQoute+1));
            }
        }
    }
    
    public void test()
    {
        printYoutubeURL();
    }
}
