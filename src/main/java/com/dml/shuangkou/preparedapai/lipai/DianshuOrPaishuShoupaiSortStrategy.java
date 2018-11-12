package com.dml.shuangkou.preparedapai.lipai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;

/**
 * 以点数、牌数为主次的理牌策略
 * 
 * @author lsc
 *
 */
public class DianshuOrPaishuShoupaiSortStrategy implements ShoupaiSortStrategy {

	@Override
	public List<List<Integer>> sortShoupai(Map<Integer, PukePai> allShoupai, int[] shoupaiDianShuAmountArray) {
		List<List<Integer>> sortlists = new ArrayList<>();
		// 以点数为主的理牌
		List<Integer> dianshuSortList = new ArrayList<>();
		sortlists.add(dianshuSortList);
		// 以牌数为主的理牌
		List<Integer> paishuSortList = new ArrayList<>();
		sortlists.add(paishuSortList);
		return sortlists;
	}

}
