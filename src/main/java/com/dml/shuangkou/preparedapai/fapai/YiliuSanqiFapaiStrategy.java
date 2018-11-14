package com.dml.shuangkou.preparedapai.fapai;

import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.player.ShuangkouPlayer;

/**
 * 6*1+7*3发牌策略
 * 
 * @author lsc
 *
 */
public class YiliuSanqiFapaiStrategy implements FapaiStrategy {

	@Override
	public void fapai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<PukePai> avaliablePaiList = currentPan.getAvaliablePaiList();
		Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap = currentPan
				.getShuangkouPlayerIdMajiangPlayerMap();
		fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 1, 6);
		fapai(avaliablePaiList, shuangkouPlayerIdMajiangPlayerMap, 3, 7);
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
