package com.bitTiger.searchAds.main;

import java.util.List;
import java.util.Map;

import static com.bitTiger.searchAds.service.OfyService.ofy;

import com.bitTiger.searchAds.adsInfo.AdsInfo;
import com.bitTiger.searchAds.adsInfo.AdsInventory;
import com.bitTiger.searchAds.adsInfo.AdsInvertedIndex;
import com.bitTiger.searchAds.adsInfo.AdsStatsInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInfo;
import com.bitTiger.searchAds.adsInfo.CampaignInventory;
import com.bitTiger.searchAds.adsInfo.Inventory;
import com.bitTiger.searchAds.adsOptimization.AdsOptimization;
import com.bitTiger.searchAds.adsOptimization.AdsOptimizationImpl;
import com.bitTiger.searchAds.index.AdsIndex;
import com.bitTiger.searchAds.index.AdsIndexImpl;
import com.bitTiger.searchAds.queryParser.QueryParser;
import com.bitTiger.searchAds.queryParser.QueryParserImpl;

import com.bitTiger.searchAds.datastore.AdsData;
import com.bitTiger.searchAds.datastore.CampaignData;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

/**
 * Defines endpoint functions APIs.
 */
@Api(
        name = "adsearchendpoints",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE },
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
        description = "API for ads search endpoints."
        )
public class AdSearchEndpoints {

    private final static QueryParser QUERY_PARSER = new QueryParserImpl();
    private final static AdsIndex ADS_INDEX = new AdsIndexImpl();
    private final static String ADS_INPUT_FILE = "ads-data.json";
    private final static String CAMPAIGN_INPUT_FILE = "campaign-data.json";
    private final static Inventory INVENTORY = ADS_INDEX.buildIndex(ADS_INPUT_FILE, CAMPAIGN_INPUT_FILE);
    private final static int K = 3;
    private final static float MAINLINE_RESERVE_PRICE = 2f;
    private final static float MIN_RESERVE_PRICE = .5f;
    private final static float MIN_RELEVANCE_SCORE = .01f;

    //http://localhost:8080/_ah/api/adsearchendpoints/v1/getTokens?name=Nike%20a%20Running%20Shoe
    @ApiMethod(name = "getTokens", path = "getTokens",
            httpMethod = HttpMethod.GET)
    public List<String> getTokens(@Named("name") String queryString) {
    
    	// add Ads data
    	AdsData ads1 = new AdsData((long) 1231, (long) 66, "basketball kobe shoe nike", 0.37f, 6.0f);
    	ofy().save().entity(ads1).now();
    	AdsData ads2 = new AdsData((long) 1232, (long) 66, "soccer shoe nike", 0.23f, 4.0f);
    	ofy().save().entity(ads2).now();
    	AdsData ads3 = new AdsData((long) 1233, (long) 67, "running shoe adidas", 0.53f, 7.5f);
    	ofy().save().entity(ads3).now();
    	AdsData ads4 = new AdsData((long) 1234, (long) 67, "soccer shoe adidas", 0.19f, 3.5f);
    	ofy().save().entity(ads4).now();
    	AdsData ads5 = new AdsData((long) 1235, (long) 67, "basketball shoe adidas", 0.29f, 5.5f);
    	ofy().save().entity(ads5).now();
    	
    	// add Campaign data
    	CampaignData cmp1 = new CampaignData((long) 66, 1500f);
    	ofy().save().entity(cmp1).now();    	
    	CampaignData cmp2 = new CampaignData((long) 67, 2800f);
    	ofy().save().entity(cmp2).now();       	
    	CampaignData cmp3 = new CampaignData((long) 68, 900f);
    	ofy().save().entity(cmp3).now();   
    	
        return QUERY_PARSER.parseQuery(queryString);
    }

    //http://localhost:8080/_ah/api/adsearchendpoints/v1/findMatch?keyWords=Nike&keyWords=Running&keyWords=Shoe
    @ApiMethod(name = "findMatch", path = "findMatch",
            httpMethod = HttpMethod.GET)
    public List<AdsStatsInfo> findMatch(@Named("keyWords") List<String> keyWords) {
        return ADS_INDEX.indexMatch(keyWords);
    }

    //http://localhost:8080/_ah/api/adsearchendpoints/v1/showAds
    @ApiMethod(name = "showAds", path = "showAds",
            httpMethod = HttpMethod.GET)
    public Map<Long, AdsInfo> showAds() {
        return INVENTORY.showAds();
    }

    //http://localhost:8080/_ah/api/adsearchendpoints/v1/showCamps
    @ApiMethod(name = "showCamps", path = "showCamps",
            httpMethod = HttpMethod.GET)
    public Map<Long, CampaignInfo> showCamps() {
        return INVENTORY.showCamps();
    }

    //http://localhost:8080/_ah/api/adsearchendpoints/v1/showIndex
    @ApiMethod(name = "showIndex", path = "showIndex",
            httpMethod = HttpMethod.GET)
    public Map<String, List<Long>> showIndex() {
        return ADS_INDEX.ShowInvertedIndex();
    }
    

    //http://localhost:8080/_ah/api/adsearchendpoints/v1/optimize?keyWords=Nike&keyWords=Running&keyWords=Shoe
    @ApiMethod(name = "optimize", path = "optimize",
            httpMethod = HttpMethod.GET)
    public List<AdsStatsInfo> optimize(@Named("keyWords") List<String> keyWords) {
        AdsOptimization adsOptimizer = new AdsOptimizationImpl(findMatch(keyWords));
        return adsOptimizer.filterAds(INVENTORY, MIN_RELEVANCE_SCORE, MIN_RESERVE_PRICE)
                .selectTopK(K).deDup().adsPricingAndAllocation(INVENTORY, MAINLINE_RESERVE_PRICE)
                .showResult();
    }

}
