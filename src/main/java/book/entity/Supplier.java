package book.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "supplier")
@Data
public class Supplier implements Serializable {
	private static final long serialVersionUID = 7L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String address;

	public Supplier(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
	private Set<Product> products;
}
