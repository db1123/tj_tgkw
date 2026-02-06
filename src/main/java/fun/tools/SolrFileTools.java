package fun.tools;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SolrFileTools {

    public   static  JSONObject getsenPost(String solrpath, JSONObject paramObject) {

        URL url = null;
        BufferedReader reader = null;
        StringBuffer data = null;
        HttpURLConnection connection = null;
        OutputStreamWriter paramout = null;
        JSONObject returnDataObject = null;
        String line = null;
        String message = "";
        try {
            url = new URL(solrpath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            paramout.write(paramObject.toString());
            paramout.flush();
            paramout.close();
            data = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
//            {"status":"success","message":"文件已成功索引","source_table":"t_project_files","source_id":1420381772193075200}
//            {"status":"error","message":"文件索引失败","source_table":"t_project_files","source_id":1420381772193075200}
            returnDataObject = JSONObject.parseObject(data.toString());
            System.out.println(returnDataObject);

        } catch (Exception e) {
            returnDataObject = null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return returnDataObject;

    }
}
