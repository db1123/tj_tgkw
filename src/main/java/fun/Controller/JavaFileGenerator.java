package fun.Controller;

import java.io.IOException;

public class JavaFileGenerator {

    /**
     * 生成Java类文件
     * @param packageName 包名（如 "com.example"）
     * @param className   类名（如 "User"）
     * @param fields      字段列表（如 {"private String name;", "private int age;"}）
     * @param savePath    保存路径（如 "src/main/java"）
     */
    public static void generateJavaClass(String packageName, String className, 
                                       String[] fields, String savePath) throws IOException {

        // 构建文件路径

//        // 构建文件路径
//gitee.com/xxxggg/student_capability_evaluation.git
//        String dirPath = savePath + "/" + packageName.replace(".", "/");
//        File dir = new File(dirPath);
//        if (!dir.exists()) dir.mkdirs();
//
//        // 生成Java内容
//        StringBuilder content = new StringBuilder();
//        content.append("package ").append(packageName).append(";\n\n");
//        //content.append("public class ").append(className).append(" {\n");
//        for (String field : fields) {
//            content.append("    ").append(field).append("\n");
//        }
//        content.append("}\n");
//        // 写入文件
//        File file = new File(dir, className + ".java");
//        FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
//        try (BufferedWriter bw = new BufferedWriter(writer)) {
//			bw.write(content.toString());
//			  //动态编译代码
//	        JavaCompiler javac;
//	        javac = ToolProvider.getSystemJavaCompiler();
//	        System.out.println(file.getPath());
//	        int compilationResult = javac.run(null,null , null, file.getPath());
//	        System.out.println(compilationResult);
//		}
   
    }
    
    
    public static void main(String[] args) {
       
	}
    
}
