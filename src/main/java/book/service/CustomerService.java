package book.service;

import book.dto.CustomerDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface CustomerService extends Remote {
	List<CustomerDto> findAll(String customerId, String name, String productName) throws RemoteException;
	boolean add(String customerId, Date birthday, String name, String address) throws RemoteException;
	boolean edit(int id, String customerId, Date birthday, String name, String address) throws RemoteException;
	boolean remove(int id) throws RemoteException;
}
