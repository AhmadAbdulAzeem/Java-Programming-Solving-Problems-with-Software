
/**
 * Write a description of Part1 here.
 * Finding  a Gene in DNA
 * 
 * @author Ahmed AbdulAzeem 
 * @version 7/10/2020
 */
public class Part1 {
    public String findSimpleGene(String dna)
    {
        String result = "";
        int length;
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
            return "";
        int endIndex = dna.indexOf("TAA", startIndex+3);
        if(endIndex == -1)
            return "";
        length = endIndex - startIndex + 3;
        if(length % 3 == 0)
        {
            result = dna.substring(startIndex, endIndex+3);
            return result;
        }
        return "";
    }
    
    void testSimpleGene()
    {
        String dna, gene;
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        gene = findSimpleGene(dna);
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + gene);
    }
    
}
