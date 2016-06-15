package com.bitTiger.searchAds.datastore;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.bitTiger.searchAds.service.OfyService;
import com.googlecode.objectify.Objectify;
import java.util.ArrayList;
import java.util.List;

public class CampaignDataOperation {

	public static List<CampaignData> getAllCampaignData()
	{
		//return new ArrayList<CampaignData>();
		Objectify ofy = OfyService.ofy();
		List<CampaignData> CampaignDataList = ofy().load().type(CampaignData.class).list();
		
		return CampaignDataList;
	}
	
	public static void deductCampaignBudget(Long Id,float chargeAmount) {
		//Objectify ofy = OfyService.ofy();
		CampaignData campaignData = ofy().load().type(CampaignData.class).id(Id).now();
		 campaignData._budget -= chargeAmount;
		 OfyService.ofy().save().entity(campaignData).now();
//		CampaignData campaignData = ofy().load().type(CampaignData.class).id(Id).now();
//	    campaignData._budget -= chargeAmount;
//		ofy().save().entity(campaignData).now();
	  }
}
