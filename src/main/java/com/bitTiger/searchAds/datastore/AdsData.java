package com.bittiger.searchAds.datastore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class AdsData {
	
	@Id Integer _adsId;
	Integer _campaignId;
	
	public AdsData(Integer _adsId, Integer _campaignId){
		this._adsId = _adsId;
		this._campaignId = _campaignId;
	};

	public Integer getId() {
		return _adsId;
	}
	
	public Integer getCampaignId() {
		return _campaignId;
	}
	
}
