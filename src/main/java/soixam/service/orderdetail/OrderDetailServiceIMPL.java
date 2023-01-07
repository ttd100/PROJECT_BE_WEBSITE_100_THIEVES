package soixam.service.orderdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.OrderDetail;
import soixam.repository.IOrderDetailRepository;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailServiceIMPL implements IOrderDetailService{
    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailByUser(Long idUser) {
        return orderDetailRepository.getOrderDetailByUser(idUser);
    }
}
