
/**
 * Write a description of Part1 here.
 * 
 * @author Ahmed AbdulAzeem 
 * @version 1.0
 */
public class Part1 {
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
    
    public void testFindStopCodon()
    {
        //            012345678901234567890123456789012345678901234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        System.out.println(findStopCodon(dna, 0, "TAA"));
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
    
    public void testFindGene()
    {
        String dna = "ATGxxxyyyzzzTAAxxxmmmyyyTAGgggjjjjkkkTGA";
        System.out.println(findGene(dna, 0));
        dna = "ATGxxxyyyzzTAAxxxmmmyyyTAGgggjjjjkkkTGA";
        System.out.println(findGene(dna, 0));
        dna = "ATGxxxyyyzzTAAxxxmmmyyTAGgggjjjjkkkTGA";
        System.out.println(findGene(dna, 0));
    }
    
    public void printAllGenes(String dna)
    {
        int startIndex = 0;
        
        while(true)
        {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty())
                break;
            System.out.println(currGene);
            
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public void testprintAllGenes()
    {
        String dna = "AATGCTAACTAGCTGACTAAT";
        printAllGenes(dna);
    }

}
