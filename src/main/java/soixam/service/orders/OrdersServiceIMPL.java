package soixam.service.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Orders;
import soixam.repository.IOrdersRepository;

import java.util.List;
import java.util.Optional;
@Service
public class OrdersServiceIMPL implements IOrdersService{
    @Autowired
    private IOrdersRepository ordersRepository;
    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }
}
