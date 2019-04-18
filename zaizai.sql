/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 8.0.13 : Database - zaizai
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zaizai` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zaizai`;

/*Table structure for table `zaizai_goods` */

DROP TABLE IF EXISTS `zaizai_goods`;

CREATE TABLE `zaizai_goods` (
  `goods_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(200) NOT NULL COMMENT '商品名称',
  `goods_price` double NOT NULL COMMENT '商品价格',
  `goods_count` int(10) NOT NULL COMMENT '商品数量',
  `goods_brand` int(10) NOT NULL COMMENT '商品品牌id',
  `goods_type` int(10) NOT NULL COMMENT '商品分类id',
  `goods_shop` int(10) NOT NULL COMMENT '店铺id',
  `goods_content` varchar(200) NOT NULL COMMENT '商品描述',
  `enable` char(1) NOT NULL COMMENT '-1商品下架；1商品上架',
  `goods_time` varchar(50) DEFAULT NULL COMMENT '商品上架时间',
  `goods_update_time` varchar(50) DEFAULT NULL COMMENT '商品修改时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_goods` */

insert  into `zaizai_goods`(`goods_id`,`goods_name`,`goods_price`,`goods_count`,`goods_brand`,`goods_type`,`goods_shop`,`goods_content`,`enable`,`goods_time`,`goods_update_time`) values (1,'毛衣123',100,10,1,1,1,'aadsa','1','2018-11-23 17:02:29','2018-12-03 15:56:24'),(2,'巧克力',66,10,3,1,1,'我是巧克力我是巧克力','1','2018-11-23 17:04:12','2018-11-23 17:04:12'),(3,'猪肉脯',99,10,1,1,1,'我是猪肉脯我是猪肉脯','1','2018-11-23 17:05:03','2018-11-23 17:05:03'),(4,'肥猪',88,10,2,1,1,'你是肥猪你是肥猪','1','2018-11-23 17:05:52','2018-11-23 17:05:52'),(6,'小松鼠',55,10,3,1,1,'你全家都是松鼠','1','2018-11-23 19:48:48','2018-11-23 19:48:48'),(7,'小松鼠1',100,10,3,1,1,'你全家都是松鼠','1','2018-12-03 15:50:48','2018-12-03 15:50:48'),(8,'小松鼠2',100,10,3,1,1,'你全家都是松鼠','1','2018-12-06 10:12:59','2018-12-06 10:12:59');

/*Table structure for table `zaizai_goods_brand` */

DROP TABLE IF EXISTS `zaizai_goods_brand`;

CREATE TABLE `zaizai_goods_brand` (
  `goods_brand_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品品牌id',
  `goods_brand_name` varchar(50) NOT NULL COMMENT '商品品牌名称',
  PRIMARY KEY (`goods_brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_goods_brand` */

insert  into `zaizai_goods_brand`(`goods_brand_id`,`goods_brand_name`) values (1,'良品铺子'),(2,'三只松鼠'),(3,'徐福记'),(4,'南极人');

/*Table structure for table `zaizai_goods_type` */

DROP TABLE IF EXISTS `zaizai_goods_type`;

CREATE TABLE `zaizai_goods_type` (
  `goods_type_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `goods_type_father_id` int(10) DEFAULT NULL COMMENT '商品分类父级id',
  `goods_type_name` varchar(50) NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`goods_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_goods_type` */

insert  into `zaizai_goods_type`(`goods_type_id`,`goods_type_father_id`,`goods_type_name`) values (1,NULL,'食品'),(2,NULL,'电子'),(3,NULL,'日用');

/*Table structure for table `zaizai_menu` */

DROP TABLE IF EXISTS `zaizai_menu`;

CREATE TABLE `zaizai_menu` (
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `menu_father_id` int(11) NOT NULL COMMENT '父级菜单',
  `menu_level` int(11) NOT NULL COMMENT '菜单层级',
  `menu_order` int(11) NOT NULL COMMENT '层次顺序 ',
  `menu_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单地址',
  `menu_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '页面显示菜单地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_menu` */

insert  into `zaizai_menu`(`menu_id`,`menu_name`,`menu_father_id`,`menu_level`,`menu_order`,`menu_url`,`menu_desc`) values (1,'商品',0,1,1,'/style/show',''),(11,'商品展示',1,2,1,'/style/good','商品 / 商品展示'),(12,'购物车',1,2,2,'/style/show','商品 / 购物车'),(13,'地址管理',1,2,3,'','商品 / 地址管理'),(2,'item1',0,1,2,'',NULL),(3,'item2',0,1,3,'',NULL),(21,'option1',2,2,1,'',NULL),(22,'option2',2,2,2,'',NULL),(23,'option3',2,2,3,'',NULL),(31,'option4',3,2,1,'',NULL),(32,'option5',3,2,2,'',NULL);

/*Table structure for table `zaizai_pic` */

DROP TABLE IF EXISTS `zaizai_pic`;

CREATE TABLE `zaizai_pic` (
  `pic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片无分组路径',
  `pic_fullpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片完整路径',
  `pic_thumbpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片缩略路径',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`pic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_pic` */

insert  into `zaizai_pic`(`pic_id`,`pic_path`,`pic_fullpath`,`pic_thumbpath`,`goods_id`) values (1,'http://39.108.213.45:8888/group1M00/00/01/rBA_kFv3wiaAYwl1AANnVIG8zNY576.png','http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wiaAYwl1AANnVIG8zNY576.png','http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wiaAYwl1AANnVIG8zNY576_60x60.png',1),(2,'http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wieABv-WAADyT0CL234187.png','http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wieABv-WAADyT0CL234187.png','http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wieABv-WAADyT0CL234187_60x60.png',1),(3,'http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wo6ANga9AANnVIG8zNY171.png','http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wo6ANga9AANnVIG8zNY171.png','http://39.108.213.45:8888/group1/M00/00/01/rBA_kFv3wo6ANga9AANnVIG8zNY171_60x60.png',2),(4,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wo6AbaY0AADyT0CL234147.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wo6AbaY0AADyT0CL234147.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wo6AbaY0AADyT0CL234147_60x60.png',2),(5,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wsGAHIepAANnVIG8zNY969.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wsGAHIepAANnVIG8zNY969.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wsGAHIepAANnVIG8zNY969_60x60.png',3),(6,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wsGAQK1gAADyT0CL234966.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wsGAQK1gAADyT0CL234966.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wsGAQK1gAADyT0CL234966_60x60.png',3),(7,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wvKAeANOAANnVIG8zNY569.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wvKAeANOAANnVIG8zNY569.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wvKAeANOAANnVIG8zNY569_60x60.png',4),(8,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wvKACQ5TAADyT0CL234216.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wvKACQ5TAADyT0CL234216.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv3wvKACQ5TAADyT0CL234216_60x60.png',4),(11,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv36SOAY2a4AAlRBXgK2nk496.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv36SOAY2a4AAlRBXgK2nk496.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv36SOAY2a4AAlRBXgK2nk496_60x60.png',6),(12,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv36SOAVH7bAAE2ZfY8C24291.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv36SOAVH7bAAE2ZfY8C24291.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFv36SOAVH7bAAE2ZfY8C24291_60x60.png',6),(13,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwE4FiAOveOAAE2ZfY8C24156.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwE4FiAOveOAAE2ZfY8C24156.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwE4FiAOveOAAE2ZfY8C24156_60x60.png',7),(14,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwE4FqAZiwrAAZ0UzKPelk222.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwE4FqAZiwrAAZ0UzKPelk222.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwE4FqAZiwrAAZ0UzKPelk222_60x60.png',7),(15,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwIhauAYmlhAAE7FhUnWXs235.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwIhauAYmlhAAE7FhUnWXs235.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwIhauAYmlhAAE7FhUnWXs235_20x20.png',8),(16,'http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwIhbGATcCLABVhzw-izF4631.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwIhbGATcCLABVhzw-izF4631.png','http://39.108.213.45:8888/group1/M00/00/02/rBA_kFwIhbGATcCLABVhzw-izF4631_20x20.png',8);

/*Table structure for table `zaizai_role` */

DROP TABLE IF EXISTS `zaizai_role`;

CREATE TABLE `zaizai_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_role` */

insert  into `zaizai_role`(`role_id`,`role_name`) values (1,'超级管理员'),(2,'管理员'),(3,'商家'),(4,'用户');

/*Table structure for table `zaizai_shop` */

DROP TABLE IF EXISTS `zaizai_shop`;

CREATE TABLE `zaizai_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商铺id',
  `shop_name` varchar(50) NOT NULL COMMENT '商铺名称',
  `shop_content` varchar(255) DEFAULT NULL COMMENT '商铺描述',
  `shop_user` int(10) DEFAULT NULL COMMENT '商铺用户id',
  `shop_craetetime` varchar(20) DEFAULT NULL COMMENT '商铺注册时间',
  `shop_updatetime` varchar(20) DEFAULT NULL COMMENT '商铺信息修改时间',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_shop` */

insert  into `zaizai_shop`(`shop_id`,`shop_name`,`shop_content`,`shop_user`,`shop_craetetime`,`shop_updatetime`) values (1,'我的第一个商铺','服装专卖店',1,NULL,NULL),(2,'良平铺子专卖店','食品店',1,NULL,NULL),(3,'徐福记专卖店','食品店',NULL,NULL,NULL);

/*Table structure for table `zaizai_test` */

DROP TABLE IF EXISTS `zaizai_test`;

CREATE TABLE `zaizai_test` (
  `test_id` int(11) NOT NULL AUTO_INCREMENT,
  `test_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_test` */

/*Table structure for table `zaizai_user` */

DROP TABLE IF EXISTS `zaizai_user`;

CREATE TABLE `zaizai_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号名',
  `user_role` int(11) NOT NULL COMMENT '用户类型',
  `user_email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `user_sex` char(1) NOT NULL COMMENT '用户性别（-1女 0未知 1男）',
  `user_img` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐加密',
  `user_status` char(1) NOT NULL COMMENT '账号状态（0正常 1停用）',
  `deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否删除（0存在 1删除 ）',
  `login_ip` varbinary(20) DEFAULT NULL COMMENT '最后登录ip',
  `login_date` varchar(20) DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(64) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `zaizai_user` */

insert  into `zaizai_user`(`user_id`,`user_name`,`login_name`,`user_role`,`user_email`,`user_phone`,`user_sex`,`user_img`,`password`,`salt`,`user_status`,`deleted`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'seller','seller',3,'aaa@qq.com','12345678912','0','路径','seller',NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'zhangsan123','zhangsan1234',1,NULL,'13986478954','1','group1/M00/00/02/rBA_kFwA6_-AOhJ5AAE2ZfY8C24884.png','ce13281aa74a54e6ab72656f6ba5a3ec','284704','1','1',NULL,NULL,'zhangsan123','2018-11-30 15:51:27',NULL,NULL,'dsadsadsadsdad'),(6,'zhangsan123','zhangsan12345',2,'793546@qq.com','13986478954','1','group1/M00/00/02/rBA_kFwA7BCAfwhnAAEUeFRl-0c713.png','6e62a1d548024cd35f0ea58fbaf6e407','70603','1','1',NULL,NULL,'zhangsan123','2018-11-30 15:51:44','zhangsan123','2018-11-30 17:02:23','dsadsadsadsdad'),(7,'zhangsan123','zhangsan6',2,'793546@qq.com','13986478954','1','group1/M00/00/02/rBA_kFwA_KCAXeYBAAEUeFRl-0c706.png','0f9fbb0caa6c664af935673dc1517c1b','788330','1','0',NULL,NULL,'zhangsan123','2018-11-30 17:02:24','zhangsan123','2018-11-30 17:03:03','dsadsadsadsdad'),(8,'zhangsan123','zhangsan8',2,'793546@qq.com','13986478954','1','group1/M00/00/02/rBA_kFwA_SCAL34bAAEUeFRl-0c267.png','80ba8fc378e946ab51bc747f0d14cc5c','646882','1','0',NULL,NULL,'zhangsan123','2018-11-30 17:04:32','zhangsan123','2018-11-30 17:06:08','dsadsadsadsdad'),(9,'zhangsan123','zhangsan9',2,'793546@qq.com','13986478954','1','group1/M00/00/02/rBA_kFwA_bOAVPvTAAFb4Uwlsfk678.png','2fc7ce314dfd5ed313b25c5e9c2ec4e5','714050','1','0',NULL,NULL,'zhangsan123','2018-11-30 17:06:29','zhangsan123','2018-11-30 17:06:59','dsadsadsadsdad');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
