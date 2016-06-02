package com.bitTiger.searchAds.adsInfo;

import java.util.Objects;

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

    public AdsStatsInfo(int campaignId, int adsId, float relevanceScore, float qualityScore,
            float rankScore, float cpc,
            boolean isMainline) {
        _campaignId = campaignId;
        _adsId = adsId;
        _relevanceScore = relevanceScore;
        _qualityScore = qualityScore;
        _rankScore = rankScore;
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
    public boolean equals(Object o) {
        if (o == null || !(o instanceof AdsStatsInfo)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        AdsStatsInfo other = (AdsStatsInfo) o;
        return this._campaignId == other._campaignId && this._adsId == other._adsId
                && floatCompare(this._relevanceScore, other._relevanceScore)
                && floatCompare(this._qualityScore, other._qualityScore)
                && floatCompare(this._rankScore, other._rankScore)
                && floatCompare(_cpc, other._cpc) && this._isMainline == other._isMainline;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._campaignId, this._adsId, this._relevanceScore,
                this._qualityScore, this._rankScore, this._cpc, this._isMainline);
    }

    @Override
    public String toString() {
        return _campaignId + " " + _adsId + " " + _relevanceScore + " " + _qualityScore + " "
                + _rankScore + " " + _cpc + " " + _isMainline;
    }

    private boolean floatCompare(float f1, float f2) {
        return Math.abs(f1 - f2) < 0.2;
    }
}