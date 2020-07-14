
/**
 * Write a description of Part2 here.
 * To determine how many occurrences of a string appear in another string.
 * @author Ahmed AbdulAzeem 
 * @version 1.0
 * 
 */
public class Part2 {
    public int howMany(String stringa, String stringb)
    {
        int length = stringa.length();
        int startIndex = 0;
        int currIndex;
        int count = 0;
        while(true)
        {
            currIndex = stringb.indexOf(stringa, startIndex);
            if(currIndex == -1)
                break;
            count++;
            startIndex = currIndex + length;
        }
        return count;
    }
    
    public void testHowMany()
    {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
}
