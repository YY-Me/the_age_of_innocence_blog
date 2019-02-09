/*
*
* BlogQQUser.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-08-10
*/
package cn.web.entity;

import java.util.Date;

public class BlogQQUser {
	/**
	 * qq用户唯一标识
	 */
	private String openid;

	/**
	 * 用户在QQ空间的昵称。
	 */
	private String nickname;

	/**
	 * 地区
	 */
	private String area;

	/**
	 * 大小为40×40像素的QQ头像URL。
	 */
	private String figureurlQq1;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 0:禁用，1:正常
	 */
	private String status;

	/**
	 * 最近更新时间
	 */
	private Date updateTime = new Date();

	/**
	 * qq用户唯一标识
	 * @return openid qq用户唯一标识
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * qq用户唯一标识
	 * @param openid qq用户唯一标识
	 */
	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	/**
	 * 用户在QQ空间的昵称。
	 * @return nickname 用户在QQ空间的昵称。
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 用户在QQ空间的昵称。
	 * @param nickname 用户在QQ空间的昵称。
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	/**
	 * 地区
	 * @return area 地区
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 地区
	 * @param area 地区
	 */
	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	/**
	 * 大小为40×40像素的QQ头像URL。
	 * @return figureurl_qq_1 大小为40×40像素的QQ头像URL。
	 */
	public String getFigureurlQq1() {
		return figureurlQq1;
	}

	/**
	 * 大小为40×40像素的QQ头像URL。
	 * @param figureurlQq1 大小为40×40像素的QQ头像URL。
	 */
	public void setFigureurlQq1(String figureurlQq1) {
		this.figureurlQq1 = figureurlQq1 == null ? null : figureurlQq1.trim();
	}

	/**
	 * 性别
	 * @return gender 性别
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 性别
	 * @param gender 性别
	 */
	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	/**
	 * 0:禁用，1:正常
	 * @return status 0:禁用，1:正常
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 0:禁用，1:正常
	 * @param status 0:禁用，1:正常
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 最近更新时间
	 * @return update_time 最近更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 最近更新时间
	 * @param updateTime 最近更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}