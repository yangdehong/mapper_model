package com.ydh.redsheep.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * IO类
 *
 * @author : yangdehong
 * @date : 2018/9/5 14:01
 */
public class IoUtil {

    public static String readModelFile(String fileName) {
        StringBuffer content = new StringBuffer();
        File file = new File(fileName);
        Reader reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (((char) tempchar) != '\r') {
                    content.append((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * @Description: 输出文件
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public static void writeOutFile(String path, String fileName, String content) {
        String filePath = ApplicationConstant.filePath;
        if (StringUtils.isNotBlank(filePath)&&!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File file = new File(filePath+path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + fileName);
            fos.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String fileName = "/Users/yangdehong/IdeaProjects/mapper_model/src/main/resources/model/XController";
        String s = readModelFile(fileName);
        System.out.println(s);
    }

}
