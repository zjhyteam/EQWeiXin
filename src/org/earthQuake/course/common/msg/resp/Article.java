package org.earthQuake.course.common.msg.resp;

/**
 * 图文model
 * 
 * @author 徐晓亮
 * @date 2014-01-06
 */
public class Article {
	// 图文消息名称
	private String Title;
	// 图文消息描述
	private String Description;
	// 图片链接---发送客服消息接口用
	//支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String Picurl;
	//图片链接---被动发送消息接口用
	private String PicUrl;
	// 点击图文消息跳转链接
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return null == Description ? "" : Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getPicurl() {
		return null == Picurl ? "" : Picurl;
	}

	public void setPicurl(String picurl) {
		Picurl = picurl;
	}

	public String getUrl() {
		return null == Url ? "" : Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
