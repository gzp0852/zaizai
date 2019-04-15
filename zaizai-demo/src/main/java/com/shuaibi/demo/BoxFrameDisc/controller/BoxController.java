package com.shuaibi.demo.BoxFrameDisc.controller;

import com.shuaibi.demo.BoxFrameDisc.bean.*;
import com.shuaibi.demo.BoxFrameDisc.dao.BoxMapper;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/28 10:17
 */
@RestController
public class BoxController {

	@Autowired
	private BoxMapper boxMapper;

	@GetMapping("/box/{id}")
	public Result getBox(@PathVariable String id) throws Exception {
		Device device = boxMapper.queryDevice(id);
		BoxTemplate boxTemplate = boxMapper.queryBoxById(device.getBoxId());
		List<DiscOnline> discOnlines = boxMapper.queryDiscOnline(id);

		boxTemplate.setDiscOnlines(discOnlines);
		return ResultUtils.success(boxTemplate);
	}

	@GetMapping("/frame/{id}")
	public Result getFrame(@PathVariable String id) {
		return ResultUtils.success(boxMapper.queryFrameById(id));
	}

	@GetMapping("/disc/{id}")
	public Result getDisc(@PathVariable String id) {
		return ResultUtils.success(boxMapper.queryDiscById(id));
	}
	// @GetMapping("/port/{id}")
	// public Result getPort(@PathVariable String id) {
	// return ResultUtils.success(boxMapper.queryPortById(id));
	// }

	@PostMapping("/create/{deviceId}")
	public Result createBox(@RequestBody BoxTemplate boxTemplate, @PathVariable String deviceId) {
		Disc discTemplate = boxTemplate.getFrameTemplate().getDiscTemplate();
		FrameTemplate frameTemplate = boxTemplate.getFrameTemplate();
		if (StringUtils.isBlank(discTemplate.getDiscId())) {
			boxMapper.createDisc(discTemplate);
		}
		boxTemplate.getFrameTemplate().setDiscTemplate(discTemplate);
		if (StringUtils.isBlank(frameTemplate.getFrameId())) {
			boxMapper.createFrame(frameTemplate);
		}
		boxTemplate.setFrameTemplate(frameTemplate);
		if (StringUtils.isBlank(boxTemplate.getBoxId())) {
			boxMapper.createBox(boxTemplate);
		}

		boxMapper.updateDevice(boxTemplate.getBoxId(), deviceId);
		return ResultUtils.success(boxTemplate);
	}

	@PostMapping("/discOnline")
	public Result createDiscOnlineAndPort(@RequestBody DiscOnline discOnline) {
		boxMapper.createDiscOnline(discOnline);
		List<Disc> discs = boxMapper.queryDiscById(discOnline.getDiscId());
		Disc disc = discs.get(0);
		int sideSize = disc.getDiscSide().equalsIgnoreCase("双面")?2:1;
		int portSize = Integer.parseInt(disc.getDiscRow()) * Integer.parseInt(disc.getDiscCol());
		String side = "";
		List<Port> ports = new ArrayList<>();
		for (int i = 0; i < sideSize; i++) {
			if (i == 1) {
				side	= "B";
			} else {
				side	= "A";
			}
			for (int j = 1; j <= portSize; j++) {
				Port port = new Port(discOnline.getId(), side, j + "", discOnline.getBoxSide() + "-" + discOnline.getBoxNo() + "-" + discOnline.getFrameNo() + "-" + "A" + "-" + j);
				ports.add(port);
			}
		}
		boxMapper.createPort(ports);
		return ResultUtils.success(ports);
	}
}
