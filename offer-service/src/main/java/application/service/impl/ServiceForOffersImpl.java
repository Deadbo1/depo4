package application.service.impl;

import application.models.Characteristics;
import application.models.Offers;
import application.models.TokenData;
import application.models.NewObj;
import application.repository.OffersDao;
import application.service.OffersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceForOffersImpl implements OffersService {

    private final OffersDao offersDao;
    private final ServiceForCharacteristicsImpl serviceForCharacteristics;
    private final ServiceForCategoriesImpl serviceForCategories;

    @Value("dgf32kld23")
    private String key;

    public ServiceForOffersImpl(OffersDao offersDao, ServiceForCharacteristicsImpl servicesForCharacteristics, ServiceForCategoriesImpl serviceForCategories) {
        this.offersDao = offersDao;
        this.serviceForCharacteristics = servicesForCharacteristics;
        this.serviceForCategories = serviceForCategories;
    }

    @Override
    public Offers getOffersById(Long id) {
        return offersDao.getOffersById(id);
    }

    public List<Characteristics> checkCharacteristics (Offers offers) {
        List<Characteristics> characteristicsList = new ArrayList<>();
        if (serviceForCharacteristics.checkCharacteristics(offers.getCharacteristics())) {
            offers.setCharacteristics(serviceForCharacteristics.getCheckCharacteristics(offers.getCharacteristics()));
        } else {
            offers.getCharacteristics().forEach(f -> {
                if (!serviceForCharacteristics.findAll().contains(f)) {
                    serviceForCharacteristics.createCharacteristics(f);
                }
                characteristicsList.add(serviceForCharacteristics.findCharacteristics(f));
            });
        }
        return characteristicsList;
    }


    @Override
    public void createOffers(Offers offers) {
        if (serviceForCharacteristics.checkCharacteristics(offers.getCharacteristics())) {
            offers.setCharacteristics(serviceForCharacteristics.getCheckCharacteristics(offers.getCharacteristics()));
        } else {
            offers.setCharacteristics(checkCharacteristics(offers));
        }
        if (!serviceForCategories.findAll().isEmpty() && serviceForCategories.checkCategories(offers.getCategoryId())) {
            offers.setCategoryId(serviceForCategories.getCheckCategories(offers.getCategoryId()));
        } else {
            serviceForCategories.createCategories(offers.getCategoryId());
        }
        offersDao.save(offers);
    }

    @Override
    public void deleteOffers(Offers offers) {
        offersDao.delete(offers);
    }

    @Override
    public void updateOffers(Long id, Offers offers) {
        Offers offers1 = getOffersById(id);
        offers1.setName(offers.getName());
        offers1.setPrice(offers.getPrice());
        offers1.setPaidTypeId(offers.getPaidTypeId());
        offers1.setCategoryId(offers.getCategoryId());
        if (serviceForCharacteristics.checkCharacteristics(offers.getCharacteristics())) {
            offers1.setCharacteristics(serviceForCharacteristics.getCheckCharacteristics(offers.getCharacteristics()));
        } else {
            offers1.setCharacteristics(checkCharacteristics(offers));
        }
        offersDao.save(offers1);
    }

    @Override
    public List<Offers> findAll() {
        return offersDao.findAll();
    }

    public List<Offers> getOfferByPaidTypeId(List<Long> longs) {
        List<Offers> offersList = offersDao.findAll();
        List<Offers> offersList1 = new ArrayList<>();
        for (Long longg : longs) {
            for (Offers offers : offersList) {
                if(offers.getPaidTypeId().equals(longg)) {
                    offersList1.add(offers);
                }
            }

        }
        return offersList1;
    }

    public Long getIdByToken(String token) {
        DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        return claims.get(TokenData.ID.getValue(), Number.class).longValue();
    }

    public String getToken() {
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8300/getToken", String.class);
        String response = responseEntity.getBody();
        return response;
    }

    public NewObj getCategoriesAndPriceById(Long id) {
        Offers offers = getOffersById(id);
        NewObj newObj = new NewObj();
        newObj.setCategoriesId(offers.getCategoryId());
        newObj.setPrice(offers.getPrice());
        return newObj;

    }
}
