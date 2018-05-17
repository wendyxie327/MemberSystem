# 数据库设计

## 用户表
DROP TABLE  IF EXISTS `tbl_user_info`;
CREATE TABLE `tbl_user_info` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID，系统自动生成',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号，此编号唯一，并且可由用户来填写',
  `rode_id` varchar(2) NOT NULL COMMENT '用户角色编号， 1老板，2区域经理，3普通会员',
  `referee_user_id` varchar(32) DEFAULT NULL COMMENT '推荐人ID',
  `higher_user_id` varchar(32) DEFAULT NULL COMMENT '上一级的用户ID',
  `higher_rode_id` varchar(2) DEFAULT NULL COMMENT '上一级人员角色ID',
  `area_name` varchar(2) NOT NULL COMMENT '用户所属区域 A B',
  `login_pwd` varchar(128) NOT NULL COMMENT '登录密码，md5加密',
  `verify_status` VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '认证具体状态：0老板，1等待成为普通会员，2成为普通会员，3等待成为区域经理，4成为区域经理，99拒绝',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `verify_time` datetime DEFAULT NULL COMMENT '提出认证时间：只有当认证状态为1、3时，此值有效',
  `update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `real_user_name` varchar(20) DEFAULT NULL COMMENT '用户真实姓名',
  `identity_id` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `address_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `address_city` varchar(20) DEFAULT NULL COMMENT '市',
  `address_area` varchar(20) DEFAULT NULL COMMENT '区',
  `address_detail` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `mobile_num` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `qq` varchar(20) DEFAULT NULL,
  `wx` varchar(30) DEFAULT NULL,
  `bank_name` varchar(50) DEFAULT NULL COMMENT '开户行名称',
  `bank_code` varchar(50) DEFAULT NULL COMMENT '开户行账号',
  `bank_user_name` varchar(50) DEFAULT NULL COMMENT '开户行用户姓名',
  `bank_address` varchar(50) DEFAULT NULL COMMENT '分行名称',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

## 用户账户表
DROP TABLE  IF EXISTS `tbl_user_account`;
CREATE TABLE `tbl_user_account` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `total_consume` decimal(12,2) DEFAULT NULL COMMENT '总收入（只加不减）',
  `remain_consume` decimal(12,2) DEFAULT NULL COMMENT '用户剩余金额（= total_consume - withdrawing_consume - withdraw_consume = remain_consume上次 - withdrawing_consume  ）',
  `withdraw_consume` decimal(12,2) DEFAULT NULL COMMENT '已提现金额（提款成功时更改该字段，只加不减）',
  `withdrawing_consume` decimal(12,2) DEFAULT NULL COMMENT '正在提现的金额',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';


## 用户账户金额变更历史表（该表只增不减）
DROP TABLE  IF EXISTS `tbl_user_account_his`;
CREATE TABLE `tbl_user_account_his` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号',
  `change_type` varchar(2) NOT NULL COMMENT '账户金额更改类型：1奖金发放、2提现（提现成功后，生成此纪录）',
  `current_consume` decimal(12,2) NOT NULL COMMENT '充值前金额，单位元',
  `changing_consume` decimal(12,2) NOT NULL COMMENT '充值金额 ，单位元(充值，正数；消费，负数)',
  `changed_consume` decimal(12,2) NOT NULL COMMENT '充值后金额，单位元',
  `third_serial_no` varchar(20) NOT NULL COMMENT '此记录对应的交易流水号，2提现时使用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户金额变更历史表（该表只增不减）';

## 奖金发放记录（该表可不创建，直接读取用户账户金额变更历史表中，变更类型为1的即可）

## 用户提现记录表
DROP TABLE  IF EXISTS `tbl_withdraw`;
CREATE TABLE `tbl_withdraw` (
  `serial_no` varchar(20) NOT NULL COMMENT '交易流水号（时间戳+6位随机数）',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID，系统自动生成',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '开户行名称',
  `bank_code` varchar(50) DEFAULT NULL COMMENT '开户行账号',
  `bank_user_name` varchar(50) DEFAULT NULL COMMENT '开户行用户姓名',
  `bank_address` varchar(50) DEFAULT NULL COMMENT '分行名称',
  `withdraw_consume` decimal(12,2) DEFAULT NULL COMMENT '提现金额',
  `real_withdraw_consume` decimal(12,2) DEFAULT NULL COMMENT '实际提现金额',
  `remain_consume` decimal(12,2) DEFAULT NULL COMMENT '提款前的剩余金额',
  `withdraw_status` varchar(2) NOT NULL COMMENT '提现状态:0打款成功，1普通提出申请，2区域已确认申请/等待老板打款（区域经理提出申请也是该状态），99拒绝提款',
  `refuse_reason` varchar(256) DEFAULT NULL COMMENT '拒绝提现原因',
  `withdraw_time` datetime DEFAULT NULL COMMENT '打款成功日期',
  `oper_user_id` varchar(32) DEFAULT NULL COMMENT '当前操作人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现记录';

## 用户推荐人员计算表
DROP TABLE  IF EXISTS `tbl_user_referee`;
CREATE TABLE `tbl_user_referee` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `area_a` int(11) NOT NULL COMMENT 'A区总人数（包含间接、直接）',
  `area_b` int(11) NOT NULL COMMENT 'B区总人数（包含间接、直接）',
  `num_update_time` datetime NOT NULL COMMENT '人员总数最近更新时间（会员审核通过，进行更新）',
  `num_enough_time` datetime DEFAULT NULL COMMENT '最近人员满6的倍数的时间（会员审核通过，进行更新）',
  `account_update_time` datetime DEFAULT NULL COMMENT '奖金最近发放时间(每天汇总)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户推荐人员计算表';


## 修改数据库tbl_user_referee,添加字段enough_num,enough_income
ALTER TABLE tbl_user_referee ADD enough_num int COMMENT '符合奖金水平-人数';
ALTER TABLE tbl_user_referee ADD enough_income int COMMENT '符合奖金水平-奖金';
