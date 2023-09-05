import pandas as pd
import mysql.connector
import configparser
import re

# Define the MySQL database connection parameters
# db_config = {
#     'host': 'localhost',      # Your MySQL server host
#     'user': 'root',       # Your MySQL username
#     'password': '1234',   # Your MySQL password
#     'database': 'db',    # Your MySQL database name
# }



config = configparser.ConfigParser()
config.read('config.ini')


# Create a MySQL connection

db_host = config['database']['host']
db_port = config['database']['port']
db_user = config['database']['user']
db_password = config['database']['password']
db_name = config['database']['database_name']


# Create a MySQL connection

try:
    conn = mysql.connector.connect(
        host=db_host,
        port=db_port,
        user=db_user,
        password=db_password,
        database=db_name
    )
    print("Connected to the database")
except mysql.connector.Error as err:
    print(f"Error: {err}")

# Read CSV data into a Pandas DataFrame
df = pd.read_csv('Staging/finalOutput.csv')  # Replace 'your_data.csv' with your CSV file path
print(df.columns)

 #account number is 12 digit not more than that----------------
def minimumLengthCheck(self,df,column, length):
    print('Values below the min length :')
    for i in df.collect():
        if len(i[column]) <= length:
            print(i[column])
    return df    

#contact number is not more than 10 digits-----------------------
def minimumLengthCheck(self,df,column, length):
    print('Values below the min length :')
    for i in df.collect():
        if len(i[column]) <= length:
            print(i[column])
    return df

#transactiontype should be credit or debit
def transactionTypeCheck(self,df,column):
    listOfTrTypes = ['credit','debit']
    print('Transactions that are not credit and debit types')
    for i in df.collect():
        if i[column] not in listOfTrTypes:
            print(i[column])
    return df

 

#dob should be
def dobFormatCheck(self,df,column):
    pattern = r'^\d{4}/\d{2}/\d{2}$'
    print('Dates that dont follow the correct standard')
    for i in df.collect():
        if len(re.match(pattern,i[column]).group())>0:
            print(i[column])
    return df

# Define a function to insert data into the MySQL database
def insert_data(row):
    cursor = conn.cursor()
    sql = """
        INSERT INTO customer_transactions (
            customerId, account_No, firstName, lastName, dob,
            panNumber, contactNo, employmentStatus, relationshipStatus,
            email, transactionId, amount, transactionType, transactionDate,
            KYC_date, defence_background, okToCall, criminalRecord,
            feedbackDate, feedback, feedbackRating
        )
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """
    values = (
        row['customerId'], row['account_No'], row['firstName'], row['lastName'], row['dob'],
        row['panNumber'], row['contactNo'], row['employmentStatus'], row['relationshipStatus'],
        row['email'], row['transactionId'], row['amount'], row['transactionType'], row['transactionDate'],
        row['KYC_date'], row['defence_background'], row['okToCall'], row['criminalRecord'],
        row['feedbackDate'], row['feedback'], row['feedbackRating']
    )
    cursor.execute(sql, values)
    conn.commit()
    cursor.close()

 

# Apply the insert_data function to each row in the DataFrame
df.apply(insert_data, axis=1)

 

# Close the MySQL connection
conn.close()