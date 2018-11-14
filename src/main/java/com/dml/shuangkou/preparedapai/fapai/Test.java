package com.dml.shuangkou.preparedapai.fapai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.dml.puke.pai.PukePai;
import com.dml.puke.pai.PukePaiMian;
import com.dml.shuangkou.player.ShuangkouPlayer;
import com.dml.shuangkou.preparedapai.lipai.DianshuOrPaishuShoupaiSortStrategy;

public class Test {
	public static void main(String[] args) {
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
				allPaiList.add(pai);
				id++;
			}
		}
		Random r = new Random(System.currentTimeMillis());
		// 随机出26份牌的叠加顺序
		Integer[] indexs = new Integer[26];
		Integer[] ordinals = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25 };
		List<Integer> indexList = new ArrayList<>(Arrays.asList(ordinals));
		for (int i = 0; i < 26; i++) {
			int index = r.nextInt(indexList.size());
			indexs[i] = indexList.remove(index);
		}

		// 最终的牌堆
		List<PukePai> finalPaiList = new ArrayList<>();
		// 临时存放的牌堆
		List<PukePai> tmpPaiList = new LinkedList<>();
		Map<Integer, List<PukePai>> temMap = new HashMap<>();
		// 将每种牌8张分成2份
		List<PukePai> firstPaiList = new ArrayList<>(6);
		List<PukePai> secondPaiList = new ArrayList<>(6);
		for (int i = 0; i < 13; i++) {
			// 随机出前一份的数量2,3,4
			int firstSize = (int) (r.nextDouble() * (4 - 2 + 1)) + 2;
			switch (firstSize) {
			case 2:
				for (int j = 0; j < firstSize; j++) {
					firstPaiList.add(allPaiList.get(i * 8 + j));
				}
				for (int j = 0; j < 8 - firstSize; j++) {
					secondPaiList.add(allPaiList.get(i * 8 + j + firstSize));
				}
				break;
			case 3:
				for (int j = 0; j < firstSize; j++) {
					firstPaiList.add(allPaiList.get(i * 8 + j));
				}
				for (int j = 0; j < 8 - firstSize; j++) {
					secondPaiList.add(allPaiList.get(i * 8 + j + firstSize));
				}
				break;
			case 4:
				for (int j = 0; j < firstSize; j++) {
					firstPaiList.add(allPaiList.get(i * 8 + j));
				}
				for (int j = 0; j < 8 - firstSize; j++) {
					secondPaiList.add(allPaiList.get(i * 8 + j + firstSize));
				}
				break;
			}
			// 保存分组和叠加顺序
			int firstIndex = indexs[i];
			int secondIndex = indexs[26 - i - 1];
			temMap.put(firstIndex, new ArrayList<>(firstPaiList));
			temMap.put(secondIndex, new ArrayList<>(secondPaiList));
			firstPaiList.clear();
			secondPaiList.clear();
		}
		// 将所有牌放入牌堆
		for (int i = 0; i < 26; i++) {
			tmpPaiList.addAll(temMap.get(i));
		}
		// 插入大、小王

		tmpPaiList.add(r.nextInt(tmpPaiList.size()), allPaiList.get(107));
		tmpPaiList.add(r.nextInt(tmpPaiList.size()), allPaiList.get(106));
		tmpPaiList.add(r.nextInt(tmpPaiList.size()), allPaiList.get(105));
		tmpPaiList.add(r.nextInt(tmpPaiList.size()), allPaiList.get(104));

		// 切牌 TODO通过链表的指针直接排序
		List<PukePai> paiList1 = new ArrayList<>();
		List<PukePai> paiList2 = new ArrayList<>();
		List<PukePai> paiList3 = new ArrayList<>();
		Map<Integer, List<PukePai>> ordinalListMap = new HashMap<>();
		ordinalListMap.put(0, paiList1);
		ordinalListMap.put(1, paiList2);
		ordinalListMap.put(2, paiList3);
		int firstIndex;
		int secondIndex;
		do {
			firstIndex = r.nextInt(tmpPaiList.size());// 第一刀位置
			secondIndex = r.nextInt(tmpPaiList.size());// 第二刀位置
		} while (firstIndex == secondIndex);// 两刀位置不相同
		if (firstIndex > secondIndex) {// 如果第二刀位置在第一刀前面，则交换位置
			firstIndex = firstIndex ^ secondIndex;
			secondIndex = firstIndex ^ secondIndex;
			firstIndex = firstIndex ^ secondIndex;
		}
		for (int j = 0; j < firstIndex; j++) {
			paiList1.add(allPaiList.get(j));
		}
		for (int j = firstIndex; j < secondIndex; j++) {
			paiList2.add(allPaiList.get(j));
		}
		for (int j = secondIndex; j < tmpPaiList.size(); j++) {
			paiList3.add(allPaiList.get(j));
		}
		List<Integer> ordinalList = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2 }));
		for (int i = 0; i < 3; i++) {
			int ordinal = r.nextInt(ordinalList.size());
			int x = ordinalList.remove(ordinal);
			finalPaiList.addAll(ordinalListMap.get(x));
		}
		ShuangkouPlayer player1 = new ShuangkouPlayer();
		player1.setId("player1");
		ShuangkouPlayer player2 = new ShuangkouPlayer();
		player2.setId("player2");
		ShuangkouPlayer player3 = new ShuangkouPlayer();
		player3.setId("player3");
		ShuangkouPlayer player4 = new ShuangkouPlayer();
		player4.setId("player4");
		List<ShuangkouPlayer> players = new ArrayList<>();
		players.add(player4);
		players.add(player3);
		players.add(player2);
		players.add(player1);
		for (int i = 0; i < 3; i++) {
			for (ShuangkouPlayer player : players) {
				for (int j = 0; j < 9; j++) {
					PukePai pukePai = finalPaiList.remove(0);
					player.addShouPai(pukePai);
				}
			}
		}
		for (ShuangkouPlayer player : players) {
			player.lipai(new DianshuOrPaishuShoupaiSortStrategy());
		}
		for (ShuangkouPlayer player : players) {
			System.out.println(player.getId());
			Map<Integer, PukePai> allShoupai = player.getAllShoupai();
			for (List<Integer> sort : player.getShoupaiIdListForSortList()) {
				for (Integer i : sort) {
					System.out.print(allShoupai.get(i).getPaiMian() + " ");
				}
				System.out.println("---------");
			}
		}
	}
}
