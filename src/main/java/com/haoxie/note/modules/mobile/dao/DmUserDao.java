package com.haoxie.note.modules.mobile.dao;

import com.haoxie.note.common.persistence.annotation.MyBatisDao;
import com.haoxie.note.common.persistence.CrudDao;
import com.haoxie.note.common.persistence.annotation.MyBatisDao;
import com.haoxie.note.modules.mobile.entity.DmUser;

/**
 * 用户信息DAO接口
 * @author 刘智科
 * @version 2018-05-09
 */
@MyBatisDao
public interface DmUserDao extends CrudDao<DmUser> {

    public DmUser getUserByPhoneNumber(String phoneNumber);

}