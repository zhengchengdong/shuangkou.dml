package com.dml.shuangkou.preparedapai.avaliablepai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dml.puke.pai.PukePai;
import com.dml.puke.pai.PukePaiMian;
import com.dml.puke.pai.QiShouLiangPaiMark;
import com.dml.puke.pai.ZuDuiLiangPaiMark;
import com.dml.shuangkou.ju.Ju;

/**
 * 最普通的加入两副牌 以红心8判断起手 以红心9分组
 * 
 * @author lsc
 *
 */
public class DoubleAvaliablePaiFiller implements AvaliablePaiFiller {

	public DoubleAvaliablePaiFiller() {
	}

	@Override
	public void fillAvaliablePai(Ju ju) throws Exception {
		Set<PukePaiMian> notPlaySet = new HashSet<>();
		PukePaiMian[] allPukePaiMianArray = PukePaiMian.values();
		List<PukePaiMian> playPaiTypeList = new ArrayList<>();
		// 移除不可用牌
		for (int i = 0; i < allPukePaiMianArray.length; i++) {
			PukePaiMian paimain = allPukePaiMianArray[i];
			if (!notPlaySet.contains(paimain)) {
				playPaiTypeList.add(paimain);
			}
		}

		List<PukePai> allPaiList = new ArrayList<>();
		// 生成两副牌
		int id = 0;
		for (PukePaiMian paiType : playPaiTypeList) {
			for (int i = 0; i < 2; i++) {
				PukePai pai = new PukePai();
				pai.setId(id);
				pai.setPaiMian(paiType);
				if (i == 1 && PukePaiMian.hongxinba.equals(paiType)) {
					pai.setMark(new QiShouLiangPaiMark());
				}
				if (i == 1 && PukePaiMian.hongxinjiu.equals(paiType)) {
					pai.setMark(new ZuDuiLiangPaiMark());
				}
				allPaiList.add(pai);
				id++;
			}
		}

		ju.getCurrentPan().setAvaliablePaiList(allPaiList);
	}

}
