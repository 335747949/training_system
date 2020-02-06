-- banner 相关表 start
CREATE TABLE `banner` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' ,
`name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner名称' ,
`open_type`  tinyint(4) NULL DEFAULT 1 COMMENT '跳转类型：1.h5页面 2..小程序' ,
`link`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径' ,
`image_url`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'banner图片地址' ,
`sort`  int(11) NULL DEFAULT NULL COMMENT '排序' ,
`start_time`  datetime NULL DEFAULT NULL COMMENT 'banner展示开始时间' ,
`end_time`  datetime NULL DEFAULT NULL COMMENT 'banner展示结束时间' ,
`enabled`  tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '是否展示 1、展示  0、隐藏' ,
`create_by`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者' ,
`create_date`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_by`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者' ,
`update_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
`remarks`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`del_flag`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标记' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `index_name` (`name`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='banner表'
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
;

INSERT INTO `exam`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2088', '首页banner配置', '2093', '1', '/train/course/banner', 'C', '0', 'train:banner:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-02-05 19:09:30', 'banner菜单');
INSERT INTO `exam`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2089', 'banner查询', '2088', '1', '#', 'F', '0', 'train:banner:list', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `exam`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2090', 'banner新增', '2088', '2', '#', 'F', '0', 'train:banner:add', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `exam`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2091', 'banner修改', '2088', '3', '#', 'F', '0', 'train:banner:edit', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `exam`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2092', 'banner删除', '2088', '4', '#', 'F', '0', 'train:banner:remove', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `exam`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2093', '小程序首页管理', '0', '4', '#', 'M', '0', '', 'fa fa-gears', 'admin', '2020-02-05 13:57:13', 'admin', '2020-02-06 11:11:00', '');


CREATE TABLE `train_course_search_history` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`keyword`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字' ,
`user_id`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户Id' ,
`del_flag`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '0正常 1删除' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='用户课程搜索记录表'
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
;



-- end