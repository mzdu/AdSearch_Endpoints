package com.bitTiger.searchAds.adsInfo;

import com.bitTiger.searchAds.datastore.*;

public class CampaignInfo {
  private final  Long _campaignId;
  private float _budget;

  public CampaignInfo(Long campaignId, float budget) {
    _budget = budget;
    _campaignId = campaignId;
  }

  public CampaignInfo(CampaignData campaignData)
  {
	  _budget = campaignData.getBudget();
	    _campaignId =  campaignData.getId();
  }
  public Long getCampaignId() {
    return _campaignId;
  }

  public float getBudget() {
    return _budget;
  }

  public void deductBudget(float chargeAmount) {
    _budget -= chargeAmount;
  }

}