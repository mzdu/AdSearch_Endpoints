package com.bitTiger.searchAds.adsOptimization;

import java.util.List;

import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.Inventory;

public interface AdsOptimization {
    AdsOptimization filterAds(Inventory inventory, float minRelevanceScore, float minReservePrice);

    AdsOptimization selectTopK(int k);

    AdsOptimization deDup();

    AdsOptimization adsPricingAndAllocation(Inventory inventory, float mainlineReservePrice);

    public List<AdsStatsInfo> showOptimizationResult(Inventory INVENTORY,
            float MIN_RELEVANCE_SCORE, float MIN_RESERVE_PRICE, Integer K,
            float MAINLINE_RESERVE_PRICE);

    List<AdsStatsInfo> getCandidateAds();
}