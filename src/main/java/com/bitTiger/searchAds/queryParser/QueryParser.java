package com.bitTiger.searchAds.queryParser;

import java.util.List;

public interface QueryParser {
    List<String> parseQuery(String queryStr);
}
