package com.shuaibi.demo.BoxFrameDisc.dao;

import com.shuaibi.demo.BoxFrameDisc.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/28 10:27
 */
public interface BoxMapper {

	BoxTemplate queryBoxById(String id);

	List<FrameTemplate> queryFrameById(String id);

	List<Disc> queryDiscById(String id);

	Device queryDevice(String id);

	List<DiscOnline> queryDiscOnline(String id);

	void createDisc(Disc discTemplate);

	void createFrame(FrameTemplate frameTemplate);

	void createBox(BoxTemplate boxTemplate);

	void updateDevice(String boxId, String deviceId);

	void createDiscOnline(DiscOnline discOnline);

	void createPort(@Param("ports") List<Port> ports);

	List<Port> queryPortById(String discOnlineId);

	//	List<Port> queryPortById(String id);
}
