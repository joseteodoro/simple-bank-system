package banking.services;

import banking.models.Account;

@FunctionalInterface
public interface AccountCreator {
    
    Account create(Long accountNumber);

}
