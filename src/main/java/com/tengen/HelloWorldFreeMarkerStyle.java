package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public class HelloWorldFreeMarkerStyle {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name","Fred");

            helloTemplate.process(params, writer);

            System.out.println(writer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
