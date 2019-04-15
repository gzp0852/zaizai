package com.shuaibi.zaizaishiro.mapper;

@Mapper
public interface SessionCRUDMapper {

	/**
	 * 增
	 *
	 * @param jsessionId
	 * @param sessionString
	 *            使用序列化工具转换为字符串后的session
	 * @return 受影响行数
	 * @Date 2018年8月24日 下午5:11:26
	 */
	@Insert("insert into sessions(jsessionid, session) values(#{jsessionId},#{sessionString})")
	int doCreate(@Param("jsessionId") String jsessionId, @Param("sessionString") String sessionString);

	/**
	 * 查
	 *
	 * @param jsessionId
	 * @return 使用序列化工具转换为字符串后的session
	 * @Date 2018年8月24日 下午5:14:55
	 */
	@Select("select session from sessions where jsessionid=#{jsessionId}")
	String doReadSession(@Param("jsessionId") String jsessionId);

	/**
	 * 改
	 *
	 * @return 受影响行数
	 * @Date 2018年8月24日 下午5:15:33
	 */
	@Update("update sessions set session=#{sessionString} where jsessionid=#{jsessionId}")
	int doUpdate(@Param("sessionString") String sessionString, @Param("jsessionId") String jsessionId);

	/**
	 * 删
	 *
	 * @return 受影响行数
	 * @Date 2018年8月24日 下午5:15:35
	 */
	@Delete("delete from sessions where jsessionid=#{jsessionId}")
	int doDelete(@Param("jsessionId") String jsessionId);
}