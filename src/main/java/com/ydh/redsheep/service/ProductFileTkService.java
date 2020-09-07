package com.ydh.redsheep.service;

import com.ydh.redsheep.entity.ColumnBO;
import com.ydh.redsheep.util.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @author : yangdehong
 * @date : 2020-07-29 11:35
 */
public class ProductFileTkService {

    public void productController(String tableName, String bigTableName, String smallTableName) {
        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_CONTROLLER_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath", ApplicationConstant.modelPath).replace("#mapperPath", ApplicationConstant.mapperPath)
                .replace("#servicePath", ApplicationConstant.servicePath).replace("#controllerPath", ApplicationConstant.controllerPath);

        IoUtil.writeOutFile(ApplicationConstant.controllerPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Controller.java", content);
    }

    public void productService(String tableName, String bigTableName, String smallTableName) {
        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_SERVICE_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath", ApplicationConstant.modelPath).replace("#mapperPath", ApplicationConstant.mapperPath)
                .replace("#servicePath", ApplicationConstant.servicePath);

        IoUtil.writeOutFile(ApplicationConstant.servicePath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Service.java", content);
    }

    public void productServiceImpl(String tableName, String bigTableName, String smallTableName) {
        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_SERVICE_IMPL_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath", ApplicationConstant.modelPath).replace("#mapperPath", ApplicationConstant.mapperPath)
                .replace("#servicePath", ApplicationConstant.servicePath);

        IoUtil.writeOutFile(ApplicationConstant.servicePath.replaceAll("\\.", File.separator) + File.separator + "impl" + File.separator,
                bigTableName + "ServiceImpl.java", content);
    }

    public void productMapper(String tableName, String bigTableName, String smallTableName) {
        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_MAPPER_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        content = content.replace("#modelPath", ApplicationConstant.modelPath).replace("#mapperPath", ApplicationConstant.mapperPath);

        IoUtil.writeOutFile(ApplicationConstant.mapperPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + "Mapper.java", content);
    }

    public void productMapperXml(String tableName, String bigTableName, String smallTableName, List<ColumnBO> columnList) {
        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_MAPPER_XML_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);

        ProductSql sql = new ProductSql(columnList);
        String baseList = sql.productBaseList();
        String baseMap = sql.productBaseMap();
        String condition = sql.productCondition();
        String baseValueList = sql.productBaseValueList();
        String updateValue = sql.productUpdateValue();
        content = content.replace("#modelPath", ApplicationConstant.modelPath).replace("#mapperPath", ApplicationConstant.mapperPath)
                .replaceAll("#baseList", baseList).replaceAll("#condition", condition).replaceAll("#baseMap", baseMap)
                .replaceAll("#baseValueList", baseValueList).replaceAll("#updateValue", updateValue);

        IoUtil.writeOutFile(ApplicationConstant.mapperPath.replaceAll("\\.", File.separator) + File.separator,
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
            String enumValue = PropertiesUtil.loadEnumProperties(columnBO.getColumnName());
            if ("PRI".equals(columnBO.getColumnKey())) {
                sb.append("@Id").append("\r\n").append("@GeneratedValue(generator = \"JDBC\")").append("\r\n");
            }
            if (StringUtils.isNotBlank(enumValue) && enumValue.startsWith("List<")) {
                sb.append("    @ColumnType(typeHandler = ListToJsonTypeHandler.class) ").append("\r\n");
                dataType = enumValue;
            } else if (StringUtils.isNotBlank(enumValue) && enumValue.startsWith("Set<")) {
                sb.append("    @ColumnType(typeHandler = SetToJsonTypeHandlerHandler.class) ").append("\r\n");
                dataType = enumValue;
            } else if (StringUtils.isNotBlank(enumValue)) {
                sb.append("    @ColumnType(typeHandler = " + enumValue + "Handler.class) ").append("\r\n");
                dataType = enumValue;
            }
            if (!"createTime".equals(columnName) && !"updateTime".equals(columnName)
                    && !"createUserId".equals(columnName) && !"updateUserId".equals(columnName)) {
                sb.append("    private ").append(dataType).append(" ").
                        append(columnName).append("; // ").append(columnComment).append("\r\n");
            }
        }

        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_MODEL_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);
        content = content.replace("#modelPath", ApplicationConstant.modelPath).replace("#content", sb.toString());

        IoUtil.writeOutFile(ApplicationConstant.modelPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + ApplicationConstant.modelSuffix + ".java", content);
    }

    public void productModel2(String tableName, String bigTableName, String smallTableName, List<ColumnBO> columnList) {
        // 文件全部内容
        StringBuffer sb = new StringBuffer();
        // 所有参数
        for (ColumnBO columnBO : columnList) {
            String columnName = columnBO.getColumnNameTrans();
            String dataType = columnBO.getDataTypeTrans();
            String columnComment = columnBO.getColumnComment();
            String enumValue = PropertiesUtil.loadEnumProperties(columnBO.getColumnName());
            if (StringUtils.isNotBlank(enumValue) && enumValue.startsWith("List<")) {
                dataType = enumValue;
            } else if (StringUtils.isNotBlank(enumValue) && enumValue.startsWith("Set<")) {
                dataType = enumValue;
            } else if (StringUtils.isNotBlank(enumValue)) {
                dataType = enumValue;
            }
            if (!"createTime".equals(columnName) && !"updateTime".equals(columnName)
                    && !"createUserId".equals(columnName) && !"updateUserId".equals(columnName)) {
                sb.append("    private ").append(dataType).append(" ").
                        append(columnName).append("; // ").append(columnComment).append("\r\n");
            }
        }

        String path = this.getClass().getClassLoader().getResource(ModelConstant.ROOT_PATH +ModelConstant.TK_MODEL2_NAME).getPath();
        String content = TransferContentUtil.transferContent(path, tableName, bigTableName, smallTableName);
        content = content.replace("#modelPath", ApplicationConstant.modelPageSearchPath).replace("#content", sb.toString());

        IoUtil.writeOutFile(ApplicationConstant.modelPageSearchPath.replaceAll("\\.", File.separator) + File.separator,
                bigTableName + ApplicationConstant.modelPageSearchSuffix + ".java", content);
    }

}
