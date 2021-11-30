package book.repository;

import book.dto.CustomerDto;
import book.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<CustomerDto> findAll(String customerId, String name, String productName) {
		List<CustomerDto> customerDtoList = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT a.id, a.customer_id, a.name, a.birthday, a.address, GROUP_CONCAT(d.name) as product_name, SUM(c.quantity) as total_quantity, SUM(c.total_price) as total_price FROM customer a LEFT JOIN sale_invoice b ON a.id = b.customer_id " +
					"LEFT JOIN detail_sale_invoice c ON b.id = c.invoice_id LEFT JOIN product d ON d.id = c.product_id " +
					"WHERE 1=1 ");
			if (!customerId.isEmpty() && customerId != null) {
				sql.append("AND a.customer_id LIKE :customer_id ");
			}
			if (!name.isEmpty() && name != null) {
				sql.append("AND a.name LIKE :name ");
			}
			if (!productName.isEmpty() && productName != null) {
				sql.append("AND d.name LIKE :productName ");
			}
			sql.append("GROUP BY a.id, a.customer_id, a.name, a.birthday, a.address ");
			sql.append("ORDER BY a.id ASC ");
			Query query = em.createNativeQuery(sql.toString());
			if (!customerId.isEmpty() && customerId != null) {
				query.setParameter("customer_id", "%" + customerId + "%");
			}
			if (!name.isEmpty() && name != null) {
				query.setParameter("name", "%" + name + "%");
			}
			if (!productName.isEmpty() && productName != null) {
				query.setParameter("productName", "%" + productName + "%");
			}
			List<Object[]> rows = query.getResultList();
			for (Object[] row : rows) {
				customerDtoList.add(new CustomerDto((Integer) row[0], (String) row[1], (String) row[2],
						(Date) row[3], (String) row[4], (String) row[5], (BigDecimal) row[6], (BigDecimal) row[7]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerDtoList;
	}

	@Transactional
	public boolean add(String customerId, Date birthday, String name, String address) {
		try {
			Customer c = new Customer(customerId, birthday, name, address);
			em.persist(c);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean edit(int id, String customerId, Date birthday, String name, String address) {
		try {
			StringBuilder sql = new StringBuilder("UPDATE customer SET customer_id = ?, birthday = ?, name = ?, address = ? WHERE id = ?");
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter(1, customerId);
			query.setParameter(2, birthday);
			query.setParameter(3, name);
			query.setParameter(4, address);
			query.setParameter(5, id);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean remove(int id) {
		try {
			StringBuilder sql = new StringBuilder("DELETE FROM customer WHERE id = ?");
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter(1, id);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
