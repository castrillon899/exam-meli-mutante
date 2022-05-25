package com.meli.mutant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
public class BundleMessage {
	
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String message, Object... args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	public String getMessage(FieldError fieldError) {
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

	public String getMessageProperties(String message, Object... args) {
		int length = args != null ? args.length : 0;
		Object[] messages = new Object[length];
		for (int i = 0; i < length; i++) {
			messages[i] = this.getMessage(args[i].toString());
		}
		return this.getMessage(message, messages);
	}

}
