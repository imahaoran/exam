package com.exam.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.mapper.ExamMapper;
import com.exam.mapper.TeacherMapper;
import com.exam.pojo.Exam;
import com.exam.pojo.Teacher;
import com.exam.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService{
	Logger logger = Logger.getLogger(getClass());
	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private ExamMapper examMapper;
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
	@Override
	public void upLoadPaper(int eid, String path, MultipartFile file) throws Exception {
		Exam exam = examMapper.selectById(eid);
		String filename = file.getOriginalFilename();
		String filepath = path+"\\"+exam.getTid()+"\\"+exam.getEid()+"\\"+"paper"+"\\"+filename;
		File paper = new File(path+"\\"+exam.getTid()+"\\"+exam.getEid()+"\\"+"paper\\");
		if(paper.exists()) {
			FileUtils.cleanDirectory(paper);
		}
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filepath));
		exam.setEpaper(exam.getTid()+"\\"+exam.getEid()+"\\"+"paper"+"\\"+filename);
		examMapper.updateById(exam);
	}


}
