package fun.tools;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileConvertTools {
    /**
     * 【文件压缩】网络文件
     *
     * @param filePath:
     * @param zipOut:
     * @return void
     */
    public static void fileToZip(String filePath, ZipOutputStream zipOut,String ffilename) throws IOException {
//        filePath = getEncodeUrl(filePath).replaceAll("\\+", "%20");
        // 需要压缩的文件
        File file = new File(filePath);
        // 获取文件名称,为解决压缩时重复名称问题，对文件名加时间戳处理
//        String fileName = FilenameUtils.getBaseName(URLDecoder.decode(file.getName(), "UTF-8")) + "-"
//                + String.valueOf(new Date().getTime()) + "."
//                + FilenameUtils.getExtension(file.getName());
        String fileName = ffilename+ "-"
                + String.valueOf(new Date().getTime()) + "."
                + FilenameUtils.getExtension(file.getName());
        InputStream fileInput = getInputStream(filePath);
        // 缓冲
        byte[] bufferArea = new byte[1024 * 10];
        BufferedInputStream bufferStream = new BufferedInputStream(fileInput, 1024 * 10);
        // 将当前文件作为一个zip实体写入压缩流,fileName代表压缩文件中的文件名称
        zipOut.putNextEntry(new ZipEntry(fileName));
        int length = 0;
        // 最常规IO操作,不必紧张
        while ((length = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
            zipOut.write(bufferArea, 0, length);
        }
        //关闭流
        fileInput.close();
        // 需要注意的是缓冲流必须要关闭流,否则输出无效
        bufferStream.close();
        // 压缩流不必关闭,使用完后再关
    }

    /**
     * 【获取网络文件的输入流】
     *
     * @param filePath: 网络文件路径
     * @return java.io.InputStream
     */
    public static InputStream getInputStream(String filePath) throws IOException {
        InputStream inputStream = null;
        // 创建URL
        URL url = new URL(filePath);
        // 试图连接并取得返回状态码
        URLConnection urlconn = url.openConnection();
        urlconn.connect();
        HttpURLConnection httpconn = (HttpURLConnection) urlconn;
        int httpResult = httpconn.getResponseCode();
        if (httpResult == HttpURLConnection.HTTP_OK) {
            inputStream = urlconn.getInputStream();
        }
        return inputStream;
    }




    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
//            String filePath = folderPath;
//            filePath = filePath.toString();
//            java.io.File myFilePath = new java.io.File(filePath);
//            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


}
