package book.service;

import book.dto.ProductDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProductService extends Remote {
	List<ProductDto> search(String productId, String productName, int categoryId, String supplierName) throws RemoteException;

	boolean add(String productName, String productId, int categoryId, String price, String supplier, String image, int totalPage, String author, String publisher) throws RemoteException;
}
