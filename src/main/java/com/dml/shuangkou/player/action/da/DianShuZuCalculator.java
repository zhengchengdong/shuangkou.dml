package com.dml.shuangkou.player.action.da;

import java.util.ArrayList;
import java.util.List;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZuGenerator;
import com.dml.puke.wanfa.dianshu.dianshuzu.DuiziDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LianduiDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LiansanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.SanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.ShunziDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.ZhadanDianShuZu;
import com.dml.shuangkou.player.action.da.solution.DaPaiDianShuSolution;
import com.dml.shuangkou.preparedapai.luanpai.BianXingWanFa;

/**
 * 通过王牌变化得到不同点数组，再通过点数组和王牌的代法产生打牌方案
 * 
 * @author lsc
 *
 */
public class DianShuZuCalculator {
	private static List<DuiziDianShuZu> duiziDianShuZuList;
	private static List<LianduiDianShuZu> lianduiDianShuZuList;
	private static List<LiansanzhangDianShuZu> liansanzhangDianShuZuList;
	private static List<SanzhangDianShuZu> sanzhangDianShuZuList;
	private static List<ShunziDianShuZu> shunziDianShuZuList;
	private static List<ZhadanDianShuZu> zhadanDianShuZuList;

	/**
	 * 在千变玩法下 根据王的当法还原打牌点数
	 */
	private static List<DaPaiDianShuSolution> calculateDaPaiDianShuSolutionForDianShuZuWithQianbian(
			ShoupaiJiesuanPai[] wangDangPaiArray, int[] dianshuCountArray) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 大小王做单张牌打出必定是作为本身的牌的点数
		// 对子
		for (DuiziDianShuZu duiziDianShuZu : duiziDianShuZuList) {
			DianShu duiziDianShuType = duiziDianShuZu.getDianShu();
			List<ShoupaiJiesuanPai> keYongList = new ArrayList<>();
			for (ShoupaiJiesuanPai shoupaiJiesuanPai : wangDangPaiArray) {
				if (shoupaiJiesuanPai.getDangPaiType().equals(duiziDianShuType)) {
					keYongList.add(shoupaiJiesuanPai);
				}
			}
			int detal = dianshuCountArray[duiziDianShuType.ordinal()] - keYongList.size();
			if (detal >= 2) {
				List<int[]> zuheList = calculateZuHeWithJiSuanPaiDang(keYongList.size());
				for (int[] zuhe : zuheList) {

				}
			} else if (detal == 1) {
				for (ShoupaiJiesuanPai jiesuanPai : keYongList) {
					DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
					solution.setDianShuZu(duiziDianShuZu);
					DianShu[] allYuanPai = jiesuanPai.getAllYuanPai();
					DianShu[] dachuDianShuArray = new DianShu[allYuanPai.length + 1];
					for (int i = 0; i < allYuanPai.length; i++) {
						dachuDianShuArray[i] = allYuanPai[i];
					}
					dachuDianShuArray[allYuanPai.length] = duiziDianShuZu.getDianShu();
					solution.setDachuDianShuArray(dachuDianShuArray);
					solution.calculateDianshuZuheIdx();
					solutionList.add(solution);
				}
			} else {
				DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
				solution.setDianShuZu(duiziDianShuZu);
				DianShu[] dachuDianShuArray = { duiziDianShuZu.getDianShu(), duiziDianShuZu.getDianShu() };
				solution.setDachuDianShuArray(dachuDianShuArray);
				solution.calculateDianshuZuheIdx();
				solutionList.add(solution);
			}
		}
		// 连对
		for (LianduiDianShuZu lianduiDianShuZu : lianduiDianShuZuList) {
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
		for (LiansanzhangDianShuZu liansanzhangDianShuZu : liansanzhangDianShuZuList) {
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
		for (SanzhangDianShuZu sanzhangDianShuZu : sanzhangDianShuZuList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(sanzhangDianShuZu);
			DianShu[] dachuDianShuArray = { sanzhangDianShuZu.getDianShu(), sanzhangDianShuZu.getDianShu(),
					sanzhangDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 顺子
		for (ShunziDianShuZu shunziDianShuZu : shunziDianShuZuList) {
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
		for (ZhadanDianShuZu zhadanDianShuZu : zhadanDianShuZuList) {
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
		return solutionList;
	}

	/**
	 * 在半千变玩法下 根据王的当法还原打牌点数
	 */
	private static List<DaPaiDianShuSolution> calculateDaPaiDianShuSolutionForDianShuZuWithBanQianbian(
			ShoupaiJiesuanPai[] wangDangPaiArray, int[] dianshuCountArray) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();

		return solutionList;
	}

	/**
	 * 在百变玩法下 根据王的当法还原打牌点数
	 */
	private static List<DaPaiDianShuSolution> calculateDaPaiDianShuSolutionForDianShuZuWithBaibian(
			ShoupaiJiesuanPai[] wangDangPaiArray, int[] dianshuCountArray) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();

		return solutionList;
	}

	/**
	 * 某种当法下如果有余牌，计算是否使用当牌的组合
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

	/**
	 * 在某种王的当法下计算出可打的点数组
	 */
	public static List<DaPaiDianShuSolution> calculateAllDaPaiDianShuSolutionWithWangDang(BianXingWanFa bx,
			int[] dianshuCountArray, ShoupaiJiesuanPai[] wangDangPaiArray) {
		// 对子
		duiziDianShuZuList = DianShuZuGenerator.generateAllDuiziDianShuZu(dianshuCountArray);
		// 连对
		lianduiDianShuZuList = new ArrayList<>();
		for (int k = 3; k < 14; k++) {// 最小3连，最大13连
			lianduiDianShuZuList.addAll(DianShuZuGenerator.generateAllLianduiDianShuZu(dianshuCountArray, k));
		}
		// 连三张
		liansanzhangDianShuZuList = new ArrayList<>();
		for (int k = 3; k < 10; k++) {// 最小3连，最大9连
			liansanzhangDianShuZuList.addAll(DianShuZuGenerator.generateAllLiansanzhangDianShuZu(dianshuCountArray, k));
		}
		// 三张牌
		sanzhangDianShuZuList = DianShuZuGenerator.generateAllSanzhangDianShuZu(dianshuCountArray);
		// 顺子
		shunziDianShuZuList = new ArrayList<>();
		for (int k = 5; k < 14; k++) {// 最小5连，最大13连
			shunziDianShuZuList.addAll(DianShuZuGenerator.generateAllShunziDianShuZu(dianshuCountArray, k));
		}
		// 炸弹
		zhadanDianShuZuList = DianShuZuGenerator.generateAllZhadanDianShuZu(dianshuCountArray);
		return calculateDaPaiDianShuSolutionForDianShuZuWithQianbian(wangDangPaiArray, dianshuCountArray);
	}

	/**
	 * 没有王的情况下生成打牌方案
	 */
	public static List<DaPaiDianShuSolution> calculateAllDaPaiDianShuSolutionWithoutWangDang(int[] dianshuCountArray) {
		List<DaPaiDianShuSolution> solutionList = new ArrayList<>();
		// 对子
		duiziDianShuZuList = DianShuZuGenerator.generateAllDuiziDianShuZu(dianshuCountArray);
		for (DuiziDianShuZu duiziDianShuZu : duiziDianShuZuList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(duiziDianShuZu);
			DianShu[] dachuDianShuArray = { duiziDianShuZu.getDianShu(), duiziDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 连对
		for (int k = 3; k < 14; k++) {// 最小3连，最大13连
			lianduiDianShuZuList = DianShuZuGenerator.generateAllLianduiDianShuZu(dianshuCountArray, k);
			for (LianduiDianShuZu lianduiDianShuZu : lianduiDianShuZuList) {
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
		}
		// 连三张
		for (int k = 3; k < 10; k++) {// 最小3连，最大9连
			liansanzhangDianShuZuList = DianShuZuGenerator.generateAllLiansanzhangDianShuZu(dianshuCountArray, k);
			for (LiansanzhangDianShuZu liansanzhangDianShuZu : liansanzhangDianShuZuList) {
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
		}
		// 三张牌
		sanzhangDianShuZuList = DianShuZuGenerator.generateAllSanzhangDianShuZu(dianshuCountArray);
		for (SanzhangDianShuZu sanzhangDianShuZu : sanzhangDianShuZuList) {
			DaPaiDianShuSolution solution = new DaPaiDianShuSolution();
			solution.setDianShuZu(sanzhangDianShuZu);
			DianShu[] dachuDianShuArray = { sanzhangDianShuZu.getDianShu(), sanzhangDianShuZu.getDianShu(),
					sanzhangDianShuZu.getDianShu() };
			solution.setDachuDianShuArray(dachuDianShuArray);
			solution.calculateDianshuZuheIdx();
			solutionList.add(solution);
		}
		// 顺子
		for (int k = 5; k < 14; k++) {// 最小5连，最大13连
			shunziDianShuZuList = DianShuZuGenerator.generateAllShunziDianShuZu(dianshuCountArray, k);
			for (ShunziDianShuZu shunziDianShuZu : shunziDianShuZuList) {
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
		}
		// 炸弹
		zhadanDianShuZuList = DianShuZuGenerator.generateAllZhadanDianShuZu(dianshuCountArray);
		for (ZhadanDianShuZu zhadanDianShuZu : zhadanDianShuZuList) {
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
		return solutionList;
	}

}
