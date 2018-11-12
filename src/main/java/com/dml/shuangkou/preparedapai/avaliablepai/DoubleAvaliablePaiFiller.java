package com.dml.shuangkou.preparedapai.avaliablepai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.dml.puke.pai.PukePai;
import com.dml.puke.pai.PukePaiMian;
import com.dml.shuangkou.ju.Ju;

/**
 * 最普通的加入两副牌
 * 
 * @author lsc
 *
 */
public class DoubleAvaliablePaiFiller implements AvaliablePaiFiller {

	private long seed;

	public DoubleAvaliablePaiFiller() {
	}

	public DoubleAvaliablePaiFiller(long seed) {
		this.seed = seed;
	}

	@Override
	public void fillAvaliablePai(Ju ju) throws Exception {
		Set<PukePaiMian> notPlaySet = new HashSet<>();
		PukePaiMian[] allPukePaiMianArray = PukePaiMian.values();
		List<PukePaiMian> playPaiTypeList = new ArrayList<>();
		for (int i = 0; i < allPukePaiMianArray.length; i++) {
			PukePaiMian paimain = allPukePaiMianArray[i];
			if (!notPlaySet.contains(paimain)) {
				playPaiTypeList.add(paimain);
			}
		}

		List<PukePai> allPaiList = new ArrayList<>();
		int id = 0;
		for (PukePaiMian paiType : playPaiTypeList) {
			for (int i = 0; i < 2; i++) {
				PukePai pai = new PukePai();
				pai.setId(id);
				pai.setPaiMian(paiType);
				allPaiList.add(pai);
				id++;
			}
		}

		Random r = new Random(seed);
		r.nextInt(allPaiList.size());
		Collections.shuffle(allPaiList, r);
		ju.getCurrentPan().setAvaliablePaiList(allPaiList);
		seed++;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

}
