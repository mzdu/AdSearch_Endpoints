package com.bitTiger.searchAds.datastore;

import java.util.ArrayList;
import java.util.List;

import com.bitTiger.searchAds.service.OfyService;
import com.googlecode.objectify.Objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AdsDataOperation {

	public static List<AdsData> getAllAdsData()
	{
		Objectify ofy = OfyService.ofy();
		List<AdsData> AdsDataList = ofy.load().type(AdsData.class).list();
		return AdsDataList;
	}
}
