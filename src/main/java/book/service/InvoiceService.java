package book.service;

import book.dto.CategoryDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface InvoiceService extends Remote {
	List<CategoryDto> search(String invoiceNo, String customerName, Date createDate, int work, String staffName, String productName) throws RemoteException;
}
