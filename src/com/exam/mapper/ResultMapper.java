package com.exam.mapper;

import java.util.List;

import com.exam.pojo.Result;

public interface ResultMapper {
	//查询全部
	List<Result> selectAll();
	//根据考试号查询
	List<Result> selectByEid(int eid);
	//根据学号查询
	List<Result> selectBySid(String sid);
	//根据学号考试号查询
	Result selectBySE(String sid,int eid);
	//插入
	int insert(Result result);
	//更新
	int updateById(Result result);
	//删除
	int deleteById(int eid);
}
