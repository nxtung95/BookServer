package book.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String username;
	private String password;
	private String address;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "user_id")
	private Staff staff;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "user_id")
	private Manager manager;
}
