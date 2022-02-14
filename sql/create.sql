CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `user_name` varchar(10) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `phone_no` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码-md5加密',
  `lock_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '账号锁定状态：0-未锁定，1-锁定',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unix_userId` (`user_id`) USING BTREE,
  UNIQUE KEY `unix_phoneNo` (`phone_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

INSERT INTO `user` (`id`, `user_id`, `user_name`, `phone_no`, `password`, `lock_status`, `create_time`, `update_time`) VALUES ('1', 'admin', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', '2021-11-19 15:11:17', '2022-02-14 10:56:31');
INSERT INTO `user` (`id`, `user_id`, `user_name`, `phone_no`, `password`, `lock_status`, `create_time`, `update_time`) VALUES ('2', 'ceshi', '普通用户', 'ceshi', 'e10adc3949ba59abbe56e057f20f883e', '0', '2022-02-14 11:49:49', '2022-02-14 11:49:51');

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单id，0表示当前为最上级',
  `menu_name` varchar(10) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `menu_path` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单链接',
  `menu_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单类型：0-菜单，1-按钮',
  `menu_sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单信息表';

INSERT INTO `manage`.`menu` (`id`, `parent_id`, `menu_name`, `menu_path`, `menu_type`, `menu_sort`, `create_time`, `update_time`) VALUES ('1', '0', '系统管理', '', '0', '1', '2022-02-14 11:11:34', '2022-02-14 11:15:10');
INSERT INTO `manage`.`menu` (`id`, `parent_id`, `menu_name`, `menu_path`, `menu_type`, `menu_sort`, `create_time`, `update_time`) VALUES ('2', '1', '用户列表', '/userList', '0', '1', '2022-02-14 11:12:16', '2022-02-14 11:14:41');
INSERT INTO `manage`.`menu` (`id`, `parent_id`, `menu_name`, `menu_path`, `menu_type`, `menu_sort`, `create_time`, `update_time`) VALUES ('3', '1', '菜单列表', '/menuList', '0', '2', '2022-02-14 11:13:19', '2022-02-14 11:14:41');
INSERT INTO `manage`.`menu` (`id`, `parent_id`, `menu_name`, `menu_path`, `menu_type`, `menu_sort`, `create_time`, `update_time`) VALUES ('4', '1', '权限列表', '/roleList', '0', '3', '2022-02-14 11:14:36', '2022-02-14 11:14:43');
INSERT INTO `manage`.`menu` (`id`, `parent_id`, `menu_name`, `menu_path`, `menu_type`, `menu_sort`, `create_time`, `update_time`) VALUES ('5', '0', '示例', '', '0', '2', '2022-02-14 11:15:09', '2022-02-14 11:15:12');
INSERT INTO `manage`.`menu` (`id`, `parent_id`, `menu_name`, `menu_path`, `menu_type`, `menu_sort`, `create_time`, `update_time`) VALUES ('6', '5', '二级菜单', '/demo', '0', '1', '2022-02-14 11:15:28', '2022-02-14 11:15:40');

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_desc` varchar(50) NOT NULL DEFAULT '' COMMENT '权限描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限信息表';

INSERT INTO `role` (`id`, `role_name`, `role_desc`, `create_time`, `update_time`) VALUES ('1', '系统管理员', '最大权限', '2022-02-14 11:19:35', '2022-02-14 11:19:35');
INSERT INTO `role` (`id`, `role_name`, `role_desc`, `create_time`, `update_time`) VALUES ('2', '普通用户', '普通权限', '2022-02-14 11:47:49', '2022-02-14 11:47:49');

CREATE TABLE `role_menu_mapper_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限id',
  `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unix_roleId_menuId` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限菜单关系表';

INSERT INTO `manage`.`role_menu_mapper_list` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES ('1', '2', '5', '2022-02-14 11:48:41', '2022-02-14 11:48:41');
INSERT INTO `manage`.`role_menu_mapper_list` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES ('2', '2', '6', '2022-02-14 11:48:46', '2022-02-14 11:48:46');


CREATE TABLE `user_role_mapper_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unix_userId_roleId` (`user_id`, `role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关系表';

INSERT INTO `manage`.`user_role_mapper_list` (`id`, `user_id`, `role_id`, `create_time`, `update_time`) VALUES ('1', 'admin', '1', '2022-02-14 11:51:51', '2022-02-14 11:51:51');
INSERT INTO `manage`.`user_role_mapper_list` (`id`, `user_id`, `role_id`, `create_time`, `update_time`) VALUES ('2', 'ceshi', '2', '2022-02-14 11:50:13', '2022-02-14 11:51:43');
