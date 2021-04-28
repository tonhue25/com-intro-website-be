package com.comintro.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageService implements MessageSourceAware {
	private static final Logger logger = Logger.getLogger(MessageService.class);

	@Autowired
	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String code) {
		return getMessage(code, null);
	}

	public String getMessage(String code, Object[] args) {
		try {
			return this.messageSource.getMessage(code, args, Locale.ROOT);
		} catch (Exception e) {
			logger.debug(code);
			logger.error("Could not get message source: " + e.getMessage());
			return "";
		}
	}

}
