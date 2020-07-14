
/**
 * Write a description of Part3 here.
 * Count number of Genes
 * @author Ahmed AbdulAzeem
 * @version 1.0
 */
public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1)
        {
            int diff = currIndex - startIndex;
            if(diff % 3 == 0)
                return currIndex;
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where)
    {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if(minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    
    
    public int countGenes(String dna)
    {
        int count = 0;
        int startIndex = 0;
        
        while(true)
        {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty())
                break;
            count++;
            
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }
    
    public void testCountGenes()
    {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
}
