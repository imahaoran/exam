package com.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.mapper.StudentMapper;
import com.exam.pojo.Student;
import com.exam.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{
	@Resource
	private StudentMapper studentMapper;
	@Override
	public List<Student> selectAllStudent() {
		return studentMapper.selectAll();
	}

	@Override
	public Student selectStudentById(String sid) {
		return studentMapper.selectById(sid);
	}

	@Override
	public int insertStudent(Student student) throws Exception {
		return studentMapper.insert(student);
	}

	@Override
	public int updateStudentById(Student student) throws Exception {
		return studentMapper.updateById(student);
	}

	@Override
	public int deleteStudentById(String sid) throws Exception {
		return studentMapper.deleteById(sid);
	}

}
