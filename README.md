# massif-pro-parent

@wangsiyao
 
关于扫包的问题记录一点个人理解，不知道对不对:  
    @MapperScan("xxx.xxx.xxx")  扫描的是XxxMapper接口而不是xml文件  
        -- XxxMapper.xml文件想要被发现：1、放到resource目录下；2、在配置文件中指定位置  
    @ComponentScan("xxx.xxx") 扫描的是Spring管理的Bean对象  
        -- 本模块下启动类同级的Bean对象自动扫描到，否则加@ComponentScan("xxx.xxx")  
        -- 非本模块（比如maven依赖的【自定义模块】）的Bean对象需要加@ComponentScan("xxx.xxx")这个注解后才可@Autowired

待解决问题：  
    system-start启动时有的用户登录判断sql语句错误
