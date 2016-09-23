package com.redrock.elan.common.project.mgm.dto.busi;

import java.util.Date;

import com.redrock.elan.common.core.dto.IdentityDto;

public class BusiPearlDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1260707814552712439L;

	/**
	 * 主键
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String id;

	/**
	 * 编号
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String code;

	/**
	 * 名称
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String name;
	private String nameShow;

	/**
	 * 级别
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String nlevel;
	private String nlevelShow;

	/**
	 * 点位
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String pointOne;
	private String pointOneShow;

	private String pointTwo;
	private String pointTwoShow;
	/**
	 * 圆度
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String circle;
	private String circleShow;
	/**
	 * 质量
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String quality;
	private String qualityShow;
	/**
	 * 光度
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String luminosity;
	private String luminosityShow;
	/**
	 * 种类
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String category;
	private String categoryShow;
	/**
	 * 产地
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String prodPlace;
	private String prodPlaceShow;
	/**
	 * 市场定价
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private Long marketPrice;

	/**
	 * 代理折扣
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String discount;

	/**
	 * 代理价格
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private Long proxyPrice;

	/**
	 * 缩略图
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String minPic;

	/**
	 * 大图片
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String maxPic;

	/**
	 * 库存
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private Long inventory;

	/**
	 * 备注
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String remark;

	/**
	 * 排序
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private Long norder;

	/**
	 * 创建时间
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private Date createTime;

	/**
	 * 不显示库存为0的记录，默认显示
	 */
	private boolean zeroNoShow = Boolean.FALSE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getNlevel() {
		return nlevel;
	}

	public void setNlevel(String nlevel) {
		this.nlevel = nlevel == null ? null : nlevel.trim();
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle == null ? null : circle.trim();
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality == null ? null : quality.trim();
	}

	public String getLuminosity() {
		return luminosity;
	}

	public void setLuminosity(String luminosity) {
		this.luminosity = luminosity == null ? null : luminosity.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getProdPlace() {
		return prodPlace;
	}

	public void setProdPlace(String prodPlace) {
		this.prodPlace = prodPlace == null ? null : prodPlace.trim();
	}

	public Long getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Long marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Long getProxyPrice() {
		return proxyPrice;
	}

	public void setProxyPrice(Long proxyPrice) {
		this.proxyPrice = proxyPrice;
	}

	public String getMinPic() {
		return minPic;
	}

	public void setMinPic(String minPic) {
		this.minPic = minPic == null ? null : minPic.trim();
	}

	public String getMaxPic() {
		return maxPic;
	}

	public void setMaxPic(String maxPic) {
		this.maxPic = maxPic == null ? null : maxPic.trim();
	}

	public Long getInventory() {
		return inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Long getNorder() {
		return norder;
	}

	public void setNorder(Long norder) {
		this.norder = norder;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNameShow() {
		return nameShow;
	}

	public void setNameShow(String nameShow) {
		this.nameShow = nameShow;
	}

	public String getNlevelShow() {
		return nlevelShow;
	}

	public void setNlevelShow(String nlevelShow) {
		this.nlevelShow = nlevelShow;
	}

	public String getCircleShow() {
		return circleShow;
	}

	public void setCircleShow(String circleShow) {
		this.circleShow = circleShow;
	}

	public String getQualityShow() {
		return qualityShow;
	}

	public void setQualityShow(String qualityShow) {
		this.qualityShow = qualityShow;
	}

	public String getLuminosityShow() {
		return luminosityShow;
	}

	public void setLuminosityShow(String luminosityShow) {
		this.luminosityShow = luminosityShow;
	}

	public String getCategoryShow() {
		return categoryShow;
	}

	public void setCategoryShow(String categoryShow) {
		this.categoryShow = categoryShow;
	}

	public String getProdPlaceShow() {
		return prodPlaceShow;
	}

	public void setProdPlaceShow(String prodPlaceShow) {
		this.prodPlaceShow = prodPlaceShow;
	}

	public String getPointOne() {
		return pointOne;
	}

	public void setPointOne(String pointOne) {
		this.pointOne = pointOne;
	}

	public String getPointOneShow() {
		return pointOneShow;
	}

	public void setPointOneShow(String pointOneShow) {
		this.pointOneShow = pointOneShow;
	}

	public String getPointTwo() {
		return pointTwo;
	}

	public void setPointTwo(String pointTwo) {
		this.pointTwo = pointTwo;
	}

	public String getPointTwoShow() {
		return pointTwoShow;
	}

	public void setPointTwoShow(String pointTwoShow) {
		this.pointTwoShow = pointTwoShow;
	}

	public boolean isZeroNoShow() {
		return zeroNoShow;
	}

	public void setZeroNoShow(boolean zeroNoShow) {
		this.zeroNoShow = zeroNoShow;
	}

}