package com.shuaibi.zaizaigoods.Service.impl;

import com.shuaibi.zaizaicommons.entity.util.FileEntity;
import com.shuaibi.zaizaicommons.util.*;
import com.shuaibi.zaizaigoods.Service.GoodService;
import com.shuaibi.zaizaicommons.entity.goods.Good;
import com.shuaibi.zaizaigoods.mapper.GoodMapper;
import com.shuaibi.zaizaigoods.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author gzp
 * @date 2018/11/16 16:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodServiceImpl implements GoodService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodMapper goodMapper;

	@Autowired
	private UploadUtil uploadUtil;

	/**
	 * 添加商品
	 * 
	 * @param good 商品
	 * @param files 图片文件
	 * @return Result
	 * @throws IOException Exception
	 */
	@Override
	public Result addGood(Good good, MultipartFile[] files) throws IOException {

		// 校验商品参数是否合理
		if (!checkGood(good, "add")) {
			return ResultUtils.warn(ResultCode.FAIL);
		}
		// 设置添加时间
		good.setTime(TimeUtils.nowTimeFormat());
		good.setUpdateTime(TimeUtils.nowTimeFormat());

		// 添加到文件服务器并且获取商品对应路径
		List<FileEntity> fileEntities = uploadUtil.batchUpload(files);
		good.setFileEntities(fileEntities);

		// 保存商品
		if (goodMapper.addGood(good) <= 0) {
			return ResultUtils.warn(ResultCode.INSERT_ERROR);
		}
		// 保存图片路径
		if (goodMapper.addPic(good.getFileEntities(), good.getGoodId()) <= 0) {
			return ResultUtils.warn(ResultCode.INSERT_PIC_ERROR);
		}
		return ResultUtils.success(null);
	}

	/**
	 * 查看商品详情
	 * 
	 * @param id 商品Id
	 * @return Result
	 */
	@Override
	public Result queryGoodById(Integer id) {
		if (id == 0 || id == null) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		Good good = goodMapper.queryGoodById(id);
		return ResultUtils.success(good);
	}

	/**
	 * 修改商品信息
	 * 
	 * @param good 商品
	 * @param files 图片文件
	 * @return Result
	 * @throws IOException Exception
	 */
	@Override
	public Result modifyGood(Good good, MultipartFile[] files) {

		if (good.getGoodId() == 0 || !checkGood(good, "modify")) {
			return ResultUtils.warn(ResultCode.FAIL);
		}

		if (files.length != 0) {
			try {
				List<FileEntity> fileEntityList = goodMapper.queryPic(good.getGoodId());
				List<FileEntity> fileEntities = uploadUtil.batchUpload(files);
				good.setFileEntities(fileEntities);
				uploadUtil.deleteFile(fileEntityList);
				goodMapper.deleteByGoodId(good.getGoodId());
				if (goodMapper.addPic(good.getFileEntities(), good.getGoodId()) <= 0) {
					return ResultUtils.warn(ResultCode.INSERT_PIC_ERROR);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResultUtils.warn(ResultCode.INSERT_PIC_ERROR, "修改图片失败");
			}
		}

		good.setUpdateTime(TimeUtils.nowTimeFormat());
		goodMapper.updateGood(good);
		return ResultUtils.success(null);
	}

	/**
	 * 删除商品
	 * 
	 * @param goodId 商品id
	 * @return Result
	 */
	@Override
	public Result deleteGood(String goodId) {
		int id = Integer.parseInt(goodId);
		goodMapper.deleteGoodByGoodId(id);
		List<FileEntity> fileEntityList = goodMapper.queryPic(id);
		try {
			uploadUtil.deleteFile(fileEntityList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		goodMapper.deleteByGoodId(id);
		return ResultUtils.success("删除成功");
	}

	@Override
	public Result fuzzyQuery(Map map) {
		Integer pageSize = 5;
		Integer pageNum = 1;
		String sortField = "";
		String sortWay = "";
		String queryStr = "";
		if (map.containsKey(PageBeanField.PAGESIZE.getField())
				&& map.get(PageBeanField.PAGESIZE.getField()) != null
				&& !"".equals(map.get(PageBeanField.PAGESIZE.getField()))) {
			pageSize = (Integer) map.get(PageBeanField.PAGESIZE.getField());
		}

		if (map.containsKey(PageBeanField.PAGENUM.getField())
				&& map.get(PageBeanField.PAGENUM.getField()) != null
				&& !"".equals(map.get(PageBeanField.PAGENUM.getField()))) {
			pageNum = (Integer) map.get(PageBeanField.PAGENUM.getField());
		}

		if (map.containsKey(PageBeanField.SORTFIELD.getField())
				&& map.get(PageBeanField.SORTFIELD.getField()) != null
				&& !"".equals(map.get(PageBeanField.SORTFIELD.getField()))) {
			sortField = (String) map.get(PageBeanField.SORTFIELD.getField());
		}

		if (map.containsKey(PageBeanField.SORTWAY.getField())
				&& map.get(PageBeanField.SORTWAY.getField()) != null
				&& !"".equals(map.get(PageBeanField.SORTWAY.getField()))) {
			sortWay = (String) map.get(PageBeanField.SORTWAY.getField());
		}

		if (map.containsKey(PageBeanField.QUERYTERM.getField())
				&& map.get(PageBeanField.QUERYTERM.getField()) != null
				&& !"".equals(map.get(PageBeanField.QUERYTERM.getField()))) {
			queryStr = (String) map.get(PageBeanField.QUERYTERM.getField());
		}

		PageBean<Good> pageBean = new PageBean<>();
		pageBean.setPageSize(pageSize);
		pageBean.setPageNum(pageNum);
		Integer begin = (pageNum - 1) * pageSize;
		List list = goodMapper.fuzzyQuery(begin, pageSize, queryStr, sortField, sortWay);
		pageBean.setData(list);
		// 查询总条数
		int count = goodMapper.queryCount(queryStr);
		pageBean.setTotalCount(count);
		int totalPage = (count - 1) / pageSize + 1;
		pageBean.setTotalPage(totalPage);

		return ResultUtils.success(pageBean);
	}

	/**
	 * 校验商品信息
	 * 
	 * @param good 商品信息
	 * @param type add还是modify
	 * @return boolean
	 */
	public boolean checkGood(Good good, String type) {
		if (type.equals("add")) {
			Good good1 = goodMapper.queryGoodByName(good.getGoodName(), good.getShop().getShopId());
			if (!ObjectUtils.isEmpty(good1)) {
				return false;
			}
		}
		if (type.equals("modify")) {
			Good good1 = goodMapper.queryGoodByName(good.getGoodName(), good.getShop().getShopId());
			if (!ObjectUtils.isEmpty(good1) && good1.getGoodName().equals(good.getGoodName())
					&& good1.getGoodId() == good.getGoodId()) {
				return false;
			}
		}
		return true;
	}

}
