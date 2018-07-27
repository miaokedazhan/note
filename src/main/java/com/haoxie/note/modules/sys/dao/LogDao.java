/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.haoxie.note.modules.sys.dao;

import com.haoxie.note.common.persistence.annotation.MyBatisDao;
import com.haoxie.note.common.persistence.CrudDao;
import com.haoxie.note.common.persistence.annotation.MyBatisDao;
import com.haoxie.note.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
