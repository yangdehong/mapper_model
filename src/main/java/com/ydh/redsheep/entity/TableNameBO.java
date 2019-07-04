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
public class TableNameBO {

    private String tableName;

    private String bigTableName;

    private String smallTableName;

}
