package book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class CustomerDto implements Serializable {
	private static final long serialVersionUID = 4L;
	private int id;
	private String customerId;
	private String customerName;
	private Date birth;
	private String address;
	private String saleProduct;
	private BigDecimal quantity;
	private BigDecimal total;
}
