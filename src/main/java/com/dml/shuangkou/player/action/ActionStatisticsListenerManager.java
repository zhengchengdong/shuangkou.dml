package com.dml.shuangkou.player.action;

import java.util.ArrayList;
import java.util.List;

import com.dml.shuangkou.ju.Ju;
import com.dml.shuangkou.player.action.da.DaAction;
import com.dml.shuangkou.player.action.da.DaActionStatisticsListener;
import com.dml.shuangkou.player.action.guo.GuoAction;
import com.dml.shuangkou.player.action.guo.GuoActionStatisticsListener;

public class ActionStatisticsListenerManager {

	private List<DaActionStatisticsListener> daActionStatisticsListeners = new ArrayList<>();
	private List<GuoActionStatisticsListener> guoActionStatisticsListeners = new ArrayList<>();

	public void updateDaActionListener(DaAction daAction, Ju ju) {
		for (DaActionStatisticsListener listener : daActionStatisticsListeners) {
			listener.update(daAction, ju);
		}
	}

	public void updateGuoActionListener(GuoAction guoAction, Ju ju) {
		for (GuoActionStatisticsListener listener : guoActionStatisticsListeners) {
			listener.update(guoAction, ju);
		}
	}

	public List<DaActionStatisticsListener> getDaActionStatisticsListeners() {
		return daActionStatisticsListeners;
	}

	public void setDaActionStatisticsListeners(List<DaActionStatisticsListener> daActionStatisticsListeners) {
		this.daActionStatisticsListeners = daActionStatisticsListeners;
	}

	public List<GuoActionStatisticsListener> getGuoActionStatisticsListeners() {
		return guoActionStatisticsListeners;
	}

	public void setGuoActionStatisticsListeners(List<GuoActionStatisticsListener> guoActionStatisticsListeners) {
		this.guoActionStatisticsListeners = guoActionStatisticsListeners;
	}

}
