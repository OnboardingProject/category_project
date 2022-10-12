package com.onboarding.accountOverview.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboarding.accountOverview.Constants.Constants;
import com.onboarding.accountOverview.Entity.Account;
import com.onboarding.accountOverview.Repository.AccountRepository;
import com.onboarding.accountOverview.Vo.AccountVO;
import com.onboarding.accountOverview.exception.IdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	// method is used to save account details
	@Override
	public Account saveAccount(Account account) {
		log.info(Constants.SAVE_START);
		account.setAccountId(UUID.randomUUID().toString());
		account.setCreatedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		account.setUpdatedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		log.info(Constants.SAVE_END);
		return accountRepository.save(account);
	}

	// method is used to update account overview
	@Override
	public String updateOverview(AccountVO accountVo) {
		log.info("Update Overview");
		Account account = accountRepository.findById(accountVo.getAccountId())
				.orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		account.setAccountOverview(accountVo.getAccountOverview());
		account.setUpdatedBy(accountVo.getUpdatedBy());
		account.setUpdatedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		accountRepository.save(account);
		log.info("Exit from update overview");
		return account.getAccountOverview();
	}

	// method is used to view account overview
	@Override
	public String viewAccount(String accountId) {
		log.info(Constants.VIEW_START);
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		log.info(Constants.VIEW_END);
		return account.getAccountOverview();
	}

}
