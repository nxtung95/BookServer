package book.service;

import book.dto.CategoryDto;
import book.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends UnicastRemoteObject implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl() throws RemoteException {
	}

	@Override
	public List<CategoryDto> findAll() throws RemoteException {
		List<CategoryDto> categoryDtoList = categoryRepository.findAll().stream().map(c -> new CategoryDto(c.getId(), c.getName())).collect(Collectors.toList());
		System.out.println("Response category list, size " + categoryDtoList.size());
		return categoryDtoList;
	}
}
