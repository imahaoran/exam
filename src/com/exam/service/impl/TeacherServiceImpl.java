package com.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.exam.mapper.TeacherMapper;
import com.exam.pojo.Teacher;
import com.exam.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService{
	Logger logger = Logger.getLogger(getClass());
	@Resource
	private TeacherMapper teacherMapper;
	@Override
	public List<Teacher> selectAllTeacher(){
		return teacherMapper.selectAll();
	}
	@Override
	public Teacher selectTeacherById(String tid){
		return teacherMapper.selectById(tid);
	}
	@Override
	public int insertTeacher(Teacher teacher) throws Exception{
		return teacherMapper.insert(teacher);
	}
	@Override
	public int updateTeacherById(Teacher teacher) throws Exception {
		return teacherMapper.updateById(teacher);
	}
	@Override
	public int deleteTeacherById(String tid) throws Exception {
		return teacherMapper.deleteById(tid);
	}


}
