Feature:WalletCreateAccount

	Scenario: duplicate account
		Given mobile number 12345678 already exists
		When the user tries to create the account with 12345678
		Then system should print "Cannot create account because account with this mobile number already exists"
		
	Scenario: invalid mobile number
		Given user wants to create an account with a mobile number which is not 8 digits
		When user gives 1234567 as mobile number or user gives 123456789 as mobile number
		Then system should print "Invalid mobile number"
		
	Scenario: invalid mobile number (non-numerical)
		Given user wants to create an account with a mobile number that includes non numerical values
		When user gives "asd1234567" as mobile number while creating the account
		Then system should print "Mobile number cannot contain non numerical values"
		
	Scenario: mobile number is null
		Given user wants to create an account 
		When user passes NULL as mobile number
		Then system should print "Mobile number cannot be NULL"
		
	Scenario: amount is invalid
		Given user wants to deposit less than zero or more than 100000
		When user inputs -1	OR user inputs 100001
		Then system should print "Deposit is out of limits"
		 
	Scenario: name is null
		Given user wants to create an account
		When user passes in NULL as name
		Then system should print "Name cannot be NULL"

	Scenario: name is empty
		Given user wants to create an account
		When user inputs an empty string "" OR user inputs only whitespaces " " for name
		Then system should print "Name cannot be empty"
	
	Scenario: name includes !@#$%^&*(){}:;"\|<>~`
		Given user wants to create an account
		When user inputs name that includes special character example "john$$$"
		Then system should print "Name cannot include special characters"
		
	Scenario: Valid create account
		Given user wants to create an account 
		When user inputs name "John Two"AND user enters amount 1000 AND mobile number 12312312
		Then system should print "John Two is saved!"