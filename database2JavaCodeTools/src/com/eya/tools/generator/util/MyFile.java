package com.eya.tools.generator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyFile {

    private String srcFolder = "E:\\yogcn\\yplat\\src\\";
    private String webFolder = "E:\\yogcn\\yplat\\WebRoot\\WEB-INF\\";

    // 建立目录
    public MyFile(String srcFolder, String webFolder) {

        this.srcFolder = srcFolder;
        this.webFolder = webFolder;

    }

    public void writeFile(String content, String fileName, String type) {
        try {

            createFolder(srcFolder);

            //FileWriter fw = new FileWriter(srcFolder+fileName, false);

            //fw.write(content);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(srcFolder + fileName, false), "UTF-8");
            osw.write(content);
            osw.close();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * 写Java文件 
     * @param content 文件内容
     * @param fileName 文件名称
     * @param type 文件类型
     * @param packageName 包名
     */
    public void writeFile(String content, String fileName, String type, String packageName) {
        try {
            //content = new String(content.getBytes(),"utf-8");
            String folder = "";
            if (!"jsp".equals(type.substring(type.indexOf(".") + 1, type.length()))) {
                packageName = packageName.replace(".", "\\");
            }

            if (type.equals("serviceimpl")) {

                fileName = srcFolder + packageName + "\\impl\\" + fileName + ".java";
                folder = srcFolder + packageName + "\\impl\\";
            } else if (type.equals("mapper")) {
                fileName = srcFolder + packageName + "\\" + fileName + ".xml";
                folder = srcFolder + packageName;
            } else if (type.equals("input.jsp")) {
                folder = webFolder + "\\jsp\\admin\\" + fileName;
                fileName = webFolder + "\\jsp\\admin\\" + fileName + "\\input.jsp";
            } else if (type.equals("list.jsp")) {
                createFolder(webFolder + "\\jsp\\admin\\" + fileName);
                fileName = webFolder + "\\jsp\\admin\\" + fileName + "\\list.jsp";
            } else if (type.equals("search.jsp")) {
                createFolder(webFolder + "\\jsp\\" + fileName);
                fileName = webFolder + "\\jsp\\" + fileName + "\\search.jsp";
            } else {
                fileName = srcFolder + packageName + "\\" + fileName + ".java";
                folder = srcFolder + packageName + "\\";
            }

            createFolder(folder);
            // 生成文件
            //FileWriter fw = new FileWriter(fileName, false);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");

            osw.write(content);
            //fw.write(content);

            osw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    // 写JS文件
    public void writeJSFile(String content, String fileName, String type) {
        //			String tempFile="";
        /*
        try {
        	if(type.equals("new")){
        		
        		createFolder(folder+jsmodule+"\\view\\"+fileName.toLowerCase());
        		
        		tempFile = folder + jsmodule +"\\view\\"+fileName.toLowerCase() + "\\New.js";
        	}
        	else if(type.equals("edit")){
        		
        		createFolder(folder+jsmodule+"\\view\\"+fileName.toLowerCase());
        		
        		tempFile = folder + jsmodule +"\\view\\"+fileName.toLowerCase() + "\\Edit.js";
        		
        	}else if(type.equals("list")){
        		
        		createFolder(folder+jsmodule+"\\view\\"+fileName.toLowerCase());
        		
        		tempFile = folder + jsmodule +"\\view\\"+fileName.toLowerCase() + "\\List.js";
        	}
        	else
        	{
        		tempFile = folder + jsmodule +"\\"+type+"\\"+fileName + ".js";
        	}
        		
        	// 生成文件
        	FileWriter fw = new FileWriter(tempFile, false);

        	fw.write(content);

        	fw.close();

        } catch (IOException e) {

        	e.printStackTrace();

        }*/
    }

    private void createFolder(String folderName) {

        File moduleFile = new File(folderName);

        if (!moduleFile.exists()) {

            moduleFile.mkdirs();

        }
    }
}
