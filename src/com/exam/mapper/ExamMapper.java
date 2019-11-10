package com.exam.mapper;

import java.util.List;

import com.exam.pojo.Exam;

public interface ExamMapper {
	
	List<Exam> selectAll();
	
	Exam selectById(int eid);
	
	int insert(Exam exam);
	
	int updateById(Exam exam);
	
	int deleteById(int eid);
	
	List<Exam> selectActive();
}
