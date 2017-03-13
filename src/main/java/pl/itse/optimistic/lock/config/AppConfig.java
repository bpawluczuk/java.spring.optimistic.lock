package pl.itse.optimistic.lock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import pl.itse.optimistic.lock.runnable.EmailSenderARunnable;
import pl.itse.optimistic.lock.runnable.EmailSenderBRunnable;


@Configuration
@ComponentScan({ "pl.itse" })
public class AppConfig {

	@Bean("emailSenderAService")
	public EmailSenderARunnable emailSenderARunnable() {
		return new EmailSenderARunnable();
	}
	
	@Bean("emailSenderBService")
	public EmailSenderBRunnable emailSenderBRunnable() {
		return new EmailSenderBRunnable();
	}

}
