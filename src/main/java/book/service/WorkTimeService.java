package book.service;

import book.dto.WorkTimeDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface WorkTimeService extends Remote {
	List<WorkTimeDto> findAll() throws RemoteException;
}
