package com.dml.shuangkou.preparedapai.luanpai;

import java.util.ArrayList;
import java.util.List;

import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.pan.PanResult;
import com.dml.shuangkou.player.ShuangkouPlayerValueObject;

/**
 * 按照上一盘的出牌的顺序将牌叠在一起
 * 
 * @author lsc
 *
 */
public class LastPanChuPaiOrdinalLuanpaiStrategy implements LuanpaiStrategy {

	public LastPanChuPaiOrdinalLuanpaiStrategy() {

	}

	@Override
	public void luanpai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		PanResult lastPanResult = ju.findLatestFinishedPanResult();
		List<DianShuZuPaiZu> dachuPaiZuList = lastPanResult.getPan().getDachuPaiZuList();
		List<String> playerIds = lastPanResult.allPlayerIds();
		List<PukePai> finalPaiList = new ArrayList<>();
		for (DianShuZuPaiZu paizu : dachuPaiZuList) {
			for (PukePai pukePai : paizu.getPaiArray()) {
				finalPaiList.add(pukePai);
			}
		}
		for (PukePai pukePai : lastPanResult.getPan().getPaiListValueObject().getPaiList()) {
			finalPaiList.add(pukePai);
		}
		playerIds.forEach((playerId) -> {
			ShuangkouPlayerValueObject player = lastPanResult.findPlayer(playerId);

			for (PukePai pukePai : player.getAllShoupai().values()) {
				finalPaiList.add(pukePai);
			}
		});
		currentPan.setAvaliablePaiList(finalPaiList);
	}

}
