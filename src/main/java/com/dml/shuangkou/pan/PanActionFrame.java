package com.dml.shuangkou.pan;

import com.dml.shuangkou.player.action.ShuangkouPlayerAction;

public class PanActionFrame {

	private ShuangkouPlayerAction action;
	private PanValueObject panAfterAction;
	private long actionTime;

	public PanActionFrame() {
	}

	public PanActionFrame(ShuangkouPlayerAction action, PanValueObject panAfterAction, long actionTime) {
		this.action = action;
		this.panAfterAction = panAfterAction;
		this.actionTime = actionTime;
	}

	public ShuangkouPlayerAction getAction() {
		return action;
	}

	public void setAction(ShuangkouPlayerAction action) {
		this.action = action;
	}

	public PanValueObject getPanAfterAction() {
		return panAfterAction;
	}

	public void setPanAfterAction(PanValueObject panAfterAction) {
		this.panAfterAction = panAfterAction;
	}

	public long getActionTime() {
		return actionTime;
	}

	public void setActionTime(long actionTime) {
		this.actionTime = actionTime;
	}

}
