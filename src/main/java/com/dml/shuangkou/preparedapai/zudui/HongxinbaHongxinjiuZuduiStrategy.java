package com.dml.shuangkou.preparedapai.zudui;

import java.util.ArrayList;
import java.util.List;

import com.dml.puke.pai.PukePai;
import com.dml.puke.pai.PukePaiMian;
import com.dml.puke.pai.QiShouLiangPaiMark;
import com.dml.puke.pai.ZuDuiLiangPaiMark;
import com.dml.puke.wanfa.position.Position;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.player.ShuangkouPlayer;

/**
 * 红心8、红心9确定组队
 * 
 * @author lsc
 *
 */
public class HongxinbaHongxinjiuZuduiStrategy implements ZuduiStrategy {

	@Override
	public void zudui(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<String> playerIdList = currentPan.sortedPlayerIdList();
		List<Position> pList = new ArrayList<>();
		pList.add(Position.xi);
		pList.add(Position.nan);
		pList.add(Position.bei);
		String qishouPlayerId = null;
		String duijiaPlayerId = null;
		for (ShuangkouPlayer player : currentPan.getShuangkouPlayerIdMajiangPlayerMap().values()) {
			for (PukePai pukePai : player.getAllShoupai().values()) {
				if (qishouPlayerId == null && pukePai.getPaiMian().equals(PukePaiMian.hongxinba)) {
					pukePai.setMark(new QiShouLiangPaiMark());
					qishouPlayerId = player.getId();
				}
				if (duijiaPlayerId == null && pukePai.getPaiMian().equals(PukePaiMian.hongxinjiu)) {
					pukePai.setMark(new ZuDuiLiangPaiMark());
					duijiaPlayerId = player.getId();
				}
			}
		}
		if (qishouPlayerId == null) {
			qishouPlayerId = playerIdList.get(0);
		}
		if (duijiaPlayerId == null) {
			qishouPlayerId = playerIdList.get(1);
		}
		currentPan.updatePlayerPosition(qishouPlayerId, Position.dong);
		playerIdList.remove(qishouPlayerId);
		if (qishouPlayerId.equals(duijiaPlayerId)) {
			while (!playerIdList.isEmpty()) {
				String playerId = playerIdList.remove(0);
				Position position = pList.remove(0);
				currentPan.updatePlayerPosition(playerId, position);
			}
		} else {
			currentPan.updatePlayerPosition(duijiaPlayerId, Position.xi);
			playerIdList.remove(duijiaPlayerId);
			pList.remove(Position.xi);
			while (!playerIdList.isEmpty()) {
				String playerId = playerIdList.remove(0);
				Position position = pList.remove(0);
				currentPan.updatePlayerPosition(playerId, position);
			}
		}
	}

}
