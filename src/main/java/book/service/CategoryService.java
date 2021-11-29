package book.service;

import book.dto.CategoryDto;
import book.entity.Category;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CategoryService extends Remote {
	List<CategoryDto> findAll() throws RemoteException;
}
