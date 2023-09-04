from pyspark.sql.functions import col

class PreValidation:
    def nullRecords(self,spark,s1):
        updatedDf = s1.dropna()
        return updatedDf
    
    def duplicates(self,spark,s1):
        column_names = ["customerId","account_No"]

        # Identify duplicates based on the specified column
        print((s1.dropDuplicates(column_names)).count())
        return s1.dropDuplicates(column_names)