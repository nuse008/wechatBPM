package com.gzjiyi.weixin.entity;

public class UserInfomation {
	private String userid; 		//成员UserID。对应管理端的帐号
	private String name;   		//成员名称
	private String department;	//成员所属部门id列表
	private String position;	//职位信息
	private String mobile;		//手机号码
	private String gender;		//性别。0表示未定义，1表示男性，2表示女性
	private String email;		//邮箱
	private String weixinid;	//微信号
	private String avatar;		//头像url。注：如果要获取小图将url最后加"64"即可
	private String status;		//关注状态: 1=已关注，2=已冻结，4=未关注
	private String extattr;		//扩展属性
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExtattr() {
		return extattr;
	}
	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}
	
}
