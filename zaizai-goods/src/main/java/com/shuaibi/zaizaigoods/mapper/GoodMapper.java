package com.shuaibi.zaizaigoods.mapper;

import com.shuaibi.zaizaicommons.entity.goods.Good;
import com.shuaibi.zaizaicommons.entity.util.FileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author gzp
 * @date 2018/11/16 16:34
 */
public interface GoodMapper {

	int addGood(@Param("good") Good good);

	Good queryGoodByName(@Param("goodName") String name, @Param("shopId") int shopId);

	Good queryGoodById(int id);

	int addPic(@Param("pics") List<FileEntity> fileEntities, @Param("goodId") int goodId);

	List<FileEntity> queryPic(int id);

	void deleteByGoodId(int id);

	boolean updateGood(@Param("good") Good good);

	void deleteGoodByGoodId(int goodId);

	List fuzzyQuery(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize, @Param("queryStr") String queryStr,
			@Param("sortField") String sortField, @Param("sortWay") String sortWay);

	int queryCount(@Param("queryStr") String queryStr);
}
