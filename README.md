# massif-pro-parent

@wangsiyao
 
关于扫包的问题记录一点个人理解，不知道对不对:  
    @MapperScan("xxx.xxx.xxx")  扫描的是XxxMapper接口而不是xml文件  
        -- XxxMapper.xml文件想要被发现：1、放到resource目录下；或者 2、在配置文件中指定位置  
    @ComponentScan("xxx.xxx") 扫描的是Spring管理的Bean对象  
        -- 本模块下启动类同级的Bean对象自动扫描到，否则加@ComponentScan("xxx.xxx")  
        -- 非本模块（比如maven依赖的【自定义模块】）的Bean对象需要加@ComponentScan("xxx.xxx")这个注解后才可@Autowired


mybatis-plus版本问题  
    由于大多模块用mybatis-plus3.5以上版本，代码生成器用3.4版本，所以把代码生成器放在了没有用到mysql的spit模块

debug日志出不来，日志级别待解决  

rabbitmq和nacos不用的话先从system-core注释掉  

一个待解决的问题：  
我的token是随机生成的而不是jwt通过userId生成的，所以不能被security解析出userId，也就无法通过UserDetailsService查询出用户信息  
如果添加拦截器（并没有添加），仍然会先走security的过滤器，所以还是每个请求都需要验证。

