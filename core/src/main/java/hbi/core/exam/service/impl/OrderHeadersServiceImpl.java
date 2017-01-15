package hbi.core.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.exam.dto.OrderHeaders;
import hbi.core.exam.mapper.OrderHeadersMapper;
import hbi.core.exam.service.IOrderHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHeadersServiceImpl extends BaseServiceImpl<OrderHeaders> implements IOrderHeadersService {


    @Autowired
    private OrderHeadersMapper orderHeadersMapper;


    @Override
    public List<OrderHeaders> selectOrder(OrderHeaders orderHeaders, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.orderHeadersMapper.selectOrder(orderHeaders);
    }
}
