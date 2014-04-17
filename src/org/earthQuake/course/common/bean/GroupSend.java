package org.earthQuake.course.common.bean;


/**
 * 短信群发信息表
 * @author 徐晓亮
 *
 */
public class GroupSend implements java.io.Serializable{

	//短信群发信息ID
	private int id;
	//标题
	private String title;
	//发送时间
	private String s_time;
	//内容
	private String content;
	//图片路径
	private String imageName;
	
	public GroupSend() {
		super();
	}
	public GroupSend(int id, String title, String s_time, String content,
			String imageName) {
		super();
		this.id = id;
		this.title = title;
		this.s_time = s_time;
		this.content = content;
		this.imageName = imageName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(String s_time) {
		this.s_time = s_time;
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
