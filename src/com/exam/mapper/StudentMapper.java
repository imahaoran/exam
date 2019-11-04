package com.exam.mapper;

import java.util.List;

import com.exam.pojo.Student;

public interface StudentMapper {
	
	List<Student> selectAll();
	
	Student selectById(String sid);
	
	int insert(Student student);
	
	int updateById(Student student);
	
	int deleteById(String sid);
}
