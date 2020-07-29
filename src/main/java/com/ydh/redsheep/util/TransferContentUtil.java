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
        content = content.replaceAll("#0", ApplicationContent.packages).replaceAll("#1", bigTableName)
                .replaceAll("#2", smallTableName).replaceAll("#3", ApplicationContent.modelSuffix).replaceAll("#4", tableName)
                .replaceAll("#author", ApplicationContent.author).replaceAll("#date", LocalDateTime.now().toString());

        return content;
    }

}
