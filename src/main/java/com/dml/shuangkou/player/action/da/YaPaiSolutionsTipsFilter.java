package com.dml.shuangkou.player.action.da;

import java.util.List;

import com.dml.shuangkou.player.action.da.solution.DaPaiDianShuSolution;

/**
 * 可压牌的提示过滤器
 * 
 * @author Neo
 *
 */
public interface YaPaiSolutionsTipsFilter {
	public List<DaPaiDianShuSolution> filter(List<DaPaiDianShuSolution> YaPaiSolutions);
}
