Feature: Customers

Background: Below are the common steps for each scenario
Given User Launch Chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and password as "admin"
And Click on Login
Then User can view Dashboard

@sanity
Scenario: Add a new Customer
When User click on customers Menu
And click on Customers Menu Item
And click on Add New button
Then User can View Add new Customers Page
When User enter Customers info
And Click on Save button
Then User can view confirmaation message "The new customer has been added successfully."
And close browser

@regression
Scenario: Search Customer by EmailID
When User click on customers Menu
And click on Customers Menu Item
And Enter customer Email
When Click on search button
Then User should found Email in the Search Table
And close browser

@regression
Scenario: Search Customer By Name
When User click on customers Menu
And click on Customers Menu Item
And Enter customer FirstName
And Enter customer LastName
When Click on search button
Then User should found Name in the Search Table
And close browser