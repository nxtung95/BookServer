package book.service;

import book.dto.CategoryDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CategoryService extends Remote {
	List<CategoryDto> findAll() throws RemoteException;
}
