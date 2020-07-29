package com.ydh.redsheep.service;

import com.ydh.redsheep.entity.ColumnBO;
import com.ydh.redsheep.util.ApplicationContent;
import com.ydh.redsheep.util.IoUtil;
import com.ydh.redsheep.util.TransferContentUtil;

import java.io.File;
import java.util.List;

/**
*
* @author : yangdehong
* @date : 2020-07-29 11:35
*/
public class ProductFileService {

    public void productController(String tableName, String bigTableName, String smallTableName) {
        String path = "src/main/java/model/XController";
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);


        content = content.replace("#modelPath", ApplicationContent.modelPath).replace("#mapperPath", ApplicationContent.mapperPath)
                .replace("#servicePath", ApplicationContent.servicePath).replace("#controllerPath", ApplicationContent.controllerPath);

        IoUtil.writeOutFile(ApplicationContent.controllerPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Controller.java", content);
    }

    public void productService(String tableName, String bigTableName, String smallTableName) {
        String path = "src/main/java/model/XService";
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath",ApplicationContent.modelPath).replace("#mapperPath", ApplicationContent.mapperPath)
                .replace("#servicePath", ApplicationContent.servicePath);

        IoUtil.writeOutFile(ApplicationContent.servicePath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Service.java", content);
    }

    public void productServiceImpl(String tableName, String bigTableName, String smallTableName) {
        String path = "src/main/java/model/XServiceImpl";
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath",ApplicationContent.modelPath).replace("#mapperPath", ApplicationContent.mapperPath)
                .replace("#servicePath", ApplicationContent.servicePath);

        IoUtil.writeOutFile(ApplicationContent.servicePath.replaceAll("\\.", File.separator) + File.separator + "impl" + File.separator,
                bigTableName + "ServiceImpl.java", content);
    }

    public void productMapper(String tableName, String bigTableName, String smallTableName) {
        String path = "src/main/java/model/XMapper";
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath",ApplicationContent.modelPath).replace("#mapperPath", ApplicationContent.mapperPath);

        IoUtil.writeOutFile(ApplicationContent.mapperPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Mapper.java", content);
    }

    public void productMapperXml(String tableName, String bigTableName, String smallTableName, List<ColumnBO> columnList) {
        String path = "src/main/java/model/XMapper.xml";
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        ProductSql sql = new ProductSql(columnList);
        String baseList = sql.productBaseList();
        String baseMap = sql.productBaseMap();
        String condition = sql.productCondition();
        String baseValueList = sql.productBaseValueList();
        String updateValue = sql.productUpdateValue();

        content = content.replace("#modelPath",ApplicationContent.modelPath).replace("#mapperPath", ApplicationContent.mapperPath)
                .replaceAll("#baseList", baseList).replaceAll("#condition", condition).replaceAll("#baseMap", baseMap)
                .replaceAll("#baseValueList", baseValueList).replaceAll("#updateValue", updateValue);

        IoUtil.writeOutFile(ApplicationContent.mapperPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Mapper.xml", content);
    }

    public void productModel(String tableName, String bigTableName, String smallTableName, List<ColumnBO> columnList) {
        // 文件全部内容
        StringBuffer sb = new StringBuffer();
        // 所有参数
        for (ColumnBO columnBO : columnList) {
            String columnName = columnBO.getColumnNameTrans();
            String dataType = columnBO.getDataTypeTrans();
            String columnComment = columnBO.getColumnComment();
            sb.append("    private ").append(dataType).append(" ").
                    append(columnName).append("; // ").append(columnComment).append("\r\n");
        }

        String path = "src/main/java/model/X";
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);
        content = content.replace("#modelPath",ApplicationContent.modelPath).replace("#content", sb.toString());

        IoUtil.writeOutFile(ApplicationContent.modelPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + ApplicationContent.modelSuffix + ".java", content);
    }

}
