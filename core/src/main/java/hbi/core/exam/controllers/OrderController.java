package hbi.core.exam.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.excel.dto.ColumnInfo;
import com.hand.hap.excel.dto.ExportConfig;
import com.hand.hap.excel.service.IExportService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.exam.dto.OrderHeaders;
import hbi.core.exam.dto.OrderLines;
import hbi.core.exam.service.IOrderHeadersService;
import hbi.core.exam.service.IOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yufeng.liu on 2017-01-11.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderHeadersService iOrderHeadersService;
    @Autowired
    private IExportService excelService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IOrderLinesService iOrderLinesService;

    @RequestMapping(
            value = "/query",
            produces = {"application/json"},
            method = RequestMethod.POST
    )
    public ResponseData orderQuery(@RequestBody OrderHeaders orderHeaders){
        return new ResponseData(this.iOrderHeadersService.selectOrder(orderHeaders,orderHeaders.getPage(),orderHeaders.getPagesize()));
    }

    @RequestMapping("/export")
    public void createXLS(HttpServletRequest request, @RequestParam String config, HttpServletResponse httpServletResponse) {
        IRequest requestContext = this.createRequestContext(request);
        try {
            JavaType javaType = this.objectMapper.getTypeFactory().constructParametrizedType(ExportConfig.class, ExportConfig.class, new Class[]{OrderHeaders.class, ColumnInfo.class});
            ExportConfig exportConfig = (ExportConfig)this.objectMapper.readValue(config, javaType);
             this.excelService.exportAndDownloadExcel("hbi.core.exam.mapper.OrderHeadersMapper.selectOrder", exportConfig, request, httpServletResponse, requestContext);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @RequestMapping("/details")
    public ResponseData orderDetails(@RequestBody OrderHeaders orderHeaders){
        return new ResponseData(this.iOrderLinesService.selectDetails(orderHeaders.getHeaderId()));
    }


    @RequestMapping("/remove")
    public ResponseData orderRemove(@RequestBody OrderHeaders orderHeaders){
        this.iOrderLinesService.deleteByHeaderId(orderHeaders.getHeaderId());
        this.iOrderHeadersService.deleteByPrimaryKey(orderHeaders);
        return new ResponseData();
    }


    @RequestMapping("/remove/lines")
    public ResponseData orderRemoveLines(@RequestBody List<OrderLines> orderLinesList){
        this.iOrderLinesService.batchDelete(orderLinesList);
        return new ResponseData();
    }

    @RequestMapping("/submit/lines")
    public ResponseData orderSubmit(HttpServletRequest request,@RequestBody List<OrderLines> orderLinesList){
        IRequest requestContext = this.createRequestContext(request);
        System.out.println("-------------------->");
        System.out.println("-------------------->");
        System.out.println("-------------------->");
        System.out.println("-------------------->" + orderLinesList.get(0).toString());
        System.out.println("-------------------->");
        System.out.println("-------------------->");
        System.out.println("-------------------->");

        return new ResponseData(this.iOrderLinesService.batchUpdate(requestContext,orderLinesList));
    }

    @RequestMapping("/submit/header")
    public OrderHeaders orderHeaderSubmit(HttpServletRequest request,@RequestBody OrderHeaders orderHeaders){
        IRequest requestContext = this.createRequestContext(request);
        if(orderHeaders.getHeaderId()==0){
            return this.iOrderHeadersService.insert(requestContext,orderHeaders);
        }else {
            return this.iOrderHeadersService.updateByPrimaryKey(requestContext,orderHeaders);
        }

    }

    @RequestMapping("/status")
    public Map<String,Boolean> orderStatusChange(@RequestBody OrderHeaders orderHeaders){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",true);
        return map;
    }
}
