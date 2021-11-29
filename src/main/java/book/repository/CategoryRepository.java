package book.repository;

import book.entity.Category;
import book.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Category> findAll() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Category c ");
			Query query = em.createQuery(sql.toString(), Category.class);
			return (List<Category>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Category findById(int id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Category c WHERE id = :id");
			Query query = em.createQuery(sql.toString(), Category.class);
			query.setParameter("id", id);
			return (Category) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
