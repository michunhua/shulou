CREATE TABLE `add_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `createdate` varchar(50) DEFAULT NULL,
  `belongs_city` varchar(20) DEFAULT NULL,
  `configuration` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=245 DEFAULT CHARSET=utf8 COMMENT='添加角色';




CREATE TABLE `apply_for_loan_information` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `amount` varchar(20) NOT NULL COMMENT '金额',
  `time_Limit` varchar(20) NOT NULL COMMENT '期限',
  `borrowing_Variety` varchar(8) NOT NULL COMMENT '借款品种,赎楼',
  `repayment` varchar(10) NOT NULL COMMENT '还款方式 0到期一次性还本付息1按月付息，到期还本',
  `receiving_Bank_Name` varchar(10) NOT NULL COMMENT '收款银行名称',
  `receiving_Account_Name` varchar(16) NOT NULL COMMENT '收款账户名',
  `receiving_Account` varchar(16) NOT NULL COMMENT '收款账号',
  `repayment_Bank_Name` varchar(12) NOT NULL COMMENT '还款银行名称',
  `repayment_Account_Name` varchar(16) NOT NULL COMMENT '还款账户名',
  `repayment_Account_Number` varchar(16) NOT NULL COMMENT '还款账号',
  `start` varchar(8) NOT NULL COMMENT '状态0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
  `ctime` varchar(50) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='申请借款信息';







CREATE TABLE `borrow_information` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '借款人姓名',
  `phoneticize` varchar(16) DEFAULT NULL COMMENT '拼音、英文姓名',
  `id_type` varchar(32) DEFAULT NULL COMMENT '身份证件类型',
  `id_number` varchar(32) DEFAULT NULL COMMENT '身份证件号码',
  `relationship_with_borrower` varchar(10) DEFAULT NULL COMMENT '与借款人关系',
  `country_and_region` varchar(24) DEFAULT NULL COMMENT '国家及地区',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别: 0男 1女',
  `Local_domicile` varchar(100) DEFAULT NULL COMMENT '本地户籍',
  `household_registration` varchar(100) DEFAULT NULL COMMENT '户籍所在地',
  `marital_status` varchar(24) DEFAULT NULL COMMENT '婚姻情况,0已婚，1未婚',
  `housing_condition_now` varchar(20) DEFAULT NULL COMMENT '0房改/继承 1按揭自置2无按揭自置 3与父母同住 4租借 5公司提供  6其他',
  `birthday` varchar(20) DEFAULT NULL COMMENT '出生日期',
  `home_address_now` varchar(40) DEFAULT NULL COMMENT '现住房地址',
  `home_phone` varchar(24) DEFAULT NULL COMMENT '住宅电话',
  `mobile_phone` varchar(50) DEFAULT NULL COMMENT '移动电话',
  `email` varchar(12) DEFAULT NULL COMMENT 'E-mail',
  `present_address_zip_code` varchar(10) DEFAULT NULL COMMENT '现住址邮编',
  `vocation` varchar(12) DEFAULT NULL COMMENT '职业',
  `unit_industry` varchar(12) DEFAULT NULL COMMENT '现单位所处行业',
  `uni_name` varchar(16) DEFAULT NULL COMMENT '现单位名称',
  `unit_address` varchar(16) DEFAULT NULL COMMENT '现单位地址',
  `enterprise_scale` varchar(10) DEFAULT NULL COMMENT '就职企业规模',
  `Revenue_in_the_previous_year` varchar(10) DEFAULT NULL COMMENT '上年营收',
  `Asset_scale` varchar(10) DEFAULT NULL COMMENT '资产规模',
  `unit_phone` varchar(12) DEFAULT NULL COMMENT '单位电话',
  `postCode` varchar(12) DEFAULT NULL COMMENT '单位邮编',
  `job_category` varchar(16) DEFAULT NULL COMMENT '职位类别',
  `seniority` varchar(8) DEFAULT NULL COMMENT '现单位工龄',
  `former_unit_name` varchar(12) DEFAULT NULL COMMENT '前单位名称',
  `former_seniority` varchar(10) DEFAULT NULL COMMENT '前单位工龄',
  `source_of_income` varchar(20) DEFAULT NULL COMMENT '收入来源',
  `monthly_income` decimal(8,2) DEFAULT NULL COMMENT '月收入',
  `Income_from_investment` varchar(20) DEFAULT NULL COMMENT '投资收益',
  `Rent_income` varchar(20) DEFAULT NULL COMMENT '租金收入',
  `Other_income` varchar(20) DEFAULT NULL COMMENT '其他收入',
  `family_number` varchar(10) DEFAULT NULL COMMENT '供养人数',
  `monthly_expenditure` decimal(8,2) DEFAULT NULL COMMENT '月支出',
  `postal_address` varchar(20) DEFAULT NULL COMMENT '通讯地址',
  `start` varchar(10) DEFAULT NULL COMMENT '状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
  `ctime` varchar(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='借款申请人资料';





CREATE TABLE `borrower_spouse` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL COMMENT '借款人配偶姓名',
  `id_Type` varchar(12) DEFAULT NULL COMMENT '身份证件类型',
  `id_Number` varchar(40) DEFAULT NULL COMMENT '身份证件号码',
  `uni_Name` varchar(20) DEFAULT NULL COMMENT '工作单位名称',
  `unit_Phone` varchar(16) DEFAULT NULL COMMENT '单位电话',
  `home_Phone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `mobile_Phone` varchar(18) DEFAULT NULL COMMENT '移动电话',
  `monthly_Income` decimal(8,2) DEFAULT NULL COMMENT '月薪（人民币）',
  `start` varchar(12) DEFAULT NULL COMMENT '状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
  `ctime` varchar(12) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='借款人配偶信息';



