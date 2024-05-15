package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.*;
import com.example.smsbackspringboot.demos.mapper.goodsMapper;
import com.example.smsbackspringboot.demos.mapper.purchaseGoodsMapper;
import com.example.smsbackspringboot.demos.mapper.purchaseMapper;
import com.example.smsbackspringboot.demos.mapper.supplierMapper;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.commom.GoodsItemVo;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import com.example.smsbackspringboot.demos.vo.commom.PurchaseInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class purchaseService {
    @Autowired
    purchaseMapper purchaseMapper;

    @Autowired
    purchaseGoodsMapper purchaseGoodsMapper;

    @Autowired
    goodsMapper goodsMapper;

    @Autowired
    supplierMapper supplierMapper;
    /**
     * 功能：查询供应单列表
     **/
    public Result getPurchaseList(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Purchase> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Purchase::getCreateTime);
//        wrapper.like(name!=null,Purchase::getSupplierName,name);
//        wrapper.like(goodName!=null,Staff::getGoodName,goodName);
        Page<Purchase> page = new Page<Purchase>(pageNum,pageSize);
//        System.out.println("2222222"+goodType);
        IPage<Purchase> purchaseIPage = purchaseMapper.selectPage(page, wrapper);
        List<Purchase> result = purchaseIPage.getRecords();
        //根据供应单信息获取商品信息
        List<PurchaseInfoVo> purchaseInfoVoList = getPurchaseInfoByPurchase(result);
        System.out.println(result);
        return Result.success(new PageVo(purchaseInfoVoList,purchaseIPage.getTotal()));
    }

    private List<PurchaseInfoVo> getPurchaseInfoByPurchase(List<Purchase> purchaseList){
        List<PurchaseInfoVo> purchaseInfoVoList = new ArrayList<>();
        for (Purchase purchase:purchaseList){
//           //获取供应单id
            Long id = purchase.getId();
            LambdaQueryWrapper<PurchaseGoods> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(id!=null,PurchaseGoods::getPurchaseId,id);
            List<PurchaseGoods> purchaseGoodsList = purchaseGoodsMapper.selectList(wrapper);
            List<GoodsItemVo> goodsList = getGoodsItem(purchaseGoodsList);
            PurchaseInfoVo purchaseInfoVo = BeanCopyUtils.copyBean(purchase, PurchaseInfoVo.class);
            purchaseInfoVo.setGoodsList(goodsList);
            Supplier supplier = getSupplier(purchase.getSupplierId());
            purchaseInfoVo.setSupplierName(supplier.getName());
            purchaseInfoVoList.add(purchaseInfoVo);
        }
        return purchaseInfoVoList;
    }


    /**
     *
     * @param purchaseGoodsList
     * @return
     */
    public List<GoodsItemVo> getGoodsItem(List<PurchaseGoods> purchaseGoodsList){
        List<GoodsItemVo> goodsList= new ArrayList<>();
        if(purchaseGoodsList != null){
            for (PurchaseGoods purchaseGoods : purchaseGoodsList) {
                Long goodId = purchaseGoods.getGoodId();
                Integer count = purchaseGoods.getGoodCount();
                // 对goodId进行处理
                System.out.println("GoodId: " + goodId);
                Goods goods = getGoodById(goodId);
                GoodsItemVo goodsItem = BeanCopyUtils.copyBean(goods,GoodsItemVo.class);
                goodsItem.setCount(purchaseGoods.getGoodCount());
                goodsItem.setCount(count);
                goodsItem.setGoodId(goodId);
                goodsItem.setGoodCost(purchaseGoods.getGoodCost());
                goodsList.add(goodsItem);
            }
        }else {
            System.out.println("找不到订单商品");
        }
        return goodsList;
    }

    /**
     * 根据id条件获取商品信息
     * @param id
     * @return
     */
    public Goods getGoodById(Long id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }

    /**
     * 根据id获取供应商信息
     * @param id
     * @return
     */
    public Supplier getSupplier(Long id){
        Supplier supplier = supplierMapper.selectById(id);
        return supplier;
    }

    /**
     * 根据name获取供应商信息
     * @param name
     * @return
     */
    public Supplier getSupplierName(String name){
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper();
        wrapper.eq(name!=null,Supplier::getName,name);
        Supplier supplier = supplierMapper.selectOne(wrapper);
        return supplier;
    }

    /**
     * 进货
     * @param purchaseInfoVo
     * @return
     */
    public int addPurchase(PurchaseInfoVo purchaseInfoVo){
        Purchase purchase = BeanCopyUtils.copyBean(purchaseInfoVo,Purchase.class);
        Supplier supplier = getSupplierName(purchaseInfoVo.getSupplierName());
        purchase.setSupplierId(supplier.getId());
        int count = purchaseMapper.insert(purchase);
        Long purchaseId = purchase.getId();
        int flag;
        for (GoodsItemVo goodsItemVo:purchaseInfoVo.getGoodsList()){
            Goods goods = BeanCopyUtils.copyBean(goodsItemVo,Goods.class);

            goods.setSupplierId(supplier.getId());
            System.out.println(goods.getGoodInventory());
            if(goods.getGoodId() == null){
                goods.setGoodInventory(goodsItemVo.getCount());
                goods.setGoodTotal(goodsItemVo.getCount());
                goods.setStackingCount(0);
                goods.setSold(0);
                goods.setGoodStatus(0);
                flag = goodsMapper.insert(goods);
                Long id = goods.getGoodId();
            }
            System.out.println(goods);
            Long id = goods.getGoodId();
            flag = goodsMapper.updateById(goods);
//            System.out.println(goodsItemVo.getCount());
            PurchaseGoods purchaseGoods = new PurchaseGoods();
            purchaseGoods.setGoodId(id);
            purchaseGoods.setPurchaseId(purchaseId);
            purchaseGoods.setGoodCount(goodsItemVo.getCount());
            purchaseGoods.setGoodCost(goodsItemVo.getGoodCost());
            int tmp = purchaseGoodsMapper.insert(purchaseGoods);
            count = count*flag*tmp;
        }
        return count;
    }

    /**
     * 编辑供应单信息
     * @param purchase
     * @return
     */
    public int editPurchase(Purchase purchase){
        int count = purchaseMapper.updateById(purchase);
        return count;
    }

    /**
     * 删除供应单信息
     * @param id
     * @return
     */
    public int deletePurchase(Long id){
        int count = purchaseMapper.deleteById(id);
        return count;
    }


}
