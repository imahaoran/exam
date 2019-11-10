package com.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.mapper.ResultMapper;
import com.exam.pojo.Result;
import com.exam.service.ResultService;
@Service
public class ResultServiceImpl implements ResultService{
	@Resource
	private ResultMapper resultMapper;

	@Override
	public List<Result> selectAllResult() {
		return resultMapper.selectAll();
	}

	@Override
	public List<Result> selectResultByEid(int eid) {
		return resultMapper.selectByEid(eid);
	}

	@Override
	public List<Result> selectResultBySid(String sid) {
		return resultMapper.selectBySid(sid);
	}

	@Override
	public int insertResult(Result result) throws Exception {
		return resultMapper.insert(result);
	}

	@Override
	public int updateResultById(Result result) throws Exception {
		return resultMapper.updateById(result);
	}

	@Override
	public int deleteResultById(int eid) throws Exception {
		return resultMapper.deleteById(eid);
	}

	@Override
	public Result selectBySE(String sid,int eid) {
		return resultMapper.selectBySE(sid, eid);
	}
}
