package org.criteoexam.service.impl;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.criteoexam.domain.Campaign;
import org.criteoexam.domain.CampaignProduct;
import org.criteoexam.domain.CampaignRequest;
import org.criteoexam.excepions.InvalidCampaignRequestException;
import org.criteoexam.repository.CampaignProductRepository;
import org.criteoexam.repository.CampaignRepository;
import org.criteoexam.repository.ProductRepository;
import org.criteoexam.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CampaignProductRepository campaignProductRepository;

    @Override
    public Campaign createCampaign(CampaignRequest request) {
        validateRequest(request);
        // insert new campaign to campaign table
        Campaign newCampaign = campaignRepository.save(Campaign.builder().campaignName(request.getCampaignName())
                .startDate(request.getStartDate()).bid(request.getBid()).build());

        // associate campaign with the products they promote
        List<CampaignProduct> campaignProductList = request.getProducts().stream().map(productName -> {
            String serialNumber = productRepository.getSerialNumber(productName);
            if (Strings.isNotEmpty(serialNumber)) {
                return new CampaignProduct(newCampaign.getCampaignID(), serialNumber);
            } else {
                throw new InvalidCampaignRequestException("product list contains unknown product: " + productName);
            }
        }).toList();
        campaignProductRepository.saveAll(campaignProductList);
        return newCampaign;
    }

    private void validateRequest(CampaignRequest request) {
        if (request.getStartDate().before(Timestamp.valueOf(LocalDateTime.now())))
            throw new InvalidCampaignRequestException("start date should be in the future");
        if (campaignRepository.getCampaignCount(request.getCampaignName()) > 0)
            throw new InvalidCampaignRequestException("campaign name already used!");
    }
}
