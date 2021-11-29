package book.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Getter
@Setter
public class Staff extends User {
	@Column(name = "staff_id")
	private String staffId;

	public Staff() {
		super();
	}
}
