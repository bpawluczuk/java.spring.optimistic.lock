package pl.itse.optimistic.lock.runnable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.itse.optimistic.lock.dao.EmailDao;
import pl.itse.optimistic.lock.model.Email;

@Component
public class EmailSenderBRunnable implements Runnable {

	@Autowired
	public EmailDao emailDao;

	private long version = 1L;
	
	public void run() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {

			Email entity = em.find(Email.class, 1);
			version = entity.getVersion();
			System.out.println("Service B version: " + version);
			
			entity.setName("email@domainB.pl");
			
			em.persist(entity);

			txn.commit();

		} catch (OptimisticLockException ex) {
			
			System.out.println("OptimisticLockException Service B: " + ex.toString());
			
		} catch (PersistenceException  ex) {

			System.out.println("PersistenceException Service B: " + ex.toString());
			
		} finally {
			if (txn.isActive())
				txn.rollback();
		}

	}

	public void sendEmail(Email email) {

		System.out.println("Email B Sender: " + email.toString());
	}

}
