package book.repository;

import book.dto.InvoiceDto;
import book.dto.ProductDto;
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
public class InvoiceRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<InvoiceDto> search(String invoiceNo, String customerName, Date createDate, int work, String staffName, String productName) {
		List<InvoiceDto> invoiceDtoList = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.invoice_no, c.NAME, d.`name`, a.created_date, b.quantity, c.price, b.total_price FROM sale_invoice a ");
			sql.append("INNER JOIN detail_sale_invoice b ON a.id = b.invoice_id ");
			sql.append("INNER JOIN product c ON b.product_id = c.id ");
			sql.append("INNER JOIN customer d ON a.customer_id = d.id ");
			sql.append("INNER JOIN staff e ON a.staff_id = e.user_id");
			sql.append("INNER JOIN user f ON e.user_id = f.id ");
			sql.append("INNER JOIN staff_work_time g ON f.id = g.staff_id ");
			sql.append("INNER JOIN work_time h ON h.id = g.work_time_id ");
			sql.append("WHERE 1=1 ");
			if (!invoiceNo.isEmpty() && invoiceNo != null) {
				sql.append("AND a.invoice_no LIKE :invoice_no ");
			}
			if (!customerName.isEmpty() && customerName != null) {
				sql.append("AND d.name LIKE :customerName ");
			}
			if (createDate != null) {
				sql.append("AND DATE(c.created_date) = :created_date ");
			}
			if (work != 0) {
				sql.append("AND h.id = :work ");
			}
			if (!staffName.isEmpty() && staffName != null) {
				sql.append("AND f.name LIKE :staffName ");
			}

			if (!productName.isEmpty() && productName != null) {
				sql.append("AND d.name LIKE :productName ");
			}
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> rows = query.getResultList();
			for (Object[] row : rows) {
				invoiceDtoList.add(new InvoiceDto((String) row[1],(String) row[2],
						(String) row[3], (Date) row[4], (BigDecimal) row[5], (BigDecimal) row[6], (BigDecimal) row[7]));
			}
			return invoiceDtoList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
