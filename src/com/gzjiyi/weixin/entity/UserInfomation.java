package com.gzjiyi.weixin.entity;

public class UserInfomation {
	private String userid; 		//��ԱUserID����Ӧ����˵��ʺ�
	private String name;   		//��Ա����
	private String department;	//��Ա��������id�б�
	private String position;	//ְλ��Ϣ
	private String mobile;		//�ֻ�����
	private String gender;		//�Ա�0��ʾδ���壬1��ʾ���ԣ�2��ʾŮ��
	private String email;		//����
	private String weixinid;	//΢�ź�
	private String avatar;		//ͷ��url��ע�����Ҫ��ȡСͼ��url����"64"����
	private String status;		//��ע״̬: 1=�ѹ�ע��2=�Ѷ��ᣬ4=δ��ע
	private String extattr;		//��չ����
	
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
