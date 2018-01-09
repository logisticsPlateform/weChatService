package com.nyh.app.core.service.publish.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nyh.app.common.exception.SystemException;
import com.nyh.app.common.util.VoToPo;
import com.nyh.app.common.vo.publish.PublishVo;
import com.nyh.app.core.orm.index.po.PubinfoPo;
import com.nyh.app.core.service.publish.UserPublishService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserPublishServiceImpl implements UserPublishService {

	@Override
	public PublishVo saveimage(MultipartFile file){
		
		 try {
			file.transferTo(new File("C://Users/Administrator/Desktop/tomcat8/webapps/ROOT/images/qq.jpg"));
		} catch (Exception e) {
			log.error("上传图片异常",e);
			throw new SystemException("系统繁忙");
			
		} 
		// file.transferTo(new File("C://Users/Administrator/Desktop/tomcat8/webapps/ROOT/qq.jpg")); 
		 PublishVo publishvo = new PublishVo();
		 publishvo.setImageUrl("http://localhost:8888/images/qq.jpg");
		 publishvo.setImageType("image/jpeg");
		 publishvo.setImageName("qq.jpg");
		 File uploadImage = new File("C://Users/Administrator/Desktop/tomcat8/webapps/ROOT/images/qq.jpg");
		 int imageSize = (int) uploadImage.length();
		 publishvo.setImageSize(imageSize);
		
		return publishvo;
	}

	@Override
	public PublishVo savecontent(PublishVo pub) {
		// TODO Auto-generated method stub
		System.out.println("pub=======>"+pub);
		
		
		
		return null;
	}






}
