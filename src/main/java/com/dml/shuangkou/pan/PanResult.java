package com.dml.shuangkou.pan;

public abstract class PanResult {

	private long panFinishTime;
	private PanValueObject pan;

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
