package book.service;

import book.dto.UserLoginDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {
	UserLoginDto login(String username, String password) throws RemoteException;
	boolean forget(String username, String newPassword) throws RemoteException;
}
