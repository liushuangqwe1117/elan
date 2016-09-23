package com.redrock.elan.common.project.mgm.dto.busi;

import java.util.Date;

import com.redrock.elan.common.core.dto.IdentityDto;

public class BusiAccessoryDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3764894844619028106L;

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
	 * 材质
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String material;
	private String materialShow;

	/**
	 * 尺寸
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String nsize;
	private String nsizeShow;

	/**
	 * 重量
	 *
	 * @mbggenerated Sun Aug 28 10:46:38 CST 2016
	 */
	private String weight;
	private String weightShow;
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

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material == null ? null : material.trim();
	}

	public String getNsize() {
		return nsize;
	}

	public void setNsize(String nsize) {
		this.nsize = nsize == null ? null : nsize.trim();
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight == null ? null : weight.trim();
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

	public String getMaterialShow() {
		return materialShow;
	}

	public void setMaterialShow(String materialShow) {
		this.materialShow = materialShow;
	}

	public String getNsizeShow() {
		return nsizeShow;
	}

	public void setNsizeShow(String nsizeShow) {
		this.nsizeShow = nsizeShow;
	}

	public String getWeightShow() {
		return weightShow;
	}

	public void setWeightShow(String weightShow) {
		this.weightShow = weightShow;
	}

	public boolean isZeroNoShow() {
		return zeroNoShow;
	}

	public void setZeroNoShow(boolean zeroNoShow) {
		this.zeroNoShow = zeroNoShow;
	}

}