package com.onboarding.accountOverview.Controller;



import javax.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.accountOverview.Constants.Constants;
import com.onboarding.accountOverview.Entity.Account;
import com.onboarding.accountOverview.Service.AccountService;
import com.onboarding.accountOverview.Vo.AccountVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
	
	
	
	@Autowired
	AccountService accountService;

	/**
	 * saveAccount method is used to save account data
	 * 
	 * @param account
	 * @return accounts
	 */
	@PostMapping
	public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account) {
		if (account != null) {
			Account accounts = accountService.saveAccount(account);
			log.info("Saved successfully");
			return new ResponseEntity<>(accounts, HttpStatus.CREATED);
		} else {
			log.debug("Error");
			return new ResponseEntity<>(account, HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping("/accountOverview")
	public ResponseEntity<String> addAccountOverview(@RequestBody @Valid AccountVO accountVo) {
		if (accountVo.getAccountId() != null) {
			String accountDescription= accountService.updateOverview(accountVo);
			log.info(Constants.SAVE_SUCCESS);
			return new ResponseEntity<>(accountDescription, HttpStatus.OK);
		} else {
			log.debug(Constants.SAVE_FAILED);
			return new ResponseEntity<>(Constants.SAVE_FAILED, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * updateOverview method is to update account overview
	 * 
	 * @param accountVo
	 * @return updatedAccount
	 */
	@PutMapping
	public ResponseEntity<String> updateOverview(@RequestBody @Valid AccountVO accountVo) {

		if (accountVo.getAccountId() != null) {
			String updatedAccount = accountService.updateOverview(accountVo);
			log.info(Constants.UPDATE_SUCCESS);
			return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
		} else {
			log.debug(Constants.UPDATE_FAILED);
			return new ResponseEntity<>(Constants.UPDATE_FAILED, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * viewAccount method is used to view Account Overview
	 * 
	 * @param accountId
	 * @return accountOverview
	 */
	@GetMapping
	public ResponseEntity<String> viewAccount(@RequestParam String accountId) {
		if (accountId != null) {
			String accountOverview = accountService.viewAccount(accountId);
			log.info(Constants.VIEW_SUCCESS);
			return new ResponseEntity<>(accountOverview, HttpStatus.OK);
		} else {
			log.debug(Constants.VIEW_FAILED);
			return new ResponseEntity<>(Constants.VIEW_FAILED, HttpStatus.NOT_FOUND);
		}
	}
}
