package com.ydh.redsheep.service;

import com.ydh.redsheep.entity.ColumnBO;

import java.util.List;

/**
*
* @author : yangdehong
* @date : 2019-07-04 15:10
*/
public class ProductSql {

    private List<ColumnBO> columnList;

    public ProductSql(List<ColumnBO> columnList) {
        this.columnList = columnList;
    }

    /**
     * 字段列表
     * @return
     */
    public String productBaseList() {
        StringBuffer sb = new StringBuffer();
        for (ColumnBO columnBO : columnList) {
            sb.append(",").append(columnBO.getColumnName());
        }
        return sb.toString().substring(1);
    }
    /**
     * 字段map
     * @return
     */
    public String productBaseMap() {
        StringBuffer sb = new StringBuffer();
        for (ColumnBO columnBO : columnList) {
            String columnName = columnBO.getColumnName();
            String dataType = columnBO.getDataType();
            dataType = "int".endsWith(dataType)?"integer":dataType;
            if ("PRI".equals(columnBO.getColumnKey())) {
                sb.append("<id column=\"").append(columnName)
                        .append("\" jdbcType=\"").append(dataType.toUpperCase())
                        .append("\" property=\"").append(columnBO.getColumnNameTrans())
                        .append("\"/>").append("\r\n");
            } else {
                sb.append("<result column=\"").append(columnName)
                        .append("\" jdbcType=\"").append(dataType.toUpperCase())
                        .append("\" property=\"").append(columnBO.getColumnNameTrans())
                        .append("\"/>").append("\r\n");
            }
        }
        return sb.toString();
    }
    /**
     * 查询条件
     * @return
     */
    public String productCondition() {
        StringBuffer sb = new StringBuffer();
        for (ColumnBO columnBO : columnList) {
            if ("String".equals(columnBO.getDataTypeTrans())) {
                sb.append("    <if test=\"model.").append(columnBO.getColumnNameTrans()).append("!= null and model.")
                        .append(columnBO.getColumnNameTrans()).append("!=''\"> ").append("\r\n");
                sb.append("      and ").append(columnBO.getColumnName()).append(" = ").append("#{model.").append(columnBO.getColumnNameTrans()).append("}").append("\r\n");
                sb.append("    </if> ").append("\r\n");
            } else {
                sb.append("    <if test=\"model.").append(columnBO.getColumnNameTrans()).append("!= null\"> ").append("\r\n");
                sb.append("      and ").append(columnBO.getColumnName()).append(" = ").append("#{model.").append(columnBO.getColumnNameTrans()).append("}").append("\r\n");
                sb.append("    </if> ").append("\r\n");
            }
        }
        return sb.toString();
    }
    /**
     * 新增的值列表
     * @return
     */
    public String productBaseValueList() {
        StringBuffer sb = new StringBuffer();
        for (ColumnBO columnBO : columnList) {
            sb.append(",").append("#{model.").append(columnBO.getColumnNameTrans()).append("}");
        }
        return sb.toString().substring(1);
    }
    /**
     * 修改的值列表
     * @return
     */
    public String productUpdateValue() {
        StringBuffer sb = new StringBuffer();
        for (ColumnBO columnBO : columnList) {
            sb.append("    <if test=\"model.").append(columnBO.getColumnNameTrans()).append("!= null\"> ").append("\r\n");
            sb.append("      ").append(columnBO.getColumnName()).append(" = ").append("#{model.").append(columnBO.getColumnNameTrans()).append("},").append("\r\n");
            sb.append("    </if> ").append("\r\n");
        }
        return sb.toString().substring(1);
    }

}
