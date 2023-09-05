package org.criteoexam.dao;

import lombok.AllArgsConstructor;
import org.criteoexam.domain.Product;
import org.criteoexam.excepions.NoServeDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@AllArgsConstructor
public class AdsDaoImpl implements AdsDao {

    @Autowired
    private EntityManager entityManager;

    /**
     * query to return the product with the highest bid for specific active campaign(date is in range of 10 dates after start)
     *
     * @param category
     * @return product
     */
    @Override
    public Product getProductWithHighestBidByCategoryName(String category) {
        return (Product) entityManager.createNativeQuery("SELECT p.product_serial_number AS product_serial_number " +
                ", p.title AS title ,p.category AS category , p.price AS price " +
                "FROM product AS p " +
                "INNER JOIN campaign_product AS cp ON p.product_serial_number = cp.product_serial_number " +
                "INNER JOIN campaign AS c ON cp.campaign_id = c.campaign_id " +
                "WHERE c.start_date >= DATE_SUB(CURDATE(), INTERVAL 10 DAY) " +
                "AND (p.category like :category)" +
                " ORDER BY c.bid DESC " +
                "LIMIT 1", Product.class).setParameter("category", category).getSingleResult();
    }


    /**
     * query to return the product with the highest bid for any active campaign(date is in range of 10 dates after start)
     *
     * @return product
     */
    @Override
    public Product getProductWithHighestBid() {
        try {
            return (Product) entityManager.createNativeQuery("SELECT p.product_serial_number AS product_serial_number " +
                    ", p.title AS title ,p.category AS category , p.price AS price " +
                    "FROM product AS p " +
                    "INNER JOIN campaign_product AS cp ON p.product_serial_number = cp.product_serial_number " +
                    "INNER JOIN campaign AS c ON cp.campaign_id = c.campaign_id " +
                    "WHERE c.start_date >= DATE_SUB(CURDATE(), INTERVAL 10 DAY) " +
                    " ORDER BY c.bid DESC " +
                    "LIMIT 1", Product.class).getSingleResult();

        } catch (NoResultException e) {
            throw new NoServeDataException("there is no Serve ads");
        }
    }
}
