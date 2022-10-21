package test.sskim;

import test.sskim.di.MyInject;

public class AccountService {

    @MyInject
    AccountRepository accountRepository;


    public void join(String joinMsg) {
        accountRepository.save(joinMsg);
    }
}
