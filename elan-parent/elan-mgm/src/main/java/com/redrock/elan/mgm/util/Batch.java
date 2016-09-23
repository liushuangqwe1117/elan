/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:wuxiangqian 2013-12-9
 */

package com.redrock.elan.mgm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分批工具
 * 
 * @author ljp
 * @date 2015-11-17 14:31:46
 */
public class Batch {

	public static int getBatch(int size, int batchSize) {
		int batchs = size / batchSize;
		if (size > batchSize * batchs) {
			batchs = batchs + 1;
		}
		return batchs;
	}

	/**
	 * 获取批次数据
	 * 
	 * @description
	 * @param datas
	 *            总数据
	 * @param batchSeq
	 *            批次序号，从0开始
	 * @param batchSize
	 *            批次尺寸
	 * @return
	 * @author wuxiangqian 不做数据合法性校验
	 * @date 2013-12-9
	 */
	@SuppressWarnings("rawtypes")
	public static List getBatchData(List datas, int batchSeq, int batchSize) {
		int fromIndex = batchSeq * batchSize;
		int toIndex = (batchSeq + 1) * batchSize;
		toIndex = toIndex > datas.size() ? datas.size() : toIndex;

		List result = datas.subList(fromIndex, toIndex);
		return result;
	}

	/**
	 * 数据分页处理
	 * 
	 * @description
	 * @param datas
	 *            总数据
	 * @param batchSize
	 *            分页大小
	 * @return
	 * @author wuxiangqian
	 * @date 2013-12-9
	 */
	@SuppressWarnings("rawtypes")
	public static List<List> splitTobatch(List datas, int batchSize) {
		List<List> result = new ArrayList<List>();
		int batchs = getBatch(datas.size(), batchSize);

		for (int i = 0; i < batchs; i++) {
			result.add(getBatchData(datas, i, batchSize));
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		System.out.println("getBatch(100, 99):"+getBatch(100, 99));
		System.out.println("getBatch(99, 99):"+getBatch(99, 99));
		System.out.println("getBatch(98, 99):"+getBatch(98, 99));
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		
		
		List<List> result = splitTobatch(list, 4);
		for(int i = 0;i<result.size();i++){
			List<Integer> l = result.get(i);
			for(int j=0;j<l.size();j++){
				System.out.print(l.get(j)+",");
			}
			System.out.println();
		}
		
	}
}
