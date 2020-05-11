package application.service;

import application.models.Orders;

import java.util.List;

public interface OrdersService {
    Orders getOrdersById(Long id);

    void createOrders(Orders orders);

    void deleteOrders(Orders orders);

    void updateOrders(Long id, Orders orders);

    List<Orders> findAll();
}