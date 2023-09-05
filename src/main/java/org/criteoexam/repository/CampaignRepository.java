package org.criteoexam.repository;

import org.criteoexam.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {

    @Query(value = "SELECT COUNT(*) FROM campaign WHERE campaign_id like :campaign ", nativeQuery = true)
    int getCampaignCount(@Param("campaign") String campaign);
}

