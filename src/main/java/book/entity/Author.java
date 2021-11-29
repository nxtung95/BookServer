package book.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author")
@Data
public class Author implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String address;
	private String phone;

	public Author(String name) {
		this.name = name;
	}
}
