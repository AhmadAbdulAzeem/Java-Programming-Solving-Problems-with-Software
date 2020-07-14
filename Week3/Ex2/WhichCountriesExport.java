/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public String countryInfo(CSVParser parser, String country)
    {
        String temp;
        for(CSVRecord record : parser)
        {
            temp = record.get("Country");
            if(temp.equals(country))
            {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    
    public void testcountryInfo()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for(CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2))
                System.out.println(record.get("Country"));
        }
        
    }
    
    public void testlistExportersTwoProducts()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int count = 0;
        for(CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if(exports.contains(exportItem))
                count++;
        }
        return count;
    }
    
    public void testnumberOfExporters()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
    }
    
    public void bigExporters(CSVParser parser, String amount)
    {
        for(CSVRecord record : parser)
        {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length())
            {
               System.out.println(record.get("Country") + " " +value);
               
            }
                
            
        }
    }
    
    public void testbigExporters()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
