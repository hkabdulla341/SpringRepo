Feature:WalletShowBalance

	Scenario: mobile not found
		Given There exist valid account with mobile number 12345678 , name as "John" and amount is 1000
		When user inputs mobile number 87654321
		Then for showblance system should print "Mobile number does not exist"

	Scenario: invalid mobile number
	 	Given user wants to access account with mobile number which is not 8 digits
		When user gives 1234567 as mobile number OR user gives 123456789 as mobile number
		Then for showblance system should print "Invalid mobile number"

	Scenario: invalid mobile number (Non-numberic)
	 	Given user wants to access account with a mobile number that includes non numerical values
		When user gives "1234567a" as mobile number to show balance of that mobile number
		Then for showblance system should print "Mobile number cannot contain non numerical values"

	Scenario: mobile number is null
	 	Given user wants to access an account and invoking showbalance
		When user passes NULL as mobile number and to show its balance
		Then for showbalance system should print "Mobile number cannot be NULL"
	
	Scenario: ShowBalanceValid
	 	Given user wants to show the balance of existing account 12345678 with account balance of 1000 and name "John"
		When user inputs 12345678 to its balance
		Then for showblance system should print "Balance of account 12345678 is 1000"