package pl.itse.optimistic.lock.runnable;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.itse.optimistic.lock.dao.EmailDao;
import pl.itse.optimistic.lock.model.Email;

@Component
public class EmailSenderARunnable implements Runnable {

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
			em.lock(entity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			version = entity.getVersion();
			System.out.println("Service A version: " + version);

			entity.setName("email@domainA.pl");

			em.persist(entity);
			em.flush();

			txn.commit();

		} catch (OptimisticLockException ex) {

			System.out.println("OptimisticLockException Service A: " + ex.toString());

		} catch (PersistenceException ex) {

			System.out.println("PersistenceException Service A: " + ex.toString());

		} finally {
			if (txn.isActive())
				txn.rollback();
		}

		// for (Email message : emailDao.findAll()) {
		// sendEmail(message);
		// }
	}

}
