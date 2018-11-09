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

	/**
	 * 牌张数，点数序号 二维索引
	 */
	private static int[][] idxArray = new int[][] { { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47 },
			{ 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113 },
			{ 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191 },
			{ 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263 },
			{ 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347 },
			{ 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421 },
			{ 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499 },
			{ 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593 } };

	private DianShuZu dianShuZu;
	private DianShu[] dachuDianShuArray;
	private String dianshuZuheIdx;

	public void calculateDianshuZuheIdx() {
		int[] dianshuCountArray = new int[15];
		for (int i = 0; i < dachuDianShuArray.length; i++) {
			DianShu dianShu = dachuDianShuArray[i];
			dianshuCountArray[dianShu.ordinal()]++;
		}
		long l = 1;
		BigInteger bi = null;
		for (int i = 0; i < 15; i++) {
			int dianshuCount = dianshuCountArray[i];
			if (dianshuCount > 0) {
				int idx = idxArray[dianshuCount - 1][i];
				if (bi == null) {
					long nl = l * idx;
					if (nl < l) {// 越乘越小说明爆掉了
						bi = BigInteger.valueOf(l).multiply(BigInteger.valueOf(idx));
					} else {
						l = nl;
					}
				} else {
					bi = bi.multiply(BigInteger.valueOf(idx));
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
