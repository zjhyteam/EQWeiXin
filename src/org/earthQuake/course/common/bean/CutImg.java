package org.earthQuake.course.common.bean;

/**
 * 切图片
 * @author 徐晓亮
 *
 */
public class CutImg implements java.io.Serializable{

	//开始X坐标
	private int startX;
	//结束Y坐标
	private int startY;
	//截取所得图片宽度
	private int width;
	//截取所得图片高度
	private int height;
	//源路径
	private String srcPath;
	//目标路径
	private String tarPath;
	
	public CutImg() {
		super();
	}
	
	public CutImg(int startX, int startY, int width, int height,
			String srcPath, String tarPath) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.srcPath = srcPath;
		this.tarPath = tarPath;
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getTarPath() {
		return tarPath;
	}
	public void setTarPath(String tarPath) {
		this.tarPath = tarPath;
	}
	
}
