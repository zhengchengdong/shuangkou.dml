package com.dml.shuangkou.pan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dml.puke.pai.PukePai;
import com.dml.puke.wanfa.dianshu.paizu.DianShuZuPaiZu;
import com.dml.puke.wanfa.position.Position;
import com.dml.puke.wanfa.position.PositionUtil;
import com.dml.shuangkou.pai.waihao.WaihaoGenerator;
import com.dml.shuangkou.player.PlayerNotFoundException;
import com.dml.shuangkou.player.ShuangkouPlayer;
import com.dml.shuangkou.player.action.ShuangkouPlayerAction;
import com.dml.shuangkou.player.action.da.DaAction;
import com.dml.shuangkou.player.action.da.KedaPaiSolutionsForTipsGenerator;
import com.dml.shuangkou.player.action.da.PlayerCanNotActionException;
import com.dml.shuangkou.player.action.da.YaPaiSolutionsTipsFilter;
import com.dml.shuangkou.player.action.da.solution.DianShuZuYaPaiSolutionCalculator;
import com.dml.shuangkou.player.action.da.solution.ZaDanYaPaiSolutionCalculator;
import com.dml.shuangkou.player.action.guo.CanNotGuoException;
import com.dml.shuangkou.player.action.guo.GuoAction;

public class Pan {
	private int no;
	private Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap = new HashMap<>();
	private Map<Position, String> positionPlayerIdMap = new HashMap<>();
	private List<PukePai> avaliablePaiList = new ArrayList<>();
	private List<DianShuZuPaiZu> dachuPaiZuList = new ArrayList<>();
	private Position actionPosition;
	private String latestDapaiPlayerId;
	private List<PanActionFrame> actionFrameList = new ArrayList<>();

	public boolean ifPlayerHasPai(String playerId) {
		ShuangkouPlayer player = shuangkouPlayerIdMajiangPlayerMap.get(playerId);
		return !player.getAllShoupai().isEmpty();
	}

	public List<String> getAllPlayerId() {
		return new ArrayList<>(shuangkouPlayerIdMajiangPlayerMap.keySet());
	}

	public PanActionFrame recordPanActionFrame(ShuangkouPlayerAction action, long actionTime) {
		PanActionFrame frame = new PanActionFrame(action, new PanValueObject(this), actionTime);
		actionFrameList.add(frame);
		return frame;
	}

	public void addPlayer(String playerId) {
		ShuangkouPlayer shuangkouPlayer = new ShuangkouPlayer();
		shuangkouPlayer.setId(playerId);
		shuangkouPlayerIdMajiangPlayerMap.put(playerId, shuangkouPlayer);
	}

	public void addFrame(PanActionFrame panActionFrame) {
		actionFrameList.add(panActionFrame);
	}

	public List<String> sortedPlayerIdList() {
		List<String> list = new ArrayList<>(shuangkouPlayerIdMajiangPlayerMap.keySet());
		Collections.sort(list);
		return list;
	}

