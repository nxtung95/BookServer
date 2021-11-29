package book.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "publisher")
@Data
public class Publisher implements Serializable {
	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String address;

	public Publisher(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	private Set<Product> products;
}
