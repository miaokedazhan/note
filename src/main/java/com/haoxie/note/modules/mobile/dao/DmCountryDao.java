/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.haoxie.note.modules.mobile.dao;

import com.haoxie.note.common.persistence.annotation.MyBatisDao;
import com.haoxie.note.common.persistence.CrudDao;
import com.haoxie.note.common.persistence.annotation.MyBatisDao;
import com.haoxie.note.modules.mobile.entity.DmCountry;

import java.util.List;

/**
 * 国际电话号码区号DAO接口
 * @author 刘智科
 * @version 2018-05-19
 */
@MyBatisDao
public interface DmCountryDao extends CrudDao<DmCountry> {

     List<DmCountry> findListChinese(DmCountry dmCountry);

     List<DmCountry> findListEnglish(DmCountry dmCountry);

     List<DmCountry> findListJapanese(DmCountry dmCountry);

     List<DmCountry> findChineseByZIMU(String str);

     List<DmCountry> finEnglishByZIMU(String str);
}