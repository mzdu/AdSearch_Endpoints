package com.bitTiger.searchAds.adsInfo;

import java.util.Arrays;
import java.util.List;
import com.bitTiger.searchAds.datastore.*;

public class AdsInfo {
  private final Long _adsId;
  private final List<String> _adsKeyWords;
  private final float _pClick;
  private final float _bid;
  private final Long _campaignId;


  public AdsInfo(Long adsId, List<String> adsKeyWords, float pClick, float bid, Long campaignId) {
     _adsId = adsId;
     _adsKeyWords = adsKeyWords;
     _pClick = pClick;
     _bid = bid;
     _campaignId = campaignId;
  }
  
  public AdsInfo (AdsData adsData)
  {
	  _adsId =  adsData.getId();
	  _adsKeyWords = Arrays.asList(adsData.getAdsKeyWords().split(" "));
	  _pClick = adsData.getpClick();
	  _bid = adsData.getDid();
	  _campaignId = adsData.getCampaignId();  
	     
  }

  public Long getCampaignId() {
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

  public Long getAdsId() {
    return _adsId;
  }

}