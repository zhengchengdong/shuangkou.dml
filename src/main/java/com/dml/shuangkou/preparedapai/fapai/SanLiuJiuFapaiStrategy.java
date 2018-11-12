package com.dml.shuangkou.preparedapai.fapai;

import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.player.ShuangkouPlayer;

/**
 * 常见的发牌策略 3 3/6 6/7 9 （3=3X9，3/6=3X1/6X4，6/7=6X1/7X3，9=9X3）
 * 
 * @author lsc
 *
 */
public class SanLiuJiuFapaiStrategy implements FapaiStrategy {

	private FaPai fapai;

	@Override
	public void fapai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<PukePai> avaliablePaiList = currentPan.getAvaliablePaiList();
		Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap = currentPan
				.getShuangkouPlayerIdMajiangPlayerMap();
		if (FaPai.san.equals(fapai)) {// 3X9
			fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 9, 3);
		}
		if (FaPai.sanliu.equals(fapai)) {// 3X1+6X4
			fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 1, 3);
			fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 4, 6);
		}
		if (FaPai.liuqi.equals(fapai)) {// 6X1+7X3
			fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 1, 6);
			fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 3, 7);
		}
		if (FaPai.jiu.equals(fapai)) {// 9X3
			fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 3, 9);
		}
	}

	public void fapai(List<PukePai> avaliablePaiList, Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap,
			int m, int n) {
		for (int i = 0; i < m; i++) {
			for (ShuangkouPlayer player : shuangkouPlayerIdMajiangPlayerMap.values()) {
				for (int j = 0; j < n; j++) {
					PukePai pukePai = avaliablePaiList.remove(0);
					player.addShouPai(pukePai);
				}
			}
		}
	}

	public FaPai getFapai() {
		return fapai;
	}

	public void setFapai(FaPai fapai) {
		this.fapai = fapai;
	}

}
