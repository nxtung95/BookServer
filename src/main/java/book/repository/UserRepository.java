package book.repository;

import book.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public User getUserByName(String username) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM User u ");
			sql.append("WHERE u.username = :username ");
			Query query = em.createQuery(sql.toString());
			query.setParameter("username", username);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public void update(User user) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE User u ");
			sql.append("SET u.password = :password ");
			sql.append("WHERE u.id = :id ");
			Query query = em.createQuery(sql.toString());
			query.setParameter("password", user.getPassword());
			query.setParameter("id", user.getId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
