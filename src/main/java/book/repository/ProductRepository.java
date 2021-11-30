package book.repository;

import book.dto.ProductDto;
import book.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ProductRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<ProductDto> search(String productId, String productName, int categoryId, String supplierName) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.id, a.product_id as productId, c.name as categoryName, a.name as productName, a.quantity, a.price, b.name as supplier, a.image, " +
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
			sql.append("ORDER BY a.id ASC ");
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
				productDtoList.add(new ProductDto((Integer) row[0], (String) row[1],(String) row[2],
						(String) row[3], (int) row[4], (BigDecimal) row[5], (String) row[6], (String) row[7], (String) row[8], (int) row[9], (String) row[10]));
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
			Publisher publisher = null;
			if (product.getPublisher() != null) {
				publisher = product.getPublisher();
				em.persist(publisher);
			}
			Supplier supplier = null;
			if (product.getSupplier() != null) {
				supplier = product.getSupplier();
				em.persist(supplier);
			}
			Set<Author> authors = null;
			if (product.getAuthors() != null && !product.getAuthors().isEmpty()) {
				authors = product.getAuthors();
				for (Author a : authors) {
					em.persist(a);
				}
			}
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO product(category_id, product_id, publisher_id, supplier_id, name, description, image, total_page, price, quantity) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			Query query = em.createNativeQuery(sql.toString());
			int index = 1;
			Integer categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
			query.setParameter(index++, categoryId);

			query.setParameter(index++, product.getProductId());

			Integer publisherId = publisher != null ? publisher.getId() : null;
			query.setParameter(index++, publisherId);

			Integer supplierId = supplier != null ? supplier.getId() : null;
			query.setParameter(index++, supplierId);

			query.setParameter(index++, product.getName());
			query.setParameter(index++, product.getDescription());
			query.setParameter(index++, product.getImage());
			query.setParameter(index++, product.getTotalPage());
			query.setParameter(index++, product.getPrice());
			query.setParameter(index++, product.getQuantity());
			BigInteger bigInteger = (BigInteger) em.createNativeQuery("SELECT LAST_INSERT_ID() FROM product").getSingleResult();
			Integer id = bigInteger.intValue() - 1;
			if (authors != null) {
				sql = new StringBuilder();
				sql.append("INSERT INTO book_author(product_id, author_id) VALUES(?, ?)");
				query = em.createNativeQuery(sql.toString());
				for (Author a : authors) {
					query.setParameter(1, id);
					query.setParameter(2, a.getId());
					query.executeUpdate();
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean update(Product product) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE product SET product_id = ?, name = ?, description = ?, image = ?, total_page = ?, price = ?, quantity = ? WHERE id = ?");
			Query query = em.createNativeQuery(sql.toString());
			int index = 1;
			query.setParameter(index++, product.getProductId());
			query.setParameter(index++, product.getName());
			query.setParameter(index++, product.getDescription());
			query.setParameter(index++, product.getImage());
			query.setParameter(index++, product.getTotalPage());
			query.setParameter(index++, product.getPrice());
			query.setParameter(index++, product.getQuantity());
			query.setParameter(index++, product.getId());
			query.executeUpdate();

			sql = new StringBuilder("UPDATE category SET name = ? WHERE id = ?");
			Integer categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
			query = em.createNativeQuery(sql.toString());
			query.setParameter(1, product.getCategory() != null ? product.getCategory().getName(): null);
			query.setParameter(2, categoryId);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM product WHERE id = ?");
			Query query = em.createNativeQuery(sql.toString());
			int index = 1;
			query.setParameter(index++, id);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
