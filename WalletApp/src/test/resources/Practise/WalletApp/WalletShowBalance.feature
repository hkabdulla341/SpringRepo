Feature:WalletShowBalance

	Scenario: mobile not found
		Given There exist valid account with mobile number 12345678 , name as "John" and amount is 1000
		When user inputs mobile number 87654321
		Then for showblance system should print "Mobile number does not exist"