	public void updateActionPositionByActionPlayer(String playerId) throws Exception {
		ShuangkouPlayer player = shuangkouPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new PlayerNotFoundException();
		}
		actionPosition = player.getPosition();
	}

	public void updatePlayerPosition(String playerId, Position position) throws PlayerNotFoundException {
		ShuangkouPlayer player = shuangkouPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new PlayerNotFoundException();
		}
		player.setPosition(position);
		positionPlayerIdMap.put(position, playerId);
	}

	public String playerIdForPosition(Position position) {
		return positionPlayerIdMap.get(position);
	}

	public DaAction da(String playerId, List<Integer> paiIds, String dianshuZuheIdx, WaihaoGenerator waihaoGenerator)
			throws Exception {
		ShuangkouPlayer daPlayer = shuangkouPlayerIdMajiangPlayerMap.get(playerId);
		if (daPlayer == null) {
			throw new PlayerNotFoundException();
		}
		if (!actionPosition.equals(daPlayer.getPosition())) {
			throw new PlayerCanNotActionException();
		}
		// 如果是大的人打牌，那先要清桌
		if (playerId.equals(latestDapaiPlayerId)) {
			shuangkouPlayerIdMajiangPlayerMap.values().forEach((player) -> {
				player.putPublicDachuPaiZuToLishi();
			});
		}
		daPlayer.da(paiIds, dianshuZuheIdx, waihaoGenerator);
		DianShuZuPaiZu publicDachuPaiZu = daPlayer.getPublicDachuPaiZu();
		dachuPaiZuList.add(publicDachuPaiZu);
		latestDapaiPlayerId = playerId;
		DaAction daAction = new DaAction(playerId);
		daAction.setDachuPaiZu(publicDachuPaiZu);
		return daAction;
	}

	public GuoAction guo(String playerId) throws Exception {
		ShuangkouPlayer player = shuangkouPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new PlayerNotFoundException();
		}
		if (!actionPosition.equals(player.getPosition())) {
			throw new PlayerCanNotActionException();
		}
		// 大的人必须出牌，不能过
		if (playerId.equals(latestDapaiPlayerId)) {
			throw new CanNotGuoException();
		}
		player.guo();
		return new GuoAction();
	}

	public void updateNextPlayersDaSolution(DianShuZuYaPaiSolutionCalculator dianShuZuYaPaiCalculator,
			ZaDanYaPaiSolutionCalculator zaDanYaPaiCalculator) {
		String dachuPlayerId = positionPlayerIdMap.get(actionPosition);
		if (dachuPlayerId != null) {
			ShuangkouPlayer dachuPlayer = shuangkouPlayerIdMajiangPlayerMap.get(dachuPlayerId);
			if (dachuPlayer != null) {
				DianShuZuPaiZu dachuPaiZu = dachuPlayer.getPublicDachuPaiZu();
				if (dachuPaiZu != null) {
					Position nextPosition = PositionUtil.nextPositionAntiClockwise(actionPosition);
					String yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
					if (yapaiPlayerId != null) {
						ShuangkouPlayer yapaiPlayer = shuangkouPlayerIdMajiangPlayerMap.get(yapaiPlayerId);
						if (yapaiPlayer != null) {
							yapaiPlayer.addDaPaiDianShuSolutions(dianShuZuYaPaiCalculator
									.calculate(dachuPaiZu.getDianShuZu(), yapaiPlayer.getShoupaiDianShuAmountArray()));
							yapaiPlayer.addDaPaiDianShuSolutions(zaDanYaPaiCalculator
									.calculate(dachuPaiZu.getDianShuZu(), yapaiPlayer.getShoupaiDianShuAmountArray()));
						}
					}
				}
			}
		}

	}

	public void generateYaPaiSolutionsForTips(YaPaiSolutionsTipsFilter yaPaiSolutionsTipsFilter) {
		Position nextPosition = PositionUtil.nextPositionAntiClockwise(actionPosition);
		String yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
		if (yapaiPlayerId != null) {
			ShuangkouPlayer yapaiPlayer = shuangkouPlayerIdMajiangPlayerMap.get(yapaiPlayerId);
			if (yapaiPlayer != null) {
				yapaiPlayer.generateYaPaiSolutionsForTips(yaPaiSolutionsTipsFilter);
			}
		}
	}

	public void generateDaPaiSolutionsForTips(KedaPaiSolutionsForTipsGenerator kedaPaiSolutionsForTipsGenerator) {
		Position nextPosition = PositionUtil.nextPositionAntiClockwise(actionPosition);
		String yapaiPlayerId = positionPlayerIdMap.get(nextPosition);
		if (yapaiPlayerId != null) {
			ShuangkouPlayer yapaiPlayer = shuangkouPlayerIdMajiangPlayerMap.get(yapaiPlayerId);
			if (yapaiPlayer != null) {
				yapaiPlayer.generateDaPaiSolutionsForTips(kedaPaiSolutionsForTipsGenerator);
			}
		}
	}

	public void updateActionPositionToNextPlayer() {
		actionPosition = PositionUtil.nextPositionAntiClockwise(actionPosition);
	}

	public ShuangkouPlayer findPlayer(String dapaiPlayerId) {
		return shuangkouPlayerIdMajiangPlayerMap.get(dapaiPlayerId);
	}

	public String nextPlayerId() {
		Position nextPosition = PositionUtil.nextPositionAntiClockwise(actionPosition);
		return positionPlayerIdMap.get(nextPosition);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Map<String, ShuangkouPlayer> getShuangkouPlayerIdMajiangPlayerMap() {
		return shuangkouPlayerIdMajiangPlayerMap;
	}

	public void setShuangkouPlayerIdMajiangPlayerMap(Map<String, ShuangkouPlayer> shuangkouPlayerIdMajiangPlayerMap) {
		this.shuangkouPlayerIdMajiangPlayerMap = shuangkouPlayerIdMajiangPlayerMap;
	}

	public Map<Position, String> getPositionPlayerIdMap() {
		return positionPlayerIdMap;
	}

	public void setPositionPlayerIdMap(Map<Position, String> positionPlayerIdMap) {
		this.positionPlayerIdMap = positionPlayerIdMap;
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

	public List<PanActionFrame> getActionFrameList() {
		return actionFrameList;
	}

	public void setActionFrameList(List<PanActionFrame> actionFrameList) {
		this.actionFrameList = actionFrameList;
	}

}
