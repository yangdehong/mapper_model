package com.ydh.redsheep.service;

import com.ydh.redsheep.entity.ColumnBO;
import com.ydh.redsheep.util.ApplicationConstant;
import com.ydh.redsheep.util.Underline2CamelUtil;

import java.util.List;

/**
*
* @author : yangdehong
* @date : 2019-07-04 15:13
*/
public class ProductFileDirector {

    private List<ColumnBO> columnList;

    private String tableName;
    private String bigTableName;
    private String smallTableName;

    public ProductFileDirector(String tableName) {
        this.columnList = OriginData.productColumnList(tableName);
        this.tableName = tableName;
        this.bigTableName = Underline2CamelUtil.underline2Camel(tableName, false);
        this.smallTableName = Underline2CamelUtil.underline2Camel(tableName);
    }

    public void start() {
        // 生产文件
        if (ApplicationConstant.isTkMybatis) {
            ProductFileTkService productFileService = new ProductFileTkService();
            productFileService.productController(tableName, bigTableName, smallTableName);
            productFileService.productService(tableName, bigTableName, smallTableName);
            productFileService.productServiceImpl(tableName, bigTableName, smallTableName);
            productFileService.productMapper(tableName, bigTableName, smallTableName);
            productFileService.productMapperXml(tableName, bigTableName, smallTableName, columnList);
            productFileService.productModel(tableName, bigTableName, smallTableName, columnList);
            productFileService.productModel2(tableName, bigTableName, smallTableName, columnList);
        } else {
            ProductFileService productFileService = new ProductFileService();
            productFileService.productController(tableName, bigTableName, smallTableName);
            productFileService.productService(tableName, bigTableName, smallTableName);
            productFileService.productServiceImpl(tableName, bigTableName, smallTableName);
            productFileService.productMapper(tableName, bigTableName, smallTableName);
            productFileService.productMapperXml(tableName, bigTableName, smallTableName, columnList);
            productFileService.productModel(tableName, bigTableName, smallTableName, columnList);
        }
    }

}
