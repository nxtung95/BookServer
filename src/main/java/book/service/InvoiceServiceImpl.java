package book.service;

import book.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Override
	public List<CategoryDto> search(String invoiceNo, String customerName, Date createDate, int work, String staffName, String productName) throws RemoteException {
		return null;
	}
}
