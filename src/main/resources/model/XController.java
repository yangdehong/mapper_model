package #0.controller;

import #0.service.#1Service;
import #0.model.#1#3;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
*
* @author : #author
* @date : #date
*/
@RestController
@RequestMapping("#2")
public class #1Controller {

    @Resource
    private #1Service #2Service;

    /**
     * @Description: 分页查询列表
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @GetMapping("select#1PageList")
    public String select#1PageList(#1#3 #2#3, Integer page, Integer pageSize){
        page = (page == null || page < 0) ? 0 : page - 1;
        pageSize = (pageSize == null) ? 20 : pageSize;
        #2Service.select#1PageList(#2#3, page, pageSize);
        return "";
    }
    /**
     * @Description: 根据ID查询
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @GetMapping("select#1ById")
    public String select#1ById(@RequestParam Long id){
        #2Service.select#1ById(id);
        return "";
    }
    /**
     * @Description: 根据ID删除
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @DeleteMapping("delete#1ById")
    public String delete#1ById(@RequestParam Long id){
        #2Service.delete#1ById(id);
        return "";
    }
    /**
     * @Description: 新增
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @PostMapping("insert#1")
    public String insert#1(@RequestBody #1#3 #2#3){
        #2Service.insert#1(#2#3);
        return "";
    }
    /**
     * @Description: 根据ID修改
     * @Param:
     * @return:
     * @Author: #author
     * @Date: #date
    */
    @PostMapping("update#1ById")
    public String update#1ById(@RequestBody #1#3 #2#3){
        #2Service.update#1ById(#2#3);
        return "";
    }

}
