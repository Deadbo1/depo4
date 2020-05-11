package application.service;

import application.models.Offers;

import java.util.List;

public interface OffersService {

    Offers getOffersById(Long id);

    void createOffers(Offers offers);

    void deleteOffers(Offers offers);

    void updateOffers(Long id, Offers offers);

    List<Offers> findAll();
}
