package com._520it.generator;

import com._520it.wms.domain.SaleAccount;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

//代码生成器
public class CodeGenerator {

    private static Configuration config;

    static {
        try {
            config = new Configuration(Configuration.VERSION_2_3_22);//创建一个configuration对象,参数为设置版本
            config.setDirectoryForTemplateLoading(new File("templates"));//从哪个文件夹里加载模板文件
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        //生成代码时调用,生成以后注释代码,预防点击上面快捷按钮后覆盖代码
        //genericted();

        System.out.println("所有代码生成完毕");
    }

    public static void genericted() throws Exception {
        ClassInfo classInfo = new ClassInfo(SaleAccount.class);//获取母板类型
        //1,生成DAO组件
        creatFile(classInfo, "IDAO.java", "src/main/java/{0}/dao/I{1}DAO.java");
        creatFile(classInfo, "DaoImpl.java", "src/main/java/{0}/dao/impl/{1}DaoImpl.java");//1,生成DAO组件

        //2,生成queryObject组件
        creatFile(classInfo, "QueryObject.java", "src/main/java/{0}/query/{1}QueryObject.java");

        //3,生成service组件
        creatFile(classInfo, "IService.java", "src/main/java/{0}/service/I{1}Service.java");
        creatFile(classInfo, "ServiceImpl.java", "src/main/java/{0}/service/impl/{1}ServiceImpl.java");

        //4,生成action组件
        creatFile(classInfo, "Action.java", "src/main/java/{0}/web/action/{1}Action.java");

        //5,生成hbm配置文件
        creatFile(classInfo, "hbm.xml", "src/main/resources/hbm/{1}.hbm.xml");

        //6,生成list.jsp,input.jsp文件
        creatFile(classInfo, "list.jsp", "src/main/webapp/WEB-INF/views/{2}/list.jsp");
        creatFile(classInfo, "input.jsp", "src/main/webapp/WEB-INF/views/{2}/input.jsp");

        //7.追加配置文件
        appendXml(classInfo, "dao.xml", "src/main/resources/applicationContext-daos.xml");
        appendXml(classInfo, "service.xml", "src/main/resources/applicationContext-services.xml");
        appendXml(classInfo, "action.xml", "src/main/resources/applicationContext-actions.xml");


    }

    //给配置文件追加内容
    public static void appendXml(ClassInfo classInfo, String templateFile, String targetFilePath) throws Exception {
        Template template = config.getTemplate(templateFile);//获取一个模板,得到一个template对象
        String path = MessageFormat.format(targetFilePath,
                classInfo.getBasePkg().replace(".", "/"),
                classInfo.getClassName(),
                classInfo.getObjectName());
        StringWriter sWriter = new StringWriter();
        template.process(classInfo, sWriter);//模板处理和输出到字符串流中
        XmlUtil.mergeXML(new File(targetFilePath), sWriter.toString());
        System.out.println(sWriter.toString());
    }


    public static void creatFile(ClassInfo classInfo, String templateFile, String targetFilePath) throws Exception {
        Template template = config.getTemplate(templateFile);//获取一个模板,得到一个template对象
        String path = MessageFormat.format(targetFilePath,
                classInfo.getBasePkg().replace(".", "/"),
                classInfo.getClassName(),
                classInfo.getObjectName());
        File dir = new File(path);
        if (!dir.getParentFile().exists()) {
            dir.getParentFile().mkdirs();
        }
        template.process(classInfo, new FileWriter(path));//模板处理和输出
    }
}
