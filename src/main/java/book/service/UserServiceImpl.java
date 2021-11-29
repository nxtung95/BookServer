package book.service;

import book.dto.UserLoginDto;
import book.entity.User;
import book.repository.UserRepository;
import book.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Service
public class UserServiceImpl extends UnicastRemoteObject implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl() throws RemoteException {
	}

	@Override
	public UserLoginDto login(String username, String password) throws RemoteException {
		System.out.println("Request login: " + username + ", password: " + password);
		User user = userRepository.getUserByName(username);
		if (user == null) {
			System.out.println("Login fail");
			return null;
		}
		String requestHash = SecurityUtils.getMD5(username + password);
		if (!requestHash.equals(user.getPassword())) {
			System.out.println("Login fail");
			return null;
		}
		System.out.println("Login success");
		String staffId = user.getStaff() != null ? user.getStaff().getName() : "";
		String role = user.getManager() != null ? user.getManager().getRole() : "";
		return new UserLoginDto(user.getName(), user.getAddress(), role, staffId);
	}

	@Override
	public boolean forget(String username, String newPassword) throws RemoteException {
		try {
			System.out.println("Request forget pass: " + username  + ",new: " + newPassword);
			User user = userRepository.getUserByName(username);
			if (user == null) {
				System.out.println("Username không hợp lệ ");
				return false;
			}
			String hashNewPassword = SecurityUtils.getMD5(username + newPassword);
			user.setPassword(hashNewPassword);
			userRepository.update(user);
			System.out.println("Reset pass success");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
