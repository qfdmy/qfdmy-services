package com.qfdmy.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis plus 代码生成器
 *
 * @author Lusifer
 * @since v1.0.0
 */
public class MyBatisPlusGenerate {

    /**
     * 作者
     */
    private static final String AUTHOR = "Lusifer";

    /**
     * 工作目录
     */
    private static final String USER_DIR = System.getProperty("user.dir");

    /**
     * JDBC 连接地址
     */
    private static final String JDBC_URL = "jdbc:mysql://mysql.funtl.com:3306/qfdmy?serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&nullNamePatternMatchesAll=true&zeroDateTimeBehavior=CONVERT_TO_NULL&tinyInt1isBit=false&autoReconnect=true&useSSL=false&pinGlobalTxToPhysicalConnection=true";

    /**
     * JDBC 驱动程序
     */
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库账号
     */
    private static final String JDBC_USERNAME = "root";

    /**
     * 数据库密码
     */
    private static final String JDBC_PASSWORD = "123456";

    /**
     * 包配置 - 父级目录
     */
    private static final String PACKAGE_PARENT = "com.qfdmy";

    /**
     * 包配置 - 模块目录 <br>
     * 注意：如果表前缀与模块命相同，生成时会删除前缀，比如：core_admin 最终构建为 Admin, AdminController ...
     */
    private static final String PACKAGE_MODULE_NAME = "";

    /**
     * 包配置 - 实体类目录
     */
    private static final String PACKAGE_ENTITY = "domain";

    /**
     * 包配置 - 数据访问接口目录
     */
    private static final String PACKAGE_MAPPER = "mapper";

    /**
     * 包配置 - 业务处理接口目录
     */
    private static final String PACKAGE_SERVICE = "service";

    /**
     * 包配置 - 业务处理实现目录
     */
    private static final String PACKAGE_SERVICE_IMPL = "service.impl";

    /**
     * 包配置 - 控制器目录
     */
    private static final String PACKAGE_CONTROLLER = "controller";

    /**
     * 要生成的表，用 `,` 分割
     */
    private static final String TABLES = "core_post";

    /**
     * 全局配置
     *
     * @return {@link GlobalConfig}
     */
    private static GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        config.setOutputDir(USER_DIR + "/src/main/java");
        config.setAuthor(AUTHOR);
        config.setOpen(false);
        return config;
    }

    /**
     * 数据源配置
     *
     * @return {@link DataSourceConfig}
     */
    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setUrl(JDBC_URL);
        config.setDriverName(JDBC_DRIVER_NAME);
        config.setUsername(JDBC_USERNAME);
        config.setPassword(JDBC_PASSWORD);
        return config;
    }

    /**
     * 包配置
     *
     * @return {@link PackageConfig}
     */
    private static PackageConfig packageConfig() {
        PackageConfig config = new PackageConfig();
        // 你哪个父目录下创建包
        config.setParent(PACKAGE_PARENT);
        // 设置模块的名字，比如 core，生成效果为 com.qfdmy.core
        config.setModuleName(PACKAGE_MODULE_NAME);
        // 实体类创建在哪个包
        config.setEntity(PACKAGE_ENTITY);
        config.setMapper(PACKAGE_MAPPER);
        config.setService(PACKAGE_SERVICE);
        config.setServiceImpl(PACKAGE_SERVICE_IMPL);
        config.setController(PACKAGE_CONTROLLER);
        return config;
    }

    /**
     * 代码生成模板配置 - Freemarker
     *
     * @return {@link TemplateConfig}
     */
    private static TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 代码生成策略配置
     *
     * @return {@link StrategyConfig}
     */
    private static StrategyConfig strategyConfig() {
        // 策略配置,数据库表配置
        StrategyConfig config = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        config.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体类的命名策略
        config.setColumnNaming(NamingStrategy.underline_to_camel);
        // 实体是否为 lombok 模型
        config.setEntityLombokModel(true);
        config.setInclude(TABLES.split(","));
        // 驼峰转连字符串
        config.setControllerMappingHyphenStyle(true);
        // REST 风格
        config.setRestControllerStyle(true);
        // 表前缀
        config.setTablePrefix(packageConfig().getModuleName() + "_");

        // 字段填充
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("is_deleted", FieldFill.INSERT));
        tableFills.add(new TableFill("create_time", FieldFill.INSERT));
        tableFills.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        config.setTableFillList(tableFills);

        // CommonBase
        config.setSuperEntityColumns("id", "create_time", "update_time");
        config.setSuperEntityClass("com.qfdmy.commons.base.BaseDomain");
        config.setSuperServiceClass("com.qfdmy.commons.base.IBaseService");
        config.setSuperServiceImplClass("com.qfdmy.commons.base.BaseServiceImpl");
        config.setSuperControllerClass("com.qfdmy.commons.base.BaseController");

        return config;
    }

    /**
     * 自定义配置
     */
    private static InjectionConfig injectionConfig() {
        InjectionConfig config = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出 mapper.xml 到 resources 目录下
        String mapperPath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(mapperPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return USER_DIR + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });

        config.setFileOutConfigList(focList);
        return config;
    }

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig());
        generator.setDataSource(dataSourceConfig());
        generator.setPackageInfo(packageConfig());
        generator.setTemplate(templateConfig());
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.setCfg(injectionConfig());
        generator.setStrategy(strategyConfig());

        generator.execute();
    }

}
