package com.bitTiger.searchAds.adsInfo;

public class AdsStatsInfo {
    private final int _campaignId;
    private final int _adsId;
    private float _relevanceScore;
    private float _qualityScore;
    private float _rankScore;
    private float _cpc;
    private boolean _isMainline;

    public AdsStatsInfo(int campaignId, int adsId) {
        _campaignId = campaignId;
        _adsId = adsId;
        _relevanceScore = 0;
        _qualityScore = 0;
        _rankScore = 0;
        _cpc = 0;
        _isMainline = false;
    }

    // for testing only
    public AdsStatsInfo(int campaignId, int adsId, float relevanceScore, float cpc,
            boolean isMainline) {
        _campaignId = campaignId;
        _adsId = adsId;
        _relevanceScore = relevanceScore;
        _qualityScore = 0;
        _rankScore = 0;
        _cpc = cpc;
        _isMainline = isMainline;
    }

    public boolean getIsMainline() {
        return _isMainline;
    }

    public void setIsMainline(boolean isMainline) {
        this._isMainline = isMainline;
    }

    public float getRelevanceScore() {
        return _relevanceScore;
    }

    public void setRelevanceScore(float relevanceScore) {
        _relevanceScore = relevanceScore;
    }

    public float getQualityScore() {
        return _qualityScore;
    }

    public void setQualityScore(float qualityScore) {
        _qualityScore = qualityScore;
    }

    public float getRankScore() {
        return _rankScore;
    }

    public void setRankScore(float rankScore) {
        _rankScore = rankScore;
    }

 public int getCampaignId() {
    return _campaignId;
  }
    public float getCpc() {
        return _cpc;
    }

    public void setCpc(float cpc) {
        _cpc = cpc;
    }

    public int getAdsId() {
        return _adsId;
    }
    @Override
    public boolean equals(Object o)
    {
    	return true;
    }
    @Override
    public int hashCode()
    {
    	int hash = _campaignId + _adsId;
    	float value = _relevanceScore  + _qualityScore + _rankScore + _cpc;
    	return (int)(value/hash);
    }
}