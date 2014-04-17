package org.earthQuake.course.common.bean;


/**
 * 地图图片基类
 * @author 徐晓亮
 * @date 2014-01-19
 */
@SuppressWarnings("serial")
public class TabMapsDetail implements java.io.Serializable{

	//地图ID
	private String mapid;
	//图片名称
	private String imageName;
	//时间
	private String time;
	//地点
	private String address;
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	//震级
	private Double magnitude;
	//震中烈度
	private Integer intensity;
	//震源深度
	private Double depth;
	
	/**
	 * default constructor
	 */
	public TabMapsDetail() {
	}
	
	/**
	 * full constructor
	 * @param mapid
	 * @param image
	 * @param time
	 * @param address
	 * @param longitude
	 * @param latitude
	 * @param magnitude
	 * @param intensity
	 * @param depth
	 */
	public TabMapsDetail(String mapid, String imageName, String time, String address,
			Double longitude, Double latitude, Double magnitude, Integer intensity,
			Double depth) {
		super();
		this.mapid = mapid;
		this.imageName = imageName;
		this.time = time;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.magnitude = magnitude;
		this.intensity = intensity;
		this.depth = depth;
	}
	
	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(Double magnitude) {
		this.magnitude = magnitude;
	}
	public Integer getIntensity() {
		return intensity;
	}
	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}
	
}
