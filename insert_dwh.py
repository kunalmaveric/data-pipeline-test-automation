import pandas as pd
import mysql.connector

 

# Define the MySQL database connection parameters
db_config = {
    'host': 'localhost',      # Your MySQL server host
    'user': 'root',       # Your MySQL username
    'password': '1234',   # Your MySQL password
    'database': 'db',    # Your MySQL database name
}

 

# Create a MySQL connection
conn = mysql.connector.connect(**db_config)

 

# Read CSV data into a Pandas DataFrame
df = pd.read_csv('finalOutput.csv')  # Replace 'your_data.csv' with your CSV file path
print(df.columns)
 

# Define a function to insert data into the MySQL database
def insert_data(row):
    cursor = conn.cursor()
    sql = """
        INSERT INTO customer_transactions_new (
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