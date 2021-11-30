package book.app;

import book.service.*;
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
			CustomerServiceImpl customerService = applicationContext.getBean(CustomerServiceImpl.class);
			InvoiceServiceImpl invoiceService = applicationContext.getBean(InvoiceServiceImpl.class);

			Naming.rebind("rmi://" + HOST + ":" + PORT + "/userService", userService);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/productService", productService);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/categoryService", categoryService);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/customerService", customerService);
			Naming.rebind("rmi://" + HOST + ":" + PORT + "/invoiceService", invoiceService);
			System.out.println("Server started!");
		} catch (Exception e) {
			System.out.println("Error starting server... " + e);
		}

	}
}
