package com.onboarding.accountOverview.Entity;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.onboarding.accountOverview.Constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Account")
public class Account {
	@Id
	private String accountId;
	private String customerName;
	private String aboutCustomer;
	private String createdDate;
	private String createdBy;
	private String updatedDate;
	private String updatedBy;

	@Size(min = 5, message = Constants.VALIDATION_MSG)
	private String accountOverview;
	private List<Engagements> engagements;
	private List<Initiatives> initiatives;

}
