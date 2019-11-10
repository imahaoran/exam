package com.exam.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.mapper.ExamMapper;
import com.exam.mapper.StudentMapper;
import com.exam.pojo.Exam;
import com.exam.pojo.Result;
import com.exam.pojo.Student;
import com.exam.service.ResultService;
import com.exam.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{
	@Resource
	private StudentMapper studentMapper;
	@Resource
	private ExamMapper examMapper;
	@Resource
	private ResultService resultService;
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

	@Override
	public void upload(int eid,String sid,String path, MultipartFile file) throws Exception {
		Exam exam = examMapper.selectById(eid);
		String filename = file.getOriginalFilename();
		String filepath = path+"\\"+exam.getTid()+"\\"+exam.getEid()+"\\"+sid+"\\"+filename;
		File filedir = new File(path+"\\"+exam.getTid()+"\\"+exam.getEid()+"\\"+sid+"\\");
		if(filedir.exists()) {
			FileUtils.cleanDirectory(filedir);
		}
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filepath));
		Result result = resultService.selectBySE(sid, eid);
		result.setSubmitfile(filename);
		resultService.updateResultById(result);
	}

}
