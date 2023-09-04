package Java_tests;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;
//import jdk.internal.access.JavaObjectInputStreamReadString;

import org.testng.Assert;

public class Stage1Tests {

	@Test(description = "For missing data value")
    public void verifyMissingRecords() throws IOException {
        // Define the path to your CSV file
		
        String csvFilePath = "src\\test\\resources\\test_data\\dummyData.csv"; // Update with your CSV file path


        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
        	SoftAssert softAssert = new SoftAssert();
            for (CSVRecord record : csvParser) {
            	System.out.println(record);
                for (String header : csvParser.getHeaderNames()) {
                    String value = record.get(header);
                    System.out.println(value);
                    if (value == null || value.trim().isEmpty()) {
                    	 System.out.println("Null or empty value found in column '" + header + "' on line " + record.getRecordNumber());
                        Assert.fail("Null or empty value found in column '" + header + "' on line " + record.getRecordNumber());
                    }
                }
            }
            softAssert.assertAll();
        }
    }
	
    @Test(description="test for file count mismatch")
    public void verifyUniqueCSVFileInFolder() {
        String folderPath = "src\\test\\resources\\test_data"; 
        File folder = new File(folderPath);
        File[] csvFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               
                return name.toLowerCase().endsWith(".csv");
            }
        });

       
        Assert.assertNotNull(csvFiles, "Unable to access the folder.");
        Assert.assertEquals(csvFiles.length, 1, "Expected one CSV file, but found " + (csvFiles != null ? csvFiles.length : 0) + " CSV files.");
    }
    @Test(description = "missing header field")
    public void verifyNoNullFields() {
    	try {
    	 String csvFilePath = "C:\\Users\\chiragsinghp\\DataPipelineAutomation\\Stage1\\src\\test\\resources\\test_data\\dummyData.csv"; // Update with your CSV file path
String[] StandardHeadernames = {"relationshipStatus","firstName","lastName","dob","panNumber","email","defence_background","okToCall"};
try(FileReader fileReader = new FileReader(csvFilePath);
              CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
            
             	List<String> header = csvParser.getHeaderNames();
             	System.out.println(header);
                for(String eachheader:StandardHeadernames) {
                	System.out.println(eachheader);
                	if(!header.contains(eachheader)) {
                		Assert.fail("missing field "+eachheader);
                	}else
                		System.out.println(eachheader+""+"fields present");
                }
                 } 
         }catch(IllegalArgumentException e) {
        	 Assert.fail("A header field is missing");
        	 e.printStackTrace();
         } 
    	catch (IOException e) {
			e.printStackTrace();
		}}
    
 @Test(description = "duplicate header field")
public void verifyNoDuplicateHeaderFields() throws FileNotFoundException {
	 String csvFilePath = "C:\\Users\\chiragsinghp\\DataPipelineAutomation\\Stage1\\src\\test\\resources\\test_data\\dummyData.csv"; // Update with your CSV file path
	 try(FileReader fileReader = new FileReader(csvFilePath);
	               CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
      	List<String> header = csvParser.getHeaderNames();
      	 Set<String> uniqueStrings = new HashSet<>();
      
          for (String str : header) {
              if (!uniqueStrings.add(str)) {
                 Assert.fail("Duplicate header found "+str);
              }
          }
         System.out.println("Headers verified successfully, no duplicates found ");

}catch (IOException e) {

	e.printStackTrace();
}
 }
@Test(description = "duplicate data")
 public void verifyNoDuplicateData() {
	 try {
		  String csvFilePath = "C:\\Users\\chiragsinghp\\DataPipelineAutomation\\Stage1\\src\\test\\resources\\test_data\\dummyData.csv"; 

	        try (FileReader fileReader = new FileReader(csvFilePath);
	             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
	        	SoftAssert softAssert = new SoftAssert();
	        	Set<String> uniqueStrings = new HashSet<>();
	            for (CSVRecord record : csvParser) {
	            	String AccountNo = record.get("account_No");
	            	System.out.println(record.get(1));	            	 	 
	                     if (!uniqueStrings.add(record.get(1))) {	 
	                    	 System.out.println("Duplicate found for "+record);
	                    	 Assert.fail();
	                    	
	                     }	                	            	 
	                }
	            System.out.println(uniqueStrings.size());
	 }}catch(Exception e) {
		 e.printStackTrace();
	 }
 }


@Test(description = "Account data type error")
public void verifyAccountDataTypeError() {
	 try {
		  String csvFilePath = "src\\test\\resources\\test_data\\dummyData.csv"; 

	        try (FileReader fileReader = new FileReader(csvFilePath);
	             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
	        	
	            for (CSVRecord record : csvParser) {
	            	String AccountNo = record.get("account_No");        	
	            	boolean isInteger = AccountNo.matches("\\d+");
	            	if(!isInteger) {
	                	System.out.println(record);
	                	Assert.fail();
	            	}                            	            	 
	                }
	 }}catch(Exception e) {
		 e.printStackTrace();
	 }
}
@Test(description = "DOB data type error")
public void verifyDOBDataTypeError() {
	 try {
		  String csvFilePath = "src\\test\\resources\\test_data\\dummyData.csv"; 

	        try (FileReader fileReader = new FileReader(csvFilePath);
	             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
	            for (CSVRecord record : csvParser) {
	            	String DOB = record.get("dob");
	            	System.out.println(DOB);
	            	if((!DOB.isBlank())) {
		            	boolean isDOB = DOB.matches("\\d{2}-\\d{2}-\\d{4}");
		            	if(isDOB==false) {	
		            		System.out.println(record);
	                	Assert.fail();
		            	}
	            	}           	            	 
	                }
	            
	 }}catch(Exception e) {
		 e.printStackTrace();
	 }
}
@Test(description = "PanNo data type error")
public void verifyPanNoDataTypeError() {
	 try {
		  String csvFilePath = "src\\test\\resources\\test_data\\dummyData.csv"; 

	        try (FileReader fileReader = new FileReader(csvFilePath);
	             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
	        	
	            for (CSVRecord record : csvParser) {	            	
	            	String PanNo = record.get("panNumber");        	
	            	boolean isAlphaNumeric = PanNo.matches("^[a-zA-Z0-9]*$");
	            	if(!isAlphaNumeric) {
	                	System.out.println(record);
	                	Assert.fail();
	            	}
	            		            	 	 
	                             	            	 
	                }
	 }}catch(Exception e) {
		 e.printStackTrace();
	 }
}
}	 

	

