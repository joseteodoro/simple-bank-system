package banking;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.naming.InitialContext;

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

	private Account createAccount(AccountCreator creator) {
		// can we add more than one account per holder?
		Long accountNumber =  BloomFilterSingleton.getInstance().generateAccountNumber();
		return creator.create(accountNumber);
	}

	private Long registryAccount(Account account) {
		this.accounts.put(account.getAccountNumber(), account);
		return account.getAccountNumber();
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		AccountCreator creator = (accountNumber) -> new CommercialAccount(company, accountNumber, pin, startingDeposit);
		Account account = this.createAccount(creator);
		return registryAccount(account);
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		AccountCreator creator = (accountNumber) -> new ConsumerAccount(person, accountNumber, pin, startingDeposit);
		Account account = this.createAccount(creator);
		return registryAccount(account);
	}

	// missing some logic about comercial accounts to validate if the user is authorized to do authenticate
	public boolean authenticateUser(Long accountNumber, int pin) {
		return this.accounts.containsKey(accountNumber) && this.accounts.get(accountNumber).validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		return this.accounts.containsKey(accountNumber)
			? this.accounts.get(accountNumber).getBalance()
			: 0.f;
	}

	public void credit(Long accountNumber, double amount) {
		if (this.accounts.containsKey(accountNumber)) {
			this.accounts.get(accountNumber).creditAccount(amount);
		}
	}

	public boolean debit(Long accountNumber, double amount) {
		return this.accounts.containsKey(accountNumber) && this.accounts.get(accountNumber).debitAccount(amount);
	}
}
