package book.service;

import book.dto.InvoiceDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InvoiceService extends Remote {
	List<InvoiceDto> search(String invoiceNo, String customerName, String productName) throws RemoteException;
}
