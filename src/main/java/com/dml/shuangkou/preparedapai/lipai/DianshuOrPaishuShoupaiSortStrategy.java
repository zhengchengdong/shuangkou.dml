package com.dml.shuangkou.preparedapai.lipai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dml.puke.pai.DianShu;
import com.dml.puke.pai.PukePai;

/**
 * 以点数、牌数为主次的理牌策略
 * 
 * @author lsc
 *
 */
public class DianshuOrPaishuShoupaiSortStrategy implements ShoupaiSortStrategy {

	@Override
	public List<List<Integer>> sortShoupai(Map<Integer, PukePai> allShoupai) {
		List<List<Integer>> sortlists = new ArrayList<>();
		// 以点数为主的理牌
		List<Integer> dianshuSort = new ArrayList<>();
		sortlists.add(dianshuSort);
		List<PukePai> dianshuSortList = new LinkedList<>();
		for (PukePai pukePai : allShoupai.values()) {
			addPukePai(dianshuSortList, pukePai);
		}
		for (PukePai pukePai : dianshuSortList) {
			dianshuSort.add(pukePai.getId());
		}
		// 以牌数为主的理牌
		List<Integer> zhangshuSort = new ArrayList<>();
		sortlists.add(zhangshuSort);
		Map<Integer, List<PukePai>> numberGroupMap = new TreeMap<>();
		List<PukePai> yiZhangList = new LinkedList<>();
		numberGroupMap.put(1, yiZhangList);
		List<PukePai> erZhangList = new LinkedList<>();
		numberGroupMap.put(2, erZhangList);
		List<PukePai> sanZhangList = new LinkedList<>();
		numberGroupMap.put(3, sanZhangList);
		List<PukePai> siZhangList = new LinkedList<>();
		numberGroupMap.put(4, siZhangList);
		List<PukePai> wuZhangList = new LinkedList<>();
		numberGroupMap.put(5, wuZhangList);
		List<PukePai> liuZhangList = new LinkedList<>();
		numberGroupMap.put(6, liuZhangList);
		List<PukePai> qiZhangList = new LinkedList<>();
		numberGroupMap.put(7, qiZhangList);
		List<PukePai> baZhangList = new LinkedList<>();
		numberGroupMap.put(8, baZhangList);
		List<PukePai> temList = new ArrayList<>();
		DianShu dianshu = null;
		for (PukePai pukePai : dianshuSortList) {
			if (dianshu == null) {
				dianshu = pukePai.getPaiMian().dianShu();
				temList.add(pukePai);
			} else if (dianshu.equals(pukePai.getPaiMian().dianShu())) {
				temList.add(pukePai);
			} else {
				List<PukePai> zhangshuList = numberGroupMap.get(temList.size());
				for (PukePai pai : temList) {
					addPukePai(zhangshuList, pai);
				}
				temList = new ArrayList<>();
				dianshu = pukePai.getPaiMian().dianShu();
				temList.add(pukePai);
			}
		}
		List<PukePai> zhangshuList = numberGroupMap.get(temList.size());
		for (PukePai pai : temList) {
			addPukePai(zhangshuList, pai);
		}
		for (List<PukePai> zhangshuList1 : numberGroupMap.values()) {
			for (PukePai pukePai : zhangshuList1) {
				zhangshuSort.add(pukePai.getId());
			}
		}
		return sortlists;
	}

	private void addPukePai(List<PukePai> sortList, PukePai pai) {
		if (sortList.isEmpty()) {
			sortList.add(pai);
		} else {
			for (int i = 0; i < sortList.size(); i++) {
				int compare = compare(pai, sortList.get(i));
				if (compare > 0) {
					if (i >= sortList.size() - 1) {
						sortList.add(pai);
						return;
					} else {
						continue;
					}
				} else {
					sortList.add(i, pai);
					return;
				}
			}
		}
	}

	private int compare(PukePai pai1, PukePai pai2) {
		int compare = pai1.getPaiMian().compareTo(pai2.getPaiMian());
		if (compare == 0) {
			return pai1.getId() - pai2.getId();
		} else {
			return compare;
		}
	}

}
