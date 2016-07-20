编译及运行环境:
jdk1.8

工程建议路径：
D:\
即D:\Handtour\xx
原因:
mybatis-generator/generatorConfig.xml 路径使用的这个
若非该路径，生成时会出现问题


使用maven的mybatis-generator插件生成：
java/dao/xxxMapper.class  (接口,候选bean)
java/model/xxx.class (数据库字段对应类)
resources/dbmappers/xxxMapper.xml (执行的sql语句 及 model、dao等等关系绑定)

使用到的组件(需确保地址得到正确配置):
Mysql
Zookeeper




