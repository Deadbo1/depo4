package application.controller;

import application.models.Orders;
import application.models.NewObj;
import application.models.Offers;
import application.models.Modl;
import application.service.impl.ServiceForOrdersImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForOrders {

    private static String k;
    private Long id;

    private final ServiceForOrdersImpl serviceForOrdersImpl;

    public ControllerForOrders(ServiceForOrdersImpl serviceForOrdersImpl) {
        this.serviceForOrdersImpl = serviceForOrdersImpl;
    }

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return serviceForOrdersImpl.findAll();
    }

    @GetMapping("/orders/{id}")
    public Orders getOrdersById(@PathVariable("id") Long id) {
        return serviceForOrdersImpl.getOrdersById(id);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrders(@RequestBody Orders orders) {
        serviceForOrdersImpl.createOrders(orders);
    }

    @PutMapping("/orders/{id}")
    public void updateOrders(@PathVariable("id") Long id, @RequestBody Orders orders) {
        serviceForOrdersImpl.updateOrders(id, orders);
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrders(@PathVariable("id") Long id) {
        serviceForOrdersImpl.deleteOrders(getOrdersById(id));
    }

    @PatchMapping("/orders/{orderId}/updateStatus/{statusId}")
    public void updateOredersStatus(@PathVariable("orderId") Long orderId, @PathVariable("statusId") Long statusId) {
        serviceForOrdersImpl.updateOrderStatus(orderId, statusId);
    }

    @GetMapping("/orders/getIdByToken")
    public Long getIdByToken() {
        k = serviceForOrdersImpl.getToken();
        id = serviceForOrdersImpl.getIdByToken(k);
        return id;
    }

    @GetMapping("/orders/getCategoriesAndPriceByOffersId/{id}")
    public NewObj getCategoriesAndPrice(@PathVariable("id") Long id) {
        ResponseEntity<NewObj> responseEntity = new RestTemplate().getForEntity("http://localhost:8200/offers/getCategoriesAndPriceById/" + id, NewObj.class);
        NewObj response = responseEntity.getBody();
        return response;
    }

    @PostMapping("/order/createByTokenAndOffer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createByTokenAndOffer(@RequestBody Modl modl) {
        ResponseEntity<Offers> responseEntity = new RestTemplate().getForEntity("http://localhost:8200/offers/getById/" + modl.getId(), Offers.class);
        Offers response = responseEntity.getBody();
        Orders orders = new Orders();
        orders.setOfferId(response.getId());
        orders.setName(response.getName());
        orders.setCustomerId(getIdByToken());
        orders.setPaid(false);
        orders.setDate(LocalDateTime.now());
        serviceForOrdersImpl.createOrders(orders);
    }

}
