package com.BoHong.customer.vo;

import java.sql.Timestamp;

/**
 * Optometryinfo entity. @author MyEclipse Persistence Tools
 */

public class Optometryinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer itemName;
	private String rqiujing;
	private String rzhujing;
	private String rzhouwei;
	private String rjiaozheng;
	private String lqiujing;
	private String lzhujing;
	private String lzhouwei;
	private String ljiaozheng;
	private String tongju;
	private Timestamp date;
	private String cost;
	private Integer creator;
	private Timestamp createTime;
	private Integer modifier;
	private Timestamp modifyTime;
	private Integer deletor;
	private Timestamp deleteTime;
	private Integer delState;

	// Constructors

	/** default constructor */
	public Optometryinfo() {
	}

	/** full constructor */
	public Optometryinfo(Integer uid, Integer itemName, String rqiujing,
			String rzhujing, String rzhouwei, String rjiaozheng,
			String lqiujing, String lzhujing, String lzhouwei,
			String ljiaozheng, String tongju, Timestamp date, String cost,
			Integer creator, Timestamp createTime, Integer modifier,
			Timestamp modifyTime, Integer deletor, Timestamp deleteTime,
			Integer delState) {
		this.uid = uid;
		this.itemName = itemName;
		this.rqiujing = rqiujing;
		this.rzhujing = rzhujing;
		this.rzhouwei = rzhouwei;
		this.rjiaozheng = rjiaozheng;
		this.lqiujing = lqiujing;
		this.lzhujing = lzhujing;
		this.lzhouwei = lzhouwei;
		this.ljiaozheng = ljiaozheng;
		this.tongju = tongju;
		this.date = date;
		this.cost = cost;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.deletor = deletor;
		this.deleteTime = deleteTime;
		this.delState = delState;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getItemName() {
		return this.itemName;
	}

	public void setItemName(Integer itemName) {
		this.itemName = itemName;
	}

	public String getRqiujing() {
		return this.rqiujing;
	}

	public void setRqiujing(String rqiujing) {
		this.rqiujing = rqiujing;
	}

	public String getRzhujing() {
		return this.rzhujing;
	}

	public void setRzhujing(String rzhujing) {
		this.rzhujing = rzhujing;
	}

	public String getRzhouwei() {
		return this.rzhouwei;
	}

	public void setRzhouwei(String rzhouwei) {
		this.rzhouwei = rzhouwei;
	}

	public String getRjiaozheng() {
		return this.rjiaozheng;
	}

	public void setRjiaozheng(String rjiaozheng) {
		this.rjiaozheng = rjiaozheng;
	}

	public String getLqiujing() {
		return this.lqiujing;
	}

	public void setLqiujing(String lqiujing) {
		this.lqiujing = lqiujing;
	}

	public String getLzhujing() {
		return this.lzhujing;
	}

	public void setLzhujing(String lzhujing) {
		this.lzhujing = lzhujing;
	}

	public String getLzhouwei() {
		return this.lzhouwei;
	}

	public void setLzhouwei(String lzhouwei) {
		this.lzhouwei = lzhouwei;
	}

	public String getLjiaozheng() {
		return this.ljiaozheng;
	}

	public void setLjiaozheng(String ljiaozheng) {
		this.ljiaozheng = ljiaozheng;
	}

	public String getTongju() {
		return this.tongju;
	}

	public void setTongju(String tongju) {
		this.tongju = tongju;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Integer getCreator() {
		return this.creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getModifier() {
		return this.modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getDeletor() {
		return this.deletor;
	}

	public void setDeletor(Integer deletor) {
		this.deletor = deletor;
	}

	public Timestamp getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Integer getDelState() {
		return this.delState;
	}

	public void setDelState(Integer delState) {
		this.delState = delState;
	}

}