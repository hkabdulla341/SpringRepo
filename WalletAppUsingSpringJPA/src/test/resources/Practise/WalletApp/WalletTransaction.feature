Feature: WalletTransaction 
    
  Scenario: mobile not found
		Given in WalletTransaction, there is account with mobile "99009901" and name "Mike One" and amount "1000"
		When in WalletTransaction, user inputs mobile number 87654321
		Then WalletTransaction system should print "Mobile number does not exist"
		
	Scenario: invalid mobile number
		Given in WalletTransaction, user wants to access account with mobile number which is not 8 digits
		When in WalletTransaction, user gives 1234567 as mobile number OR user gives 123456789 as mobile number
		Then WalletTransaction system should print "Invalid mobile number"

	Scenario: invalid mobile number (non-numerical value)
		Given in WalletTransaction,user wants to access account with a mobile number that includes non numerical values
		When in WalletTransaction, user gives "1234567a" as mobile number while depositing to a account
		Then WalletTransaction system should print "Mobile number cannot contain non numerical values"

	Scenario: mobile number is null
		Given in WalletTransaction, user wants to access an account 
		When in WalletTransaction, user passes NULL as mobile number
		Then WalletTransaction system should print "Mobile number cannot be NULL" 
    
  Scenario: name is valid, mobile number is valid, amount is valid
    	Given in WalletTransaction, there is account with mobile "99009900" and name "Mike Two" and amount "1000"
    	When in WalletTransaction, account with mobile "99009900", deposit 5 times of amount "2000" and withdraw 3 times of amount "1000"
    	Then WalletTransaction system should print "mobile 99009900 has 9 transactions"
    