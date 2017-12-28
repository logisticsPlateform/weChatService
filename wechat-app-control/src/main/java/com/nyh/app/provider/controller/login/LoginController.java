package com.nyh.app.provider.controller.login;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyh.app.core.service.userInfo.UserInfoService;
import com.nyh.app.provider.controller.AbstractController;

@RestController
@RequestMapping("/login")
public class LoginController extends AbstractController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/doLogin")
	public ResponseEntity<Map<String, Object>> findUserByName() throws IOException {
		return wrapperSupplier(()->userInfoService.insert());
	}
	

}
