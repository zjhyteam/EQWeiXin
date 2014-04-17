package org.earthQuake.test;

/*
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see <http://www.apache.org/>.
 * renren.com
 * <input type="hidden" name="origURL" value="http://www.renren.com/home" />
 * <input type="hidden" name="domain" value="renren.com" />
 * <input type="hidden" name="key_id" value="1" />
 * <input type="submit" id="login" class="input-submit login-btn" value="登录人人网" tabindex="5"/>
 * http://s.xnimg.cn/a36853/n/apps/login/login-all.js
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.xml.internal.ws.transport.http.client.HttpCookie;

/**
 * 
 * Purpose:
 * 
 * @author: shihuangzhe.com
 * @since: JDK 1.6
 * @date: 2012-4-28
 * 
 */
public class RrLogin {
	/** 帐号 */
	private static final String userName = "370273662@qq.com";
	/** 密码 */
	private static final String password = "15858275422";
	/** 网域 */
	private static final String domain = "renren.com";
	/** key_id */
	private static final String keyID = "1";
	/** 表单提交url */
	private static String loginURL = "http://www.renren.com/PLogin.do";
	/** 登陆成功后，跳转到我自己的blog日志,人人默认跳转路径为 http://www.renren.com/home */
	private static final String targetUrl = "http://www.renren.com/238452997#notice";
	/** 表单域常量(跳转url) */
	private static final String _ORGI_URL = "origURL";
	/** 表单域常量(网域) */
	private static final String _DOMAIN = "domain";
	/** 表单域常量(key_id) */
	private static final String _KEY_ID = "key_id";
	/** 表单域常量(帐号) */
	private static final String _EMAIL = "email";
	/** 表单域常量(密码) */
	private static final String _PASSWORD = "password";
	/** ThreadSafeClientConnManager保证多线程安全 */
	private DefaultHttpClient client = new DefaultHttpClient(
			new ThreadSafeClientConnManager());
	/** response相应 */
	private HttpResponse response;

	/**
	 * Purpose: 登陆renren.com
	 * 
	 * @throws Exception
	 * @return: void
	 */
	private boolean login(String userName, String password) {
		boolean isLogin = false;
		HttpPost httpost = new HttpPost(loginURL);
		// 为请求参数赋值
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair(_ORGI_URL, targetUrl));
		nvps.add(new BasicNameValuePair(_DOMAIN, domain));
		nvps.add(new BasicNameValuePair(_KEY_ID, keyID));
		nvps.add(new BasicNameValuePair(_EMAIL, userName));
		nvps.add(new BasicNameValuePair(_PASSWORD, password));
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			// 获取请求相应
			response = client.execute(httpost);
			System.out.println(response.getStatusLine());// 返回302
			// 设置cookie,renren.com用于身份验证的cookie有两个,名字分别是p和t.
			// HttpClientParams.setCookiePolicy(client.getParams(),
			// CookiePolicy.BROWSER_COMPATIBILITY);
			// 因为HttpClient 4.0默认cookie策略会报WARN警告，所以手动定制cookie策略
			CookieSpecFactory csf = new CookieSpecFactory() {
				public CookieSpec newInstance(HttpParams params) {
					return new BrowserCompatSpec() {
						@Override
						public void validate(Cookie cookie, CookieOrigin origin)
								throws MalformedCookieException {
							// nothing to do
						}
					};
				}
			};
			client.getCookieSpecs().register("easy", csf);
			client.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
			isLogin = true;
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException!");
		} catch (ClientProtocolException e) {
			System.err.println("ClientProtocolException!");
		} catch (IOException e) {
			System.err.println("IOException!");
		} finally {
			httpost.abort();
		}
		return isLogin;
	}

	/**
	 * Purpose: 获取blog内容
	 * 
	 * @param response
	 * @return: String
	 */
	private void showResult(String userName, String password) {
		try {
			if (!login(userName, password)) {
				System.err.println("登陆失败!");
				System.exit(0);
			}
			/*
			 * 注意，因为renren.com登陆成功后，需要再次经过
			 * http://www.renren.com/callback.do?t=da278e2526f9b2387ea22e57578a85d93
			 * &
			 * origURL=http%3A%2F%2Fblog.renren.com%2Fblog%2F84082953%2F398292611
			 * &needNotify=false 这种方式跳转，所以需要再次处理发一次请求
			 */

			// 打印所有相应头
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				Header header = headers[i];
				System.out.println(header.getName() + ": " + header.getValue());
			}
			// 获取真实跳转路径
			Header locationHeader = response.getFirstHeader("Location");
			// 频繁登陆，failCode = 512，就会要求输入验证码登陆了
			HttpGet httpget = new HttpGet(locationHeader.getValue());
			HttpResponse response2 = client.execute(httpget);
			System.out.println(response2.getStatusLine()); // HTTP/1.1 200 OK
			// 获取Entity
			HttpEntity entity = response2.getEntity();
			// 解析html，拿出blog
			String[] context = htmlToPlainText(EntityUtils.toString(entity));
			System.out.println("---------解析后的内容----------- ");
			System.out.print("Title:  ");
			System.out.println(context[0]);
			System.out.print("Context:  ");
			System.out.println(context[1]);
		} catch (ClientProtocolException e) {
			System.err.println("ClientProtocolException!");
		} catch (ParseException e) {
			System.err.println("ParseException!");
		} catch (IOException e) {
			System.err.println("IOException!");
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			client.getConnectionManager().shutdown();
		}
	}

	// /**
	// * Purpose: 正则提取blog内容
	// * @param orgTest
	// * @return: String
	// */
	// private String printBlog(String orgTest) {
	// // 正则匹配规则
	// // String regexp = "<div\\s*id=\"blogContent\"\\s*[^>]*>(.+?)</div>";
	// // String regexp =
	// "(<div id=\"blogContent\" class=\"text-article\")(.+?)( </div>)";
	// Pattern pattern =
	// Pattern.compile("<div\\s*id=\"blogContent\"\\s*[^>]*>(.+?)</div>");
	// Matcher m = pattern.matcher(orgTest);
	// if (!m.find()) {
	// return null;
	// }
	// return m.group(0);
	// }
	/**
	 * Purpose: 使用jsoup解析Html
	 * 
	 * @param html
	 * @return: String[]
	 */
	private static String[] htmlToPlainText(String html) {
		String[] content = new String[] { "", "" };
		Document doc = Jsoup.parse(html);
		// 提取blog标题
		Elements titles = doc.select("h3.title-article>strong");
		for (Element oneSelect : titles)
			content[0] += oneSelect.text();
		// 提取blog内容
		Elements contents = doc.select("div#blogContent");
		for (Element oneSelect : contents)
			content[1] += oneSelect.text();
		return content;
	}

	/**
	 * Purpose: 测试
	 * 
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		RrLogin renRen = new RrLogin();
		renRen.showResult(userName, password);
	}
}