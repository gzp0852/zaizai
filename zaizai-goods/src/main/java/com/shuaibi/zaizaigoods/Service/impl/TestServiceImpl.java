package com.shuaibi.zaizaigoods.Service.impl;

import com.shuaibi.zaizaicommons.entity.goods.Test;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.util.ResultCode;
import com.shuaibi.zaizaicommons.util.ResultUtils;
import com.shuaibi.zaizaigoods.Service.ITestService;
import com.shuaibi.zaizaigoods.mapper.ITestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author gzp
 * @date 2018/12/3 16:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl implements ITestService {

	@Autowired
	private ITestMapper iTestMapper;

	/**
	 * 新增
	 * @param test Test
	 * @return Result
	 */
	@Override
	public Result addTest(Test test) {
		// 首先校验
		if (ObjectUtils.isEmpty(test) || "".equals(test.getName())) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR); // 这个是我抄我们以前项目的，返回一个json的Result对象，对象包含code码，成功失败的信息，成功失败后返回的数据
		}
		iTestMapper.addTest(test);
		return ResultUtils.success("新增成功");
	}

	/**
	 * 根据id查
	 * @param testId id
	 * @return Result
	 */
	@Override
	public Result queryTest(int testId) {
		Test test = iTestMapper.queryTestById(testId);
		if (ObjectUtils.isEmpty(test)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "查无此人");
		}
		return ResultUtils.success(test);
	}

	/**
	 * 修改
	 * @param test Test
	 * @return Result
	 */
	@Override
	public Result updateTest(Test test) {
		if (ObjectUtils.isEmpty(test) || test.getId() == 0) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "参数错误");
		}
		// ----一些其他校验然后才执行下面更新，这里字段少
		iTestMapper.updateTest(test);
		return ResultUtils.success("更新成功");
	}

	/**
	 * 批量删除
	 * @param ids id数组
	 * @return Result
	 */
	@Override
	public Result deleteTest(int[] ids) {
		if (ObjectUtils.isEmpty(ids)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		iTestMapper.deleteTest(ids);
		return ResultUtils.success("删除成功");
	}
}
