package org.criteoexam.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "campaign_product", schema = "ads_schema")
@IdClass(CampaignProduct.CampaignProductId.class)
public class CampaignProduct {
    @Column(name = "campaign_id")
    @Id
    private int campaignId;
    @Column(name = "product_serial_number")
    @Id
    private String productSerialNumber;

    public CampaignProduct(int campaignId, String productSerialNumber) {
        this.campaignId = campaignId;
        this.productSerialNumber = productSerialNumber;
    }

    public CampaignProduct() {

    }

    public static class CampaignProductId implements Serializable {

        private int campaignId;
        private String productSerialNumber;

        public CampaignProductId(int campaignId, String productSerialNumber) {
            this.campaignId = campaignId;
            this.productSerialNumber = productSerialNumber;
        }

        public CampaignProductId() {

        }
    }
}




