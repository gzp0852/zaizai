package com.shuaibi.zaizaigoods.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.util.ResultCode;
import com.shuaibi.zaizaicommons.util.ResultUtils;
import com.shuaibi.zaizaigoods.Service.GoodService;
import com.shuaibi.zaizaicommons.entity.goods.Good;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author gzp
 * @date 2018/11/16 16:04
 */
@Api("Good")
@RestController
@RequestMapping("/goods")
public class GoodController {

	@Autowired
	private GoodService goodService;

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 添加商品
	 * 
	 * @param goodStr 商品参数Json
	 * @param files 图片文件
	 * @return Result
	 * @throws IOException Exception
	 */
	@ApiOperation(value = "添加商品", notes = "添加商品，包括图片")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodInfo", value = "添加用户的参数", required = true, dataType = "Json"),
			@ApiImplicitParam(name = "img", value = "商品图片集", dataType = "file", paramType = "formData") })
	@PostMapping("/addGood")
	public Result addGoods(@RequestParam("goodInfo") String goodStr,
			@RequestParam("img") MultipartFile[] files) throws IOException {
		if (ObjectUtils.isEmpty(goodStr)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		Good good = mapper.readValue(goodStr, Good.class);
		Result result = goodService.addGood(good, files);
		return result;
	}

	/**
	 * 查看商品详情
	 * 
	 * @param id 商品Id
	 * @return Result
	 */

	@ApiOperation(value = "查看商品详情", notes = "根据商品id查看商品的详情")
	@ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "Integer", paramType = "path")
	@GetMapping("/queryGoodById/{id}")
	public Result queryGoodById(@PathVariable(value = "id") Integer id) {
		Result result = goodService.queryGoodById(id);
		return result;
	}

	/**
	 * 修改商品信息
	 * 
	 * @param params 商品json
	 * @param files 图片文件
	 * @return Result
	 * @throws IOException Exception
	 */
	@ApiOperation(value = "修改商品信息", notes = "修改商品信息，包括图片")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodInfo", value = "商品修改的信息", required = true, dataType = "Json"),
			@ApiImplicitParam(name = "img", value = "商品图片集", dataType = "file", paramType = "File") })
	@PostMapping("/modifyGood")
	public Result modifyGood(@RequestParam("goodInfo") String params,
			@RequestParam("img") MultipartFile[] files) throws IOException {
		if (ObjectUtils.isEmpty(params)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		Good good = mapper.readValue(params, Good.class);
		Result result = goodService.modifyGood(good, files);
		return result;
	}

	/**
	 * 删除商品
	 * 
	 * @param goodId 商品id
	 * @return Result
	 */
	@ApiOperation(value = "删除商品", notes = "根据商品id删除商品")
	@ApiImplicitParam(name = "goodId", value = "商品id", required = true, dataType = "String", paramType = "delete")
	@GetMapping("/deleteGood/{goodId}")
	public Result deleteGood(@PathVariable String goodId) {
		Result result = goodService.deleteGood(goodId);
		return result;
	}

	/**
	 * 模糊查询
	 *
	 * @param params 模糊查询条件
	 * @return Result
	 * @throws IOException Exception
	 */
	@ApiOperation(value = "模糊查询", notes = "根据输入的字符串模糊查询符合的商品，品牌，分类")
	@ApiImplicitParam(name = "params", value = "模糊查询条件", required = true, dataType = "Json")
	@PostMapping("/fuzzyQuery")
	public Result fuzzyquery(@RequestBody String params) throws IOException {
		if (ObjectUtils.isEmpty(params)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		Map map = mapper.readValue(params, Map.class);
		Result result = goodService.fuzzyQuery(map);
		return result;
	}
}
