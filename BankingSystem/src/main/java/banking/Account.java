package banking;

/**
 * Abstract bank account class.<br>
 * <br>
 *
 * Private Variables:<br>
 * {@link #accountHolder}: AccountHolder<br>
 * {@link #accountNumber}: Long<br>
 * {@link #pin}: int<br>
 * {@link #balance}: double
 */
// would like lombok here
public abstract class Account {
	private final AccountHolder accountHolder;

	private final Long accountNumber;

	private final int pin;

	// thread safe here please
	private volatile double balance;

	protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.balance = startingDeposit;
	}

	public AccountHolder getAccountHolder() {
		return this.accountHolder;
	}

	public boolean validatePin(int attemptedPin) {
		return this.pin == attemptedPin;
	}

	public double getBalance() {
		return this.balance;
	}

	public Long getAccountNumber() {
		return this.accountNumber;
	}

	public void creditAccount(double amount) {
		this.balance += amount;
	}

	// not great about perfomance, but at least is thread safe.
	// TODO make it better later
	public synchronized boolean debitAccount(double amount) {
		if (this.balance < amount) return false;
		this.balance -= amount;
		return true;
	}
}
