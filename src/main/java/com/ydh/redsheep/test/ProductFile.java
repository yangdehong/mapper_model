package com.ydh.redsheep.test;

import com.ydh.redsheep.entity.ColumnBO;
import com.ydh.redsheep.util.*;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author : yangdehong
* @date : 2019-07-04 15:13
*/
public class ProductFile {

    private List<ColumnBO> columnList = new ArrayList<>();

    private String tableName;

    private String bigTableName;

    private String smallTableName;

    public static final String allColumnSql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA. COLUMNS WHERE table_name = ? AND table_schema = (select database())";

    public ProductFile(String tableName, String bigTableName, String smallTableName) {
        this.tableName = tableName;
        this.bigTableName = bigTableName;
        this.smallTableName = smallTableName;
    }

    public void start() {
        productColumnList(allColumnSql, tableName);

        // 生产文件
        productController(tableName);
        productService(tableName);
        if (ApplicationContent.isTkMybatis) {
            productServiceImpl2(tableName);
            productMapper2(tableName);
            productModel2(tableName);
            productXml2(tableName);
        } else {
            productServiceImpl(tableName);
            productMapper(tableName);
            productModel(tableName);
            productXml(tableName);
        }
    }

    public String transferContent(String filePath, String tableName) {

        String content = IoUtil.readModelFile(filePath);
        content = content.replaceAll("#0", ApplicationContent.packages).replaceAll("#1", bigTableName)
                .replaceAll("#2", smallTableName).replaceAll("#3", ApplicationContent.modelSuffix).replaceAll("#4", tableName)
                .replaceAll("#author", ApplicationContent.author).replaceAll("#date", LocalDateTime.now().toString());

        return content;
    }

    /**
     * @Description: 生成Controller
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productController(String tableName) {

        String path = "src/main/resources/model/XController";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        IoUtil.writeOutFile(ApplicationContent.controllerPath + File.separator,
                bigTableName + "Controller.java", content);
    }

    /**
     * @Description: 生成service
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productService(String tableName) {

        String path = "src/main/resources/model/XService";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        IoUtil.writeOutFile(ApplicationContent.servicePath + File.separator,
                bigTableName + "Service.java", content);
    }

    /**
     * @Description: 生成serviceimpl
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productServiceImpl(String tableName) {
        String path = "src/main/resources/model/XServiceImpl";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        IoUtil.writeOutFile(ApplicationContent.servicePath + File.separator + "impl" + File.separator,
                bigTableName + "ServiceImpl.java", content);
    }
    /**
     * @Description: 生成serviceimpl
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productServiceImpl2(String tableName) {
        String path = "src/main/resources/model/XServiceImpl2";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        IoUtil.writeOutFile(ApplicationContent.servicePath + File.separator + "impl" + File.separator,
                bigTableName + "ServiceImpl.java", content);
    }

    /**
     * @Description: 生成mapper
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productMapper(String tableName) {
        String path = "src/main/resources/model/XMapper";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        IoUtil.writeOutFile(ApplicationContent.mapperPath + File.separator,
                bigTableName + "Mapper.java", content);
    }

    /**
     * @Description: 生成mapper
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productMapper2(String tableName) {
        String path = "src/main/resources/model/XMapper2";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        IoUtil.writeOutFile(ApplicationContent.mapperPath + File.separator,
                bigTableName + "Mapper.java", content);
    }

    /**
     * @Description: 生成xml
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productXml(String tableName) {

        String path = "src/main/resources/model/XMapper.xml";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        ProductSql sql = new ProductSql(columnList);
        String baseList = sql.productBaseList();
        String baseMap = sql.productBaseMap();
        String condition = sql.productCondition();
        String baseValueList = sql.productBaseValueList();
        String updateValue = sql.productUpdateValue();

        content = content.replaceAll("#baseList", baseList).replaceAll("#condition", condition)
                .replaceAll("#baseMap", baseMap)
                .replaceAll("#baseValueList", baseValueList).replaceAll("#updateValue", updateValue);

        IoUtil.writeOutFile(ApplicationContent.mapperPath + File.separator,
                bigTableName + "Mapper.xml", content);
    }

    /**
     * @Description: 生成xml
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productXml2(String tableName) {

        String path = "src/main/resources/model/XMapper2.xml";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);

        ProductSql sql = new ProductSql(columnList);
        String baseList = sql.productBaseList();
        String baseMap = sql.productBaseMap();
        content = content.replaceAll("#baseList", baseList)
                .replaceAll("#baseMap", baseMap);

        IoUtil.writeOutFile(ApplicationContent.mapperPath + File.separator,
                bigTableName + "Mapper.xml", content);
    }

    /**
     * @Description: 生成model
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productModel(String tableName) {
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

        String path = "src/main/resources/model/X";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);
        content = content.replaceAll("#content", sb.toString());

        IoUtil.writeOutFile(ApplicationContent.modelPath + File.separator,
                bigTableName + ApplicationContent.modelSuffix + ".java", content);
    }

    /**
     * @Description: 生成model
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public void productModel2(String tableName) {
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

        String path = "src/main/resources/model/X2";
        String content = transferContent(ApplicationContent.projectPath + path, tableName);
        content = content.replaceAll("#lombokdata", "@Data").replaceAll("#content", sb.toString());

        IoUtil.writeOutFile(ApplicationContent.modelPath + File.separator,
                bigTableName + ApplicationContent.modelSuffix + ".java", content);
    }

    public void productColumnList(String allColumnSql, String tableName) {
        List<String> params = new ArrayList<>();
        params.add(tableName);
        // 创建DBHelper对象
        JdbcUtil db = new JdbcUtil(allColumnSql, params);
        // 转驼峰
        ResultSet ret = null;
        try {
            // 执行语句，得到结果集
            ret = db.pst.executeQuery();
            while (ret.next()) {
                String columnName = ret.getString(1);
                String dataType = ret.getString(2);
                String columnComment = ret.getString(3);
                columnList.add(new ColumnBO(columnName, Underline2CamelUtil.underline2Camel(columnName), dataType,
                        DataTypeTransformUtil.dataTypeTransform("mysql", dataType), columnComment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
