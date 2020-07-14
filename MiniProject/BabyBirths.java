
/**
 * Write a description of BabyBirths here.
 * 
 * @author Ahmed AbdulAzeem 
 * @version 1.0
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    public void totalBirths()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int femaleTotalBirths = 0;
        int maleTotalBirths = 0;
        int totalBirths = 0;
        
        for(CSVRecord record : parser)
        {
            
            
            if(record.get(1).equals("M"))
            {
                maleTotalBirths++;
            }
            else
            {
                femaleTotalBirths++;
            }
            totalBirths = maleTotalBirths + femaleTotalBirths;
        }
        System.out.println("Total number is " + totalBirths);
        System.out.println("Total number of boys is " + maleTotalBirths);
        System.out.println("Total number of girls is " + femaleTotalBirths);
    }
    
    public void testTotalBirths()
    {
        totalBirths();
    }
    
    public int getRank(int year, String name, String gender)
    {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int rankMale = 0;
        int rankFemale = 0;
        for(CSVRecord record : parser)
        {
            if(record.get(1).equals("F"))
            {
                rankFemale++;
            }
            if(record.get(1).equals("M"))
            {
                rankMale++;
            }
            if(record.get(0).equals(name) && record.get(1).equals(gender))
            {
                if(record.get(1).equals("M"))
                {
                    return rankMale;
                }
                else
                {
                    return rankFemale;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank()
    {
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender)
    {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int rankMale = 0;
        int rankFemale = 0;
        for(CSVRecord record : parser)
        {
            if(record.get(1).equals("F"))
            {
                rankFemale++;
            }
            if(record.get(1).equals("M"))
            {
                rankMale++;
            }
            if(((rank == rankMale) || (rank == rankFemale)) && (record.get(1).equals(gender)))
            {
                return record.get(0);
            }
        }
        return "NO NAME";
    }
    
    public void testGetName()
    {
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int maxRank = 0;
        int currentRank;
        int intYear;
        int maxRankedYear = 0;
        String stringYear;
        boolean firstIteration = true;
        for(File f : dr.selectedFiles())
        {
            stringYear = f.getName().substring(3, 7);
            intYear = Integer.parseInt(stringYear);
            currentRank = getRank(intYear, name, gender);
            if(firstIteration)
            {
                firstIteration = false;
                maxRank = currentRank;
                maxRankedYear = intYear;
            }
            if(currentRank < maxRank)
            {
                maxRank = currentRank;
                maxRankedYear = intYear;
            }
        }
        if(maxRank == 0)
        {
            return -1;
        }
        return maxRankedYear;
    }
    
    public void testYearOfHighestRank()
    {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender)
    {
        double totalRanks = 0;
        int count = 0;
        int currentRank;
        int intYear;
        String stringYear;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            stringYear = f.getName().substring(3, 7);
            intYear = Integer.parseInt(stringYear);
            currentRank = getRank(intYear, name, gender);
            if(currentRank != -1)
            {
                count++;
                totalRanks += currentRank;
            }
        }
        if(totalRanks == 0)
        {
            return -1;
        }
        return totalRanks / count;
    }
    
    public void testGetAverageRank()
    {
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        int totalBirths = 0;
        int femaleRank = 0;
        int maleRank = 0;
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int comparedRank = getRank(year, name, gender);
        for(CSVRecord record : parser)
        {
            if(record.get(1).equals("M"))
            {
                maleRank++;
            }
            else
            {
                femaleRank++;
            }
            int numBirths = Integer.parseInt(record.get(2));
            if(((maleRank < comparedRank) || (femaleRank < comparedRank)) && (gender.equals(record.get(1))))
            {
                totalBirths += numBirths;
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher()
    {
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
    }
}
