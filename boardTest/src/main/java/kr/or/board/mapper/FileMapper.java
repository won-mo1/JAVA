package kr.or.board.mapper;

import java.util.List;
import java.util.Map;

public interface FileMapper {

	List<Map<String, Object>> getList();

	void insertFile(Map<String, Object> map);

}
