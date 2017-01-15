package hbi.core.exam.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.exam.dto.Companys;
import hbi.core.exam.mapper.CompanysMapper;
import hbi.core.exam.service.CompanysService;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanysServiceImpl extends BaseServiceImpl<Companys> implements CompanysService{

    @Autowired
    private CompanysMapper companysMapper;


}
