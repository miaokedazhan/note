package com.haoxie.note.modules.mobile.service;

import java.util.Calendar;
import java.util.List;

import com.haoxie.note.modules.mobile.dao.DmUserDao;
import com.haoxie.note.modules.mobile.entity.DmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haoxie.note.common.persistence.Page;
import com.haoxie.note.common.service.CrudService;
import com.haoxie.note.modules.mobile.entity.DmUser;
import com.haoxie.note.modules.mobile.dao.DmUserDao;

/**
 * 用户信息Service
 * @author 刘智科
 * @version 2018-05-09
 */
@Service
@Transactional(readOnly = true)
public class DmUserService extends CrudService<DmUserDao, DmUser> {

	@Autowired
	private DmUserDao dmUserDao;

	public DmUser get(String id) {
		return super.get(id);
	}

	public DmUser getUserByPhoneNumber(String phoneNumber) {
		return dmUserDao.getUserByPhoneNumber(phoneNumber);
	}
	
	public List<DmUser> findList(DmUser dmUser) {
		return super.findList(dmUser);
	}
	
	public Page<DmUser> findPage(Page<DmUser> page, DmUser dmUser) {
		return super.findPage(page, dmUser);
	}
	
	@Transactional(readOnly = false)
	public void save(DmUser dmUser) {
		super.save(dmUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(DmUser dmUser) {
		super.delete(dmUser);
	}
	
}