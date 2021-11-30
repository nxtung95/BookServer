package book.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "product_id")
	private String productId;

	private String name;
	private String description;
	private String image;

	@Column(name = "total_page")
	private int totalPage;
	private BigDecimal price;
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "publisher_id", referencedColumnName = "id")
	private Publisher publisher;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private Supplier supplier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private Category category;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_author",
			joinColumns = { @JoinColumn(name = "product_id") },
			inverseJoinColumns = {@JoinColumn(name = "author_id") })
	private Set<Author> authors;

	public Product(String productId, String name, String description, String image, int totalPage, BigDecimal price, int quantity) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.image = image;
		this.totalPage = totalPage;
		this.price = price;
		this.quantity = quantity;
	}
}
