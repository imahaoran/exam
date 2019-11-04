package com.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.mapper.ExamMapper;
import com.exam.pojo.Exam;
import com.exam.pojo.Result;
import com.exam.pojo.Student;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.service.StudentService;
@Service
public class ExamServiceImpl implements ExamService{
	@Resource
	private ExamMapper examMapper;
	@Resource
	private ResultService resultService;
	@Resource
	private StudentService studentService;
	@Override
	public List<Exam> selectAllExam() {
		return examMapper.selectAll();
	}

	@Override
	public Exam selectExamById(int eid) {
		return examMapper.selectById(eid);
	}

	@Override
	public int insertExam(Exam exam) throws Exception{
		return examMapper.insert(exam);
	}

	@Override
	public int deleteExamById(int eid) throws Exception {
		return examMapper.deleteById(eid);
	}

	@Override
	public int updateExam(Exam exam) throws Exception {
		return examMapper.updateById(exam);
	}

	@Override
	public List<Student> selectStudents(int eid) throws Exception{
		List<Student> students = new ArrayList<Student>();
		List<Result> results = resultService.selectResultByEid(eid);
		for (Result result : results) {
			students.add(result.getStudent());
		}
		return students;
	}
}
