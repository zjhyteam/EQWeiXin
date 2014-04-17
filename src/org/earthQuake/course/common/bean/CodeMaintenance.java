package org.earthQuake.course.common.bean;

/**
 * 代码维护表
 * @author 徐晓亮
 *
 */
public class CodeMaintenance implements java.io.Serializable{

	//代码ID
	private Integer id;
	//代码名称
	private String name;
	//代码值
	private String value;
	//有效1无效0
	private Integer valid;
	//代码
	private String code;
	//代码描述
	private String description;
	//1:增加，0:不增加
	private Integer flag;
	
	public CodeMaintenance() {
		super();
	}
	
	public CodeMaintenance(Integer id, String name, String value, Integer valid,
			String code, String description, Integer flag) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.valid = valid;
		this.code = code;
		this.description = description;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
