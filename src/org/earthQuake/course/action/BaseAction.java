package org.earthQuake.course.action;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware,ApplicationAware {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	
	/**
	 * 得到当前项目绝对路径
	 * @return
	 */
	public String getBasePath() {
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	
	/**
	 * 得到项目真实路径
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getRealPath(){
		return request.getRealPath("/");
	}
	
	/**
	 * 向前台输出内容
	 * @param obj
	 */
	public void print(Object obj){
		PrintWriter out = null;
		try {
			response.setContentType("text/html;chatset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(obj);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 将数据以json格式输出
	 * @param str
	 * @return
	 */
	public void printJSON(Object obj){
		PrintWriter out = null;
		try {
			String str=JSONUtil.serialize(obj); //将数据序列化成json格式
			response.setContentType("text/html;chatset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(str);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
    
	/**
	 * struts自动注入这些数据
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		this.response=response;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}
}
