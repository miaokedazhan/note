/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.haoxie.note.modules.mobile.service;

import com.haoxie.note.modules.mobile.dao.DmApkDao;
import com.haoxie.note.modules.mobile.entity.DmApk;
import com.haoxie.note.common.persistence.Page;
import com.haoxie.note.common.service.CrudService;
import com.haoxie.note.modules.mobile.dao.DmApkDao;
import com.haoxie.note.modules.mobile.entity.DmApk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 移动版本管理Service
 * @author 刘智科
 * @version 2018-05-10
 */
@Service
@Transactional(readOnly = true)
public class DmApkService extends CrudService<DmApkDao, DmApk> {
	@Autowired
	private DmApkDao dmApkDao;

	public DmApk getNewApkForAndroid(String packagename) {
		return dmApkDao.getNewApkForAndroid(packagename);
	}

	public Map<String,Object> isAvailableForIos() {
		return dmApkDao.isAvailableForIos();
	}

	@Transactional(readOnly = false)
	public void  updataAvailableForIos(String available) {
		dmApkDao.updataAvailableForIos(available);
	}

	public DmApk getNewApkForIos(String packagename) {
		return dmApkDao.getNewApkForIos(packagename);
	}

	public List<DmApk> getApkList(String packagename) {
		return dmApkDao.getApkList(packagename);
	}

	public DmApk checkVersion(String version) {
		return dmApkDao.checkVersion(version);
	}

	public DmApk get(String id) {
		return super.get(id);
	}

	public List<DmApk> findList(DmApk dmApk) {
		return super.findList(dmApk);
	}

	public Page<DmApk> findPage(Page<DmApk> page, DmApk dmApk) {
		return super.findPage(page, dmApk);
	}

	@Transactional(readOnly = false)
	public void save(DmApk dmApk) {
		super.save(dmApk);
	}

	@Transactional(readOnly = false)
	public void delete(DmApk dmApk) {
		super.delete(dmApk);
	}

}