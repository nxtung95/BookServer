package book.service;

import book.dto.ProductDto;
import book.entity.*;
import book.repository.CategoryRepository;
import book.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl extends UnicastRemoteObject implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public ProductServiceImpl() throws RemoteException {
	}

	@Override
	public List<ProductDto> search(String productId, String productName, int categoryId, String supplierName) throws RemoteException {
		try {
			List<ProductDto> products = productRepository.search(productId, productName, categoryId, supplierName);
			System.out.println("Response product list, size: " + products.size());
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(String productName, String productId, int categoryId, String price, String supplier, String image, int totalPage, String author, String publisher) throws RemoteException {
		try {
			Product product = new Product(productId, productName, "", image, totalPage, new BigDecimal(price), 0);
			if (publisher != null && publisher != "") {
				product.setPublisher(new Publisher(publisher));
			}
			if (author != null && author != "") {
				Set<Author> authorSet = new HashSet<>();
				authorSet.add(new Author(author));
				product.setAuthors(authorSet);
			}
			product.setSupplier(new Supplier(supplier));
			Category category = categoryRepository.findById(categoryId);
			product.setCategory(category);

			boolean result = productRepository.add(product);
			System.out.println("Response add product, result: " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
