package com.dml.shuangkou.player.action.da.solution;

import java.util.List;

import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZu;

/**
 * 通过非炸弹去压牌的方案
 * 
 * @author Neo
 *
 */
public interface ZaDanYaPaiSolutionCalculator {
	public List<DaPaiDianShuSolution> calculate(DianShuZu beiYaDianShuZu, int[] dianShuAmountArray);
}
