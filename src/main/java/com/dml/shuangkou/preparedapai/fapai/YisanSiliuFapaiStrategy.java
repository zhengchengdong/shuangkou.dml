package com.dml.shuangkou.preparedapai.fapai;

import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.player.ShuangkouPlayer;

/**
 * 3*1+6*4发牌策略
 * 
 * @author lsc
 *
 */
public class YisanSiliuFapaiStrategy implements FapaiStrategy {

	@Override
	public void fapai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<PukePai> avaliablePaiList = currentPan.getAvaliablePaiList();
		Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap = currentPan
				.getShuangkouPlayerIdMajiangPlayerMap();
		fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 1, 3);
		fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 4, 6);
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
}
