package pl.itse.optimistic.lock.main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.itse.optimistic.lock.config.AppConfig;
import pl.itse.optimistic.lock.runnable.EmailSenderARunnable;
import pl.itse.optimistic.lock.runnable.EmailSenderBRunnable;

/*
* Simple example how to use optimistic lock in spring application
* 
* @author Borys Pawluczuk
*/

public class Main {

	public static void main(String[] args) {

		// Get context application
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		// Email sender A service ...

		EmailSenderARunnable emailSenderAService = context.getBean("emailSenderAService", EmailSenderARunnable.class);

		Thread threadEmailSenderAService = new Thread(emailSenderAService);

		threadEmailSenderAService.start();
		
		
		// Email sender B service ...

		EmailSenderBRunnable emailSenderBService = context.getBean("emailSenderBService", EmailSenderBRunnable.class);

		Thread threadEmailSenderBService = new Thread(emailSenderBService);

		threadEmailSenderBService.start();		

	}

}
