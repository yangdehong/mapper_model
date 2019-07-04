package #0.service.impl;

import #0.model.#1#3;
import #0.mapper.#1Mapper;
import #0.service.#1Service;

import java.lang.Long;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *
 * @author : #author
 * @date : #date
 */
@Service
public class #1ServiceImpl implements #1Service {

    @Resource
    private #1Mapper #2Mapper;

    /**
     * @Description: 分页查询
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @Override
    public void select#1PageList(#1#3 #2#3, int page, int pageSize){
        PageHelper.startPage(page, pageSize);
        #2Mapper.select(#2#3);
    }
    /**
     * @Description: 根据ID查询
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @Override
    public #1#3 select#1ById(Long id){
        return #2Mapper.selectByPrimaryKey(id);
    }
    /**
     * @Description: 根据ID删除
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @Override
    public void delete#1ById(Long id){
        #2Mapper.deleteByPrimaryKey(id);
    }
    /**
     * @Description: 新增
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @Override
    public void insert#1(#1#3 #2#3){
        #2Mapper.insert(#2#3);
    }
    /**
     * @Description: 根据ID修改
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @Override
    public void update#1ById(#1#3 #2#3){
        #2Mapper.updateByPrimaryKeySelective(#2#3);
    }

}
