package com.shuaibi.zaizaigoods.mapper;

import com.shuaibi.zaizaicommons.entity.goods.Test;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gzp
 * @date 2018/12/3 16:26
 */

public interface ITestMapper { //mybatis指向此地址，所以不需要加@Mapper什么的，对应的文件在resources的mapper下，配置信息在application-dev下
	void addTest(Test test);

	Test queryTestById(@Param("id") int testId);

	void updateTest(@Param("test") Test test); //这里用Param传（也可以和addTest一样），因为有些情况传过来的是Map，所以用这种方式可以不用再去写一个实体类

	void deleteTest(int[] ids);
}
