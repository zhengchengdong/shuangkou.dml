package com.dml.shuangkou.pan;

import java.util.List;

import com.dml.shuangkou.player.ShuangkouPlayerValueObject;

public abstract class PanResult {

	private long panFinishTime;
	private PanValueObject pan;

	public List<String> allPlayerIds() {
		return pan.allPlayerIds();
	}

	public ShuangkouPlayerValueObject findPlayer(String playerId) {
		return pan.findPlayer(playerId);
	}

	public long getPanFinishTime() {
		return panFinishTime;
	}

	public void setPanFinishTime(long panFinishTime) {
		this.panFinishTime = panFinishTime;
	}

	public PanValueObject getPan() {
		return pan;
	}

	public void setPan(PanValueObject pan) {
		this.pan = pan;
	}

}
