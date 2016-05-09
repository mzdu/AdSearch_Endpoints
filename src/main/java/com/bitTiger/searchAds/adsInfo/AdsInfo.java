package com.bitTiger.searchAds.adsInfo;

import java.util.List;

public class AdsInfo {
  private final int _adsId;
  private final List<String> _adsKeyWords;
  private final float _pClick;
  private final float _bid;
  private final int _campaignId;


  public AdsInfo(int adsId, List<String> adsKeyWords, float pClick, float bid, int campaignId) {
     _adsId = adsId;
     _adsKeyWords = adsKeyWords;
     _pClick = pClick;
     _bid = bid;
     _campaignId = campaignId;
  }

  public int getCampaignId() {
    return _campaignId;
  }

  public List<String> getAdsKeyWords() {
    return _adsKeyWords;
  }

  public float getpClick() {
    return _pClick;
  }

  public float getBid() {
    return _bid;
  }

  public int getAdsId() {
    return _adsId;
  }

}