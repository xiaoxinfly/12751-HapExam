package hbi.core.exam.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.exam.dto.OrderLines;
import hbi.core.exam.mapper.OrderLinesMapper;
import hbi.core.exam.service.IOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yufeng.liu on 2017-01-13.
 */
@Service
public class OrderLinesServiceImpl extends BaseServiceImpl<OrderLines> implements IOrderLinesService{

    @Autowired
    private OrderLinesMapper orderLinesMapper;

    @Override
    public List<OrderLines> selectDetails(Long headerId) {
        return this.orderLinesMapper.selectDetails(headerId);
    }

    @Override
    public int deleteByHeaderId(Long headerId) {
        return this.orderLinesMapper.deleteByHeaderId(headerId);
    }
}
