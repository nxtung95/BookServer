package book.app;

import book.service.CategoryServiceImpl;
import book.service.ProductServiceImpl;
import book.service.UserService;
import book.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerApp {
	private static final int PORT = 1099;
	private static final String HOST = "127.0.0.1";

	public ServerApp() {

	}

	public static void main(String[] args) {
		try {
			System.out.println("Server starting...");
			LocateRegistry.createRegistry(1099);
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("BeanConfig.xml");
			UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
			ProductServiceImpl productService = applicationContext.getBean(ProductServiceImpl.class);
			CategoryServiceImpl categoryService = applicationContext.getBean(CategoryServiceImpl.class);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/userService", userService);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/productService", productService);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/categoryService", categoryService);
			System.out.println("Server started!");
		} catch (Exception e) {
			System.out.println("Error starting server... " + e);
		}

	}
}
