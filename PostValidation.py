
class PostValidation:
    """
       This
       1. count - sourceData v/s targetData.
       2. account number is 12 digit not more than that
       3. contact number is not more than 10 digits
       4. transactiontype should be credit or debit
       5. dob should be yyyy-mm-dd
    """
    def countMismatch(self,spark,sourceData,targetData):
        if sourceData.count() == targetData.count():
            return 'Count Match Successful!!!'
        else:
            return 'Source and Target count does not match!!!'        
