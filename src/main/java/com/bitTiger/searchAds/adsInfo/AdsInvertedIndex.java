package com.bitTiger.searchAds.adsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdsInvertedIndex {
  private Map<String, List<Integer>>  _adsInvertedIndex;

  public AdsInvertedIndex() {
    _adsInvertedIndex = new HashMap<String, List<Integer>>();
  }

  public void insertIndex(String keyWord, int adsId) {
    if (!_adsInvertedIndex.containsKey(keyWord)) {
      _adsInvertedIndex.put(keyWord, new ArrayList<Integer>());
    }
    _adsInvertedIndex.get(keyWord).add(adsId);
  }

  public List<Integer> retrieveIndex(String keyWord) {
    return _adsInvertedIndex.get(keyWord);
  }

}