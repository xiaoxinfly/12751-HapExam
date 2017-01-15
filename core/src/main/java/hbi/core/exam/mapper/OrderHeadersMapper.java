package hbi.core.exam.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.exam.dto.OrderHeaders;

import java.util.List;

public interface OrderHeadersMapper extends Mapper<OrderHeaders>{

    List<OrderHeaders> selectOrder(OrderHeaders orderHeaders);
}
