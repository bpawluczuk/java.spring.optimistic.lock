package pl.itse.optimistic.lock.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.itse.optimistic.lock.model.Email;

@Component
@Transactional
public class EmailDao {

	@PersistenceContext
	EntityManager entityManager;
	
	public void create(Email entity) {
		entityManager.persist(entity);
	}
	
	public void update(int id, String name) {
		
		Email entity = entityManager.find(Email.class, id);
		entity.setName(name);
		entityManager.persist(entity);
	}
	
	public void delete(int id) {
		
		Email entity = entityManager.find(Email.class, id);
		entityManager.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> findAll() {

		return entityManager.createQuery("from " + Email.class.getName()).getResultList();		
	}

}
