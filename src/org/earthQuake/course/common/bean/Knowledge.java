package org.earthQuake.course.common.bean;

import java.math.BigInteger;

/**
 * 地震科普知识类
 * @author 徐晓亮
 *
 */
public class Knowledge {

	//地震科普知识ID
	private Integer id;
	//菜单序号
	private Integer num;
	//关键字标记 1：启用，0：不启用
	private Integer keywordsflag;
	//关键字
	private String keywords;
	//标题
	private String title;
	//摘要
	private String summary;
	//内容
	private String content;
	//图片名称
	private String imageName;
	
	public Knowledge() {
		super();
	}
	
	public Knowledge(Integer id, Integer num, Integer keywordsflag,
			String keywords, String title, String summary, String content,
			String imageName) {
		super();
		this.id = id;
		this.num = num;
		this.keywordsflag = keywordsflag;
		this.keywords = keywords;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.imageName = imageName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getKeywordsflag() {
		return keywordsflag;
	}
	public void setKeywordsflag(Integer keywordsflag) {
		this.keywordsflag = keywordsflag;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
