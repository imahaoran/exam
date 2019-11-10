package com.exam.service;

import java.util.List;

import com.exam.pojo.Result;

public interface ResultService {
		//��ѯȫ��
		List<Result> selectAllResult();
		//���ݿ��ԺŲ�ѯ
		List<Result> selectResultByEid(int eid);
		//����ѧ�Ų�ѯ
		List<Result> selectResultBySid(String sid);
		//���һ���¼�¼
		int insertResult(Result result) throws Exception;
		//���¼�¼
		int updateResultById(Result result) throws Exception;
		//ɾ����¼
		int deleteResultById(int eid) throws Exception;
		//����ѧ�ſ��ԺŲ�ѯ
		Result selectBySE(String sid,int eid);
}
