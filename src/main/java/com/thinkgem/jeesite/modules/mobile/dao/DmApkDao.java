/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mobile.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.entity.DmApk;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 移动版本管理DAO接口
 * @author 刘智科
 * @version 2018-05-10
 */
@MyBatisDao
public interface DmApkDao extends CrudDao<DmApk> {

    DmApk getNewApkForAndroid(@Param("packagename") String packagename);

    DmApk getNewApkForIos(@Param("packagename") String packagename);

    List<DmApk> getApkList(@Param("packagename") String packagename);

    DmApk checkVersion(@Param("version") String version);

    Map<String,Object> isAvailableForIos();

    void updataAvailableForIos(@Param("available") String available);

}