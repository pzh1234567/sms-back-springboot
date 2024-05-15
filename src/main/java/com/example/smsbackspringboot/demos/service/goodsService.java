package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.entiy.RolePower;
import com.example.smsbackspringboot.demos.entiy.Supplier;
import com.example.smsbackspringboot.demos.mapper.goodsMapper;
import com.example.smsbackspringboot.demos.mapper.supplierMapper;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.commom.GoodsSupplierVo;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import com.example.smsbackspringboot.demos.vo.param.goodQueryReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class goodsService {

    @Autowired
    goodsMapper goodsMapper;

    @Autowired
    supplierMapper supplierMapper;


    /**
     * 根据查询条件获取商品信息
     * @param
     * @return
     */
    public Result getGoodsList(String goodName, Integer goodType, Integer pageNum, Integer pageSize, Integer goodStatus) {
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(goodType!=null,Goods::getGoodType,goodType);
        wrapper.eq(goodStatus!=null,Goods::getGoodStatus,goodStatus);
        wrapper.like(goodName!=null,Goods::getGoodName,goodName);

        Page<Goods> page = new Page<Goods>(pageNum,pageSize);
        System.out.println("2222222"+goodType);
        IPage<Goods> goodsIPage = goodsMapper.selectPage(page, wrapper);
        List<Goods> result = goodsIPage.getRecords();
        List<GoodsSupplierVo> goodsSupplierVoList = getGoodsSupplierList(result);
//        List<Long> goodsIdList = result.stream().map(Goods::getGoodId).collect(Collectors.toList());
        System.out.println(result);
        return Result.success(new PageVo(goodsSupplierVoList,goodsIPage.getTotal()));
    }

    public List<GoodsSupplierVo> getGoodsSupplierList(List<Goods> result){
        List<GoodsSupplierVo> goodsSupplierVoList = new ArrayList<>();
        for (Goods goods:result){
            Long id = goods.getSupplierId();
            Supplier supplier = getSupplier(id);
            GoodsSupplierVo goodsSupplierVo = BeanCopyUtils.copyBean(goods,GoodsSupplierVo.class);
            goodsSupplierVo.setSupplierName(supplier.getName());
            goodsSupplierVoList.add(goodsSupplierVo);
        }
        return goodsSupplierVoList;
    }

    public Supplier getSupplier(Long id){
        Supplier supplier = supplierMapper.selectById(id);
        return supplier;
    }

    /**
     * 根据id条件获取商品信息
     * @param id
     * @return
     */
    public Goods getGoodById(String id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }

    /**
     * 根据Id删除商品
     */
    public Result deletedGoodById(Long id){
        int count = goodsMapper.deleteById(id);
        return Result.success(count);
    };

    /**
     * 根据Id编辑商品
     */

    public Result updateGoodsInfoById(Goods goods){
        int count = goodsMapper.updateById(goods);
        return Result.success(count);
    }

    /**
     * 编辑商品销量
     * @param id
     * @param num
     * @return
     */
    public int reduceGoodsInventory(Long id, int num){
        Goods goods = goodsMapper.selectById(id);
        int sold = goods.getSold() + num;
        int inventory = goods.getGoodTotal() - sold;
        goods.setGoodInventory(inventory);
        goods.setSold(sold);
        int count = goodsMapper.updateById(goods);
        return count;
    }

    /**
     * 库存警告，查询库存小于10的商品
     * @return
     */
    public Result getGoodsByInventory(){
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        wrapper.lt(Goods::getGoodInventory,10);
        List<Goods> goodsList = goodsMapper.selectList(wrapper);
        List<GoodsSupplierVo> goodsSupplierVoList = getGoodsSupplierList(goodsList);
        return Result.success(goodsSupplierVoList);
    }

    /**
     * 获取不同种类商品的销量
     * @return
     */
    public Result getTypeSold(){
        List<Integer> typeSolds = new ArrayList<>();
        int i = 1;
        while(i <= 7) {
            LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Goods::getGoodType,i);
            List<Goods> goodsList = goodsMapper.selectList(wrapper);
            int count = 0;
            for (Goods goods:goodsList){
                count += goods.getGoodTotal()-goods.getGoodInventory();
            }
            typeSolds.add(count);
            i++;
        }
        return Result.success(typeSolds);
    }


    public Result gethighsellingGood(){
        // 创建 QueryWrapper 对象
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        // 设置查询条件，按销量降序排列
        wrapper.orderByDesc("sold").last("limit 3");
        // 执行查询并返回结果
        List<Goods> goodsList = goodsMapper.selectList(wrapper);
        return Result.success(goodsList);
    }
}
