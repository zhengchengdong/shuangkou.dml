package com.dml.shuangkou.pai.dianshuzu;

import java.util.ArrayList;
import java.util.List;

import com.dml.puke.wanfa.dianshu.dianshuzu.DanGeZhadanDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.DuiziDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LianduiDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.LiansanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.SanzhangDianShuZu;
import com.dml.puke.wanfa.dianshu.dianshuzu.ShunziDianShuZu;

/**
 * 所有的点数组不包括单张点数组和王炸
 * 
 * @author lsc
 *
 */
public class PaiXing {
	private List<DuiziDianShuZu> duiziDianShuZuList = new ArrayList<>();
	private List<LianduiDianShuZu> lianduiDianShuZuList = new ArrayList<>();
	private List<LiansanzhangDianShuZu> liansanzhangDianShuZuList = new ArrayList<>();
	private List<SanzhangDianShuZu> sanzhangDianShuZuList = new ArrayList<>();
	private List<ShunziDianShuZu> shunziDianShuZuList = new ArrayList<>();
	private List<DanGeZhadanDianShuZu> danGeZhadanDianShuZuList = new ArrayList<>();
	private List<LianXuZhadanDianShuZu> lianXuZhadanDianShuZuList = new ArrayList<>();

	public PaiXing() {

	}

	public boolean hasZhadan() {
		if (!lianXuZhadanDianShuZuList.isEmpty() || !danGeZhadanDianShuZuList.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<DuiziDianShuZu> getDuiziDianShuZuList() {
		return duiziDianShuZuList;
	}

	public void setDuiziDianShuZuList(List<DuiziDianShuZu> duiziDianShuZuList) {
		this.duiziDianShuZuList = duiziDianShuZuList;
	}

	public List<LianduiDianShuZu> getLianduiDianShuZuList() {
		return lianduiDianShuZuList;
	}

	public void setLianduiDianShuZuList(List<LianduiDianShuZu> lianduiDianShuZuList) {
		this.lianduiDianShuZuList = lianduiDianShuZuList;
	}

	public List<LiansanzhangDianShuZu> getLiansanzhangDianShuZuList() {
		return liansanzhangDianShuZuList;
	}

	public void setLiansanzhangDianShuZuList(List<LiansanzhangDianShuZu> liansanzhangDianShuZuList) {
		this.liansanzhangDianShuZuList = liansanzhangDianShuZuList;
	}

	public List<SanzhangDianShuZu> getSanzhangDianShuZuList() {
		return sanzhangDianShuZuList;
	}

	public void setSanzhangDianShuZuList(List<SanzhangDianShuZu> sanzhangDianShuZuList) {
		this.sanzhangDianShuZuList = sanzhangDianShuZuList;
	}

	public List<ShunziDianShuZu> getShunziDianShuZuList() {
		return shunziDianShuZuList;
	}

	public void setShunziDianShuZuList(List<ShunziDianShuZu> shunziDianShuZuList) {
		this.shunziDianShuZuList = shunziDianShuZuList;
	}

	public List<DanGeZhadanDianShuZu> getDanGeZhadanDianShuZuList() {
		return danGeZhadanDianShuZuList;
	}

	public void setDanGeZhadanDianShuZuList(List<DanGeZhadanDianShuZu> danGeZhadanDianShuZuList) {
		this.danGeZhadanDianShuZuList = danGeZhadanDianShuZuList;
	}

	public List<LianXuZhadanDianShuZu> getLianXuZhadanDianShuZuList() {
		return lianXuZhadanDianShuZuList;
	}

	public void setLianXuZhadanDianShuZuList(List<LianXuZhadanDianShuZu> lianXuZhadanDianShuZuList) {
		this.lianXuZhadanDianShuZuList = lianXuZhadanDianShuZuList;
	}

}
