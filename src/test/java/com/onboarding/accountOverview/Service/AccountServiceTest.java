package com.onboarding.accountOverview.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onboarding.accountOverview.Entity.*;
import com.onboarding.accountOverview.Repository.AccountRepository;
import com.onboarding.accountOverview.Vo.AccountVO;
import com.onboarding.accountOverview.exception.IdNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	@InjectMocks
	private AccountServiceImpl accountService;
	@Mock
	private AccountRepository accountRepository;
	public List<Initiatives> initiatives() {
        List<Initiatives> initiative = new ArrayList<Initiatives>();
        initiative.add(new Initiatives("name", "desc"));
        return initiative;
    }



   public List<Engagements> engagements() {
        List<Engagements> engagements = new ArrayList<Engagements>();
        engagements.add(new Engagements("name", "desc"));
        return engagements;
    }
	@Test
	public void viewAccountTestSuccess() {
		Account account = new Account("0dc10f46-d862-422c-8ee7-bf48c1f55cd7", "account", "account",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin", "walmart", engagements(),initiatives());
      
		when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(account));
		String accountOverview=accountService.viewAccount("0dc10f46-d862-422c-8ee7-bf48c1f55cd7");
		assertEquals("walmart", accountOverview);
	}
	@Test
	public void viewAccountTestFailure() {
		Account account = new Account("0dc10f46-d862-422c-8ee7-bf48c1f55cd7", "account", "account",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin", null, engagements(),initiatives());
      
		when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(account));
		String accountOverview=accountService.viewAccount("0dc10f46-d862-422c-8ee7-bf48c1f55cd7");
		assertNull(accountOverview);
	}
	@Test
    void updateOverviewTest_Success()
    {
        Account account = new Account("521cbcfa-f527-4fed-9b4c-5cc369160355", "account", "account",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin", "about retail", engagements(),initiatives());
            AccountVO accountVo=new AccountVO("521cbcfa-f527-4fed-9b4c-5cc369160355","about retail","admin");
            account.setAccountOverview(accountVo.getAccountOverview());
            account.setUpdatedBy(accountVo.getUpdatedBy());
            account.setUpdatedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(account));
            String accountOverView=accountService.updateOverview(accountVo);
           assertEquals("about retail",accountOverView);
    }
	 @Test
	    void updateOverviewfailure()
	    {
	        Account account = new Account("521cbcfa-f527-4fed-9b4c-5cc369160355", "account", "account",
	                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin",
	                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "admin", null, engagements(),initiatives());
	            AccountVO accountVo=new AccountVO("521cbcfa-f527-4fed-9b4c-5cc369160355",null,"admin");
	            account.setAccountOverview(accountVo.getAccountOverview());
	            account.setUpdatedBy(accountVo.getUpdatedBy());
	            account.setUpdatedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	            when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(account));
	            String accountOverView=accountService.updateOverview(accountVo);
	            assertNull(accountOverView);
	}
	 @Test    
	 public void viewAccountException() 
	 {        
		 String accountId = "521cbcfa-f527-4fed-9b4c-5cc369160355";        
		 when(accountRepository.findById(Mockito.anyString())).thenThrow(new IdNotFoundException("Id Not Found"));        
		 IdNotFoundException ex = assertThrows(IdNotFoundException.class,() -> accountService.viewAccount(accountId));        
		 assertEquals("Id Not Found", ex.getErrorMessage());    
		 }
}

