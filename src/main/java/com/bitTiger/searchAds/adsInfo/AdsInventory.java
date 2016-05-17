package com.bitTiger.searchAds.adsInfo;

import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdsInventory {
    private final Map<Integer, AdsInfo> _adsInfoInventory;

    public AdsInventory() {
        _adsInfoInventory = new HashMap<Integer, AdsInfo>();
    }

    public void insertAds(AdsInfo adsInfo) {
        _adsInfoInventory.put(adsInfo.getAdsId(), adsInfo);
    }
    
    public Map<Integer, AdsInfo> GetAdsInfoInventory()
    {
    	return _adsInfoInventory;
    }
    public AdsInfo findAds(int adsId) {
        return _adsInfoInventory.get(adsId);
    }

    public int adsQuantity() {
        return _adsInfoInventory.size();
    }
    
    @Override
    public String toString() {      
        return _adsInfoInventory.toString(); 
    }
    
    public boolean isEmpty() {
    	return _adsInfoInventory.isEmpty();
    }

}
