package com.dml.shuangkou.pan;

import java.util.ArrayList;
import java.util.List;

import com.dml.puke.pai.PaiListValueObject;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.puke.wanfa.position.Position;
import com.dml.shuangkou.player.ShuangkouPlayerValueObject;

public class PanValueObject {
	private int no;
	private List<ShuangkouPlayerValueObject> shuangkouPlayerList;
	private PaiListValueObject paiListValueObject;
	private List<DianShuZuPaiZu> dachuPaiZuList;
	private Position actionPosition;
	private String latestDapaiPlayerId;

	public PanValueObject() {
	}

	public PanValueObject(Pan pan) {
		no = pan.getNo();
		shuangkouPlayerList = new ArrayList<>();
		pan.getShuangkouPlayerIdMajiangPlayerMap().values()
				.forEach((shuangkouPlayer) -> shuangkouPlayerList.add(new ShuangkouPlayerValueObject(shuangkouPlayer)));
		paiListValueObject = new PaiListValueObject(pan.getAvaliablePaiList());
		dachuPaiZuList = new ArrayList<>(pan.getDachuPaiZuList());
		actionPosition = pan.getActionPosition();
		latestDapaiPlayerId = pan.getLatestDapaiPlayerId();
	}

	public List<String> allPlayerIds() {
		List<String> list = new ArrayList<>();
		for (ShuangkouPlayerValueObject player : shuangkouPlayerList) {
			list.add(player.getId());
		}
		return list;
	}

	public ShuangkouPlayerValueObject findPlayer(String playerId) {
		for (ShuangkouPlayerValueObject player : shuangkouPlayerList) {
			if (player.getId().equals(playerId)) {
				return player;
			}
		}
		return null;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public PaiListValueObject getPaiListValueObject() {
		return paiListValueObject;
	}

	public void setPaiListValueObject(PaiListValueObject paiListValueObject) {
		this.paiListValueObject = paiListValueObject;
	}

	public List<DianShuZuPaiZu> getDachuPaiZuList() {
		return dachuPaiZuList;
	}

	public void setDachuPaiZuList(List<DianShuZuPaiZu> dachuPaiZuList) {
		this.dachuPaiZuList = dachuPaiZuList;
	}

	public List<ShuangkouPlayerValueObject> getShuangkouPlayerList() {
		return shuangkouPlayerList;
	}

	public void setShuangkouPlayerList(List<ShuangkouPlayerValueObject> shuangkouPlayerList) {
		this.shuangkouPlayerList = shuangkouPlayerList;
	}

	public Position getActionPosition() {
		return actionPosition;
	}

	public void setActionPosition(Position actionPosition) {
		this.actionPosition = actionPosition;
	}

	public String getLatestDapaiPlayerId() {
		return latestDapaiPlayerId;
	}

	public void setLatestDapaiPlayerId(String latestDapaiPlayerId) {
		this.latestDapaiPlayerId = latestDapaiPlayerId;
	}

}
