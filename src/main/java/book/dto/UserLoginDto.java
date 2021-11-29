package book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String role;
	private String staffIf;
}
