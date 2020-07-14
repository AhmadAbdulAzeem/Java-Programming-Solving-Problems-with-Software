/**
 * Write a description of Part1 here.
 * Finding  a Gene in DNA
 * 
 * @author Ahmed AbdulAzeem 
 * @version 7/10/2020
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb)
    {
        int findedIndex, tempIndex = 0;
        int count = 0;
        while(true)
        {
            findedIndex = stringb.indexOf(stringa, tempIndex);
            if(findedIndex == -1)
                break;
            else
            {
                count++;
                tempIndex = findedIndex + stringa.length();
            }
        }
        if(count >= 2)
            return true;
        return false;
    }
    
    public String lastPart(String stringa, String stringb)
    {
        int index = stringb.indexOf(stringa);
        if(index == -1)
            return stringb;
        return stringb.substring(index+stringa.length());
    }
    
    public void testing()
    {
        boolean test = twoOccurrences("a", "banana");
        System.out.println(test);
        
        //test lastPart
        String stringa = "an";
        String stringb = "banana";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + lastPart(stringa, stringb));
    }
}
