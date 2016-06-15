package com.bitTiger.searchAds.help;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bitTiger.searchAds.adsInfo.AdsInfo;
import com.bitTiger.searchAds.adsInfo.AdsInventory;
import com.bitTiger.searchAds.adsInfo.AdsInvertedIndex;
import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInventory;
import com.bitTiger.searchAds.adsInfo.Inventory;

public class Help {

    public static AdsInventory ReturnAdsInventory() {
        AdsInventory adsInventory = new AdsInventory();
        // AdsInfo(int adsId, List<String> adsKeyWords, float pClick, float bid,
        // int campaignId)
        AdsInfo adsInfo = new AdsInfo((long)1231, Arrays.asList("basketball", "kobe", "shoe", "nike"),
                0.3f, 6.0f, (long)66);
        adsInventory.insertAds(adsInfo);
        adsInfo = new AdsInfo((long)1232, Arrays.asList("running", "shoe", "nike"), 0.63f, 4.5f, (long)66);
        adsInventory.insertAds(adsInfo);
        adsInfo = new AdsInfo((long)1233, Arrays.asList("soccer", "shoe", "nike"), 0.23f, 4.0f, (long)66);
        adsInventory.insertAds(adsInfo);
        adsInfo = new AdsInfo((long)1234, Arrays.asList("running", "shoe", "adidas"), 0.53f, 7.5f, (long)67);
        adsInventory.insertAds(adsInfo);
        adsInfo = new AdsInfo((long)1235, Arrays.asList("soccer", "shoe", "adidas"), 0.19f, 3.5f, (long)67);
        adsInventory.insertAds(adsInfo);
        adsInfo = new AdsInfo((long)1236, Arrays.asList("basketball", "shoe", "adidas"), 0.29f, 5.5f, (long)67);
        adsInventory.insertAds(adsInfo);
        return adsInventory;
    }

    public static CampaignInventory ReturnCampaignInventory() {
        CampaignInventory campaignInventory = new CampaignInventory();
        CampaignInfo campaignInfo = new CampaignInfo((long)66, 1500);
        campaignInventory.insertCampaign(campaignInfo);
        campaignInfo = new CampaignInfo((long)67, 2800);
        campaignInventory.insertCampaign(campaignInfo);
        campaignInfo = new CampaignInfo((long)68, 900);
        campaignInventory.insertCampaign(campaignInfo);
        return campaignInventory;
    }

    public static AdsInvertedIndex ReturnAdsInvertedIndex() {

        AdsInvertedIndex adsInvertedIndex = new AdsInvertedIndex();
        adsInvertedIndex.insertIndex("basketball", 1231);
        adsInvertedIndex.insertIndex("basketball", 1236);
        adsInvertedIndex.insertIndex("kobe", 1231);
        adsInvertedIndex.insertIndex("shoe", 1231);
        adsInvertedIndex.insertIndex("shoe", 1232);
        adsInvertedIndex.insertIndex("shoe", 1233);
        adsInvertedIndex.insertIndex("shoe", 1234);
        adsInvertedIndex.insertIndex("shoe", 1235);
        adsInvertedIndex.insertIndex("shoe", 1236);
        adsInvertedIndex.insertIndex("nike", 1231);
        adsInvertedIndex.insertIndex("nike", 1232);
        adsInvertedIndex.insertIndex("nike", 1233);
        adsInvertedIndex.insertIndex("running", 1232);
        adsInvertedIndex.insertIndex("running", 1234);
        adsInvertedIndex.insertIndex("soccer", 1233);
        adsInvertedIndex.insertIndex("soccer", 1235);
        adsInvertedIndex.insertIndex("adidas", 1234);
        adsInvertedIndex.insertIndex("adidas", 1235);
        adsInvertedIndex.insertIndex("adidas", 1236);
        return adsInvertedIndex;
    }

    public static ArrayList<AdsStatsInfo> ReturnadsStatsInfoList() {
        ArrayList<AdsStatsInfo> adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        // AdsStatsInfo(int campaignId, int adsId, float relevanceScore, float
        // qualityScore, float rankScore, float cpc, boolean isMainline)
        // query is "nike running shoe"
        AdsStatsInfo adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 3f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1235, 0.33f, 0.2f, 0.4f, 3f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1236, 0.33f, 0.3f, 0.6f, 5f, false);
        adsStatsInfoList.add(adsStatsInfo);

        return adsStatsInfoList;
    }

    public static Inventory ReturnInventoryWithSingleItem() {
        AdsInventory adsInventory = new AdsInventory();
        AdsInfo adsInfo = new AdsInfo((long)1231, Arrays.asList("basketball", "kobe", "shoe", "nike"),
                0.3f, 6.0f, (long)66);
        adsInventory.insertAds(adsInfo);
        CampaignInventory campaignInventory = new CampaignInventory();
        CampaignInfo campaignInfo = new CampaignInfo((long)66, 1500);
        campaignInventory.insertCampaign(campaignInfo);
        Inventory inventory = new Inventory(adsInventory, campaignInventory);
        return inventory;
    }

}
