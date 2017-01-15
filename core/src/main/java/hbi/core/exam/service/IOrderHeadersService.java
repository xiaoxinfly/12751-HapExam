package hbi.core.exam.service;

import com.hand.hap.system.service.IBaseService;
import hbi.core.exam.dto.OrderHeaders;

import java.util.List;

/**
 * Created by yufeng.liu on 2017-01-10.
 */
public interface IOrderHeadersService extends IBaseService<OrderHeaders>{
    List<OrderHeaders> selectOrder(OrderHeaders orderHeaders, int pageNum, int pageSize);

}
