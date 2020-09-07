package com.ydh.redsheep.util;

import java.time.LocalDateTime;

/**
*
* @author : yangdehong
* @date : 2020-07-29 11:44
*/
public class TransferContentUtil {

    public static String transferContent(String filePath, String tableName, String bigTableName, String smallTableName) {

        String content = IoUtil.readModelFile(filePath);
        content = content.replaceAll("#0", ApplicationConstant.packages).replaceAll("#1", bigTableName)
                .replaceAll("#2", smallTableName).replaceAll("#3", ApplicationConstant.modelSuffix)
                .replaceAll("#4", tableName).replaceAll("#5", ApplicationConstant.modelPageSearchSuffix)
                .replaceAll("#author", ApplicationConstant.author).replaceAll("#date", LocalDateTime.now().toString());

        return content;
    }

}
