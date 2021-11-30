package book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work_time")
@Data
public class WorkTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;

	@Column(name = "end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;
}
