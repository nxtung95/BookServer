package book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 2L;

	private Integer id;
	private String productId;
	private String categoryName;
	private String productName;
	private int quantity;
	private BigDecimal price;
	private String supplier;
	private String image;
	private String author;
	private int totalPage;
	private String publisher;
}
