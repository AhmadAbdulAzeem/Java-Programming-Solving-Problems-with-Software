
/**
 * Write a description of Part1 here.
 * Finding Genes in a DNA
 * @author Ahmed AbdulAzeem
 * @version 1.0
 */

import edu.duke.*;

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
    
    public StorageResource getAllGenes(String dna)
    {
        int startIndex = 0;
        StorageResource genes = new StorageResource();
        while(true)
        {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty())
                break;
            genes.add(currGene);
            
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return genes;
    }
    
    public void testgetAllGenes()
    {
        String dna = "AATGCTAACTAGCTGACTAAT";
        StorageResource genes = getAllGenes(dna);
        for(String s : genes.data())
        {
            System.out.println(s);
        }
    }
    
    public double cgRatio(String gene)
    {
        int length = gene.length();
        double count = 0.0;
        char c;
        for(int i = 0; i < length; i++)
        {
            c = gene.charAt(i);
            if(c == 'C' || c == 'G')
                count++;
        }
        return count / length;
    }
    
    public void testcgRatio()
    {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public int countCTG (String dna)
    {
        String codon = "CTG";
        int findedIndex, tempIndex = 0;
        int count = 0;
        while(true)
        {
            findedIndex = dna.indexOf(codon, tempIndex);
            if(findedIndex == -1)
                break;
            else
            {
                count++;
                tempIndex = findedIndex + codon.length();
            }
        }
        return count;
    }
    
    public void testcountCTG()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa.txt");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println(countCTG(dna)); //5
    } 
    
    public void processGenes(StorageResource sr)
    {
        int count = 0;  // the number of Strings in sr that are longer than 9 characters
        int count1 = 0; //the number of strings in sr whose C-G-ratio is higher than 0.35
        int biggestLength = 0;
        
        for(String gene : sr.data())
        {
            if(gene.length() > 60)
            {
                System.out.println("string that is longer than 9 characters " + gene);
                count++;
            }
            
            if(cgRatio(gene) > 0.35)
            {
                System.out.println("whose C-G-ratio is higher than 0.35 " + gene);
                count1++;
            }
            
            if(gene.length() > biggestLength)
                biggestLength = gene.length();
            
        }
        System.out.println("the number of Strings in sr that are longer than 60 characters " + count);
        System.out.println("the number of strings in sr whose C-G-ratio is higher than 0.35 " + count1);
        System.out.println("the longest gene in sr is " + biggestLength);
    }
    
    public void testProcessGenes()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa.txt");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("Number of genes " + sr.size());
    }
    
    
}
