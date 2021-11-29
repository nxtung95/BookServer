package book.repository;

import book.dto.ProductDto;
import book.entity.Product;
import book.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<ProductDto> search(String productId, String productName, int categoryId, String supplierName) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.product_id as productId, c.name as categoryName, a.name as productName, a.quantity, a.price, b.name as supplier, a.image, " +
					"GROUP_CONCAT(f.name) as author, a.total_page as totalPage, d.name as publisher FROM product a ");
			sql.append("INNER JOIN supplier b ON  a.supplier_id = b.id ");
			sql.append("INNER JOIN category c ON  a.category_id = c.id ");
			sql.append("LEFT JOIN publisher d ON  a.publisher_id = d.id ");
			sql.append("LEFT JOIN book_author e ON  a.id = e.product_id ");
			sql.append("LEFT JOIN author f ON  e.author_id = f.id ");
			sql.append("WHERE 1=1 ");
			if (productId != "" && productId != null) {
				sql.append("AND a.product_id LIKE :productId ");
			}
			if (productName != "" && productName != null) {
				sql.append("AND a.name LIKE :productName ");
			}
			if (categoryId != 0) {
				sql.append("AND c.id = :categoryId ");
			}
			if (supplierName != "" && supplierName != null) {
				sql.append("AND b.name LIKE :supplierName ");
			}
			sql.append("GROUP BY a.product_id, c.name, a.name, a.quantity, a.price, b.name, a.image, a.total_page, d.name ");
			Query query = em.createNativeQuery(sql.toString());
			if (productId != "" && productId != null) {
				query.setParameter("productId", "%" + productId + "%");
			}
			if (productName != "" && productName != null) {
				query.setParameter("productName", "%" + productName + "%");
			}
			if (categoryId != 0) {
				query.setParameter("categoryId", categoryId);
			}
			if (supplierName != "" && supplierName != null) {
				query.setParameter("supplierName", "%" + supplierName + "%");
			}
			List<Object[]> rows = query.getResultList();
			List<ProductDto> productDtoList = new ArrayList<>(rows.size());
			for (Object[] row : rows) {
				productDtoList.add(new ProductDto((String) row[0],(String) row[1],
						(String) row[2], (int) row[3], (BigDecimal) row[4], (String) row[5], (String) row[6], (String) row[7], (int) row[8], (String) row[9]));
			}
			return productDtoList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public boolean add(Product product) {
		try {
			em.persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
