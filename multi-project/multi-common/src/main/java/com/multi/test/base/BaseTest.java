/**
 * 
 */
package com.multi.test.base;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.multi.common.util.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 基础单元测试类 2017-12-20
 * 
 * @author pandengfeng
 *
 */
@RunWith(SpringRunner.class)
public class BaseTest {
	/**
	 * jdbc执行模版
	 */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	/**
	 * test请求发送模版
	 */
	@Autowired
	protected TestRestTemplate testRestTemplate;
	/**
	 * 请求头
	 */
	private static HttpHeaders headers = new HttpHeaders();
	/**
	 * 请求 MediaType 类型
	 */
	private static List<MediaType> mediaTypeList = new ArrayList<MediaType>();
	static {
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeList);
	}

	/**
	 * 发送POST 请求 默认 "application/json"
	 * 
	 * @param params
	 *            参数
	 * @param url
	 *            链接
	 * @return body内容
	 */
	public String doPost(Map<String, String> params, String url) {
		String result = testRestTemplate.postForObject(url, this.getHttpEntity(params), String.class);
		return result;
	}

	/**
	 * 发送GET 请求
	 * 
	 * @param params
	 *            参数
	 * @param url
	 *            链接,链接请中勿拼接参数
	 * @return body内容
	 */
	public String doGet(Map<String, String> params, String url) {
		String result = testRestTemplate.getForObject(this.getUrlFor(params, url), String.class);
		return result;
	}

	/**
	 * 发送PUT 请求
	 * 
	 * @param params
	 *            参数
	 * @param url
	 *            链接,链接请中勿拼接参数
	 * @return body内容
	 */
	public String doPut(Map<String, String> params, String url) {
		ResponseEntity<String> result = testRestTemplate.exchange(url, HttpMethod.PUT, this.getHttpEntity(params),
				String.class);
		return result.getBody();
	}

	/**
	 * 发送 Delete 请求
	 * 
	 * @param params
	 *            参数
	 * @param url
	 *            链接,链接请中勿拼接参数
	 * @return body内容
	 */
	public String doDelete(Map<String, String> params, String url) {
		ResponseEntity<String> result = testRestTemplate.exchange(this.getUrlFor(params, url), HttpMethod.DELETE,
				this.getHttpEntity(params), String.class);
		return result.getBody();
	}

	/**
	 * 处理参数和url
	 * 
	 * @param params
	 *            参数
	 * @param url
	 *            链接
	 * @return 处理好的url
	 */
	public String getUrlFor(Map<String, String> params, String url) {
		Set<String> keys = params.keySet();
		String urlParams = "";
		if (keys.size() > 0) {
			urlParams += "?";
		}
		for (String key : keys) {
			urlParams += key + "=" + params.get(key) + "&";
		}
		return url + urlParams;
	}

	/**
	 * 自定义请求 HttpEntity Map String,String
	 * 
	 * @param params	Map String,String
	 * @return HttpEntity Map String,String对象
	 */
	public HttpEntity<Map<String, String>> getHttpEntity(Map<String, String> params) {

		return new HttpEntity<Map<String, String>>(params, headers);
	}

	/**
	 * @return the testRestTemplate
	 */
	public TestRestTemplate getTestRestTemplate() {
		return testRestTemplate;
	}

	/**
	 * @return the mediaTypeList
	 */
	public static List<MediaType> getMediaTypeList() {
		return mediaTypeList;
	}

	/**
	 * @param mediaTypeList
	 *            the mediaTypeList to set
	 */
	public static void setMediaTypeList(List<MediaType> mediaTypeList) {
		BaseTest.mediaTypeList = mediaTypeList;
	}

	/**
	 * @return the headers
	 */
	public static HttpHeaders getHeaders() {
		return headers;
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public static void setHeaders(HttpHeaders headers) {
		BaseTest.headers = headers;
	}
	/**
	 * 执行sql 文件
	 * @param relativePath 文件路径
	 */
	protected void initDataSQL(String relativePath) {
		StringBuilder sqlBuilder = createSqlBuilder(relativePath);
		String[] sqls = splitSqls(sqlBuilder);
		for (String sql : sqls) {
			jdbcTemplate.execute(sql);
		}
	}
	/**
	 * 分段执行sql
	 * @param sqlBuilder	sql串
	 * @return	分段的sql
	 */
	protected String[] splitSqls(StringBuilder sqlBuilder) {
		List<String> sqlList = new ArrayList<String>();
		int lastIndex = 0;
		int index = -1;
		while (true) {
			index = sqlBuilder.indexOf(";", lastIndex);
			String sql = null;
			if (index == -1) {
				if (lastIndex != 0) {
					sql = sqlBuilder.substring(lastIndex).trim();
					if (sql != null && !"".equals(sql) && !sql.startsWith("--")) {
						sqlList.add(sql);
					}
				}
				break;
			}
			sql = sqlBuilder.substring(lastIndex, index).trim();
			if (sql != null && !"".equals(sql) && !sql.startsWith("--")) {
				sqlList.add(sql);
			}
			lastIndex = index + 1;
		}
		return sqlList.toArray(new String[sqlList.size()]);
	}
	/**
	 * 读取sql文件
	 * @param relativePath 路径
	 * @return StringBuilder类型sql
	 */
	protected StringBuilder createSqlBuilder(String relativePath) {
		StringBuilder sqlBuilder = new StringBuilder();
		URL url = Thread.currentThread().getContextClassLoader().getResource(relativePath);
		try {
			sqlBuilder = FileUtils.readFile(new File(url.getFile()), "UTF-8");
		} catch (Exception ex) {
		}

		return sqlBuilder;
	}
}
