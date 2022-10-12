package com.onboarding.accountOverview.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorInfo {
	List<String> errorMessage;
	LocalDateTime errorDate;
}
