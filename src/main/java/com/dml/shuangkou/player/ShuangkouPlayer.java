package com.dml.shuangkou.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dml.puke.pai.DianShu;
import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.puke.wanfa.position.Position;
import com.dml.shuangkou.pai.waihao.WaihaoGenerator;
import com.dml.shuangkou.player.action.da.DaPaiException;
import com.dml.shuangkou.player.action.da.KedaPaiSolutionsForTipsGenerator;
import com.dml.shuangkou.player.action.da.YaPaiSolutionsTipsFilter;
import com.dml.shuangkou.player.action.da.solution.DaPaiDianShuSolution;
import com.dml.shuangkou.preparedapai.lipai.ShoupaiSortStrategy;

public class ShuangkouPlayer {

	private String id;
	private Position position;
	private Map<Integer, PukePai> allShoupai = new HashMap<>();
	private int[] shoupaiDianShuAmountArray = new int[15];// 用于方便计算

	/**
	 * 可以有多种理牌方案
	 */
	private List<List<Integer>> shoupaiIdListForSortList = new ArrayList<>();

	/**
	 * 历史打出牌组，不包含还在公示的打出牌组
	 */
	private List<DianShuZuPaiZu> lishiDachuPaiZuList = new ArrayList<>();

	/**
	 * 还在公示的打出牌组
	 */
	private DianShuZuPaiZu publicDachuPaiZu;

	/**
	 * 用于提示的打牌的方案。这些提示是提示可以打的牌，不一定能压上一手牌，也不一定提示所有方案。
	 */
	private List<DaPaiDianShuSolution> daPaiSolutionsForTips = new ArrayList<>();

	/**
	 * 可以压上一手牌的所有候选方案,key是基于“算术基本定理”的dianshuZuheIdx
	 */
	private Map<String, DaPaiDianShuSolution> yaPaiSolutionCandidates = new HashMap<>();

	/**
	 * 用于给用户提示的可以压上一手牌的方案，是yaPaiSolutionCandidates的子集。顺序有意义，就是提示顺序。
	 */
	private List<DaPaiDianShuSolution> yaPaiSolutionsForTips = new ArrayList<>();

	public void addShouPai(PukePai pukePai) {
		allShoupai.put(pukePai.getId(), pukePai);
		int ordinal = pukePai.getPaiMian().dianShu().ordinal();
		shoupaiDianShuAmountArray[ordinal]++;
	}

	public void putYaPaiSolutionCandidates(List<DaPaiDianShuSolution> solutions) {
		solutions.forEach((solution) -> yaPaiSolutionCandidates.put(solution.getDianshuZuheIdx(), solution));
	}

	public List<DaPaiDianShuSolution> takeYaPaiSolutionCandidates() {
		return new ArrayList<>(yaPaiSolutionCandidates.values());
	}

	public void da(List<Integer> paiIds, String dianshuZuheIdx, WaihaoGenerator waihaoGenerator) throws Exception {
		DaPaiDianShuSolution solution = yaPaiSolutionCandidates.get(dianshuZuheIdx);
		if (solution == null) {
			throw new DaPaiException();
		}
		List<DianShu> dapaiDianshuList = new ArrayList<>();
		PukePai[] paiArray = new PukePai[paiIds.size()];
		for (int i = 0; i < paiIds.size(); i++) {
			Integer paiId = paiIds.get(i);
			PukePai pai = allShoupai.get(paiId);
			if (pai == null) {
				throw new DaPaiException();
			}
			paiArray[i] = pai;
			dapaiDianshuList.add(pai.getPaiMian().dianShu());
		}
		DianShu[] solutionDianShuArray = solution.getDachuDianShuArray();
		for (int i = 0; i < solutionDianShuArray.length; i++) {
			DianShu solutionDianShu = solutionDianShuArray[i];
			if (!dapaiDianshuList.remove(solutionDianShu)) {
				throw new DaPaiException();
			}
		}
		if (!dapaiDianshuList.isEmpty()) {
			throw new DaPaiException();
		}

		if (publicDachuPaiZu != null) {
			lishiDachuPaiZuList.add(publicDachuPaiZu);
		}
		DianShuZuPaiZu newPublicDachuPaiZu = new DianShuZuPaiZu();
		newPublicDachuPaiZu.setDianShuZu(solution.getDianShuZu());
		newPublicDachuPaiZu.setPaiArray(paiArray);
		waihaoGenerator.generateWaihao(newPublicDachuPaiZu);
		publicDachuPaiZu = newPublicDachuPaiZu;
		for (int i = 0; i < paiIds.size(); i++) {
			Integer paiId = paiIds.get(i);
			PukePai pukePai = allShoupai.get(paiId);
			int ordinal = pukePai.getPaiMian().dianShu().ordinal();
			shoupaiDianShuAmountArray[ordinal]--;
			allShoupai.remove(paiId);
		}
	}

