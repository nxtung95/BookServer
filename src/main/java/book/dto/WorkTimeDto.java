package book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class WorkTimeDto implements Serializable {
	private static final long serialVersionUID = 6L;
	int id;
	private Time startTime;
	private Time endTime;
}
