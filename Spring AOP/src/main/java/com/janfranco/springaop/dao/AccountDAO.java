package com.janfranco.springaop.dao;

import com.janfranco.springaop.entity.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    public List<Account> addAccount(Account account) {
        System.out.println(getClass() + ": adding account");
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Jane Franco"));
        accounts.add(new Account("Account 1"));
        accounts.add(new Account("Account 2"));
        accounts.add(new Account("Account test"));
        return accounts;
    }

    public Account getAccount() throws Exception {
        throw new Exception("getAccount exception");
    }

    public void doWork() {
        for (int i=0; i<10000000; i++) {
            long x = (long) i * i;
        }
    }
}
