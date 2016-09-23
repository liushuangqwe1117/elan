package com.redrock.elan.common.core.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EProductProperty implements BaseEnum<EProductProperty, String> {
	/*PEARL_NAME("PEARL_NAME", "珍珠名称"),*/
	PEARL_POINT1("PEARL_POINT1", "珍珠点位1"), 
	PEARL_POINT2("PEARL_POINT2", "珍珠点位2"), 
	PEARL_NLEVEL("PEARL_NLEVEL", "珍珠颜色"),
	PEARL_CIRCLE("PEARL_CIRCLE", "珍珠形状"),  
	PEARL_NUMINOSITY("PEARL_NUMINOSITY","珍珠光泽"), 
	PEARL_QUALITY("PEARL_QUALITY", "珍珠瑕疵度"), 
	PEARL_CATEGORY("PEARL_CATEGORY", "珍珠种类"),
	PEARL_PROD_PLACE("PEARL_PROD_PLACE", "珍珠产地"),

	ACCESSORY_CATEGORY("ACCESSORY_CATEGORY", "配件品类"), 
	ACCESSORY_STYLE("ACCESSORY_STYLE", "配件款式"), 
	ACCESSORY_METERIAL("ACCESSORY_METERIAL", "配件材质"), 
	/*ACCESSORY_NSIZE("ACCESSORY_NSIZE","配件尺寸"),*/ 
	ACCESSORY_WEIGHT("ACCESSORY_WEIGHT", "配件镶嵌"),

	GOODS_CATEGORY("GOODS_CATEGORY", "成品品类"), 
	GOODS_STYLE("GOODS_STYLE", "成品款式");

	private String value;
	private String displayName;

	EProductProperty(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	static Map<String, EProductProperty> dataMap = new HashMap<String, EProductProperty>();
	static List<EProductProperty> pearItems = new ArrayList<EProductProperty>();
	static List<EProductProperty> accessoryItems = new ArrayList<EProductProperty>();
	static List<EProductProperty> goodsItems = new ArrayList<EProductProperty>();
	static List<EProductProperty> configItems = new ArrayList<EProductProperty>();
	static {
		for (EProductProperty roleType : values()) {
			dataMap.put(roleType.getValue(), roleType);
			if(roleType.getValue().startsWith("PEARL_")) {
				pearItems.add(roleType);
			}
			if(roleType.getValue().startsWith("ACCESSORY_")) {
				accessoryItems.add(roleType);		
			}
			if(roleType.getValue().startsWith("GOODS_")) {
				goodsItems.add(roleType);
			}
			configItems.add(roleType);
		}
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	public static List<EProductProperty> getPearlProductProperty() {
		return pearItems;
	}

	public static List<EProductProperty> getAccessoryProductProperty() {
		return accessoryItems;
	}
	
	public static List<EProductProperty> getGoodsProductProperty() {
		return goodsItems;
	}
	
	public static List<EProductProperty> getConfigProductProperty() {
		return configItems;
	} 

	public static EProductProperty getEnum(String value) {
		return dataMap.get(value);
	}
}
