package com.bitTiger.searchAds.datastore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class CampaignData {
	
	@Id Long _campaignId;
	float _budget;
	
	public CampaignData(Long _campaignId, float _budget){
		this._campaignId = _campaignId;
		this._budget = _budget;
	};
	/**
     * Just making the default constructor private.
     */
    private CampaignData() {}
	public Long getId() {
		return _campaignId;
	}
	
	public float getBudget() {
		return _budget;
	}
	
}