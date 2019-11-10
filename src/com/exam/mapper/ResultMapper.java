package com.exam.mapper;

import java.util.List;

import com.exam.pojo.Result;

public interface ResultMapper {
	//��ѯȫ��
	List<Result> selectAll();
	//���ݿ��ԺŲ�ѯ
	List<Result> selectByEid(int eid);
	//����ѧ�Ų�ѯ
	List<Result> selectBySid(String sid);
	//����ѧ�ſ��ԺŲ�ѯ
	Result selectBySE(String sid,int eid);
	//����
	int insert(Result result);
	//����
	int updateById(Result result);
	//ɾ��
	int deleteById(int eid);
}
