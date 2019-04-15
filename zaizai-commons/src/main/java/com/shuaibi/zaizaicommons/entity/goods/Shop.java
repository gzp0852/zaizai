package com.shuaibi.zaizaicommons.entity.goods;

import com.shuaibi.zaizaicommons.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 店铺
 * @author gzp
 * @date 2018/11/17 12:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Shop {

	private int shopId;

	private String shopName;

	private String shopContent;

	private User user;

	private String createTime;

	private String updateTime;
}
