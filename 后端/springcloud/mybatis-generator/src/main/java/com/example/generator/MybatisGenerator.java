package com.example.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class MybatisGenerator {
    //数据库URL
    private static final String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=UTC&useSSL=false";
    //数据库用户名
    private static final String username = "root";
    //数据库密码
    private static final String password = "root";
    //private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG=new DataSourceConfig.Builder(url,username,password);
    //作者名称
    private static final String AUTHOR = "丁祥 QQ 2421341497";
    //输出目录
    private static final String OUTPUTDIR = "E://javawebproject/springcloud/role-service/src/main/java/";
    //父包名
    private static final String PARENT = "com.example.role";
    //mapper目录
    private static final String MAPPER_DIR = "E://javawebproject/springcloud/role-service/src/main/resources/mybatis/mapper";
    //表名
    private static final String TABLE_NAME = "role";

    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            .disableOpenDir()
                            // .enableSwagger() // 开启 swagger 模式
                            // .fileOverride() // 覆盖已生成文件
                            .outputDir(OUTPUTDIR); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT) // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_DIR)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAME) // 设置需要生成的表名
                            .addTablePrefix("", "") // 设置过滤表前缀
                            .entityBuilder().enableLombok().disableSerialVersionUID()
                            .controllerBuilder().enableRestStyle()
                            .mapperBuilder().enableMapperAnnotation();
                })

                .execute();
    }
}
