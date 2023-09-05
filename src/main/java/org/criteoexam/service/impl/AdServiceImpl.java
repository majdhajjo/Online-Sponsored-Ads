package org.criteoexam.service.impl;

import lombok.AllArgsConstructor;
import org.criteoexam.dao.AdsDao;
import org.criteoexam.domain.Product;
import org.criteoexam.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {

    @Autowired
    private AdsDao adsDao;

    @Override
    public Product serveAd(String category) {
        Product productWithHighestBid;
        try {
            productWithHighestBid = adsDao.getProductWithHighestBidByCategoryName(category);
        } catch (EmptyResultDataAccessException e) {  // if products for the matching category.
            productWithHighestBid = adsDao.getProductWithHighestBid();
        }
        return productWithHighestBid;
    }
}