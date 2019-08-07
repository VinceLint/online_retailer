package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.DropshipMapper;
import cn.neusoft.retailer.web.pojo.Dropship;
import cn.neusoft.retailer.web.service.DropshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DropshipServiceImpl implements DropshipService {
    @Autowired
    DropshipMapper dropshipMapper;

    public boolean deleteByPrimaryKey(Integer dropshipId){
        return dropshipMapper.deleteByPrimaryKey(dropshipId)!=0;
    }

    public Boolean insert(Dropship record){
        if (dropshipMapper.insert(record)!=0)return true;
        return false;
//        return dropshipMapper.insert(record)!=0;
    }

    public Dropship selectByPrimaryKey(Integer dropshipId){
        return dropshipMapper.selectByPrimaryKey(dropshipId);
    }

    public List<Dropship> selectByBvoIdAndGoodsId(Map<String,Object> map){
        return dropshipMapper.selectByBvoIdAndGoodsId(map);
    }

    @Override
    public boolean existByByBvoIdAndGoodsId(Map<String, Object> map) {
        boolean b = false;
        int bvoId=(int)map.get("bvoId");
        List<Dropship> dropships=dropshipMapper.selectByBvoId(bvoId);
        int goodsId=(int) map.get("goodsId");
        for (Dropship dropship:dropships){
            if (dropship.getGoodsId()==goodsId){
                b=true;
                break;
            }
            else{
                b=false;
            }
        }
        return b;
    }


}
