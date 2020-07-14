
/**
 * Write a description of CSVMin here.
 * Parse CSV files about tempratures and some other features
 * @author Ahmed AbdulAzeem 
 * @version 1.0 7/12/2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord smallestRecord = null;
        for(CSVRecord currentRow : parser)
        {
            smallestRecord = getSmallestOfTwo(smallestRecord, currentRow, "TemperatureF");
        }
        return smallestRecord;
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord smallesRecord, CSVRecord currentRow, String item)
    {
        if(smallesRecord == null)
        {
            smallesRecord = currentRow;
        }
        else
        {
            double currTemp = Double.parseDouble(currentRow.get(item));
            double smallestTemp = Double.parseDouble(smallesRecord.get(item));
            if(currTemp < smallestTemp)
            {
                smallesRecord = currentRow;
            }
        }
        return smallesRecord;
    }
    
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("coldest temperature was " + coldest.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestRecord = null;
        String result = "";
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = coldestHourInFile(parser);
            smallestRecord = getSmallestOfTwo(smallestRecord, currRow, "TemperatureF");
            if(smallestRecord.get("TemperatureF").equals(currRow.get("TemperatureF")))
            {
                result = f.getName();
            }
        }
        return result;
    }
    
    public void testFileWithColdestTemperature()
    {
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);  
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowesHumidity = null;
        double currHumidity = 0, lowestHumidity = 0;
        for(CSVRecord currentRow : parser)
        {
            if(lowesHumidity == null)
            {
                lowesHumidity = currentRow;
            }
            else
            {
                String s1 = currentRow.get("Humidity");
                if(!(s1.equals("N/A")))
                    currHumidity = Double.parseDouble(s1);
                
                String s2 = lowesHumidity.get("Humidity");
                if(!(s2.equals("N/A")))
                    lowestHumidity = Double.parseDouble(s2);
                
                if(currHumidity < lowestHumidity)
                {
                    lowesHumidity = currentRow;
                }
            }
        }
        return lowesHumidity;
    }
    
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = lowestHumidityInFile(parser);
            lowestHumidity = getSmallestOfTwo(lowestHumidity, currRow, "Humidity");
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser)
    {
        double averageTemprature = 0.0;
        String temprature = "";
        int count = 0;
        for(CSVRecord record : parser)
        {
            temprature = record.get("TemperatureF");
            averageTemprature += Double.parseDouble(temprature);
            count++;
        }
        return (averageTemprature/count);
    }
    
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double result = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + result);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double sum = 0.0;
        int count = 0;
        double humidity = 0.0;
        double temp = 0.0;
        for(CSVRecord record : parser)
        {
            humidity = Double.parseDouble(record.get("Humidity"));
            temp = Double.parseDouble(record.get("TemperatureF"));
            if(humidity >= value)
            {
                sum += temp;
                count++;
            }
        }
        if(sum == 0.0)
            return -1;
        return sum / count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double result = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(result == -1)
        {
            System.out.println("No temperatures with that humidity");
        }
        else
        {
            System.out.println("Average Temp when high Humidity is " + result);
        }
    }
        
}
