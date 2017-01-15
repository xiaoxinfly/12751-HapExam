package hbi.core.exam.service;

import com.hand.hap.system.service.IBaseService;
import hbi.core.exam.dto.OrderLines;

import java.util.List;

/**
 * Created by yufeng.liu on 2017-01-13.
 */
public interface IOrderLinesService extends IBaseService<OrderLines>{

    List<OrderLines> selectDetails(Long headerId);

    int deleteByHeaderId(Long headerId);
}
