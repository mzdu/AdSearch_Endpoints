package com.bitTiger.searchAds.adsOptimization;

import com.bitTiger.searchAds.adsInfo.Inventory;

public interface AdsOptimization {
    AdsOptimization filterAds(Inventory inventory, float minRelevanceScore, float minReservePrice);

    AdsOptimization selectTopK(int k);

    AdsOptimization deDup();

    AdsOptimization adsPricingAndAllocation(Inventory inventory, float mainlineReservePrice);
}