package com.example.smsbackspringboot.demos.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smsbackspringboot.demos.common.AliPayConfig;
import com.example.smsbackspringboot.demos.entiy.AliPay;
import com.example.smsbackspringboot.demos.entiy.Customer;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.mapper.orderMapper;
import com.example.smsbackspringboot.demos.service.customerService;
import com.example.smsbackspringboot.demos.service.goodsService;
import com.example.smsbackspringboot.demos.service.orderGoodsService;
import com.example.smsbackspringboot.demos.service.orderService;
import com.example.smsbackspringboot.demos.vo.commom.GoodsItemVo;
import com.example.smsbackspringboot.demos.vo.commom.OrderInfoVo;
import com.example.smsbackspringboot.demos.vo.param.GoodItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author
 * @Date Created in  2023/5/5 15:23
 * @DESCRIPTION:
 * @Version V1.0
 */
@RestController
@RequestMapping("alipay")
@Transactional(rollbackFor = Exception.class)
public class AliPayController {

    @Resource
    private AliPayConfig aliPayConfig;

    @Resource
    private com.example.smsbackspringboot.demos.mapper.orderMapper orderMapper;

    @Autowired
    goodsService goodsService;

    @Autowired
    com.example.smsbackspringboot.demos.service.orderService orderService;
    @Autowired
    com.example.smsbackspringboot.demos.service.customerService customerService;

    private static final String GATEWAY_URL ="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT ="JSON";
    private static final String CHARSET ="utf-8";
    private static final String SIGN_TYPE ="RSA2";

    @GetMapping("/pay") // 前端路径参数格式?subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no",aliPay.getTraceNo());
        bizContent.set("total_amount",aliPay.getTotalAmount());
        bizContent.set("subject",aliPay.getSubject());
        bizContent.set("product_code","FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());

        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");


            String sign=params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content,sign,aliPayConfig.getAlipayPublicKey(),"UTF-8");
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

//                double cost = (double)params.get("buyer_pay_amount");
                String time = params.get("gmt_payment");
                // 更新订单已支付的逻辑代码
                QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("order_id",tradeNo);
                Order orders = orderMapper.selectOne(queryWrapper);
//                String time= getTime();
                List<Order> orderList = new ArrayList<>();
                orderList.add(orders);

                List<OrderInfoVo>  orderInfoList = orderService.listOrderDetailInfoByOrderId(orderList);
                if (!orderInfoList.isEmpty()) {
                    // 获取第一个元素
                    OrderInfoVo firstOrderInfo = orderInfoList.get(0);
                    for (GoodsItemVo item : firstOrderInfo.getGoodsList()) {
                        Long goodsId = item.getGoodId();
                        int num = item.getCount();
                        //减少对应商品库存
                        int flag = goodsService.reduceGoodsInventory(goodsId, num);
                    }
                }

                if(orders!=null){
                    orders.setOrderStatus(1);
                    orders.setPaytime(time);
//                    orders.setPayState(2);
                    orderMapper.updateById(orders);
                }
            }
        }
        return "success";
    }

    public static String getTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedDateTime = now.format(formatter);

        // 输出格式化后的时间
        System.out.println(formattedDateTime);
        return  formattedDateTime;
    }

}
