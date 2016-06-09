package com.bitTiger.searchAds.datastore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class AdsData {
	
	@Id Long _adsId;
	Long _campaignId;
	String _adsKeyWords;
	float _pclick;
	float _bid;
	
	public AdsData(Long _adsId, Long _campaignId, String _adsKeyWords, float _pclick, float _bid){
		this._adsId = _adsId;
		this._campaignId = _campaignId;
		this._adsKeyWords = _adsKeyWords;
		this._pclick = _pclick;
		this._bid = _bid;
	};

	
	public Long getId() {
		return _adsId;
	}
	
	public Long getCampaignId() {
		return _campaignId;
	}
	
}
