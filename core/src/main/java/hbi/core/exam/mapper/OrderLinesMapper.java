package hbi.core.exam.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.exam.dto.OrderLines;

import java.util.List;

public interface OrderLinesMapper extends Mapper<OrderLines> {

    List<OrderLines> selectDetails(Long headerId);
    int deleteByHeaderId(Long headerId);

}
