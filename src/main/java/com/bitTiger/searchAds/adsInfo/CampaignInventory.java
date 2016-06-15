package com.bitTiger.searchAds.adsInfo;

import java.util.Map;
import java.util.HashMap;

public class CampaignInventory {
  private final Map<Long, CampaignInfo> _campaignInventory;

  public CampaignInventory() {
    _campaignInventory = new HashMap<Long, CampaignInfo>();
  }

  public void insertCampaign(CampaignInfo campaignInfo) {
    _campaignInventory.put(campaignInfo.getCampaignId(), campaignInfo);
  }

  public CampaignInfo findCampaign(Long campaignId) {
    return _campaignInventory.get(campaignId);
  }  
  
  public Map<Long, CampaignInfo> GetCampaignInventory()
  {
	  return _campaignInventory;
  }

}