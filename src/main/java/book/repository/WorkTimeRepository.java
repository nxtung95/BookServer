package book.repository;

import book.entity.WorkTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class WorkTimeRepository {
	@PersistenceContext
	private EntityManager em;

	public List<WorkTime> findAll() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM work_time c ");
			Query query = em.createQuery(sql.toString(), WorkTime.class);
			return (List<WorkTime>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
