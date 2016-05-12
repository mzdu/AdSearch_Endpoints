package com.bitTiger.searchAds.adsInfo;

import java.util.Map;
import java.util.HashMap;

public class CampaignInventory {
  private final Map<Integer, CampaignInfo> _campaignInventory;

  public CampaignInventory() {
    _campaignInventory = new HashMap<Integer, CampaignInfo>();
  }

  public void insertCampaign(CampaignInfo campaignInfo) {
    _campaignInventory.put(campaignInfo.getCampaignId(), campaignInfo);
  }

  public CampaignInfo findCampaign(Integer campaignId) {
    return _campaignInventory.get(campaignId);
  }  
  
  public Map<Integer, CampaignInfo> GetCampaignInventory()
  {
	  return _campaignInventory;
  }

}