package com.redrock.elan.common.project.mgm.dto.busi;

import java.util.Date;

import com.redrock.elan.common.core.dto.IdentityDto;

public class BusiGoodsDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8326704278571683209L;

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
	 * 种类
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String category;
	private String categoryShow;
	/**
	 * 款式
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String style;
	private String styleShow;

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
	 * 是否特价品:0-非特价,1-特价
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private Short special;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style == null ? null : style.trim();
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

	public Short getSpecial() {
		return special;
	}

	public void setSpecial(Short special) {
		this.special = special;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCategoryShow() {
		return categoryShow;
	}

	public void setCategoryShow(String categoryShow) {
		this.categoryShow = categoryShow;
	}

	public String getStyleShow() {
		return styleShow;
	}

	public void setStyleShow(String styleShow) {
		this.styleShow = styleShow;
	}

	public boolean isZeroNoShow() {
		return zeroNoShow;
	}

	public void setZeroNoShow(boolean zeroNoShow) {
		this.zeroNoShow = zeroNoShow;
	}

}