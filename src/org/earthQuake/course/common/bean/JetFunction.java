package org.earthQuake.course.common.bean;

/**
 * ext页面功能菜单管理
 * @author 徐晓亮
 *
 */
public class JetFunction implements java.io.Serializable{
    private String functionid;
    private String location;
    private String title;
    private String parent;
    private Integer orderno;
    private String nodetype;
    private String type;
    private String description;
    private String log;
    private String developer;
    private String active;
    private String functiondesc;
    private String auflag;
    private String rbflag;
    private String param1;
    private String param2;
    private String createdate;
    private String owner;


    public JetFunction(String functionid, String location, String title,
			String parent, Integer orderno, String nodetype, String type,
			String description, String log, String developer, String active,
			String functiondesc, String auflag, String rbflag, String param1,
			String param2, String createdate, String owner) {
		super();
		this.functionid = functionid;
		this.location = location;
		this.title = title;
		this.parent = parent;
		this.orderno = orderno;
		this.nodetype = nodetype;
		this.type = type;
		this.description = description;
		this.log = log;
		this.developer = developer;
		this.active = active;
		this.functiondesc = functiondesc;
		this.auflag = auflag;
		this.rbflag = rbflag;
		this.param1 = param1;
		this.param2 = param2;
		this.createdate = createdate;
		this.owner = owner;
	}

	public JetFunction() {
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFunctiondesc() {
        return functiondesc;
    }

    public void setFunctiondesc(String functiondesc) {
        this.functiondesc = functiondesc;
    }

    public String getAuflag() {
        return auflag;
    }

    public void setAuflag(String auflag) {
        this.auflag = auflag;
    }

    public String getRbflag() {
        return rbflag;
    }

    public void setRbflag(String rbflag) {
        this.rbflag = rbflag;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
