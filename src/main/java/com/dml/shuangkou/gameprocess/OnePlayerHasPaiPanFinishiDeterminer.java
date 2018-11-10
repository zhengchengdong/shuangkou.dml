package com.dml.shuangkou.gameprocess;

import java.util.List;

import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;

/**
 * 如果最多只有一个人手上有牌，一盘结束
 * 
 * @author lsc
 *
 */
public class OnePlayerHasPaiPanFinishiDeterminer implements CurrentPanFinishiDeterminer {

	@Override
	public boolean determineToFinishCurrentPan(Ju ju) {
		Pan currentPan = ju.getCurrentPan();
		List<String> playerIds = currentPan.getAllPlayerId();
		int remain = 0;
		for (String playerId : playerIds) {
			if (currentPan.ifPlayerHasPai(playerId)) {
				remain++;
			}
		}
		if (remain <= 1) {
			return true;
		}
		return false;
	}

}
