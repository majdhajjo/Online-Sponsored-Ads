package org.criteoexam.dao;

import org.criteoexam.domain.Product;

public interface AdsDao {
    Product getProductWithHighestBidByCategoryName(String category);
    Product getProductWithHighestBid();
}
