package com.dml.shuangkou.player.action.da;

import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;
import com.dml.shuangkou.player.action.da.solution.DaPaiDianShuSolution;

/**
 * 生成所有可打的牌的方案
 * 
 * @author Neo
 *
 */
public interface AllKedaPaiSolutionsGenerator {
	public List<DaPaiDianShuSolution> generateAllKedaPaiSolutions(Map<Integer, PukePai> allShoupai);
}
