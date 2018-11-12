package com.dml.shuangkou.pan;

import java.util.List;

import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;

public class PanValueObject {
	private int no;
	private List<PukePai> avaliablePaiList;
	private List<DianShuZuPaiZu> dachuPaiZuList;

	public PanValueObject() {
	}

	public PanValueObject(Pan pan) {
		this.no = pan.getNo();
		this.avaliablePaiList = pan.getAvaliablePaiList();
		this.dachuPaiZuList = pan.getDachuPaiZuList();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public List<PukePai> getAvaliablePaiList() {
		return avaliablePaiList;
	}

	public void setAvaliablePaiList(List<PukePai> avaliablePaiList) {
		this.avaliablePaiList = avaliablePaiList;
	}

	public List<DianShuZuPaiZu> getDachuPaiZuList() {
		return dachuPaiZuList;
	}

	public void setDachuPaiZuList(List<DianShuZuPaiZu> dachuPaiZuList) {
		this.dachuPaiZuList = dachuPaiZuList;
	}

}
