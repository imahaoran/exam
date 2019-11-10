package com.exam.service;

import java.util.List;

import com.exam.pojo.Result;

public interface ResultService {
		//查询全部
		List<Result> selectAllResult();
		//根据考试号查询
		List<Result> selectResultByEid(int eid);
		//根据学号查询
		List<Result> selectResultBySid(String sid);
		//添加一条新纪录
		int insertResult(Result result) throws Exception;
		//更新记录
		int updateResultById(Result result) throws Exception;
		//删除记录
		int deleteResultById(int eid) throws Exception;
		//根据学号考试号查询
		Result selectBySE(String sid,int eid);
}
