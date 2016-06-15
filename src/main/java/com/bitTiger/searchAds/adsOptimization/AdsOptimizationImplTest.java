package com.bitTiger.searchAds.adsOptimization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import com.bitTiger.searchAds.adsInfo.AdsInventory;
import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInventory;
import com.bitTiger.searchAds.adsInfo.Inventory;
import com.bitTiger.searchAds.help.Help;

@RunWith(DataProviderRunner.class)
public class AdsOptimizationImplTest {
    AdsOptimizationImpl ads;
    Inventory inventory;

    @Before
    public void setUp() throws Exception {
        AdsInventory adsInventory = Help.ReturnAdsInventory();
        CampaignInventory campaignInventory = Help.ReturnCampaignInventory();
        // AdsInvertedIndex index = Help.ReturnAdsInvertedIndex();
        ArrayList<AdsStatsInfo> adsList = Help.ReturnadsStatsInfoList();
        ads = new AdsOptimizationImpl(adsList);
        inventory = new Inventory(adsInventory, campaignInventory);
    }

    @After
    public void tearDown() throws Exception {
        ads = null;
        inventory = null;
    }

    /************
     * for filterAds
     ***********/

    @Test(expected = IllegalArgumentException.class)
    public void filterAdsShouldThrowIllegalArgumentException_For_MinRelevanceScoreLessThanZero() {
        System.out
                .println("filterAdsShouldThrowIllegalArgumentException_For_MinRelevanceScoreLessThanZero");
        ads.filterAds(inventory, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterAdsShouldThrowIllegalArgumentException_For_MinRelevanceScoreBiggerThanOne() {
        System.out
                .println("filterAdsShouldThrowIllegalArgumentException_For_MinRelevanceScoreBiggerThanOne");
        ads.filterAds(inventory, 2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterAdsShouldThrowIllegalArgumentException_For_MinReservePriceLessThanZero() {
        System.out
                .println("filterAdsShouldThrowIllegalArgumentException_For_MinReservePriceLessThanZero");
        ads.filterAds(inventory, 1, -1);
    }

    @Test
    @UseDataProvider("filterAdsData")
    public void filterAdsShouldReturnAValidOutput(float minRelevanceScore, float minReservePrice,
            AdsOptimizationImpl expected) {
        System.out.println("filterAdsShouldReturnAValidOutput");
        AdsOptimization result = ads.filterAds(inventory, minRelevanceScore, minReservePrice);
        assertEquals(expected, result);

    }

    @DataProvider
    public static Object[][] filterAdsData() {
        ArrayList<AdsStatsInfo> adsStatsInfoList = new ArrayList<AdsStatsInfo>();
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
        AdsOptimizationImpl expected1 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 3f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected2 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected3 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
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
        AdsOptimizationImpl expected4 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1236, 0.33f, 0.3f, 0.6f, 5f, false);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected5 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        AdsOptimizationImpl expected6 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected7 = new AdsOptimizationImpl(adsStatsInfoList);

        return new Object[][] { { 0.3f, 0f, expected1 }, { 0.5f, 0f, expected2 },
                { 1f, 0f, expected3 }, { 0f, 1f, expected4 }, { 0f, 5f, expected5 },
                { 0f, 8f, expected6 }, { 0.5f, 5f, expected7 } };
    }

    /************
     * for selectTopK
     ***********/
    @Test(expected = IllegalArgumentException.class)
    public void selectTopKShouldThrowIllegalArgumentException_For_KLessThanZero() {
        System.out.println("selectTopKShouldThrowIllegalArgumentException_For_KLessThanZero");
        ads.selectTopK(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectTopKShouldThrowIllegalArgumentException_For_KEqualToZero() {
        System.out.println("selectTopKShouldThrowIllegalArgumentException_For_KEqualToZero");
        ads.selectTopK(0);
    }

    @Test
    @UseDataProvider("selectTopKData")
    public void selectTopKShouldReturnAValidOutput(int k, AdsOptimizationImpl expected) {
        System.out.println("selectTopKShouldReturnAValidOutput");
        // to do, for candidateAds size > k + 1 and <= k+1
        assertEquals(expected, ads.selectTopK(k));
    }

    @DataProvider
    public static Object[][] selectTopKData() {
        List<AdsStatsInfo> adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        AdsStatsInfo adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected1 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 3f, true);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected2 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 3f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1236, 0.33f, 0.3f, 0.6f, 5f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1235, 0.33f, 0.2f, 0.4f, 3f, false);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected3 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 4f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 3f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1236, 0.33f, 0.3f, 0.6f, 5f, false);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1235, 0.33f, 0.2f, 0.4f, 3f, false);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected4 = new AdsOptimizationImpl(adsStatsInfoList);

        return new Object[][] { { 1, expected1 }, { 3, expected2 }, { 6, expected3 },
                { 7, expected4 } };
    }

    /************
     * for adsPricingAndAllocation
     ***********/

    @Test(expected = IllegalArgumentException.class)
    public void adsPricingAndAllocationShouldThrowIllegalArgumentException_For_MainlineReservePriceLessThanZero() {
        System.out
                .println("adsPricingAndAllocationShouldThrowIllegalArgumentException_For_MainlineReservePriceLessThanZero");
        ads.adsPricingAndAllocation(inventory, -1);
    }

    @Test
    @UseDataProvider("adsPricingAndAllocationData")
    public void adsPricingAndAllocationShouldReturnAValidOutput(float mainlineReservePrice,
            AdsOptimizationImpl expected) {
        System.out.println("adsPricingAndAllocationShouldReturnAValidOutput");
        AdsOptimization result = ads.adsPricingAndAllocation(inventory, mainlineReservePrice);
        assertEquals(expected, result);
    }

    @DataProvider
    public static Object[][] adsPricingAndAllocationData() {
        ArrayList<AdsStatsInfo> adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        // AdsStatsInfo(int campaignId, int adsId, float relevanceScore, float
        // qualityScore, float rankScore, float cpc, boolean isMainline)
        AdsStatsInfo adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f,
                0.95f / 0.8f * 4.5f, true); // cpc ~5.34
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 0.5f / 0.95f * 4f, true);// 2.11
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 0.7f / 0.5f * 7.5f, true);// 10.5
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 0.2f / 0.7f * 3.5f, true);// 1
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1235, 0.33f, 0.2f, 0.4f, 0.3f / 0.2f * 5.5f, true);// 8.25
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected1 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        // AdsStatsInfo(int campaignId, int adsId, float relevanceScore, float
        // qualityScore, float rankScore, float cpc, boolean isMainline)
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 0.95f / 0.8f * 4.5f, true); // cpc
        // ~5.34
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 0.5f / 0.95f * 4f, false);// 2.11
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 0.7f / 0.5f * 7.5f, true);// 10.5
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 0.2f / 0.7f * 3.5f, false);// 1
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1235, 0.33f, 0.2f, 0.4f, 0.3f / 0.2f * 5.5f, true);// 8.25
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected2 = new AdsOptimizationImpl(adsStatsInfoList);

        adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        // AdsStatsInfo(int campaignId, int adsId, float relevanceScore, float
        // qualityScore, float rankScore, float cpc, boolean isMainline)
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 0.95f / 0.8f * 4.5f, false); // cpc
        // ~5.34
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1232, 1f, 0.95f, 0.96f, 0.5f / 0.95f * 4f, false);// 2.11
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)66, (long)1233, 0.67f, 0.5f, 0.61f, 0.7f / 0.5f * 7.5f, false);// 10.5
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 0.2f / 0.7f * 3.5f, false);// 1
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1235, 0.33f, 0.2f, 0.4f, 0.3f / 0.2f * 5.5f, false);// 8.25
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected3 = new AdsOptimizationImpl(adsStatsInfoList);

        return new Object[][] { { 0.5f, expected1 }, { 3f, expected2 }, { 11f, expected3 } };
    }

    public void adsPricingAndAllocationShouldReturnAValidOutput_WithInputSizeEqualToOne_OnMainline() {
        System.out
                .println("adsPricingAndAllocationShouldReturnAValidOutput_WithInputSizeEqualToOne_OnMainline");
        float mainlineReservePrice = 3;
        inventory = Help.ReturnInventoryWithSingleItem();
        List<AdsStatsInfo> adsList = new ArrayList<AdsStatsInfo>();
        AdsStatsInfo adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsList.add(adsStatsInfo);
        AdsOptimization expected = new AdsOptimizationImpl(adsList);
        AdsOptimization result = ads.adsPricingAndAllocation(inventory, mainlineReservePrice);
        assertEquals(expected, result);
    }

    public void adsPricingAndAllocationShouldReturnAValidOutput_WithInputSizeEqualToOne_NotOnMainline() {
        System.out
                .println("adsPricingAndAllocationShouldReturnAValidOutput_WithInputSizeEqualToOne_NotOnMainline");
        float mainlineReservePrice = 6;
        inventory = Help.ReturnInventoryWithSingleItem();
        List<AdsStatsInfo> adsList = new ArrayList<AdsStatsInfo>();
        AdsStatsInfo adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, false);
        adsList.add(adsStatsInfo);
        AdsOptimization expected = new AdsOptimizationImpl(adsList);
        AdsOptimization result = ads.adsPricingAndAllocation(inventory, mainlineReservePrice);
        assertEquals(expected, result);
    }

    /************
     * for deDup
     ***********/
    @Test
    public void deDupShouldReturnAValidOutput() {
        System.out.println("deDupShouldReturnAValidOutput");
        AdsOptimization result = ads.deDup();

        List<AdsStatsInfo> adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        AdsStatsInfo adsStatsInfo = new AdsStatsInfo((long)66, (long)1231, 0.5f, 0.8f, 0.9f, 5.0f, true);
        adsStatsInfoList.add(adsStatsInfo);
        adsStatsInfo = new AdsStatsInfo((long)67, (long)1234, 0.67f, 0.7f, 0.8f, 6.5f, true);
        adsStatsInfoList.add(adsStatsInfo);
        AdsOptimizationImpl expected = new AdsOptimizationImpl(adsStatsInfoList);

        assertEquals(expected, result);
    }
}
