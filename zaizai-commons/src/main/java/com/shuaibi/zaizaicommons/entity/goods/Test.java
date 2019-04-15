package com.shuaibi.zaizaicommons.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author gzp
 * @date 2018/12/3 16:14
 */
@Data//这里用了依赖lombok,只用注解就可以简化代码，不用写getset和equest
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Test {
	private int id;
	private String name;
}
