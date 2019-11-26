package com.exam.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.mapper.ExamMapper;
import com.exam.mapper.TeacherMapper;
import com.exam.pojo.Exam;
import com.exam.pojo.PageInfo;
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
	@Override
	public void zipExam(String srcPath,String tarName) {
		File srcfile = new File(srcPath);
		File tarFile = new File(tarName);
		String rootName = tarFile.getName();
		int length = rootName.length();
		length = length - 4;
		rootName = rootName.substring(0,length);
 		if(!srcfile.exists()) {
			System.out.println("Ô´²»´æÔÚ");
			return;
		}
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(tarFile));
			compressDir(zos, srcfile,rootName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(zos!=null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void compressDir(ZipOutputStream zos, File srcFile, String parentDir) {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		File[] files = srcFile.listFiles();
		try {
			for (File file : files) {
				if (file.isFile()) {
					ZipEntry zipEntry = new ZipEntry(parentDir + File.separator + file.getName());
					zos.putNextEntry(zipEntry);
					fin = new FileInputStream(file);
					bin = new BufferedInputStream(fin);
					int x = 0;
					while ((x = bin.read()) != -1) {
						zos.write(x);
					}
				} else {
					compressDir(zos, file, parentDir + File.separator + file.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bin != null) {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			return;
		}
		delete(file);
	}
	private void delete(File file) {
		if(file.isFile()) {
			file.delete();
		}else {
			File[] files = file.listFiles();
			for (File f : files) {
				delete(f);
			}
			file.delete();
		}
	}
	@Override
	public PageInfo selectPage(PageInfo pageInfo) {
		
		return null;
	}


}
