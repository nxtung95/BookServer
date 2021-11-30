package book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "customer_id")
	private String customerId;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String name;
	private String address;

	public Customer(String customerId, Date birthday, String name, String address) {
		this.customerId = customerId;
		this.birthday = birthday;
		this.name = name;
		this.address = address;
	}
}
