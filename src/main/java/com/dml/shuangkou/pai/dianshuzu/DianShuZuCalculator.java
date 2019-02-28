package com.dml.shuangkou.pai.dianshuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DanGeZhadanDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZuGenerator;
import com.dml.puke.wanfa.dianshu.dianshuzu.DuiziDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LianduiDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LiansanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.SanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.ShunziDianShuZu;
import com.dml.shuangkou.pai.jiesuanpai.ShoupaiJiesuanPai;
import com.dml.shuangkou.player.action.da.solution.DaPaiDianShuSolution;
import com.dml.shuangkou.wanfa.BianXingWanFa;

/**
 * 通过王牌变化得到不同点数组，再通过点数组和王牌的代法产生打牌方案
 * 
 * @author lsc
 *
 */
public class DianShuZuCalculator {

	/**
	 * 计算单张点数组
	 */
	public static List<DanzhangDianShuZu> calculateDanzhangDianShuZu(int[] dianshuCountArray) {
		// 单张
		List<DanzhangDianShuZu> danzhangDianShuZuList = DianShuZuGenerator
				.generateAllDanzhangDianShuZu(dianshuCountArray);
		return danzhangDianShuZuList;
	}

	/**
	 * 根据单张点数组计算所有单张打法
	 */
	public static List<DaPaiDianShuSolution> generateAllDanzhangDaPaiDianShuSolution(
			List<DanzhangDianShuZu> danzhangDianShuZuList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (DanzhangDianShuZu danzhangDianShuZu : danzhangDianShuZuList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(danzhangDianShuZu);
			DianShu[] dachuDianShuArray = { danzhangDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 计算对子点数组
	 */
	public static List<DuiziDianShuZu> calculateDuiziDianShuZu(int[] dianshuCountArray) {
		// 对子
		List<DuiziDianShuZu> duiziDianShuZuList = DianShuZuGenerator.generateAllDuiziDianShuZu(dianshuCountArray);
		return duiziDianShuZuList;
	}

	/**
	 * 计算三张点数组
	 */
	public static List<SanzhangDianShuZu> calculateSanzhangDianShuZu(int[] dianshuCountArray) {
		// 三张牌
		List<SanzhangDianShuZu> sanzhangDianShuZuList = DianShuZuGenerator
				.generateAllSanzhangDianShuZu(dianshuCountArray);
		return sanzhangDianShuZuList;
	}

	/**
	 * 计算顺子点数组
	 */
	public static List<ShunziDianShuZu> calculateShunziDianShuZu(int[] dianshuCountArray) {
		// 顺子
		List<ShunziDianShuZu> shunziDianShuZuList = new ArrayList<>();
		for (int k = 5; k < 14; k++) {// 最小5连，最大13连
			shunziDianShuZuList.addAll(DianShuZuGenerator.generateAllShunziDianShuZu(dianshuCountArray, k));
		}
		return shunziDianShuZuList;
	}

	/**
	 * 计算连对点数组
	 */
	public static List<LianduiDianShuZu> calculateLianduiDianShuZu(int[] dianshuCountArray) {
		// 连对
		List<LianduiDianShuZu> lianduiDianShuZuList = new ArrayList<>();
		for (int k = 3; k < 14; k++) {// 最小3连，最大13连
			lianduiDianShuZuList.addAll(DianShuZuGenerator.generateAllLianduiDianShuZu(dianshuCountArray, k));
		}
		return lianduiDianShuZuList;
	}

	/**
	 * 计算连三张点数组
	 */
	public static List<LiansanzhangDianShuZu> calculateLiansanzhangDianShuZu(int[] dianshuCountArray) {
		// 连三张
		List<LiansanzhangDianShuZu> liansanzhangDianShuZuList = new ArrayList<>();
		for (int k = 3; k < 10; k++) {// 最小3连，最大9连
			liansanzhangDianShuZuList.addAll(DianShuZuGenerator.generateAllLiansanzhangDianShuZu(dianshuCountArray, k));
		}
		return liansanzhangDianShuZuList;
	}

	/**
	 * 计算普通炸弹点数组
	 */
	public static List<DanGeZhadanDianShuZu> calculateDanGeZhadanDianShuZu(int[] dianshuCountArray) {
		// 炸弹
		List<DanGeZhadanDianShuZu> danGeZhadanDianShuZuList = DianShuZuGenerator
				.generateAllZhadanDianShuZu(dianshuCountArray);
		return danGeZhadanDianShuZuList;
	}

	/**
	 * 计算连续炸弹点数组
	 */
	public static List<LianXuZhadanDianShuZu> calculateLianXuZhadanDianShuZu(int[] dianshuCountArray) {
		// 连续炸弹
		List<LianXuZhadanDianShuZu> lianXuZhadanDianShuZuList = generateAllLianXuZhadanDianShuZu(dianshuCountArray);
		return lianXuZhadanDianShuZuList;
	}

	/**
	 * 计算王炸点数组
	 */
	public static List<WangZhadanDianShuZu> calculateWangZhadanDianShuZu(int[] dianshuCountArray) {
		List<WangZhadanDianShuZu> wangZhadanList = new ArrayList<>();
		int xiaowangCount = dianshuCountArray[13];
		int dawangCount = dianshuCountArray[14];
		for (int i = 0; i <= xiaowangCount; i++) {
			for (int j = 0; j <= dawangCount; j++) {
				if (i + j >= 3) {
					WangZhadanDianShuZu angZhadan = new WangZhadanDianShuZu(i, j);
					wangZhadanList.add(angZhadan);
				}
			}
		}
		return wangZhadanList;
	}

	public static List<DaPaiDianShuSolution> generateAllWangZhadanDianShuZu(List<WangZhadanDianShuZu> wangZhadanList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (WangZhadanDianShuZu wangZhadanDianShuZu : wangZhadanList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(wangZhadanDianShuZu);
			List<DianShu> dachuDianShuList = new ArrayList<>();
			DianShu[] lianXuDianShuArray = wangZhadanDianShuZu.getDianShuZu();
			for (int count = 0; count < wangZhadanDianShuZu.getXiaowangCount(); count++) {
				dachuDianShuList.add(lianXuDianShuArray[0]);
			}
			for (int count = 0; count < wangZhadanDianShuZu.getDawangCount(); count++) {
				dachuDianShuList.add(lianXuDianShuArray[1]);
			}
			DianShu[] dachuDianShuArray = dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]);
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	/**
	 * 没有王的情况下生成打牌方案
	 */
	public static List<DaPaiDianShuSolution> calculateAllDaPaiDianShuSolutionWithoutWangDang(PaiXing paiXing) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 对子
		for (DuiziDianShuZu duiziDianShuZu : paiXing.getDuiziDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(duiziDianShuZu);
			DianShu[] dachuDianShuArray = { duiziDianShuZu.getDianShu(), duiziDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 连对
		for (LianduiDianShuZu lianduiDianShuZu : paiXing.getLianduiDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(lianduiDianShuZu);
			DianShu[] lianXuDianShuArray = lianduiDianShuZu.getLianXuDianShuArray();
			DianShu[] dachuDianShuArray = new DianShu[2 * lianXuDianShuArray.length];
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				dachuDianShuArray[i * 2] = lianXuDianShuArray[i];
				dachuDianShuArray[i * 2 + 1] = lianXuDianShuArray[i];
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 连三张
		for (LiansanzhangDianShuZu liansanzhangDianShuZu : paiXing.getLiansanzhangDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(liansanzhangDianShuZu);
			DianShu[] lianXuDianShuArray = liansanzhangDianShuZu.getLianXuDianShuArray();
			DianShu[] dachuDianShuArray = new DianShu[3 * lianXuDianShuArray.length];
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				dachuDianShuArray[i * 3] = lianXuDianShuArray[i];
				dachuDianShuArray[i * 3 + 1] = lianXuDianShuArray[i];
				dachuDianShuArray[i * 3 + 2] = lianXuDianShuArray[i];
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 三张牌
		for (SanzhangDianShuZu sanzhangDianShuZu : paiXing.getSanzhangDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(sanzhangDianShuZu);
			DianShu[] dachuDianShuArray = { sanzhangDianShuZu.getDianShu(), sanzhangDianShuZu.getDianShu(),
					sanzhangDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 顺子
		for (ShunziDianShuZu shunziDianShuZu : paiXing.getShunziDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(shunziDianShuZu);
			DianShu[] lianXuDianShuArray = shunziDianShuZu.getLianXuDianShuArray();
			DianShu[] dachuDianShuArray = new DianShu[lianXuDianShuArray.length];
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				dachuDianShuArray[i] = lianXuDianShuArray[i];
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 炸弹
		for (DanGeZhadanDianShuZu zhadanDianShuZu : paiXing.getDanGeZhadanDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(zhadanDianShuZu);
			DianShu[] dachuDianShuArray = new DianShu[zhadanDianShuZu.getSize()];
			for (int i = 0; i < zhadanDianShuZu.getSize(); i++) {
				dachuDianShuArray[i] = zhadanDianShuZu.getDianShu();
			}
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 连续炸弹
		for (LianXuZhadanDianShuZu lianXuZhadanDianShuZu : paiXing.getLianXuZhadanDianShuZuList()) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(lianXuZhadanDianShuZu);
			List<DianShu> dachuDianShuList = new ArrayList<>();
			DianShu[] lianXuDianShuArray = lianXuZhadanDianShuZu.getLianXuDianShuArray();
			int[] dianshuZhangshuArray = lianXuZhadanDianShuZu.getLianXuDianShuSizeArray();
			for (int i = 0; i < lianXuDianShuArray.length; i++) {
				for (int j = 0; j < dianshuZhangshuArray[i]; j++) {
					dachuDianShuList.add(lianXuDianShuArray[i]);
				}
			}
			DianShu[] dachuDianShuArray = dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]);
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		return solutionList;
	}

	public static List<DaPaiDianShuSolution> calculateAllDaPaiDianShuSolutionWithWangDang(PaiXing paiXing,
			ShoupaiJiesuanPai[] wangDangPaiArray, int[] dianshuCountArray, BianXingWanFa bx) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		int[] dianshuCount = dianshuCountArray.clone();
		// 大小王做单张牌打出必定是作为本身的牌的点数
		// 对子
		for (DuiziDianShuZu duiziDianShuZu : paiXing.getDuiziDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu duiziDianShuType = duiziDianShuZu.getDianShu();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
				if (shoupaiJiesuanPai.getDangPaiType().equals(duiziDianShuType)) {
					keYongList.add(shoupaiJiesuanPai);
					dianshuCount[duiziDianShuType.ordinal()]--;
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList
						.addAll(generateSolutionForDuiziDianShuZu(zuheList, duiziDianShuZu, dianshuCount, keYongList));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(duiziDianShuZu);
				DianShu[] dachuDianShuArray = new DianShu[2];
				for (int i = 0; i < 2; i++) {
					dachuDianShuArray[i] = duiziDianShuZu.getDianShu();
				}
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 连对
		for (LianduiDianShuZu lianduiDianShuZu : paiXing.getLianduiDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu[] lianXuDianShuArray = lianduiDianShuZu.getLianXuDianShuArray();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (DianShu dianshu : lianXuDianShuArray) {
				for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
					if (shoupaiJiesuanPai.getDangPaiType().equals(dianshu)) {
						keYongList.add(shoupaiJiesuanPai);
						dianshuCount[dianshu.ordinal()]--;
					}
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList.addAll(
						generateSolutionForLianduiDianShuZu(zuheList, lianduiDianShuZu, dianshuCount, keYongList, bx));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(lianduiDianShuZu);
				DianShu[] dachuDianShuArray = new DianShu[lianduiDianShuZu.length() * 2];
				for (int i = 0; i < lianduiDianShuZu.length(); i++) {
					dachuDianShuArray[2 * i] = lianduiDianShuZu.getLianXuDianShuArray()[i];
					dachuDianShuArray[2 * i + 1] = lianduiDianShuZu.getLianXuDianShuArray()[i];
				}
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 连三张
		for (LiansanzhangDianShuZu liansanzhangDianShuZu : paiXing.getLiansanzhangDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu[] lianXuDianShuArray = liansanzhangDianShuZu.getLianXuDianShuArray();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (DianShu dianshu : lianXuDianShuArray) {
				for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
					if (shoupaiJiesuanPai.getDangPaiType().equals(dianshu)) {
						keYongList.add(shoupaiJiesuanPai);
						dianshuCount[dianshu.ordinal()]--;
					}
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList.addAll(generateSolutionForLiansanzhangDianShuZu(zuheList, liansanzhangDianShuZu,
						dianshuCount, keYongList, bx));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(liansanzhangDianShuZu);
				DianShu[] dachuDianShuArray = new DianShu[liansanzhangDianShuZu.length() * 3];
				for (int i = 0; i < liansanzhangDianShuZu.length(); i++) {
					dachuDianShuArray[3 * i] = liansanzhangDianShuZu.getLianXuDianShuArray()[i];
					dachuDianShuArray[3 * i + 1] = liansanzhangDianShuZu.getLianXuDianShuArray()[i];
					dachuDianShuArray[3 * i + 2] = liansanzhangDianShuZu.getLianXuDianShuArray()[i];
				}
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 三张牌
		for (SanzhangDianShuZu sanzhangDianShuZu : paiXing.getSanzhangDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu duiziDianShuType = sanzhangDianShuZu.getDianShu();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
				if (shoupaiJiesuanPai.getDangPaiType().equals(duiziDianShuType)) {
					keYongList.add(shoupaiJiesuanPai);
					dianshuCount[duiziDianShuType.ordinal()]--;
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList.addAll(
						generateSolutionForSanzhangDianShuZu(zuheList, sanzhangDianShuZu, dianshuCount, keYongList));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(sanzhangDianShuZu);
				DianShu[] dachuDianShuArray = new DianShu[3];
				for (int i = 0; i < 3; i++) {
					dachuDianShuArray[i] = sanzhangDianShuZu.getDianShu();
				}
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 顺子
		for (ShunziDianShuZu shunziDianShuZu : paiXing.getShunziDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu[] lianXuDianShuArray = shunziDianShuZu.getLianXuDianShuArray();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (DianShu dianshu : lianXuDianShuArray) {
				for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
					if (shoupaiJiesuanPai.getDangPaiType().equals(dianshu)) {
						keYongList.add(shoupaiJiesuanPai);
						dianshuCount[dianshu.ordinal()]--;
					}
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList.addAll(
						generateSolutionForShunziDianShuZu(zuheList, shunziDianShuZu, dianshuCount, keYongList, bx));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(shunziDianShuZu);
				DianShu[] dachuDianShuArray = new DianShu[shunziDianShuZu.length()];
				for (int i = 0; i < shunziDianShuZu.length(); i++) {
					dachuDianShuArray[i] = shunziDianShuZu.getLianXuDianShuArray()[i];
				}
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 炸弹
		for (DanGeZhadanDianShuZu zhadanDianShuZu : paiXing.getDanGeZhadanDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu duiziDianShuType = zhadanDianShuZu.getDianShu();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
				if (shoupaiJiesuanPai.getDangPaiType().equals(duiziDianShuType)) {
					keYongList.add(shoupaiJiesuanPai);
					dianshuCount[duiziDianShuType.ordinal()]--;
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList.addAll(
						generateSolutionForZhadanDianShuZu(zuheList, zhadanDianShuZu, dianshuCount, keYongList));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(zhadanDianShuZu);
				DianShu[] dachuDianShuArray = new DianShu[zhadanDianShuZu.getSize()];
				for (int i = 0; i < zhadanDianShuZu.getSize(); i++) {
					dachuDianShuArray[i] = zhadanDianShuZu.getDianShu();
				}
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 连续炸弹
		for (LianXuZhadanDianShuZu lianXuZhadanDianShuZu : paiXing.getLianXuZhadanDianShuZuList()) {
			dianshuCount = dianshuCountArray.clone();
			DianShu[] lianXuDianShuArray = lianXuZhadanDianShuZu.getLianXuDianShuArray();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (DianShu dianshu : lianXuDianShuArray) {
				for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
					if (shoupaiJiesuanPai.getDangPaiType().equals(dianshu)) {
						keYongList.add(shoupaiJiesuanPai);
						dianshuCount[dianshu.ordinal()]--;
					}
				}
			}
			if (keYongList.size() > 0) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				solutionList.addAll(generateSolutionForLianXuZhadanDianShuZu(zuheList, lianXuZhadanDianShuZu,
						dianshuCount, keYongList));
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(lianXuZhadanDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				int[] dianshuZhangshuArray = lianXuZhadanDianShuZu.getLianXuDianShuSizeArray();
				for (int i = 0; i < lianXuDianShuArray.length; i++) {
					for (int j = 0; j < dianshuZhangshuArray[i]; j++) {
						dachuDianShuList.add(lianXuDianShuArray[i]);
					}
				}
				DianShu[] dachuDianShuArray = dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]);
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	/**
	 * 计算是否使用当牌的组合
	 */
	private static List<int[]> calculateZuHeWithJiSuanPaiDang(int dangCount) {
		List<int[]> zuheList = new ArrayList<>();
		int maxZuheCode = (int) Math.pow(2, dangCount);// 二进制0代表不用，1代表用
		int[] modArray = new int[dangCount];
		for (int m = 0; m < dangCount; m++) {
			modArray[m] = (int) Math.pow(2, dangCount - 1 - m);
		}
		for (int zuheCode = 0; zuheCode < maxZuheCode; zuheCode++) {
			int[] zuheArray = new int[dangCount];
			int temp = zuheCode;
			for (int n = 0; n < dangCount; n++) {
				int mod = modArray[n];
				int shang = temp / mod;
				int yu = temp % mod;
				zuheArray[n] = shang;
				temp = yu;
			}
			zuheList.add(zuheArray);
		}
		return zuheList;
	}

	private static boolean verifyZuHeForDuiziDianShuZu(int[] zuhe, DuiziDianShuZu duiziDianShuZu, int[] dianshuCount,
			List<ShoupaiJiesuanPai> keYongList) {
		int num = 0;
		for (int i = 0; i < zuhe.length; i++) {
			num += zuhe[i];
		}
		num += dianshuCount[duiziDianShuZu.getDianShu().ordinal()];
		return num >= 2;
	}

	private static List<DaPaiDianShuSolution> generateSolutionForDuiziDianShuZu(List<int[]> zuheList,
			DuiziDianShuZu duiziDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			// 验证组合是否合法
			if (verifyZuHeForDuiziDianShuZu(zuhe, duiziDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(duiziDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				int k = 0;
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1) {
						ShoupaiJiesuanPai pai = keYongList.get(i);
						dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
						k++;
					}
				}
				// 不能全都是王当的牌
				if (k >= 2) {
					continue;
				}
				while (k < 2) {
					dachuDianShuList.add(duiziDianShuZu.getDianShu());
					k++;
				}
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	private static boolean verifyZuHeForSanzhangDianShuZu(int[] zuhe, SanzhangDianShuZu sanzhangDianShuZu,
			int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		int num = 0;
		for (int i = 0; i < zuhe.length; i++) {
			num += zuhe[i];
		}
		num += dianshuCount[sanzhangDianShuZu.getDianShu().ordinal()];
		return num >= 3;
	}

	private static List<DaPaiDianShuSolution> generateSolutionForSanzhangDianShuZu(List<int[]> zuheList,
			SanzhangDianShuZu sanzhangDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			// 验证组合是否合法
			if (verifyZuHeForSanzhangDianShuZu(zuhe, sanzhangDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(sanzhangDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				int k = 0;
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1) {
						ShoupaiJiesuanPai pai = keYongList.get(i);
						dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
						k++;
					}
				}
				// 不能全都是王当的牌
				if (k >= 3) {
					continue;
				}
				while (k < 3) {
					dachuDianShuList.add(sanzhangDianShuZu.getDianShu());
					k++;
				}
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	private static boolean verifyZuHeForZhadanDianShuZu(int[] zuhe, DanGeZhadanDianShuZu zhadanDianShuZu,
			int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		int num = 0;
		for (int i = 0; i < zuhe.length; i++) {
			num += zuhe[i];
		}
		num += dianshuCount[zhadanDianShuZu.getDianShu().ordinal()];
		return num >= zhadanDianShuZu.getSize();
	}

	private static List<DaPaiDianShuSolution> generateSolutionForZhadanDianShuZu(List<int[]> zuheList,
			DanGeZhadanDianShuZu zhadanDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			// 验证组合是否合法
			if (verifyZuHeForZhadanDianShuZu(zuhe, zhadanDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(zhadanDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				int k = 0;
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1) {
						ShoupaiJiesuanPai pai = keYongList.get(i);
						dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
						k++;
					}
				}
				// 不能全都是王当的牌
				if (k >= zhadanDianShuZu.getSize()) {
					continue;
				}
				while (k < zhadanDianShuZu.getSize()) {
					dachuDianShuList.add(zhadanDianShuZu.getDianShu());
					k++;
				}
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	private static boolean verifyZuHeForShunziDianShuZu(int[] zuhe, ShunziDianShuZu shunziDianShuZu, int[] dianshuCount,
			List<ShoupaiJiesuanPai> keYongList) {
		boolean allSuccess = true;
		for (DianShu dianshu : shunziDianShuZu.getLianXuDianShuArray()) {
			// 验证组合是否合法
			int num = 0;
			for (int i = 0; i < zuhe.length; i++) {
				if (zuhe[i] == 1 && dianshu.equals(keYongList.get(i).getDangPaiType())) {
					num++;
				}
			}
			num += dianshuCount[dianshu.ordinal()];
			if (num < 1) {
				allSuccess = false;
			}
		}
		return allSuccess;
	}

	private static List<DaPaiDianShuSolution> generateSolutionForShunziDianShuZu(List<int[]> zuheList,
			ShunziDianShuZu shunziDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList, BianXingWanFa bx) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			if (verifyZuHeForShunziDianShuZu(zuhe, shunziDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(shunziDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				Map<DianShu, Integer> dianshuCountMap = new HashMap<>();
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1) {
						ShoupaiJiesuanPai pai = keYongList.get(i);
						dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
						Integer count = dianshuCountMap.get(pai.getDangPaiType());
						if (count == null) {
							dianshuCountMap.put(pai.getDangPaiType(), 1);
						}
					}
				}
				int danzhang = 0;// 如果只有一个单张的顺子，说明其余四张由王代替，这种情况不能作为顺子
				for (DianShu dianshu : shunziDianShuZu.getLianXuDianShuArray()) {
					Integer count = dianshuCountMap.get(dianshu);
					if (count == null) {
						dachuDianShuList.add(dianshu);
						danzhang++;
					}
				}
				dachuDianShuList = dianshuSort(dachuDianShuList, shunziDianShuZu.getLianXuDianShuArray(), 1, bx);
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				if (danzhang > 1) {
					solutionList.add(solution);
				}
			}
		}
		return solutionList;
	}

	private static boolean verifyZuHeForLianduiDianShuZu(int[] zuhe, LianduiDianShuZu lianduiDianShuZu,
			int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		boolean allSuccess = true;
		for (DianShu dianshu : lianduiDianShuZu.getLianXuDianShuArray()) {
			// 验证组合是否合法
			int num = 0;
			for (int i = 0; i < zuhe.length; i++) {
				if (zuhe[i] == 1 && dianshu.equals(keYongList.get(i).getDangPaiType())) {
					num++;
				}
			}
			num += dianshuCount[dianshu.ordinal()];
			if (num < 2) {
				allSuccess = false;
			}
		}
		return allSuccess;
	}

	private static List<DaPaiDianShuSolution> generateSolutionForLianduiDianShuZu(List<int[]> zuheList,
			LianduiDianShuZu lianduiDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList,
			BianXingWanFa bx) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			if (verifyZuHeForLianduiDianShuZu(zuhe, lianduiDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(lianduiDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				Map<DianShu, Integer> dianshuCountMap = new HashMap<>();
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1) {
						ShoupaiJiesuanPai pai = keYongList.get(i);
						dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
						Integer count = dianshuCountMap.get(pai.getDangPaiType());
						if (count == null) {
							dianshuCountMap.put(pai.getDangPaiType(), 1);
						} else {
							dianshuCountMap.put(pai.getDangPaiType(), count + 1);
						}
					}
				}
				for (DianShu dianshu : lianduiDianShuZu.getLianXuDianShuArray()) {
					Integer count = dianshuCountMap.get(dianshu);
					if (count == null) {
						dachuDianShuList.add(dianshu);
						dachuDianShuList.add(dianshu);
					} else if (count == 1) {
						dachuDianShuList.add(dianshu);
					}
				}
				dachuDianShuList = dianshuSort(dachuDianShuList, lianduiDianShuZu.getLianXuDianShuArray(), 2, bx);
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	private static boolean verifyZuHeForLiansanzhangDianShuZu(int[] zuhe, LiansanzhangDianShuZu liansanzhangDianShuZu,
			int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		boolean allSuccess = true;
		for (DianShu dianshu : liansanzhangDianShuZu.getLianXuDianShuArray()) {
			// 验证组合是否合法
			int num = 0;
			for (int i = 0; i < zuhe.length; i++) {
				if (zuhe[i] == 1 && dianshu.equals(keYongList.get(i).getDangPaiType())) {
					num++;
				}
			}
			num += dianshuCount[dianshu.ordinal()];
			if (num < 3) {
				allSuccess = false;
			}
		}
		return allSuccess;
	}

	private static List<DaPaiDianShuSolution> generateSolutionForLiansanzhangDianShuZu(List<int[]> zuheList,
			LiansanzhangDianShuZu liansanzhangDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList,
			BianXingWanFa bx) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			if (verifyZuHeForLiansanzhangDianShuZu(zuhe, liansanzhangDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(liansanzhangDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				Map<DianShu, Integer> dianshuCountMap = new HashMap<>();
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1) {
						ShoupaiJiesuanPai pai = keYongList.get(i);
						dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
						Integer count = dianshuCountMap.get(pai.getDangPaiType());
						if (count == null) {
							dianshuCountMap.put(pai.getDangPaiType(), 1);
						} else {
							dianshuCountMap.put(pai.getDangPaiType(), count + 1);
						}
					}
				}
				for (DianShu dianshu : liansanzhangDianShuZu.getLianXuDianShuArray()) {
					Integer count = dianshuCountMap.get(dianshu);
					if (count == null) {
						dachuDianShuList.add(dianshu);
						dachuDianShuList.add(dianshu);
						dachuDianShuList.add(dianshu);
					} else if (count == 1) {
						dachuDianShuList.add(dianshu);
						dachuDianShuList.add(dianshu);
					} else if (count == 2) {
						dachuDianShuList.add(dianshu);
					}
				}
				dachuDianShuList = dianshuSort(dachuDianShuList, liansanzhangDianShuZu.getLianXuDianShuArray(), 3, bx);
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	private static boolean verifyZuHeForLianXuZhadanDianShuZu(int[] zuhe, LianXuZhadanDianShuZu lianXuZhadanDianShuZu,
			int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		boolean allSuccess = true;
		int k = 0;
		for (DianShu dianshu : lianXuZhadanDianShuZu.getLianXuDianShuArray()) {
			// 验证组合是否合法
			if (dianshu.ordinal() < 13) {
				int num = 0;
				for (int i = 0; i < zuhe.length; i++) {
					if (zuhe[i] == 1 && dianshu.equals(keYongList.get(i).getDangPaiType())) {
						num++;
					}
				}
				num += dianshuCount[dianshu.ordinal()];
				if (num < lianXuZhadanDianShuZu.getLianXuDianShuSizeArray()[k]) {
					allSuccess = false;
				}
			} else {
				allSuccess = false;
			}
			k++;
		}
		return allSuccess;
	}

	private static List<DaPaiDianShuSolution> generateSolutionForLianXuZhadanDianShuZu(List<int[]> zuheList,
			LianXuZhadanDianShuZu lianXuZhadanDianShuZu, int[] dianshuCount, List<ShoupaiJiesuanPai> keYongList) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		for (int[] zuhe : zuheList) {
			if (verifyZuHeForLianXuZhadanDianShuZu(zuhe, lianXuZhadanDianShuZu, dianshuCount, keYongList)) {// 合法
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(lianXuZhadanDianShuZu);
				List<DianShu> dachuDianShuList = new ArrayList<>();
				Map<DianShu, Integer> dianshuCountMap = new HashMap<>();
				DianShu[] lianXuDianShuArray = lianXuZhadanDianShuZu.getLianXuDianShuArray();
				int[] lianXuDianShuSizeArray = lianXuZhadanDianShuZu.getLianXuDianShuSizeArray();
				for (DianShu dianshu : lianXuDianShuArray) {
					for (int i = 0; i < zuhe.length; i++) {
						if (zuhe[i] == 1 && dianshu.equals(keYongList.get(i).getDangPaiType())) {
							ShoupaiJiesuanPai pai = keYongList.get(i);
							dachuDianShuList.addAll(Arrays.asList(pai.getAllYuanPai()));
							Integer count = dianshuCountMap.get(pai.getDangPaiType());
							if (count == null) {
								dianshuCountMap.put(pai.getDangPaiType(), 1);
							} else {
								dianshuCountMap.put(pai.getDangPaiType(), count + 1);
							}
						}
					}
				}
				for (int i = 0; i < lianXuDianShuSizeArray.length; i++) {
					int size = lianXuDianShuSizeArray[i];
					if (i > lianXuDianShuArray.length - 1) {
						break;
					}
					DianShu dianshu = lianXuDianShuArray[i];
					Integer count = dianshuCountMap.get(dianshu);
					if (count == null) {
						count = 0;
					}
					for (int k = 0; k < size - count; k++) {
						dachuDianShuList.add(dianshu);
					}
				}
				solution.setDachuDianShuArray(dachuDianShuList.toArray(new DianShu[dachuDianShuList.size()]));
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		return solutionList;
	}

	private static void calcuateLianXuZhadanDianShuZu(DianShu[] lianXuDianShuArray, int[] dianshuZhangshuArray,
			List<LianXuZhadanDianShuZu> lianXuZhadanList, int n) {
		for (int m = 4; m <= dianshuZhangshuArray[n]; m++) {
			int[] dianshuZhangshuArray1 = dianshuZhangshuArray.clone();
			dianshuZhangshuArray1[n] = m;
			calcuateLianXuZhadanDianShuZu(lianXuDianShuArray, dianshuZhangshuArray1, lianXuZhadanList, n + 1);
		}
		if (n >= lianXuDianShuArray.length) {
			LianXuZhadanDianShuZu lianXuZhadan = new LianXuZhadanDianShuZu(lianXuDianShuArray,
					dianshuZhangshuArray.clone());
			lianXuZhadanList.add(lianXuZhadan);
		}
	}

	private static List<LianXuZhadanDianShuZu> generateAllLianXuZhadanDianShuZu(int[] dianShuAmountArray) {
		List<LianXuZhadanDianShuZu> lianXuZhadanList = new ArrayList<>();
		for (int i = 0; i < dianShuAmountArray.length; i++) {
			int[] dianshuZhangshuArray = new int[8];// 每种点数可能有多少张牌，最多8连
			int lianXuZhadanLianXuCount = 0;
			int j = i;
			while (j < 19 && dianShuAmountArray[j % 13] >= 4) {// 任意4张或者4张以上点数相连的牌，3起最小，到2
				lianXuZhadanLianXuCount++;
				j++;
			}
			if (lianXuZhadanLianXuCount >= 3) {
				for (int size = 3; size <= lianXuZhadanLianXuCount; size++) {
					DianShu[] lianXuDianShuArray = new DianShu[size];
					for (int k = 0; k < size; k++) {
						dianshuZhangshuArray[k] = dianShuAmountArray[(i + k) % 13];
						lianXuDianShuArray[k] = DianShu.getDianShuByOrdinal((i + k) % 13);
					}
					calcuateLianXuZhadanDianShuZu(lianXuDianShuArray, dianshuZhangshuArray.clone(), lianXuZhadanList,
							0);
				}
			}
		}
		return lianXuZhadanList;
	}

	/**
	 * 顺子、连对、连三张的排序
	 */
	private static List<DianShu> dianshuSort(List<DianShu> dachuDianShuList, DianShu[] lianXuDianShuArray, int size,
			BianXingWanFa bx) {
		LinkedList<DianShu> finalDianShuList = new LinkedList<>();
		LinkedList<DianShu> xiaowangDianShuList = new LinkedList<>();
		LinkedList<DianShu> dawangDianShuList = new LinkedList<>();

		for (int i = 0; i < dachuDianShuList.size(); i++) {
			DianShu dianshu = dachuDianShuList.get(i);
			if (dianshu.equals(DianShu.xiaowang)) {
				xiaowangDianShuList.add(dianshu);
				dachuDianShuList.remove(i);
				i--;
			} else if (dianshu.equals(DianShu.dawang)) {
				dawangDianShuList.add(dianshu);
				dachuDianShuList.remove(i);
				i--;
			}
		}
		for (DianShu dianshu : lianXuDianShuArray) {
			for (int i = 0; i < size; i++) {
				if (dachuDianShuList.isEmpty()) {
					finalDianShuList.addAll(xiaowangDianShuList);
					finalDianShuList.addAll(dawangDianShuList);
					return finalDianShuList;
				} else {
					boolean has = false;
					for (int j = 0; j < dachuDianShuList.size(); j++) {
						DianShu dachuDainshu = dachuDianShuList.get(j);
						if (dachuDainshu.equals(dianshu)) {
							finalDianShuList.add(dachuDainshu);
							dachuDianShuList.remove(j);
							has = true;
							break;
						}
					}
					if (!has) {
						if (bx.equals(BianXingWanFa.qianbian)) {
							if (!xiaowangDianShuList.isEmpty()) {
								finalDianShuList.add(xiaowangDianShuList.removeFirst());
							} else {
								finalDianShuList.add(dawangDianShuList.removeFirst());
							}
						} else if (bx.equals(BianXingWanFa.banqianbian)) {
							if (!xiaowangDianShuList.isEmpty()) {
								finalDianShuList.add(xiaowangDianShuList.removeFirst());
								finalDianShuList.add(xiaowangDianShuList.removeFirst());
							} else {
								finalDianShuList.add(dawangDianShuList.removeFirst());
							}
						} else if (bx.equals(BianXingWanFa.baibian)) {
							finalDianShuList.add(dawangDianShuList.removeFirst());
						}
					}
				}
			}
		}
		return finalDianShuList;
	}
}
