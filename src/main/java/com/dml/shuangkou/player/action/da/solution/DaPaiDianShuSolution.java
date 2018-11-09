package com.dml.shuangkou.player.action.da.solution;

import java.math.BigInteger;

import com.dml.puke.pai.DianShu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DianShuZu;

/**
 * 打牌点数方案
 * 
 * @author Neo
 *
 */
public class DaPaiDianShuSolution {
	private DianShuZu dianShuZu;
	private DianShu[] dachuDianShuArray;
	private String dianshuZuheIdx;

	public void calculateDianshuZuheIdx() {
		int[] dianshuIdxSumArray = new int[15];
		for (int i = 0; i < dachuDianShuArray.length; i++) {
			DianShu dianShu = dachuDianShuArray[i];
			dianshuIdxSumArray[dianShu.ordinal()] += dianShu.idx();
		}
		long l = 1;
		BigInteger bi = null;
		for (int i = 0; i < 15; i++) {
			int dianshuIdxSum = dianshuIdxSumArray[i];
			if (dianshuIdxSum > 0) {
				if (bi == null) {
					long nl = l * dianshuIdxSum;
					if (nl < l) {// 越乘越小说明爆掉了
						bi = BigInteger.valueOf(l).multiply(BigInteger.valueOf(dianshuIdxSum));
					} else {
						l = nl;
					}
				} else {
					bi = bi.multiply(BigInteger.valueOf(dianshuIdxSum));
				}
			}
		}
		if (bi == null) {
			dianshuZuheIdx = String.valueOf(l);
		} else {
			dianshuZuheIdx = bi.toString();
		}
	}

	public DianShuZu getDianShuZu() {
		return dianShuZu;
	}

	public void setDianShuZu(DianShuZu dianShuZu) {
		this.dianShuZu = dianShuZu;
	}

	public DianShu[] getDachuDianShuArray() {
		return dachuDianShuArray;
	}

	public void setDachuDianShuArray(DianShu[] dachuDianShuArray) {
		this.dachuDianShuArray = dachuDianShuArray;
	}

	public String getDianshuZuheIdx() {
		return dianshuZuheIdx;
	}

	public void setDianshuZuheIdx(String dianshuZuheIdx) {
		this.dianshuZuheIdx = dianshuZuheIdx;
	}

}