	public void guo() {
		yaPaiSolutionCandidates.clear();
		yaPaiSolutionsForTips.clear();
		yaPaiSolutionsForTips.clear();
	}

	public void putPublicDachuPaiZuToLishi() {
		if (publicDachuPaiZu != null) {
			lishiDachuPaiZuList.add(publicDachuPaiZu);
			publicDachuPaiZu = null;
		}
	}

	public void addDaPaiDianShuSolutions(List<DaPaiDianShuSolution> solutions) {
		solutions.forEach((solution) -> yaPaiSolutionCandidates.put(solution.getDianshuZuheIdx(), solution));
	}

	public void generateYaPaiSolutionsForTips(YaPaiSolutionsTipsFilter yaPaiSolutionsTipsFilter) {
		yaPaiSolutionsForTips = yaPaiSolutionsTipsFilter.filter(new ArrayList<>(yaPaiSolutionCandidates.values()));
	}

	public void generateDaPaiSolutionsForTips(KedaPaiSolutionsForTipsGenerator kedaPaiSolutionsForTipsGenerator) {
		daPaiSolutionsForTips = kedaPaiSolutionsForTipsGenerator
				.generateKedaPaiSolutionsForTips(shoupaiDianShuAmountArray);
	}

	public void lipai(ShoupaiSortStrategy shoupaiSortStrategy) {
		shoupaiIdListForSortList = shoupaiSortStrategy.sortShoupai(allShoupai);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Map<Integer, PukePai> getAllShoupai() {
		return allShoupai;
	}

	public void setAllShoupai(Map<Integer, PukePai> allShoupai) {
		this.allShoupai = allShoupai;
	}

	public int[] getShoupaiDianShuAmountArray() {
		return shoupaiDianShuAmountArray;
	}

	public void setShoupaiDianShuAmountArray(int[] shoupaiDianShuAmountArray) {
		this.shoupaiDianShuAmountArray = shoupaiDianShuAmountArray;
	}

	public List<List<Integer>> getShoupaiIdListForSortList() {
		return shoupaiIdListForSortList;
	}

	public void setShoupaiIdListForSortList(List<List<Integer>> shoupaiIdListForSortList) {
		this.shoupaiIdListForSortList = shoupaiIdListForSortList;
	}

	public List<DianShuZuPaiZu> getLishiDachuPaiZuList() {
		return lishiDachuPaiZuList;
	}

	public void setLishiDachuPaiZuList(List<DianShuZuPaiZu> lishiDachuPaiZuList) {
		this.lishiDachuPaiZuList = lishiDachuPaiZuList;
	}

	public DianShuZuPaiZu getPublicDachuPaiZu() {
		return publicDachuPaiZu;
	}

	public void setPublicDachuPaiZu(DianShuZuPaiZu publicDachuPaiZu) {
		this.publicDachuPaiZu = publicDachuPaiZu;
	}

	public List<DaPaiDianShuSolution> getDaPaiSolutionsForTips() {
		return daPaiSolutionsForTips;
	}

	public void setDaPaiSolutionsForTips(List<DaPaiDianShuSolution> daPaiSolutionsForTips) {
		this.daPaiSolutionsForTips = daPaiSolutionsForTips;
	}

	public Map<String, DaPaiDianShuSolution> getYaPaiSolutionCandidates() {
		return yaPaiSolutionCandidates;
	}

	public void setYaPaiSolutionCandidates(Map<String, DaPaiDianShuSolution> yaPaiSolutionCandidates) {
		this.yaPaiSolutionCandidates = yaPaiSolutionCandidates;
	}

	public List<DaPaiDianShuSolution> getYaPaiSolutionsForTips() {
		return yaPaiSolutionsForTips;
	}

	public void setYaPaiSolutionsForTips(List<DaPaiDianShuSolution> yaPaiSolutionsForTips) {
		this.yaPaiSolutionsForTips = yaPaiSolutionsForTips;
	}

}
