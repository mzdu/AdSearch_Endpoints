package com.bitTiger.searchAds.datastore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class AdsData {
	
	@Id Long _adsId;
	Integer _campaignId;
	
	public AdsData(Long _adsId, Integer _campaignId){
		this._adsId = _adsId;
		this._campaignId = _campaignId;
	};

	public Long getId() {
		return _adsId;
	}
	
	public Integer getCampaignId() {
		return _campaignId;
	}
	
}
