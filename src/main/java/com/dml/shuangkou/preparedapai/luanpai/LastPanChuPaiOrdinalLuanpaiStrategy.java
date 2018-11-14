package com.dml.shuangkou.preparedapai.luanpai;

import java.util.ArrayList;
import java.util.Arrays;
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
	private BianXingWanFa bx;

	public LastPanChuPaiOrdinalLuanpaiStrategy() {

	}

	public LastPanChuPaiOrdinalLuanpaiStrategy(BianXingWanFa bx) {
		this.bx = bx;
	}

	@Override
	public void luanpai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		PanResult lastPanResult = ju.findLatestFinishedPanResult();
		List<DianShuZuPaiZu> dachuPaiZuList = lastPanResult.getPan().getDachuPaiZuList();
		List<PukePai> allPaiList = new ArrayList<>();
		for (DianShuZuPaiZu paizu : dachuPaiZuList) {
			allPaiList.addAll(Arrays.asList(paizu.getPaiArray()));
		}
		currentPan.setAvaliablePaiList(allPaiList);
	}

	public BianXingWanFa getBx() {
		return bx;
	}

	public void setBx(BianXingWanFa bx) {
		this.bx = bx;
	}

}
