package soixam.service.orderdetail;

import soixam.model.OrderDetail;
import soixam.service.IGeneric;

import java.util.List;

public interface IOrderDetailService extends IGeneric<OrderDetail> {
    List<OrderDetail> getOrderDetailByUser(Long idUser);
}
