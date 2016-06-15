package com.bitTiger.searchAds.adsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdsInvertedIndex {
  private Map<String, List<Long>>  _adsInvertedIndex;

  public AdsInvertedIndex() {
    _adsInvertedIndex = new HashMap<String, List<Long>>();
  }

  public void insertIndex(String keyWord, long adsId) {
    if (!_adsInvertedIndex.containsKey(keyWord)) {
      _adsInvertedIndex.put(keyWord, new ArrayList<Long>());
    }
    _adsInvertedIndex.get(keyWord).add(adsId);
  }

  public List<Long> retrieveIndex(String keyWord) {
    return _adsInvertedIndex.get(keyWord);
  }
  
  public Map<String, List<Long>> GetAdsInvertedIndex()
  {
	  return  _adsInvertedIndex;
  }

}