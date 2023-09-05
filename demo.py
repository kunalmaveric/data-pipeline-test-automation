from pyspark.sql import SparkSession
from pyspark.sql.functions import col
import pandas as pd
from PostValidation import PostValidation
from PreValidation import PreValidation

def readDataFile(csv_file_path):
      # Read the CSV file
      df = spark.read.csv(csv_file_path, header=True, inferSchema=True)
      return df

def writeToTarget(sourceData):
      #Write final filtered output to target 
      duplicate_df = sourceData.toPandas()
      output_csv_path = "Staging/finalOutput.csv"
      duplicate_df.to_csv(output_csv_path, index=False)

if __name__ == '__main__':
      # Initialize SparkSession
      spark = SparkSession.builder.appName("ReadCSV").getOrCreate()

      #Reading source data
      sourceData = readDataFile('Landing/dummyData_new.csv')

      #Object creation for Pre inserting data to staging layer
      s1 = PreValidation()
      updatedSourceData = s1.nullRecords(spark,sourceData)
      updatedSourcedata1 = s1.duplicates(spark,updatedSourceData)
      # print(updatedSourcedata1.show(3))
      writeToTarget(updatedSourcedata1)
      targetData = readDataFile('Staging/finalOutput.csv')

      #Object creation for Post 
      s2 = PostValidation()
      s2.countMismatch(spark,sourceData,targetData)
      spark.stop()