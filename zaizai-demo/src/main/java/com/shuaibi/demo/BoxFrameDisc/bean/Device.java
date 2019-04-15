package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/30 16:01
 */
@Data
public class Device {
		/**
		 * 设施id
		 */
		private String deviceId;

		private String boxId;

		/**
		 * 设施类型
		 */
		private String deviceType;

		/**
		 * 设施名称
		 */
		private String deviceName;

		/**
		 * 设施状态
		 */
		private String deviceStatus;

		/**
		 * 设施编号
		 */
		private String deviceCode;

		/**
		 * 详细地址
		 */
		private String address;

		/**
		 * 责任单位
		 */
		private String accountabilityUnit;

		/**
		 * 部署状态
		 */
		private String deployStatus;

		/**
		 * 省
		 */
		private String provinceName;

		/**
		 * 市
		 */
		private String cityName;

		/**
		 * 区
		 */
		private String districtName;


		/**
		 * gps定位
		 */
		private String positionGps;

		/**
		 * 基础定位
		 */
		private String positionBase;

		/**
		 * 关联区域id
		 */
		private String areaId;

		/**
		 * 备注
		 */
		private String remarks;

		/**
		 * 关联特性字段表id
		 */
		private String specificFieldId;

		/**
		 * 创建人
		 */
		private String createUser;

		/**
		 * 创建时间
		 */
		private Timestamp createTime;

		/**
		 * 更新人
		 */
		private String updateUser;

		/**
		 * 更新时间
		 */
		private Timestamp updateTime;

		/**
		 * 是否删除
		 */
		private String isDeleted;
}
