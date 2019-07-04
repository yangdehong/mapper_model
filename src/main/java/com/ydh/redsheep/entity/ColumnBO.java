package com.ydh.redsheep.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
* @author : yangdehong
* @date : 2019-07-04 15:02
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnBO {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 转换后的列名
     */
    private String columnNameTrans;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 转换后的数据类型
     */
    private String dataTypeTrans;
    /**
     * 注释
     */
    private String columnComment;

}
