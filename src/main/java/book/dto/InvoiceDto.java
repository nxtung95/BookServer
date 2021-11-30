package book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class InvoiceDto implements Serializable {
	private static final long serialVersionUID = 5L;

	private String invoiceNo;
	private String productName;
	private String customerName;
	private Date saleDate;
	private BigDecimal quantiy;
	private BigDecimal price;
	private BigDecimal totalPrice;
}
