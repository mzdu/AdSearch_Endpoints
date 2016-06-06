package com.bitTiger.searchAds.index;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bitTiger.searchAds.adsInfo.AdsInfo;
import com.bitTiger.searchAds.adsInfo.AdsInventory;
import com.bitTiger.searchAds.adsInfo.AdsInvertedIndex;
import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInventory;
import com.bitTiger.searchAds.adsInfo.Inventory;
import com.bitTiger.searchAds.lock.BudgetLock;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import com.bitTiger.searchAds.lock.BudgetLock;

public class AdsIndexImpl implements AdsIndex {
    private final AdsInventory _adsInventory;
    private final CampaignInventory _campaignInventory;
    private final AdsInvertedIndex _adsInvertedIndex;

    public AdsIndexImpl() {
        _adsInventory = new AdsInventory();
        _campaignInventory = new CampaignInventory();
        _adsInvertedIndex = new AdsInvertedIndex();
    }

    public AdsInventory get_adsInventory() {
        return _adsInventory;
    }


    public CampaignInventory get_campaignInventory() {
        return _campaignInventory;
    }



    public AdsInvertedIndex get_adsInvertedIndex() {
        return _adsInvertedIndex;
    }

    @Override
    public List<AdsStatsInfo> indexMatch(List<String> keyWords) {
        List<AdsStatsInfo> adsStatsInfoList = new ArrayList<AdsStatsInfo>();
        if (keyWords != null) {
            Iterator<String> keywordsIterator = keyWords.iterator();
            Map<Integer, Integer> hitCounts = new HashMap<Integer, Integer>();
            while (keywordsIterator.hasNext()) {
                String keyWord = keywordsIterator.next();
                List<Integer> matchedAdsIds = _adsInvertedIndex.retrieveIndex(keyWord.toLowerCase());
                if (matchedAdsIds != null) {
                    Iterator<Integer> matchedAdsIdsIterator = matchedAdsIds.iterator();
                    while (matchedAdsIdsIterator.hasNext()) {
                        Integer matchedAdsId = matchedAdsIdsIterator.next();
                        hitCounts.put(matchedAdsId, hitCounts.get(matchedAdsId) == null ? 1 : hitCounts.get(matchedAdsId) + 1);
                    }
                }
            }
            Iterator<Map.Entry<Integer, Integer>> hitCountsIterator = hitCounts.entrySet().iterator();
            while (hitCountsIterator.hasNext()) {
                Map.Entry<Integer, Integer> hitCountsEntry = hitCountsIterator.next();
                Integer adsId = hitCountsEntry.getKey();
                Integer hitCount = hitCountsEntry.getValue();
                AdsInfo adsInfo = _adsInventory.findAds(adsId);
                if (adsInfo != null) {
                    int campaignId = adsInfo.getCampaignId();
                    CampaignInfo campaignInfo = _campaignInventory.findCampaign(campaignId);
                    if (campaignInfo.getBudget() > 0) {
                        BudgetLock.lock.readLock().lock();
                        if (campaignInfo.getBudget() > 0) {
                            try {
                                AdsStatsInfo adsStatsInfo = new AdsStatsInfo(campaignId,adsId);
                                adsStatsInfo.setRelevanceScore(hitCount*1.0f/adsInfo.getAdsKeyWords().size());
                                adsStatsInfo.setQualityScore(0.75f*adsInfo.getpClick()+0.25f*adsStatsInfo.getRelevanceScore());
                                adsStatsInfo.setRankScore(adsStatsInfo.getQualityScore() * adsInfo.getBid());
                                adsStatsInfoList.add(adsStatsInfo);
                            }
                            finally {
                                BudgetLock.lock.readLock().unlock();
                            }
                        }
                    }
                }
            }
        }

        return adsStatsInfoList;
    }



    @Override
    public Inventory buildIndex(String adsFileName, String campaignFileName){
        Gson gson = new Gson();
        try {
            //Ads ads = gson.fromJson(new FileReader(adsFileName), Ads.class);
            Ads ads = getStaticAds();
            if(ads != null)
            {
                List<AdsInfo> adsInfo = ads.getAds();
                for(AdsInfo adInfo : adsInfo)
                {
                    if(adInfo != null)
                    {
                        List<String> keyWords = adInfo.getAdsKeyWords();
                        int adInfoId = adInfo.getAdsId();
                        for(String keyWord : keyWords)
                        {
                            _adsInvertedIndex.insertIndex(keyWord.toLowerCase(), adInfoId);
                        }
                        _adsInventory.insertAds(adInfo);
                    }

                }
                //Campaigns campaigns = gson.fromJson(new FileReader(campaignFileName), Campaigns.class);
                Campaigns campaigns = getStaticCamps();
                if(campaigns != null)
                {
                    List<CampaignInfo> campaignInfos = campaigns.getCampaigns();
                    for(CampaignInfo campaignInfo : campaignInfos)
                    {
                        if(campaignInfo != null) {
                            _campaignInventory.insertCampaign(campaignInfo);
                        }

                    }
                }
            }
        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
        //}
        return new Inventory(_adsInventory,_campaignInventory);
        // TODO Auto-generated method stub
    }

    class Ads {
        private final List<AdsInfo> _ads;
        public Ads(List<AdsInfo> ads) {
            _ads = ads;
        }
        public List<AdsInfo> getAds() {
            return _ads;
        }
    }

    class Campaigns {
        private final List<CampaignInfo> _campaigns;
        public Campaigns(List<CampaignInfo> campaigns) {
            _campaigns = campaigns;
        }
        public List<CampaignInfo> getCampaigns() {
            return _campaigns;
        }

    }

    private Ads getStaticAds() {
        List<AdsInfo> _ads = new ArrayList<AdsInfo>();
        _ads.add(new AdsInfo(1231, new ArrayList<String>(){{add("basketball");add("kobe");add("shoe");add("nike");}}, 0.37f, 6.0f, 66));
        _ads.add(new AdsInfo(1232, new ArrayList<String>(){{add("running");add("shoe");add("nike");}}, 0.63f, 4.5f, 66));
        _ads.add(new AdsInfo(1233, new ArrayList<String>(){{add("soccer");add("shoe");add("nike");}}, 0.23f, 4.0f, 66));
        _ads.add(new AdsInfo(1234, new ArrayList<String>(){{add("running");add("shoe");add("adidas");}}, 0.53f, 7.5f, 67));
        _ads.add(new AdsInfo(1235, new ArrayList<String>(){{add("soccer");add("shoe");add("adidas");}}, 0.19f, 3.5f, 67));
        _ads.add(new AdsInfo(1236, new ArrayList<String>(){{add("basketball");add("shoe");add("adidas");}}, 0.29f, 5.5f, 67));
        return new Ads(_ads);
    }

    private Campaigns getStaticCamps() {
        List<CampaignInfo> _campaigns = new  ArrayList<CampaignInfo>();
        _campaigns.add(new CampaignInfo(66, 1500f));
        _campaigns.add(new CampaignInfo(67, 2800f));
        _campaigns.add(new CampaignInfo(68, 900f));
        return new Campaigns(_campaigns);
    }

    @Override
    public Map<String, List<Integer>> ShowInvertedIndex()
    {
        return _adsInvertedIndex.GetAdsInvertedIndex();
    }

}