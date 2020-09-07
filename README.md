# mapper_model
## 简介
这个项目基础框架是用的springboot2.x+jdbc完成的，配置修改在application.yml。
默认端口是80，可以自行修改。
model生成是基于lombok插件的，不明白的可以看看这个：https://blog.csdn.net/u014803081/article/details/84574016

## 模型
模型位置：src/main/resources/java/model
模型常量：com.ydh.redsheep.util.ModelConstant
## 模型中需要替换的内容
#### #0
包路径：mybatis.packages.path的值，比如com.ydh.redsheep
#### #1
开头大写的类名：比如表名是my_dog，就是MyDog
#### #2
开头小写的类名：比如表名是my_dog，就是myDog
#### #3
实体类型的后缀：model.suffix的值，比如PO
#### #5
实体类型的后缀：model.page.search.suffix的值，比如VO
#### #4
表名：就是数据库的表名字
#### #controllerPath
controller包路径：mybatis.controller.path的值，比如com.ydh.redsheep.controller
#### #servicePath
service包路径：mybatis.service.path的值，比如com.ydh.redsheep.service
#### #mapperPath
mapper包路径：mybatis.mapper.path的值，比如com.ydh.redsheep.mapper
#### #modelPath
model包路径：mybatis.model.path的值，比如com.ydh.redsheep.entity.po
#### #author
作者信息：author的值，比如yangdehong
#### #date
时间，不需要修改

## 使用
jdbc.properties中修改数据库连接
parmas.properties中修改要生成的表的名字，生成文件包名等
enums.properties中做枚举类型映射

启动方法：com.ydh.redsheep.test.Main中的main函数，直接执行就可以了



# 尽情期待web版本
......遥遥无期......

