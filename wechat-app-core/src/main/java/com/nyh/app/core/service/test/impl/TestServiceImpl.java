package com.nyh.app.core.service.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyh.app.common.vo.test.TestVo;
import com.nyh.app.core.orm.test.domain.Test;
import com.nyh.app.core.orm.test.mapper.TestMapper;
import com.nyh.app.core.service.test.TestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper testMapper;

	@Override
	public String insert(TestVo testVo) {
		// TODO Auto-generated method stub
		testMapper.insert(testVo);
		return "success";
	}

	@Override
	public Test findUserByName(String name) {
		// TODO Auto-generated method stub
		Test findUserByName = testMapper.findUserByName(name);
		log.info("findUserByName[{}]",findUserByName);
		return findUserByName;
	}

}
