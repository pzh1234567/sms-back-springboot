package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.mapper.goodsMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import com.example.smsbackspringboot.demos.vo.param.goodQueryReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class goodsService {

    @Autowired
    goodsMapper goodsMapper;

    /**
     * 功能：新增商品
     **/
//    public void addGoods(TGoodEntity good) {
//        System.out.println("1111111");
//        TGoodEntity dbGood = goodsMapper.selectById(good.getGoodId());
//        if (dbGood != null) {
//            throw new CustomException("该用户已经存在");
//        } else {
//            goodsMapper.addGoods(good);
//        }
//    }

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
        System.out.println(result);


        return Result.success(new PageVo(goodsIPage.getRecords(),goodsIPage.getTotal()));
    }
    /**
     * 根据id条件获取商品信息
     * @param id
     * @return
     */
    public List<Goods> getGoodById(String id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper();
        wrapper.eq("good_id",id);
        List<Goods> goodList = goodsMapper.selectList(wrapper);
        return goodList;
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

}
