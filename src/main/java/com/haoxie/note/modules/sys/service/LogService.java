/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.haoxie.note.modules.sys.service;

import com.haoxie.note.modules.sys.dao.LogDao;
import com.haoxie.note.modules.sys.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haoxie.note.common.persistence.Page;
import com.haoxie.note.common.service.CrudService;
import com.haoxie.note.common.utils.DateUtils;
import com.haoxie.note.modules.sys.dao.LogDao;
import com.haoxie.note.modules.sys.entity.Log;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}
	
}
