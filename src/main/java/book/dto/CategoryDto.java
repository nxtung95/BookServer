package book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CategoryDto implements Serializable {
	private static final long serialVersionUID = 3L;
	private int id;
	private String name;
}
