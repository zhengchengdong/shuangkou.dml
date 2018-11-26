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

	public void updateListenersForNextPan() {
		daActionStatisticsListeners.forEach((listener) -> listener.updateForNextPan());
	}

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

	public <T extends DaActionStatisticsListener> T findDaListener(Class<T> type) {
		for (DaActionStatisticsListener listener : daActionStatisticsListeners) {
			if (listener.getClass().equals(type)) {
				return (T) listener;
			}
		}
		return null;
	}

	public <T extends GuoActionStatisticsListener> T findGuoListener(Class<T> type) {
		for (GuoActionStatisticsListener listener : guoActionStatisticsListeners) {
			if (listener.getClass().equals(type)) {
				return (T) listener;
			}
		}
		return null;
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
