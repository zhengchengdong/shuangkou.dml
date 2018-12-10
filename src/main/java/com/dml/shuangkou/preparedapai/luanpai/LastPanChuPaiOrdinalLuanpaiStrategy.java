package com.dml.shuangkou.preparedapai.luanpai;

import java.util.ArrayList;
import java.util.List;

import com.dml.puke.pai.PaiListValueObject;
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
		PaiListValueObject paiListValueObject = lastPanResult.getPan().getPaiListValueObject();
		List<DianShuZuPaiZu> dachuPaiZuList = lastPanResult.getPan().getDachuPaiZuList();
		List<PukePai> allPaiList = currentPan.getAvaliablePaiList();
		List<PukePai> finalPaiList = new ArrayList<>();
		for (PukePai pukePai : paiListValueObject.getPaiList()) {
			allPaiList.remove(pukePai.getId());
		}
		finalPaiList.addAll(paiListValueObject.getPaiList());
		for (DianShuZuPaiZu paizu : dachuPaiZuList) {
			for (PukePai pukePai : paizu.getPaiArray()) {
				finalPaiList.add(pukePai);
				allPaiList.remove(pukePai.getId());
			}
		}
		finalPaiList.addAll(allPaiList);
		currentPan.setAvaliablePaiList(finalPaiList);
	}

}
