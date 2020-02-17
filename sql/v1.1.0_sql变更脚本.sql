CREATE TABLE `banner` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' ,
`name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner名称' ,
`open_type`  tinyint(4) NULL DEFAULT 1 COMMENT '跳转类型：1.小程序页面  2.小程序tab页  3.h5页面' ,
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


ALTER TABLE `exam_user_error_question` ADD COLUMN `examination_id` int(11) NULL COMMENT '考试id' AFTER `exam_question_id`;

ALTER TABLE `train_course`
ADD COLUMN `is_new`  tinyint(4) NULL DEFAULT 0 COMMENT '最新推荐  0.否  1.是' AFTER `state`,
ADD COLUMN `is_good`  tinyint(4) NULL DEFAULT 0 COMMENT '精品推荐   0. 否  1.是' AFTER `is_new`;


UPDATE sys_dict_data
SET dict_label = '展示',
remark = '课程展示状态'
WHERE dict_code = 100;

UPDATE sys_dict_data
SET dict_label = '隐藏',
remark = '课程展示状态'
WHERE dict_code = 101;

CREATE TABLE `train_course_directory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，课程目录表id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父目录ID',
  `parent_ids` varchar(200) DEFAULT NULL COMMENT '祖级列表',
  `name` varchar(200) DEFAULT NULL COMMENT '课程目录名称',
  `order_num` int(11) DEFAULT NULL COMMENT '显示顺序',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态0.正常 1.停用',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记 0.正常 1.删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='课程目录表';

insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('100','0','0','默认全部','1','0',NULL,'2020-02-10 14:56:32','admin','2020-02-10 23:45:17',NULL,'0');

ALTER TABLE `train_course_section` ADD COLUMN `directory_id` int(11) NULL COMMENT '课程目录ID' AFTER `name`;

insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('101','100','0,100','第一章','1','0','admin','2020-02-13 14:54:09',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('102','101','0,100,101','1.1','1','0','admin','2020-02-13 14:54:20',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('103','101','0,100,101','1.2','2','0','admin','2020-02-13 14:54:26',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('104','100','0,100','第二章','2','0','admin','2020-02-13 14:54:36',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('105','104','0,100,104','2.1','1','0','admin','2020-02-13 14:54:43',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('106','104','0,100,104','2.2','2','0','admin','2020-02-13 14:54:49',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('107','100','0,100','第三章','3','0','admin','2020-02-13 14:55:05',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('108','107','0,100,107','3.1','1','0','admin','2020-02-13 14:55:11',NULL,NULL,NULL,'0');
insert into `train_course_directory` (`id`, `parent_id`, `parent_ids`, `name`, `order_num`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remarks`, `del_flag`) values('109','107','0,100,107','3.2','2','0','admin','2020-02-13 14:55:19',NULL,NULL,NULL,'0');

insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('2094','课程目录','2000','1','/train/course/directory','C','0','train:course:directory:view','#','admin','2020-02-10 09:33:39','',NULL,'');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('2095','课程目录新增','2094','1','#','F','0','train:course:directory:add','#','admin','2020-02-10 12:36:58','',NULL,'');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('2096','课程目录修改','2094','2','#','F','0','train:course:directory:edit','#','admin','2020-02-10 12:37:24','',NULL,'');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('2097','课程目录删除','2094','3','#','F','0','train:course:directory:remove','#','admin','2020-02-10 12:37:57','',NULL,'');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('2098','课程目录搜索','2094','4','#','F','0','train:course:directory:list','#','admin','2020-02-10 12:38:50','',NULL,'');