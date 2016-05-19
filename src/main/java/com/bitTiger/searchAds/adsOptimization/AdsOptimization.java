package com.bitTiger.searchAds.adsOptimization;

import java.util.List;

import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.Inventory;

public interface AdsOptimization {
    AdsOptimization filterAds(Inventory inventory, float minRelevanceScore, float minReservePrice);

    AdsOptimization selectTopK(int k);

    AdsOptimization deDup();

    AdsOptimization adsPricingAndAllocation(Inventory inventory, float mainlineReservePrice);

    List<AdsStatsInfo> showResult();
}