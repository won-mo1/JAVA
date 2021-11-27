package kr.or.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.mapper.FileMapper;

@Service
public class FileService {

	@Autowired
	FileMapper fileMapper;

	public List<Map<String, Object>> getList() {
		// TODO Auto-generated method stub
		return fileMapper.getList();
	}

	public void insertFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		fileMapper.insertFile(map);
	}
	
}
