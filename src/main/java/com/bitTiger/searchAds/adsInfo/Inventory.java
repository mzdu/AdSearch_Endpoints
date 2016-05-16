package com.bitTiger.searchAds.adsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final AdsInventory _adsInventory;
    private final CampaignInventory _campaignInventory;

    public Inventory(AdsInventory _adsInventory,
            CampaignInventory _campaignInventory) {
        this._adsInventory = _adsInventory;
        this._campaignInventory = _campaignInventory;
    }
    
    public Map<Integer, AdsInfo>  showAds() {
    	return _adsInventory.GetAdsInfoInventory();
    } 

    public Map<Integer, CampaignInfo> showCamps() {
    	return _campaignInventory.GetCampaignInventory();
    } 

    public AdsInfo findAds(Integer adsId) {
        return _adsInventory.findAds(adsId);
    }

    public CampaignInfo findCampaign(Integer campaignId) {
        return _campaignInventory.findCampaign(campaignId);
    }

    public int adsQuantity() {
        return _adsInventory.adsQuantity();
    }

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return _adsInventory.isEmpty();
	}
}