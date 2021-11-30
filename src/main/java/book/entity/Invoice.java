package book.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sale_invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "invoice_no")
	private String invoiceNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id", referencedColumnName = "user_id")
	private Staff staff;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "detail_sale_invoice",
			joinColumns = { @JoinColumn(name = "invoice_id") },
			inverseJoinColumns = {@JoinColumn(name = "product_id") })
	private Set<Product> products;
}
