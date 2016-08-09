编译及运行环境:
jdk1.8


项目启动前请确保下列组件可用(已运行)，(需确保地址得到正确配置)，否则会启动失败:
Mysql
Zookeeper

git的使用：
建议从develop分支生成本地分支feature_xxx,
然后通过git rebase develop进行合并
这样git history中的分支历史树图不会分叉得厉害
指令的使用可参考文章：http://blog.csdn.net/hudashi/article/details/7664631

如何提供一个服务:
1.在Handtour-ServiceAPI工程 com/handtours/service/api/project/${projectName}/ 下创建接口类
2.在Handtour-Service工程 com/handtours/service/impl/project/${projectName}/ 下创建实现类
3.在resources/application-service/applicationContext-${projectName}.xml 中注册该bean
4.在resources/application-provider/applicationContext-${projectName}.xml 中注册该服务

如何消费提供的服务:
<dubbo:reference id="iUserService" interface="com.handtours.service.api.project.back.IUser" version="1.0.0"/>
引用即可






