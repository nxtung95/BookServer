package book.service;

import book.dto.CategoryDto;
import book.dto.InvoiceDto;
import book.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl extends UnicastRemoteObject implements InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;

	public InvoiceServiceImpl() throws RemoteException { }

	@Override
	public List<InvoiceDto> search(String invoiceNo, String customerName, String productName) throws RemoteException {
		return invoiceRepository.search(invoiceNo, customerName, productName);
	}
}
