package org.criteoexam.service;


import org.criteoexam.domain.Campaign;
import org.criteoexam.domain.CampaignRequest;

public interface CampaignService {
    Campaign createCampaign(CampaignRequest request);
}
