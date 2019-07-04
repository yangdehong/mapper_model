package com.ydh.redsheep.test;

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
        int index = 0;
        for (ColumnBO columnBO : columnList) {
            if (index==0) {
                sb.append("<id column=\"").append(columnBO.getColumnName())
                        .append("\" jdbcType=\"").append(columnBO.getDataType().toUpperCase())
                        .append("\" property=\"").append(columnBO.getColumnNameTrans())
                        .append("\"/>").append("\r\n");
            } else {
                sb.append("<result column=\"").append(columnBO.getColumnName())
                        .append("\" jdbcType=\"").append(columnBO.getDataType().toUpperCase())
                        .append("\" property=\"").append(columnBO.getColumnNameTrans())
                        .append("\"/>").append("\r\n");
            }
            index++;
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
