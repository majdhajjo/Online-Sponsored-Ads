package org.criteoexam.service.impl;

import org.criteoexam.dao.AdsDao;
import org.criteoexam.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.NoResultException;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class AdServiceImplTest {

    @Mock
    private AdsDao adsDaoMock;

    private AdServiceImpl classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new AdServiceImpl(adsDaoMock);
    }

    @Test
    public void serveAd_specificCategory() {
        String category = "category1";
        Product prodExpected = new Product();
        prodExpected.setCategory(category);
        prodExpected.setPrice(new BigDecimal(10));
        prodExpected.setTitle("title1");
        Mockito.when(adsDaoMock.getProductWithHighestBidByCategoryName(category)).thenReturn(prodExpected);
        Product product = classUnderTest.serveAd(category);
        Assert.assertEquals(prodExpected, product);
    }

    @Test
    public void serveAd_unknownCategory() {
        String category2 = "category2";
        Product prodExpected = new Product();
        prodExpected.setCategory(category2);
        prodExpected.setPrice(new BigDecimal(10));
        prodExpected.setTitle("title2");
        Mockito.when(adsDaoMock.getProductWithHighestBidByCategoryName(category2)).thenThrow(EmptyResultDataAccessException.class);
        Mockito.when(adsDaoMock.getProductWithHighestBid()).thenReturn(prodExpected);
        Product product = classUnderTest.serveAd(category2);
        Assert.assertEquals(prodExpected, product);
    }
}
