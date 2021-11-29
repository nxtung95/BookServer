package book.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "store")
@Data
public class Store implements Serializable {
	private static final long serialVersionUID = 6L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String phone;
	private String address;
}
