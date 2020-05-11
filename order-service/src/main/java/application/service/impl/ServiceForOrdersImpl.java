package application.service.impl;

import application.models.Orders;
import application.models.TokenData;
import application.repository.OrdersDao;
import application.service.OrdersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServiceForOrdersImpl implements OrdersService {

    private final OrdersDao ordersDao;
    private final ServiceForStatusImpl serviceForStatusImpl;

    @Value("dgf32kld23")
    private String key;

    public ServiceForOrdersImpl (OrdersDao ordersDao, ServiceForStatusImpl serviceForStatusImpl) {
        this.ordersDao = ordersDao;
        this.serviceForStatusImpl = serviceForStatusImpl;
    }

    @Override
    public Orders getOrdersById(Long id) {
        return ordersDao.getOrdersById(id);
    }

    @Override
    public void createOrders(Orders orders) {
        if (serviceForStatusImpl.checkStatus(orders.getStatusId())) {
            orders.setStatusId(serviceForStatusImpl.getCheckStatus(orders.getStatusId()));
        }
        ordersDao.save(orders);
    }

    @Override
    public void deleteOrders(Orders orders) {
        ordersDao.delete(orders);
    }

    @Override
    public void updateOrders(Long id, Orders orders) {
        Orders orders1 = getOrdersById(id);
        orders1.setCustomerId(orders.getCustomerId());
        orders1.setDate(orders.getDate());
        orders1.setName(orders.getName());
        orders1.setOfferId(orders.getOfferId());
        orders1.setPaid(orders.isPaid());
        orders1.setStatusId(orders.getStatusId());
        ordersDao.save(orders1);
    }

    @Override
    public List<Orders> findAll() {
        return ordersDao.findAll();
    }

    public void updateOrderStatus (Long id, Long statusId) {
        Orders orders = getOrdersById(id);
        orders.setStatusId(serviceForStatusImpl.getStatusById(statusId));
        ordersDao.save(orders);
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
}
