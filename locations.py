import pyrebase
import csv
import pprint
import sys

# Function to convert a csv file to a list of dictionaries.  Takes in one variable called "variables_file" 
def csv_dict_list(variables_file):
# Open variable-based csv, iterate over the rows and map values to a list of dictionaries containing key/value pairs
	reader = csv.DictReader(open(variables_file, 'rb'))
	dict_list = []
	for line in reader:
	    dict_list.append(line)
	return dict_list


config = {
    "apiKey" : "api",
    "authDomain" : "auth",
    "databaseURL" : "dtb",
    "projectId": "pjID",
    "storageBucket" : "sB",
    "messagingSenderId" : "ID"
}
firebase = pyrebase.initialize_app(config)
db = firebase.database()



variables_file = 'LocationData.csv'

locations = csv_dict_list(variables_file)

for location in locations:
	location['Key'] = location.pop('\xef\xbb\xbfKey')
	# db.child("locations").push(location,None) 

users = db.child("locations").get()
for user in users.each():
	print(user.val()['Website']) # users

