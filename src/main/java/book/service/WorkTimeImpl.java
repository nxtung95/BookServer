package book.service;

import book.dto.WorkTimeDto;
import book.entity.WorkTime;
import book.repository.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkTimeImpl extends UnicastRemoteObject implements WorkTimeService {
	@Autowired
	private WorkTimeRepository workTimeRepository;

	public WorkTimeImpl() throws RemoteException { }

	@Override
	public List<WorkTimeDto> findAll() throws RemoteException {
		List<WorkTime> workTimes = workTimeRepository.findAll();
		List<WorkTimeDto> workTimeDtoList = workTimes.stream().map(w -> new WorkTimeDto(w.getId(), w.getStartTime(), w.getEndTime())).collect(Collectors.toList());
		return workTimeDtoList;
	}
}
