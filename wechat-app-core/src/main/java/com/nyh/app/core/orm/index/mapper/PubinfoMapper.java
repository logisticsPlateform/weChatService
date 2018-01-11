/**  
 * All rights Reserved, Designed By Suixingpay.
 * @author: houmin[hou_no1@163.com] 
 * @date: 2017-12-26 22:16:39  
 * @Copyright ©2017 Suixingpay. All rights reserved. 
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.nyh.app.core.orm.index.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.nyh.app.core.orm.index.domain.Pubinfo;
import com.nyh.app.core.orm.index.po.PubinfoPo;
import com.nyh.app.common.vo.index.PubinfoVo;

/**
 * TODO
 *
 * @author houmin[hou_no1@163.com]
 * @date: 2017-12-26 22:16:39
 * @review: houmin[hou_no1@163.com]/2017-12-26 22:16:39
 */
@Repository
public interface PubinfoMapper {

	@Insert("INSERT INTO PUBLISH_INFO" +
		"(UUID,USER_ID,CONTENT,IMAGE_URL,CREATE_DATE)" +
		"VALUES" +
		"(REPLACE(UUID(),'-',''), #{userId}, #{content}, #{imageUrl}, #{createDate})")
    void insert(PubinfoVo vo);

    @Select("SELECT UUID,USER_ID,CONTENT,IMAGE_URL,DATE_FORMAT(CREATE_DATE, '%Y-%m-%d %H:%i:%s') CREATE_DATE FROM PUBLISH_INFO")
	List<Pubinfo> findAll();
    
    @Select("SELECT COUNT(*) FROM PUBLISH_INFO")
    int findCount();
    
    @Insert("INSERT INTO PUBLISH_INFO" +
    		"(UUID,USER_ID,CONTENT,IMAGE_URL,CREATE_DATE)" +
    		"VALUES" +
    		"(REPLACE(UUID(),'-',''), #{userId}, #{content}, #{imageUrl}, #{createDate})")
    void insert_po(PubinfoPo po);

}