package com.onboarding.accountOverview.Service;

import org.springframework.stereotype.Service;

import com.onboarding.accountOverview.Entity.Account;
import com.onboarding.accountOverview.Vo.AccountVO;
@Service
public interface AccountService {
	 Account saveAccount(Account account);
	 String updateOverview(AccountVO accountVo);
	 String viewAccount(String accountId);
	 
}
