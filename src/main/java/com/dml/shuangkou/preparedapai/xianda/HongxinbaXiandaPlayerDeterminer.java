package com.dml.shuangkou.preparedapai.xianda;

import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.position.Position;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.player.ShuangkouPlayer;

/**
 * 有标记的红心八先打牌
 * 
 * @author lsc
 *
 */
public class HongxinbaXiandaPlayerDeterminer implements XiandaPlayerDeterminer {

	@Override
	public String determineXiandaPlayer(Ju ju) {
		Pan currentPan = ju.getCurrentPan();
		String daplayerId = null;
		for (ShuangkouPlayer player : currentPan.getShuangkouPlayerIdMajiangPlayerMap().values()) {
			for (PukePai pukePai : player.getAllShoupai().values()) {
				if (pukePai.getMark() != null && pukePai.getMark().name().equals("qishouLiangPai")) {
					daplayerId = player.getId();
					return daplayerId;
				}
			}
		}
		daplayerId = currentPan.playerIdForPosition(Position.dong);
		return daplayerId;
	}

}
