package org.criteoexam.web;

import lombok.AllArgsConstructor;
import org.criteoexam.domain.Campaign;
import org.criteoexam.domain.CampaignRequest;
import org.criteoexam.domain.ErrMsg;
import org.criteoexam.domain.Product;
import org.criteoexam.excepions.InvalidCampaignRequestException;
import org.criteoexam.excepions.NoServeDataException;
import org.criteoexam.service.AdService;
import org.criteoexam.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/online-sponsored-ads")
@AllArgsConstructor
public class AdResource {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private AdService adService;


    @PostMapping("/create/campaign")
    public ResponseEntity<Object> createCampaign(@RequestBody CampaignRequest campaignRequest) {
        try {
            Campaign campaign = campaignService.createCampaign(campaignRequest);
            return ResponseEntity.ok(campaign);
        } catch (InvalidCampaignRequestException e) {
            return ResponseEntity.badRequest().body(ErrMsg.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ErrMsg.builder().message("Internal Server Error").build());
        }
    }

    @GetMapping("/serve/ad/{category_name}")
    public ResponseEntity<Object> serveAd(@PathVariable("category_name") String category) {
        try {
            Product product = adService.serveAd(category);
            return ResponseEntity.ok(product);
        } catch (NoServeDataException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(ErrMsg.builder().message("Internal Server Error").build());
        }
    }
}
