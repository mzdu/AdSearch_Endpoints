package com.bitTiger.searchAds.adsInfo;

public class CampaignInfo {
  private final int _campaignId;
  private float _budget;

  public CampaignInfo(int campaignId, float budget) {
    _budget = budget;
    _campaignId = campaignId;
  }

  public int getCampaignId() {
    return _campaignId;
  }

  public float getBudget() {
    return _budget;
  }

  public void deductBudget(float chargeAmount) {
    _budget -= chargeAmount;
  }

}