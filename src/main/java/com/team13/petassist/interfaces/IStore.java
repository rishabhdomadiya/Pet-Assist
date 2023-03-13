package com.team13.petassist.interfaces;

import java.util.List;

import com.team13.petassist.entity.Store;

public interface IStore {
	public List<Store> getStoreId();
	public List<Store> getFilteredStores(String locationName);
}
