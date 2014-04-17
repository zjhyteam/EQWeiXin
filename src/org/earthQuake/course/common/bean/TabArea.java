package org.earthQuake.course.common.bean;

/**
 * 地震区域表
 * @author 徐晓亮
 *
 */
public class TabArea implements java.io.Serializable{
	//地震信息ID
	private String cata_id;
	//显示级别。全球：1，中国：2，浙江：3
	private int level_id;
	public String getCata_id() {
		return cata_id;
	}
	public void setCata_id(String cata_id) {
		this.cata_id = cata_id;
	}
	public int getLevel_id() {
		return level_id;
	}
	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}
	
}
