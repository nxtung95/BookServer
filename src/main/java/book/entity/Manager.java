package book.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Getter
@Setter
public class Manager extends User {
	private String role;

	public Manager() {
		super();
	}
}
