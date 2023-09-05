package org.criteoexam.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "campaign",schema = "ads_schema")
@Builder
public class Campaign {
    @Id
    @Column(name = "campaign_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignID;
    private String campaignName;
    private Timestamp startDate;
    private BigDecimal bid;
}
