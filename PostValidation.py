
class PostValidation:
    """
       This
       1. count - 
       2.  
    """
    def countMismatch(self,spark,sourceData,targetData):
        if sourceData.count() == targetData.count():
            return 'Count Match Successful!!!'
        else:
            return 'Source and Target count does not match!!!'        