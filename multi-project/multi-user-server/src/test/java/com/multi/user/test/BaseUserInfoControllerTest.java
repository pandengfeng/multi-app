/**
 * 
 */
package com.multi.user.test;



import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import com.multi.user.UserServerApplication;
import com.multi.bean.base.ReturnCode;
import com.multi.jwt.config.JwtUtil;
import com.multi.test.base.BaseTest;

/**
 * BaseUserInfoController单元测试
 * 2017-12-20
 * @author pandengfeng
 *
 */
@SpringBootTest(classes = UserServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseUserInfoControllerTest extends BaseTest{
	
	
	
	public String login() {
		super.initDataSQL("initSql/BaseUserInfoTest_login_delete.sql");
		Map<String,String> params = new HashMap<String,String>();
		
		//正常登录
		params.put("account", "test");
		params.put("password", "123456");
		String body=this.doPost(params, "/user/login");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0000.getCode(),resBean.get("returnCode"));
			return resBean.getString("data");
		} catch (JSONException e) {
			return null;
		}
	}
	
	@Test
	public void loginTest() {
		super.initDataSQL("initSql/BaseUserInfoTest_login_delete.sql");
		Map<String,String> params = new HashMap<String,String>();
		
		//正常登录
		params.put("account", "test");
		params.put("password", "123456");
		String body=this.doPost(params, "/user/login");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0000.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
			
		}
		//帐号为空
		body = null;
		params.remove("account");
		body=this.doPost(params, "/user/login");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0002.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//密码为空
		body = null;
		params.remove("password");
		params.put("account", "test");
		body=this.doPost(params, "/user/login");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0002.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//帐号密码错误
		body = null;
		params.put("account", "test123");
		params.put("password", "12345612");
		body=this.doPost(params, "/user/login");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0004.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		
	}
	
	@Test
	public void registerTest() {
		super.initDataSQL("initSql/BaseUserInfoTest_register.sql");
		Map<String,String> params = new HashMap<String,String>();
		//正常注册
		params.put("account", "test");
		params.put("password", "123456");
		params.put("userName", "pandengfeng");
		String body=this.doPost(params, "/user/register");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0000.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//帐号为空
		body = null;
		params.remove("account");
		body=this.doPost(params, "/user/register");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0002.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//密码为空
		body = null;
		params.remove("password");
		params.put("account", "test");
		body=this.doPost(params, "/user/register");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0002.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//密码为空
		body = null;
		params.remove("userName");
		params.put("password", "123456");
		body=this.doPost(params, "/user/register");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0002.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
	}
	
	@Test
	public void deleteTest() {
		//登录获取token
		String token = this.login();
		boolean success = true;
		if(StringUtils.isEmpty(token)) {
			success = false;
		}
		Assert.assertEquals(true,success);
		HttpHeaders headers = BaseTest.getHeaders();
		headers.set(JwtUtil.HEADER_STRING, token);
		
		Map<String,String> params = new HashMap<String,String>();
		//正常禁用
		params.put("account", "test");
		String body=this.doDelete(params, "/user");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0000.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//帐号为空
		body = null;
		params.remove("account");
		body=this.doDelete(params, "/user");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0002.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
		//用户不存在
		body = null;
		params.put("account","test1");
		body=this.doDelete(params, "/user");
		try {
			JSONObject resBean = new JSONObject(body);
			Assert.assertEquals(ReturnCode.CODE0005.getCode(),resBean.get("returnCode"));
		} catch (JSONException e) {
		}
	}
	
	
	
}
