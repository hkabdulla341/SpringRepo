Feature:WalletDeposit
	
	Scenario: mobile not found
		Given in WalletDepositTest, system has one account with mobile number "22334466", name is "Nike" and amount is 50000
		When in WalletDepositTest, user inputs mobile number 87654321
		Then WalletDepositTest system should print "Mobile number does not exist"
		
	Scenario: invalid mobile number
		Given in WalletDepositTest, user wants to access account with mobile number which is not 8 digits
		When in WalletDepositTest, user gives 1234567 as mobile number OR user gives 123456789 as mobile number
		Then WalletDepositTest system should print "Invalid mobile number"

	Scenario: invalid mobile number (non-numerical value)
		Given in WalletDepositTest,user wants to access account with a mobile number that includes non numerical values
		When in WalletDepositTest, user gives "1234567a" as mobile number while depositing to a account
		Then WalletDepositTest system should print "Mobile number cannot contain non numerical values"

	Scenario: mobile number is null
		Given in WalletDepositTest, user wants to access an account 
		When in WalletDepositTest, user passes NULL as mobile number
		Then WalletDepositTest system should print "Mobile number cannot be NULL"
		
	Scenario: amount is invalid
		Given in WalletDepositTest, user wants to deposit less than 0 or more than 100000
		When in WalletDepositTest, user inputs -1 OR user inputs 100001
		Then WalletDepositTest system should print "Deposit is out of limits"

	Scenario: amount is out of limits
		Given in WalletDepositTest, system has one account with mobile number "22334466", name is "Nike" and amount is 50000
		When user inputs deposit amount as 50001 for account with mobile number "22334466"
		Then WalletDepositTest system should print "Maximum balance can only be 100000"

	Scenario: WalletDepositValid
		Given in WalletDepositTest, system has one account with mobile number "22334466", name is "Nike" and amount is 50000
		When user inputs deposit amount as 1000 for account with mobile number "22334466"
		Then WalletDepositTest system should print "Balance of account 22334466 is 51000"