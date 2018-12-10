package com.dml.shuangkou.preparedapai.luanpai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.pan.Pan;
import com.dml.shuangkou.pan.PanResult;

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
		List<PukePai> allPaiList = currentPan.getAvaliablePaiList();
		List<PukePai> finalPaiList = new ArrayList<>();
		List<PukePai> removePukePaiList = new ArrayList<>();
		List<PukePai> tempPaiList = new ArrayList<>();
		for (DianShuZuPaiZu paizu : dachuPaiZuList) {
			for (PukePai pukePai : paizu.getPaiArray()) {
				finalPaiList.add(pukePai);
				removePukePaiList.add(pukePai);
			}
		}
		for (PukePai pukePai : allPaiList) {
			boolean remove = false;
			for (PukePai pukePai1 : removePukePaiList) {
				if (pukePai1.getId() == pukePai.getId()) {
					remove = true;
					break;
				}
			}
			if (!remove) {
				tempPaiList.add(pukePai);
			}
		}
		Collections.shuffle(tempPaiList);
		finalPaiList.addAll(tempPaiList);
		currentPan.setAvaliablePaiList(finalPaiList);
	}

}
