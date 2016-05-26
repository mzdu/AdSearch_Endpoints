package com.bitTiger.searchAds.adsOptimization;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.Set;

import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.Inventory;

public class AdsOptimizationImpl implements AdsOptimization {
    private final List<AdsStatsInfo> _candidateAds;

    public AdsOptimizationImpl(List<AdsStatsInfo> candidateAds) {
        if (candidateAds == null) {
            throw new NullPointerException("Null parameter: can't create the AdsOptimizationImpl object");
        }
        _candidateAds = candidateAds;
    }
    
    @Override
    public List<AdsStatsInfo> filterCandidateAds(Inventory inventory, float minRelevanceScore, float minReservePrice)
    {
    	 if (minRelevanceScore < 0 || minRelevanceScore > 1) {
             throw new IllegalArgumentException(
                     "Minimum Relevance Score should be a number between 0 and 1.");
         }
         if (minReservePrice < 0) {
             throw new IllegalArgumentException(
                     "Minimum Reserve Price should be a non-negtive number.");
         }
         if (inventory.isEmpty()) {
             return null;
         }
         // filter ads whose relevance score < min relevance score;
         for (Iterator<AdsStatsInfo> iterator = _candidateAds.iterator(); iterator.hasNext();) {
             if (iterator.next().getRelevanceScore() < minRelevanceScore) {
                 iterator.remove();
             }
         }
         // filter ads whose bid < min reserve price
         for (Iterator<AdsStatsInfo> iterator = _candidateAds.iterator(); iterator.hasNext();) {
             if (inventory.findAds(iterator.next().getAdsId()).getBid() < minReservePrice) {
                 iterator.remove();
             }
         }
         return  _candidateAds;
    	
    	
    }

    @Override
    public AdsOptimization filterAds(Inventory inventory, float minRelevanceScore, float minReservePrice) {
        if (minRelevanceScore < 0 || minRelevanceScore > 1) {
            throw new IllegalArgumentException(
                    "Minimum Relevance Score should be a number between 0 and 1.");
        }
        if (minReservePrice < 0) {
            throw new IllegalArgumentException(
                    "Minimum Reserve Price should be a non-negtive number.");
        }
        if (inventory.isEmpty()) {
            return this;
        }
        // filter ads whose relevance score < min relevance score;
        for (Iterator<AdsStatsInfo> iterator = _candidateAds.iterator(); iterator.hasNext();) {
            if (iterator.next().getRelevanceScore() < minRelevanceScore) {
                iterator.remove();
            }
        }
        // filter ads whose bid < min reserve price
        for (Iterator<AdsStatsInfo> iterator = _candidateAds.iterator(); iterator.hasNext();) {
            if (inventory.findAds(iterator.next().getAdsId()).getBid() < minReservePrice) {
                iterator.remove();
            }
        }
        return this;
    }

    /*
     * returns a list that contains top k+1 AdsStatsInfo object from
     * _candidateAds
     */
    @Override
    public AdsOptimization selectTopK(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("The parameter should be a positive integer.");
        }
        if (_candidateAds.size() == 0) {
            return this;
        }
        Collections.sort(_candidateAds, new Comparator<AdsStatsInfo>() {
            @Override
            public int compare(AdsStatsInfo ads1, AdsStatsInfo ads2) {
                float diff = ads2.getRankScore() - ads1.getRankScore();
                return diff == 0 ? 0 : (diff > 0 ? 1 : -1);
            }
        });
        if (_candidateAds.size() > k + 1) {
            _candidateAds.subList(k + 1, _candidateAds.size()).clear();
        }
        return this;
    }

    @Override
    public AdsOptimization adsPricingAndAllocation(Inventory inventory, float mainlineReservePrice) {
        if (mainlineReservePrice < 0) {
            throw new IllegalArgumentException("The parameter should be a non-negtive integer.");
        }
        // if no ads in the list
        if (_candidateAds.size() == 0) {
            return this;
        }
        // if only one ads in the list, it pays its bid price
        if (_candidateAds.size() == 1) {
            AdsStatsInfo ads = _candidateAds.get(0);
            ads.setCpc(inventory.findAds(ads.getAdsId()).getBid());
            inventory.findCampaign(ads.getCampaignId()).deductBudget(ads.getCpc());
            ads.setIsMainline(ads.getCpc() >= mainlineReservePrice);
            return this;
        }

        for (int i = 0; i < _candidateAds.size() - 1; i++) {
            AdsStatsInfo currentAds = _candidateAds.get(i);
            AdsStatsInfo nextAds = _candidateAds.get(i + 1);
            float bid = inventory.findAds(nextAds.getAdsId()).getBid();
            float price = nextAds.getQualityScore() / currentAds.getQualityScore() * bid + 0.01f;
            currentAds.setCpc(price);
            inventory.findCampaign(currentAds.getCampaignId()).deductBudget(currentAds.getCpc());
            currentAds.setIsMainline(currentAds.getCpc() >= mainlineReservePrice);
        }
        _candidateAds.remove(_candidateAds.size() - 1);
        return this;
    }

    @Override
    public String toString() {
        if (_candidateAds.size() == 0) {
            return "";
        }
        String result = "Ads Id || Quality Score || Rank Score";
        for (Iterator<AdsStatsInfo> iterator = _candidateAds.iterator(); iterator.hasNext();) {
            AdsStatsInfo info = iterator.next();
            result = result + info.getAdsId() + " " + info.getQualityScore()
                    + " " + info.getRankScore() + "\n";
        }
        return result;
    }

    @Override
    public AdsOptimization deDup() {
        if (_candidateAds.size() == 0) {
            return this;
        }
        // this set contains all existing campaign ids
        Set<Integer> campaignSet = new HashSet<Integer>();
        for (Iterator<AdsStatsInfo> iterator = _candidateAds.iterator(); iterator.hasNext();) {
            Integer cid = iterator.next().getCampaignId();
            if (campaignSet.contains(cid)) {
                iterator.remove();
            } else {
                campaignSet.add(cid);
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AdsOptimizationImpl) {
            return this._candidateAds.equals(((AdsOptimizationImpl) o)._candidateAds);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return _candidateAds.hashCode();
    }

    @Override
    public List<AdsStatsInfo> showResult() {
        return _candidateAds;
    }

}
