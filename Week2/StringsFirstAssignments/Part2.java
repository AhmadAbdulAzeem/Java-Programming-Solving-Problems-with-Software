/**
 * Write a description of Part1 here.
 * Finding  a Gene in DNA
 * 
 * @author Ahmed AbdulAzeem 
 * @version 7/10/2020
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String endCodon)
    {
        String result = "";
        boolean isLower = true;
        if(dna.startsWith("A") || dna.startsWith("T") || dna.startsWith("C") || dna.startsWith("G"))
        {
            isLower = false;
        }
        else
        {
            dna.toUpperCase();
        }
        int length;
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1)
            return "";
        int endIndex = dna.indexOf(endCodon, startIndex+3);
        if(endIndex == -1)
            return "";
        length = endIndex - startIndex + 3;
        if(length % 3 == 0)
        {
            result = dna.substring(startIndex, endIndex+3);
            if(false)
                result.toLowerCase();
            return result;
        }
        return "";
    }
    
    void testSimpleGene()
    {
        String dna, gene;
        dna = "atgctataa";
        gene = findSimpleGene(dna,"atg", "taa");
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + gene);
    }
}
