package com.dml.shuangkou.preparedapai.fapai;

import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.player.ShuangkouPlayer;

/**
 * 每人每次发9张
 * 
 * @author lsc
 *
 */
public class YiciJiuzhangFapaiStrategy implements FapaiStrategy {

	@Override
	public void fapai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<PukePai> avaliablePaiList = currentPan.getAvaliablePaiList();
		Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap = currentPan
				.getShuangkouPlayerIdMajiangPlayerMap();
		for (int i = 0; i < 3; i++) {
			for (ShuangkouPlayer player : shuangkouPlayerIdMajiangPlayerMap.values()) {
				for (int j = 0; j < 9; j++) {
					PukePai pukePai = avaliablePaiList.remove(0);
					player.addShouPai(pukePai);
				}
			}
		}
	}

}
