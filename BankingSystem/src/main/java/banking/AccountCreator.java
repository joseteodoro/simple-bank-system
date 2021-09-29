package banking;

@FunctionalInterface
public interface AccountCreator {
    
    Account create(Long accountNumber, int pin, double startingDeposit);

}
