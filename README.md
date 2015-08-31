Digital wallet application


Requirement:


You will be storing digital wallet data--User, IDCard, WebLogin, and BankAccount--into MongoDB. Instead of installing and running your own instance on EC2, you can sign up for a free hosted MongoDB-as-a-Service accountÂ at MongoLab. Of course, feel free to install a local instance on your dev machine, but you need to use a remote instance for final deployment.
This exampleÂ shows you how to persist a customer (similar to User) model into the database using Spring Data for MongoDB. If you dont want Spring Data, you can use any MongoDB client (JavaÂ or Scala) framework.
The data model that you will be storing into MongoDB collection(s) are:

User:
user_id (System generated field) - {integer}
email (Required) - {string}
password (Required) - {string}
name (Optional) {string}
created_at (System generated field) - {DateTime}
updated_at (System generated field) - {DateTime}

IDCard:
card_id (System generated field) - {integer}
card_name (Required) - {string}
card_number (Required) - {string}
expiration_date (Optional) - {Date}

WebLogin:
login_id (System generated field) - {integer}
url (Required) - {string}
login (Required) - {string}
password (Required) - {string}

BankAccount:
ba_id (System generated field) - {integer}
account_name (Optional) - {string}
routing_number (Required) - {string}
account_number (Required) - {string}

API's to be implemented:

1.Create User
Resource: /users
Description: Add a new user to the digital wallet system.
Request: 
POST /users (with the following payload in the request body)
HTTP Headers:
Content-type: application/json
Response:
HTTP Code: 201

2.View User
Resource: /users/{user_id}
Description: View an existing user of the wallet.
Request:
GET /users/u-123456
Accept: application/json
Response:
HTTP Code: 200

3.Update User
Resource: /users/{user_id}
Description: Update an existing user information.
Request:
PUT /users/u-123456 (with the following payload in the request body)
HTTP Headers:
Content-type: application/json

4.Create ID Card
Resource: /users/{user_id}/idcards
Description: Add a new ID card to the wallet.
Request:
POST /users/{user_id}/idcards (with the following payload in the request body)
HTTP Headers:
Content-type: application/json
5.List All ID Cards
Resource: /users/{user_id}/idcards
Description: List zero or more ID cards from the wallet.
Request: 
GET /users/{user_id}/idcards
HTTP Headers:
Accept-type: application/json
Response:
HTTP Code: 200

6.Delete ID Card
Resource: /users/{user_id}/idcards/{card_id}
Description: Delete an ID card from the wallet.
Request:
DELETE /users/{user_id}/idcards/{card_id}
Response:
HTTP Code: 204

7.Create Web Login
Resource: /users/{user_id}/weblogins
Description: Store a new web login in the wallet.
Request:
POST /users/{user_id}/weblogins (with the following payload in the request body)
HTTP Headers:
Content-type: application/json

8.List All Web-site Logins
Resource: /users/{user_id}/weblogins
Description: List zero or more web-site logins from the wallet.
Request:
GET /users/{user_id}/weblogins
HTTP Headers:
Accept-type: application/json
Response:
HTTP Code: 200

9.Delete Web Login
Resource: /users/{user_id}/weblogins/{login_id}
Description: Delete a web login from the wallet.
Request:Â 
DELETE /users/{user_id}/weblogins/{login_id}
Response:
HTTP Code: 204

10.Create Bank Account
Resource: /users/{user_id}/bankaccounts
Description: Save a bank account info in the wallet.
Request:
POST /users/{user_id}/bankaccounts (with the following payload in the request body)
HTTP Headers:
Content-type: application/json

11.List All Bank Accounts
Resource: /users/{user_id}/bankaccounts
Description: List zero or more bank accounts from the wallet.
Request:
GET /users/{user_id}/backaccounts
HTTP Headers:
Accept-type: application/json
Response:
HTTP Code: 200

12.Delete Bank Account
Resource: /users/{user_id}/bankaccounts/{ba_id}
Description: Delete a bank account from the wallet.
Request:
DELETE /users/{user_id}/bankaccounts/{ba_id}
Response:
HTTP Code: 204

Part II
In the part II, you will learn how to consume a RESTful API from a client perspective.
{
 routing_number: 121000358,
 account_number: 040834236
}
In the Create Bank Account API from the assignment 1, you will now be calling this routing number lookup API to valid user inputâ€™s routing numbers as well as fill in the optional account_name field with the customer_name field that you got from the response. So, you will be using the lookup API for the routing number validation and the customer name auto-population.
Suppose a routing_number user entered was 121000358. Then make HTTP GET call to this API:
Request:
GET https://www.routingnumbers.info/api/data.json?rn=121000358
Response:
{
message: "OK",
change_date: "101310",
office_code: "O",
record_type_code: "1",
new_routing_number: "000000000",
rn: "121000358",
state: "VA",
address: "8001 VILLA PARK DRIVE",
telephone: "800-446-0135",
data_view_code: "1",
code: 200,
customer_name: "BANK OF AMERICA, N.A.",
city: "HENRICO",
routing_number: "121000358",
institution_status_code: "1",
zip: "23228"
}
Since the response code is 200, the routing number 121000358 is valid. For any invalid routing number, You will get a 404 code instead. Finally, take the customer_name field value (BANK OF AMERICA, N.A) 
and set it in the account_name when you store a new bank account object in the database.
You can use REST clients like Unirest or Spring REST Template.
Example:
Spring REST Template Example.
