package banking;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

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

	private Account createAccount(AccountCreator creator, int pin, double startingDeposit) {
		// can we add more than one account per holder?
		Long accountNumber =  BloomFilterSingleton.getInstance().generateAccountNumber();
		return creator.create(accountNumber, pin, startingDeposit);
	}

	private Long registryAccount(Account account) {
		this.accounts.put(account.getAccountNumber(), account);
		return account.getAccountNumber();
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		AccountCreator creator = (n, p, d) -> new CommercialAccount(company, n, p, d);
		Account account = this.createAccount(creator, pin, startingDeposit);
		return registryAccount(account);
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		AccountCreator creator = (n, p, d) -> new ConsumerAccount(person, n, p, d);
		Account account = this.createAccount(creator, pin, startingDeposit);
		return registryAccount(account);
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
