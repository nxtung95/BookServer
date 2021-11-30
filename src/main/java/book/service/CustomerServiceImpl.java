package book.service;

import book.dto.CustomerDto;
import book.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerServiceImpl() throws RemoteException {}

	@Override
	public List<CustomerDto> findAll(String customerId, String name, String productName) throws RemoteException {
		List<CustomerDto> customerDtoList = customerRepository.findAll(customerId, name, productName);
		System.out.println("Response customer list: size " + customerDtoList.size());
		return customerDtoList;
	}

	@Override
	public boolean add(String customerId, Date birthday, String name, String address) throws RemoteException {
		return customerRepository.add(customerId, birthday, name, address);
	}

	@Override
	public boolean edit(int id, String customerId, Date birthday, String name, String address) throws RemoteException {
		return customerRepository.edit(id, customerId, birthday, name, address);
	}

	@Override
	public boolean remove(int id) throws RemoteException {
		return customerRepository.remove(id);
	}
}
