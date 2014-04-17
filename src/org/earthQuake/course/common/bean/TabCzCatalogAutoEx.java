package org.earthQuake.course.common.bean;

/**
 * 地震地图信息
 * @author 徐晓亮
 *
 */
public class TabCzCatalogAutoEx implements java.io.Serializable{
	private String cata_id;
	private String event_id;
	private String operator;
	private String save_time;
	private String eq_type;
	private String o_time;
	private Integer o_time_ns;
	private Double lat;
	private Double lon;
	private Double depth;
	private Double ml;
	private Double ms;
	private Double md;
	private Double mb;
	private Double mmb;
	private Double mw;
	private Double m;
	private Double dmin;
	private Double gap_azi;
	private Double rms;
	private Double erh;
	private Double erz;
	private String qloc;
	private String qnet;
	private String qcom;
	private Integer sum_stn;
	private Integer used_stn;
	private Integer sum_pha;
	private Integer used_pha;
	private String explosion_flag;
	private String epic_id;
	private String source_id;
	private String location_cname;
	private String location_province;
	private String location_city;
	private String location_district;
	private String location_bname;
	
	public TabCzCatalogAutoEx() {
		super();
	}
	
	public TabCzCatalogAutoEx(String cata_id, String event_id, String operator,
			String save_time, String eq_type, String o_time, Integer o_time_ns,
			Double lat, Double lon, Double depth, Double ml, Double ms,
			Double md, Double mb, Double mmb, Double mw, Double m, Double dmin,
			Double gap_azi, Double rms, Double erh, Double erz, String qloc,
			String qnet, String qcom, Integer sum_stn, Integer used_stn,
			Integer sum_pha, Integer used_pha, String explosion_flag,
			String epic_id, String source_id, String location_cname,
			String location_province, String location_city,
			String location_district, String location_bname) {
		super();
		this.cata_id = cata_id;
		this.event_id = event_id;
		this.operator = operator;
		this.save_time = save_time;
		this.eq_type = eq_type;
		this.o_time = o_time;
		this.o_time_ns = o_time_ns;
		this.lat = lat;
		this.lon = lon;
		this.depth = depth;
		this.ml = ml;
		this.ms = ms;
		this.md = md;
		this.mb = mb;
		this.mmb = mmb;
		this.mw = mw;
		this.m = m;
		this.dmin = dmin;
		this.gap_azi = gap_azi;
		this.rms = rms;
		this.erh = erh;
		this.erz = erz;
		this.qloc = qloc;
		this.qnet = qnet;
		this.qcom = qcom;
		this.sum_stn = sum_stn;
		this.used_stn = used_stn;
		this.sum_pha = sum_pha;
		this.used_pha = used_pha;
		this.explosion_flag = explosion_flag;
		this.epic_id = epic_id;
		this.source_id = source_id;
		this.location_cname = location_cname;
		this.location_province = location_province;
		this.location_city = location_city;
		this.location_district = location_district;
		this.location_bname = location_bname;
	}
	public String getCata_id() {
		return cata_id;
	}
	public void setCata_id(String cata_id) {
		this.cata_id = cata_id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getSave_time() {
		return save_time;
	}
	public void setSave_time(String save_time) {
		this.save_time = save_time;
	}
	public String getEq_type() {
		return eq_type;
	}
	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}
	public String getO_time() {
		return o_time;
	}
	public void setO_time(String o_time) {
		this.o_time = o_time;
	}
	public Integer getO_time_ns() {
		return o_time_ns;
	}
	public void setO_time_ns(Integer o_time_ns) {
		this.o_time_ns = o_time_ns;
	}
	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getDepth() {
		return depth;
	}
	public void setDepth(Double depth) {
		this.depth = depth;
	}
	public Double getMl() {
		return ml;
	}
	public void setMl(Double ml) {
		this.ml = ml;
	}
	public Double getMs() {
		return ms;
	}
	public void setMs(Double ms) {
		this.ms = ms;
	}
	public Double getMd() {
		return md;
	}
	public void setMd(Double md) {
		this.md = md;
	}
	public Double getMb() {
		return mb;
	}
	public void setMb(Double mb) {
		this.mb = mb;
	}
	public Double getMmb() {
		return mmb;
	}
	public void setMmb(Double mmb) {
		this.mmb = mmb;
	}
	public Double getMw() {
		return mw;
	}
	public void setMw(Double mw) {
		this.mw = mw;
	}
	public Double getM() {
		return m;
	}
	public void setM(Double m) {
		this.m = m;
	}
	public Double getDmin() {
		return dmin;
	}
	public void setDmin(Double dmin) {
		this.dmin = dmin;
	}
	public Double getGap_azi() {
		return gap_azi;
	}
	public void setGap_azi(Double gap_azi) {
		this.gap_azi = gap_azi;
	}
	public Double getRms() {
		return rms;
	}
	public void setRms(Double rms) {
		this.rms = rms;
	}
	public Double getErh() {
		return erh;
	}
	public void setErh(Double erh) {
		this.erh = erh;
	}
	public Double getErz() {
		return erz;
	}
	public void setErz(Double erz) {
		this.erz = erz;
	}
	public String getQloc() {
		return qloc;
	}
	public void setQloc(String qloc) {
		this.qloc = qloc;
	}
	public String getQnet() {
		return qnet;
	}
	public void setQnet(String qnet) {
		this.qnet = qnet;
	}
	public String getQcom() {
		return qcom;
	}
	public void setQcom(String qcom) {
		this.qcom = qcom;
	}
	public Integer getSum_stn() {
		return sum_stn;
	}
	public void setSum_stn(Integer sum_stn) {
		this.sum_stn = sum_stn;
	}
	public Integer getUsed_stn() {
		return used_stn;
	}
	public void setUsed_stn(Integer used_stn) {
		this.used_stn = used_stn;
	}
	public Integer getSum_pha() {
		return sum_pha;
	}
	public void setSum_pha(Integer sum_pha) {
		this.sum_pha = sum_pha;
	}
	public Integer getUsed_pha() {
		return used_pha;
	}
	public void setUsed_pha(Integer used_pha) {
		this.used_pha = used_pha;
	}
	public String getExplosion_flag() {
		return explosion_flag;
	}
	public void setExplosion_flag(String explosion_flag) {
		this.explosion_flag = explosion_flag;
	}
	public String getEpic_id() {
		return epic_id;
	}
	public void setEpic_id(String epic_id) {
		this.epic_id = epic_id;
	}
	public String getSource_id() {
		return source_id;
	}
	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}
	public String getLocation_cname() {
		return location_cname;
	}
	public void setLocation_cname(String location_cname) {
		this.location_cname = location_cname;
	}
	public String getLocation_province() {
		return location_province;
	}
	public void setLocation_province(String location_province) {
		this.location_province = location_province;
	}
	public String getLocation_city() {
		return location_city;
	}
	public void setLocation_city(String location_city) {
		this.location_city = location_city;
	}
	public String getLocation_district() {
		return location_district;
	}
	public void setLocation_district(String location_district) {
		this.location_district = location_district;
	}
	public String getLocation_bname() {
		return location_bname;
	}
	public void setLocation_bname(String location_bname) {
		this.location_bname = location_bname;
	}
	
}