CREATE TABLE `borrows_informations` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '共同借款人姓名',
  `phoneticize` varchar(16) DEFAULT NULL COMMENT '拼音、英文姓名',
  `id_type` varchar(32) DEFAULT NULL COMMENT '身份证件类型',
  `id_number` varchar(32) DEFAULT NULL COMMENT '身份证件号码',
  `relationship_with_borrower` varchar(10) DEFAULT NULL COMMENT '与借款人关系',
  `country_and_region` varchar(24) DEFAULT NULL COMMENT '国家及地区',
  `sex` char(10) DEFAULT NULL COMMENT '性别: 0男 1女',
  `Local_domicile` varchar(100) DEFAULT NULL COMMENT '本地户籍',
  `household_registration` varchar(100) DEFAULT NULL COMMENT '户籍所在地',
  `marital_status` char(24) DEFAULT NULL COMMENT '婚姻情况,0已婚，1未婚',
  `housing_condition_now` varchar(20) DEFAULT NULL COMMENT '0房改/继承 1按揭自置2无按揭自置 3与父母同住 4租借 5公司提供  6其他',
  `birthday` varchar(20) DEFAULT NULL COMMENT '出生日期',
  `home_address_now` varchar(40) DEFAULT NULL COMMENT '现住房地址',
  `home_phone` varchar(24) DEFAULT NULL COMMENT '住宅电话',
  `mobile_phone` varchar(50) DEFAULT NULL COMMENT '移动电话',
  `email` varchar(12) DEFAULT NULL COMMENT 'E-mail',
  `present_address_zip_code` varchar(10) DEFAULT NULL COMMENT '现住址邮编',
  `vocation` varchar(12) DEFAULT NULL COMMENT '职业',
  `unit_industry` varchar(12) DEFAULT NULL COMMENT '现单位所处行业',
  `uni_name` varchar(16) DEFAULT NULL COMMENT '现单位名称',
  `unit_address` varchar(16) DEFAULT NULL COMMENT '现单位地址',
  `enterprise_scale` varchar(10) DEFAULT NULL COMMENT '就职企业规模',
  `Revenue_in_the_previous_year` varchar(10) DEFAULT NULL COMMENT '上年营收',
  `asset_scale` varchar(10) DEFAULT NULL COMMENT '资产规模',
  `unit_phone` varchar(12) DEFAULT NULL COMMENT '单位电话',
  `postCode` varchar(12) DEFAULT NULL COMMENT '单位邮编',
  `job_category` varchar(16) DEFAULT NULL COMMENT '职位类别',
  `seniority` varchar(8) DEFAULT NULL COMMENT '现单位工龄',
  `former_unit_name` varchar(12) DEFAULT NULL COMMENT '前单位名称',
  `former_seniority` varchar(10) DEFAULT NULL COMMENT '前单位工龄',
  `source_of_income` varchar(20) DEFAULT NULL COMMENT '收入来源',
  `monthly_income` decimal(8,2) DEFAULT NULL COMMENT '月收入',
  `Income_from_investment` decimal(8,2) DEFAULT NULL COMMENT '投资收益',
  `Rent_income` decimal(8,2) DEFAULT NULL COMMENT '租金收入',
  `Other_income` decimal(8,2) DEFAULT NULL COMMENT '其他收入',
  `family_number` varchar(10) DEFAULT NULL COMMENT '供养人数',
  `monthly_expenditure` decimal(8,2) DEFAULT NULL COMMENT '月支出',
  `postal_address` varchar(20) DEFAULT NULL COMMENT '通讯地址',
  `start` varchar(10) DEFAULT NULL COMMENT '状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
  `ctime` varchar(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='共同借款申请人资料';





CREATE TABLE `city` (
  `id` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `parent_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `short` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;




CREATE TABLE `co_borrower_spouse` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL COMMENT '共同借款人配偶姓名',
  `id_Type` varchar(12) DEFAULT NULL COMMENT '身份证件类型',
  `id_Other` varchar(100) DEFAULT NULL COMMENT '其他证件',
  `id_Number` varchar(40) DEFAULT NULL COMMENT '身份证件号码',
  `uni_Name` varchar(20) DEFAULT NULL COMMENT '工作单位名称',
  `unit_Phone` varchar(16) DEFAULT NULL COMMENT '单位电话',
  `home_Phone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `mobile_Phone` varchar(16) DEFAULT NULL COMMENT '移动电话',
  `monthly_Income` varchar(16) DEFAULT NULL COMMENT '月薪（人民币）',
  `start` varchar(8) DEFAULT NULL COMMENT '状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
  `ctime` varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='共同借款人配偶信息';








CREATE TABLE `contacts_table` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `contacts` varchar(100) NOT NULL COMMENT '联系人信息',
  `contacts1` varchar(100) NOT NULL COMMENT '联系人信息2',
  `contacts2` varchar(100) NOT NULL COMMENT '联系人信息3',
  `relationship` char(10) NOT NULL COMMENT '联系人关系',
  `relationship1` char(10) NOT NULL COMMENT '联系人关系',
  `relationship2` char(10) NOT NULL COMMENT '联系人关系',
  `c_Telephone` varchar(100) NOT NULL COMMENT '联系人电话',
  `c_Telephone1` varchar(100) NOT NULL COMMENT '联系人电话1',
  `c_Telephone2` varchar(100) NOT NULL COMMENT '联系人电话2',
  `start` char(100) NOT NULL COMMENT '状态',
  `ctime` varchar(200) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


CREATE TABLE `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(10) DEFAULT NULL,
  `url` varchar(20) NOT NULL,
  `method` varchar(5) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `orderline` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='菜单';





CREATE TABLE `note_description` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `note_Description1` varchar(1000) DEFAULT NULL COMMENT '创建备注',
  `note_Description2` varchar(1000) DEFAULT NULL COMMENT '初审备注',
  `note_Description3` varchar(1000) DEFAULT NULL COMMENT '终审备注',
  `start` varchar(100) NOT NULL COMMENT '状态',
  `ctime` varchar(200) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


CREATE TABLE `permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `r_id` varchar(10) NOT NULL,
  `url` varchar(40) DEFAULT NULL,
  `checkboxid` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`,`r_id`),
  KEY `FK_role_id` (`r_id`)
) ENGINE=MyISAM AUTO_INCREMENT=261 DEFAULT CHARSET=utf8 COMMENT='权限';



CREATE TABLE `property_information` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ownership_And_Percentage` varchar(10) NOT NULL COMMENT '权属人及占比',
  `property_Address` varchar(20) NOT NULL COMMENT '房产地址',
  `construction_Area` varchar(10) NOT NULL COMMENT '建筑面积',
  `inner_Area` varchar(10) NOT NULL COMMENT '套内面积',
  `sales_Contract_Number` varchar(15) NOT NULL COMMENT '买卖合同编号',
  `certificate_Of_Title` varchar(15) NOT NULL COMMENT '产权证号',
  `property_For` varchar(15) NOT NULL COMMENT '房产用于',
  `the_Assessed_Value` varchar(15) NOT NULL COMMENT '评估值',
  `original_Loan_Bank` varchar(10) NOT NULL COMMENT '原贷款银行',
  `original_Loan_Amount` decimal(8,2) NOT NULL COMMENT '原贷款金额',
  `loan_Outstanding_Balance` decimal(8,2) NOT NULL COMMENT '原贷款尚欠本息余额',
  `house_Account` varchar(16) NOT NULL COMMENT '供楼账号',
  `transaction_Price` decimal(8,2) NOT NULL COMMENT '买卖成交价',
  `purchase_Deposit` decimal(8,2) NOT NULL COMMENT '购房定金',
  `supervision_Of_Funds` varchar(12) NOT NULL COMMENT '资金监管',
  `new_Loan_Bank` varchar(12) NOT NULL COMMENT '新贷款银行',
  `new_Loan_Approval_Amount` decimal(8,2) NOT NULL COMMENT '新贷款批复金额',
  `new_Loan_Bank_Account_Number` varchar(16) NOT NULL COMMENT '新贷款行账号',
  `start` varchar(8) DEFAULT NULL COMMENT '状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
  `ctime` varchar(16) DEFAULT NULL COMMENT '更新时间',
  `note_Description` varchar(1000) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='房产资料';




CREATE TABLE `recordsubmit_table` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fallbackname` varchar(20) DEFAULT NULL,
  `submit` varchar(25) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `spare1` varchar(20) DEFAULT NULL,
  `createDate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='流转记录&状态改动';





CREATE TABLE `sequence_role` (
  `seq_name` varchar(50) NOT NULL,
  `current_val` int(11) NOT NULL,
  `increment_val` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='权限序列';






CREATE TABLE `user_login` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(8) NOT NULL,
  `employeeis_name` varchar(10) NOT NULL,
  `distribution_role` varchar(20) NOT NULL,
  `belongs_city` varchar(30) DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `r_id` int(10) DEFAULT NULL,
  `create_date` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户登录';





CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_Id` int(20) NOT NULL,
  `role_Id` int(20) NOT NULL,
  PRIMARY KEY (`id`,`user_Id`,`role_Id`),
  KEY `user_Id` (`user_Id`),
  KEY `role_Id` (`role_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色';






CREATE TABLE `usermanagement` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(15) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `assigning_Roles` varchar(10) DEFAULT NULL,
  `Note` varchar(1000) DEFAULT NULL,
  `employeeis_name` varchar(30) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户权限管理&用户列表';



