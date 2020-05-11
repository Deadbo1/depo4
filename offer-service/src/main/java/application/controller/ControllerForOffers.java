package application.controller;

import application.models.NewObj;
import application.models.Offers;
import application.models.PaidType;
import application.models.ListPaidType;
import application.service.impl.ServiceForOffersImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForOffers {

    private final ServiceForOffersImpl serviceForOffers;
    private static String k;
    private Long id;

    public ControllerForOffers(ServiceForOffersImpl serviceForOffers) {
        this.serviceForOffers = serviceForOffers;
    }

    @GetMapping("/offers")
    public List<Offers> getAllOffers() {
        return serviceForOffers.findAll();
    }

    @GetMapping("/offers/getById/{id}")
    public Offers getOffersById(@PathVariable("id") Long id) {
        return serviceForOffers.getOffersById(id);
    }

    @PostMapping("/offers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffers(@RequestBody Offers offers) {
        serviceForOffers.createOffers(offers);
    }

    @PutMapping("/offers/{id}")
    public void updateOffers(@PathVariable("id") Long id, @RequestBody Offers offers) {
        serviceForOffers.updateOffers(id, offers);
    }

    @DeleteMapping("/offers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffers(@PathVariable("id") Long id) {
        serviceForOffers.deleteOffers(getOffersById(id));
    }

    @GetMapping("/offers/getIdByToken")
    public Long getIdByToken() {
        k = serviceForOffers.getToken();
        id = serviceForOffers.getIdByToken(k);
        return id;
    }


    @GetMapping("/offers/getOffersByCustomerPaidTypeId")
    public List<Offers> getOffersByPaidType() {
        ResponseEntity<ListPaidType> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/customer/getTypes/" + getIdByToken(), ListPaidType.class);
        ListPaidType response = responseEntity.getBody();
        List<Long> paidTypes = new ArrayList<>();
        assert response != null;
        for (PaidType paidType : response.getPaidTypeList()) {
            paidTypes.add(paidType.getId());
        }
        return serviceForOffers.getOfferByPaidTypeId(paidTypes);
    }

    @GetMapping("/offers/getCategoriesAndPriceById/{id}")
    public NewObj getCategoriesAndPriceById (@PathVariable("id") Long id) {
       return serviceForOffers.getCategoriesAndPriceById(id);
    }

    @PostMapping("/offers/check")
    public ResponseEntity checkCreating(@RequestBody Long offerId) {
        ResponseEntity responseEntity = new RestTemplate().postForEntity("http://localhost:8100/order/createByTokenAndOffer", offerId, ResponseEntity.class);
         if(responseEntity.getStatusCode() == HttpStatus.CREATED) {
             return new ResponseEntity(HttpStatus.OK);
         }
         return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}