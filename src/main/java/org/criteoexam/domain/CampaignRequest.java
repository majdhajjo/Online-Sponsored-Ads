package org.criteoexam.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class CampaignRequest {
    private String campaignName;
    private Timestamp startDate;
    // should be list of serial numbers
    private List<String> products;
    private BigDecimal bid;
}
