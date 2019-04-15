package com.shuaibi.zaizaigoods.Service;

import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.entity.goods.Good;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author gzp
 * @date 2018/11/16 16:14
 */
public interface GoodService {

	Result addGood(Good good, MultipartFile[] file) throws IOException;

	Result queryGoodById(Integer id);

	Result modifyGood(Good good, MultipartFile[] files);

	Result deleteGood(String goodId);

	Result fuzzyQuery(Map map);
}
