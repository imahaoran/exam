package com.exam.mapper;

import java.util.List;

import com.exam.pojo.PageInfo;
import com.exam.pojo.Teacher;

public interface TeacherMapper {
	
	List<Teacher> selectAll();
	
	Teacher selectById(String tid);
	
	int insert(Teacher teacher);
	
	int updateById(Teacher teacher);
	
	int deleteById(String tid);
	
	List<Teacher> selectByPage(PageInfo pageInfo);
	
	int selectCount();
}
