package cn.commons.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {

	private static final long serialVersionUID = 357423675837671836L;

	/**
	 * 
	 */
	private String uid;

	/**
	 * 
	 */
	private String username;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String nickname;

	/**
	 * 
	 */
	private String phone;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private Date birthday;

	/**
	 * 
	 */
	private Integer sex;

	/**
	 * 
	 */
	private String headimgurl;

	/**
	 * 
	 */
	private Integer status;

	/**
	 * 
	 */
	private Date createtime;

	/**
	 * 
	 */
	private Date updatetime = new Date();

	/**
	 * 
	 * @return uid 
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * 
	 * @param uid 
	 */
	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	/**
	 * 
	 * @return username 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username 
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * 
	 * @return password 
	 */
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * 
	 * @return nickname 
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 
	 * @param nickname 
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	/**
	 * 
	 * @return phone 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 * @param phone 
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * 
	 * @return email 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email 
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * 
	 * @return birthday 
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 
	 * @param birthday 
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 
	 * @return sex 
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 
	 * @param sex 
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 
	 * @return headimgurl 
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * 
	 * @param headimgurl 
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl == null ? null : headimgurl.trim();
	}

	/**
	 * 
	 * @return status 
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 
	 * @return createtime 
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 
	 * @param createtime 
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 
	 * @return updatetime 
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * 
	 * @param updatetime 
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
