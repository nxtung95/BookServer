package book.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category implements Serializable {
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Product> products;

	public Category(Integer id) {
		this.id = id;
	}
}
