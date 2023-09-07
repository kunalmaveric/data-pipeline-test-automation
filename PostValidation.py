
class PostValidation:
    """
       count - sourceData v/s targetData.
    """
    def countMismatch(self,spark,sourceData,targetData):
        if sourceData.count() == targetData.count():
            return 'Count Match Successful!!!'
        else:
            return 'Source and Target count does not match!!!'        
