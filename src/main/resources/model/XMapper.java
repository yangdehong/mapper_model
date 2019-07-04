package #0.mapper;

import java.lang.*;
import java.util.*;
import java.math.*;
import #0.model.#1#3;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author : #author
 * @date : #date
 */
public interface #1Mapper {

    /**
     * @Description: 分页列表
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    List<#1#3> select#1PageList(@Param("model") #1#3 #2#3, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
    /**
     * @Description: 分页统计
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    Long select#1PageCount(@Param("model") #1#3 #2#3);
    /**
     * @Description: 根据ID查询
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    #1#3 select#1ById(@Param("id") Long id);
    /**
     * @Description: 根据ID删除
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    void delete#1ById(@Param("id") Long id);
    /**
     * @Description: 新增
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    void insert#1(@Param("model") #1#3 #2#3);
    /**
     * @Description: 根据ID修改
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    void update#1ById(@Param("model") #1#3 #2#3);

}
