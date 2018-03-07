Feature:WalletWithdraw
	
	Scenario: mobile not found
		Given in WalletWithdrawTest, system has one account with mobile number "99889988", name is "Nike Six" and amount is 50000
		When in WalletWithdrawTest, user inputs mobile number 87654321
		Then WalletWithdrawTest system should print "Mobile number does not exist"
		
	Scenario: invalid mobile number
		Given in WalletWithdrawTest, user wants to access account with mobile number which is not 8 digits
		When in WalletWithdrawTest, user gives 1234567 as mobile number OR user gives 123456789 as mobile number
		Then WalletWithdrawTest system should print "Invalid mobile number"

	Scenario: invalid mobile number (non-numerical value)
		Given in WalletWithdrawTest,user wants to access account with a mobile number that includes non numerical values
		When in WalletWithdrawTest, user gives "1234567a" as mobile number while depositing to a account
		Then WalletWithdrawTest system should print "Mobile number cannot contain non numerical values"

	Scenario: mobile number is null
		Given in WalletWithdrawTest, user wants to access an account 
		When in WalletWithdrawTest, user passes NULL as mobile number
		Then WalletWithdrawTest system should print "Mobile number cannot be NULL"
		
	Scenario: amount is invalid
		Given in WalletWithdrawTest, user wants to deposit less than 0 or more than 100000
		When in WalletWithdrawTest, user inputs -1 OR user inputs 100001
		Then WalletWithdrawTest system should print "Deposit is out of limits"
	
	Scenario: insufficient funds
		Given in WalletWithdrawTest, system has one account with mobile number "88998899", name is "Nike Seven" and amount is 50000
		When in WalletWithdrawTest, user wants to withdraw 50001 for account with mobile number "22334466"
		Then WalletWithdrawTest system should print "Insufficient funds for withdrawal"

	Scenario: WalletWithdrawValid
		Given in WalletWithdrawTest, system has one account with mobile number "55665566", name is "Nike Eight" and amount is 50000
		When in WalletWithdrawTest, user wants to withdraw 1000 for account with mobile number "22334466"
		Then WalletWithdrawTest system should print "Balance of account 22334466 is 49000"