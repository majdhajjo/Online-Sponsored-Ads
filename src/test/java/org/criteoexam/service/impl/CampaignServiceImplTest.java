package org.criteoexam.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.criteoexam.domain.Campaign;
import org.criteoexam.domain.CampaignProduct;
import org.criteoexam.domain.CampaignRequest;
import org.criteoexam.excepions.InvalidCampaignRequestException;
import org.criteoexam.repository.CampaignProductRepository;
import org.criteoexam.repository.CampaignRepository;
import org.criteoexam.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceImplTest {
    @Mock
    private CampaignProductRepository campaignProductRepositoryMock;
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CampaignRepository campaignRepositoryMock;
    private CampaignServiceImpl classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new CampaignServiceImpl(campaignRepositoryMock, productRepositoryMock, campaignProductRepositoryMock);
    }

    @Test
    public void createCampaign() {
        Campaign campaignExpected = Campaign.builder().campaignID(1).campaignName("campaign1").build();
        List<String> products = Arrays.asList("product1", "product2");
        CampaignRequest request = CampaignRequest.builder().campaignName("campaign1").bid(new BigDecimal(10))
                .products(products).startDate(Timestamp.valueOf(LocalDateTime.now().plusHours(1))).build();
        Mockito.when(campaignRepositoryMock.save(Mockito.any())).thenReturn(campaignExpected);
        Mockito.when(productRepositoryMock.getSerialNumber(Mockito.anyString())).thenReturn("123");
        Mockito.when(campaignProductRepositoryMock.saveAll(Mockito.any())).thenReturn(List.of(new CampaignProduct()));
        Campaign campaign = classUnderTest.createCampaign(request);
        Assert.assertEquals(campaignExpected, campaign);
    }

    @Test(expected = InvalidCampaignRequestException.class)
    public void createCampaign_unknownProd() {
        Campaign campaignExpected = Campaign.builder().campaignID(1).campaignName("campaign1").build();
        List<String> products = Arrays.asList("product1", "product2");
        CampaignRequest request = CampaignRequest.builder().campaignName("campaign1").bid(new BigDecimal(10))
                .products(products).startDate(Timestamp.valueOf(LocalDateTime.now().plusHours(1))).build();
        Mockito.when(campaignRepositoryMock.save(Mockito.any())).thenReturn(campaignExpected);
        Mockito.when(productRepositoryMock.getSerialNumber(Mockito.anyString())).thenReturn(Strings.EMPTY);
        classUnderTest.createCampaign(request);
        Assert.fail();
    }

    @Test(expected = InvalidCampaignRequestException.class)
    public void createCampaign_invalidDate() {
        List<String> products = Arrays.asList("product1", "product2");
        CampaignRequest request = CampaignRequest.builder().campaignName("campaign1").bid(new BigDecimal(10))
                .products(products).startDate(Timestamp.valueOf(LocalDateTime.now().minusHours(1))).build();
        classUnderTest.createCampaign(request);
        Assert.fail();
    }

    @Test(expected = InvalidCampaignRequestException.class)
    public void createCampaign_campaignAlreadyExists() {
        List<String> products = Arrays.asList("product1", "product2");
        CampaignRequest request = CampaignRequest.builder().campaignName("campaign1").bid(new BigDecimal(10))
                .products(products).startDate(Timestamp.valueOf(LocalDateTime.now().plusHours(1))).build();
        Mockito.when(campaignRepositoryMock.getCampaignCount(Mockito.any())).thenReturn(5);
        classUnderTest.createCampaign(request);
        Assert.fail();
    }
}