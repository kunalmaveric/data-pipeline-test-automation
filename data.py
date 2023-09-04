from faker import Faker
import random
import string as Str
import csv

# Initialize the Faker instance
fake = Faker()

# Custom function to generate a random customer ID
def generate_customer_id():
    return fake.random_int(min=1000, max=999999)

# Custom function to generate a random first name
def generate_first_name():
    return fake.first_name()

# Custom function to generate a random last name
def generate_last_name():
    return fake.last_name()

# Custom function to generate a random date of birth
def generate_date_of_birth():
    return fake.date_of_birth(minimum_age=18, maximum_age=70)

# Custom function to generate a random PAN card number
def generate_pan_card():
    return ''.join(random.choice(Str.ascii_uppercase + Str.digits) for _ in range(10))

# Custom function to generate a random contact number
def generate_contact():
    return str(fake.random_int(min=6000000000, max=9999999999))

# Generate fake banking data
def generate_bank_data():
    customer_id = generate_customer_id()
    account_number = fake.random_int(12)
    first_name = generate_first_name()
    last_name = generate_last_name()
    dob = generate_date_of_birth()
    pan_card = generate_pan_card()
    contact = generate_contact()
    emp_info = random.choice(["Active", "Closed"])
    rel_status = random.choice(["Single", "Married"])
    return {
        "Customer Id": customer_id,
        "Account Number": account_number,
        "First Name": first_name,
        "Last Name": last_name,
        "DOB": dob,
        "PAN Card": pan_card,
        "Contact": contact,
        "Employment Info": emp_info,
        "Relationship Status": rel_status,
    }

# Generate and print sample banking data
num_entries = 500  # Adjust the number of entries you want
data = []

for _ in range(num_entries):
    entry = generate_bank_data()
    data.append(entry)
    # print(entry)

# Create and write data to a CSV file
csv_file_name = 'PI_DAta2.csv'

with open(csv_file_name, "w", encoding="UTF-8", newline="") as csv_file:
    fieldnames = data[0].keys()
    csv_writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
    
    csv_writer.writeheader()
    csv_writer.writerows(data)

print(f"Data saved to {csv_file_name}")
