package com.onboarding.accountOverview.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onboarding.accountOverview.Entity.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

}
