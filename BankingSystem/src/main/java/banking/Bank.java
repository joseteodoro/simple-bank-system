package banking;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
// would like lombok here
public class Bank implements BankInterface {

	// why should be linkedhere? removing that because linked one is slower than simple hash one
	private Map<Long, Account> accounts;

	public Bank() {
		// make it thread safe cause we need to run in a concurrent environment;
		this.accounts = Collections.synchronizedMap(new HashMap<>());
	}

	private Account getAccount(Long accountNumber) {
		return (Objects.isNull(accountNumber))
			? null
			: this.accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		// can we add more than one account per company?

		new CommercialAccount(company, accountNumber, pin, startingDeposit)
		return 
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		// complete the function
        return -1L;
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		// complete the function
        return true;
	}

	public double getBalance(Long accountNumber) {
		// complete the function
        return -1;
	}

	public void credit(Long accountNumber, double amount) {
		// complete the function
	}

	public boolean debit(Long accountNumber, double amount) {
		// complete the function
        return true;
	}
}
