package fun.mbg;

import fun.tools.ParamTools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

    public static void main(String[] args) throws Exception {
//        String a = "C0F85C3EE88B14C89D53C04A22C9558BD6B4D86F34F4FFD42A741D4D797A1371";
//        String b ="BD856E2C086025B1CDA567DF0E08B99CF2142210BC380261AAFAF21E2EBFC922";
//        System.out.println(ParamTools.getdeParam(a));
//        System.out.println(ParamTools.getdeParam(b));
//
//        String c = "0";
//        System.out.println(ParamTools.getEnParam(c));
//        JSONObject params = new JSONObject();
//        params.put("question", "请列出西游记的主要角色、简介以及最终结局。格式如下:{name:\"人物姓名\",memo:\"人物简介\",result:\"结局\"}");
//        params.put("temperature", 0.5);
//        params.put("answer_parameters", "[{\"name\": \"biography\", \"description\": \"西游记的主要角色、简介以及最终结局\"}]");
//
//        try {
//            String url = "http://192.168.18.34:8000/api/interface_llm_start";
//
//            // 表单格式调用
//            String formParams = "question=请列出西游记的主要角色、简介以及最终结局。格式如下:{name:\"人物姓名\",memo:\"人物简介\",result:\"结局\"}&temperature=0.5&answer_parameters=[{\"name\": \"biography\", \"description\": \"西游记的主要角色、简介以及最终结局\"}]";
//            String response2 = sendPost(url, formParams, "application/x-www-form-urlencoded; charset=UTF-8");
//            System.out.println(response2);
//        } catch (Exception e) {
//            System.err.println("处理异常: " + e.getMessage());
//            e.printStackTrace();
//        }

//        String data = "|_1_||_1360703288168812544_||_1360703381768900608_||_1360703698283663360_||_1360704125377056768_|";
//
//        // 找到第一个"||_"之后的位置
//        int start = data.indexOf("||_") + 3;
//        // 找到"_||"的位置
//        int end = data.indexOf("_||", start);
//
//        String targetValue = data.substring(start, end);
//        System.out.println(targetValue); // 输出: 1360703288168812544


//        String result = AI_interface.sendPost_json_search("gAAAAABoSpVOLKn-t6bBfqi1QrwcRzv239UHujYj023W-RP2c_SKoYlJHoK4ewUlbi41nOol6HlDqdLNKbCNHRyOg-rT8CesV3SBxIunjvSq7uHU6gjoPnw=");
//        JSONObject jsonObject1 = JSONObject.parseObject(result);
//
//        System.out.println(jsonObject1);
//        String code = jsonObject1.getString("code");
//        if (code.equals("200")) {
//            String info = jsonObject1.getString("info");
//            JSONObject jsonObject1_info = JSONObject.parseObject(info);
//            int state= jsonObject1_info.getInteger("state");
//            String answer = jsonObject1_info.getString("answer");
//            JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
//            String ai_result = jsonObject1_answer.getString("ai_result");
//            System.out.println(ai_result);
////            JSONObject parsedObject = JSONObject.parseObject(ai_result);
////            System.out.println(parsedObject.getString("training_program_id"));
////            System.out.println(parsedObject.getFloat("final_score"));
////            JSONArray jsonArray = JSONArray.parseArray(ai_result); // Correct for FastJSON
////
////            for (int i = 0; i < jsonArray.size(); i++) {
////                JSONObject obj = jsonArray.getJSONObject(i);
////                String courseEnrollmentId = obj.getString("CourseEnrollment_id");
////                int finalScore = obj.getInteger("final_score");
////
////                System.out.println("CourseEnrollment_id: " + courseEnrollmentId);
////                System.out.println("final_score: " + finalScore);
////                System.out.println("----------------------");
////            }
//
//            Object parsed = JSON.parse(ai_result);
//
//            if (parsed instanceof JSONObject) {
//                JSONObject jsonObject = (JSONObject) parsed;
//                System.out.println("这是一个 JSONObject: " + jsonObject);
//            } else if (parsed instanceof JSONArray) {
//                JSONArray jsonArray = (JSONArray) parsed;
//                System.out.println("这是一个 JSONArray: " + jsonArray);
//            } else {
//                System.err.println("无效的JSON格式！");
//            }
//        }

        System.out.println(ParamTools.getdeParam("A207BAC5F4E68D9AEB73CC80D1ADE39813710E418E056FD955B258A321082546"));


        String a= "[\n" +
                "            {\n" +
                "                id: 1,\n" +
                "                name: \"大学物理（上册）\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 101,\n" +
                "                        name: \"大学物理（上册）- 理学院\",\n" +
                "                        matchRate: 98,\n" +
                "                        courseCode: \"PHY101\",\n" +
                "                        teacher: \"张教授\",\n" +
                "                        isActive: true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        id: 102,\n" +
                "                        name: \"大学物理（上）- 工学院\",\n" +
                "                        matchRate: 85,\n" +
                "                        courseCode: \"PHY102\",\n" +
                "                        teacher: \"李教授\",\n" +
                "                        isActive: false\n" +
                "                    },\n" +
                "                    {\n" +
                "                        id: 103,\n" +
                "                        name: \"大学物理（上册）- 远程教育学院\",\n" +
                "                        matchRate: 75,\n" +
                "                        courseCode: \"PHY103\",\n" +
                "                        teacher: \"王讲师\",\n" +
                "                        isActive: false\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: true\n" +
                "            },\n" +
                "            {\n" +
                "                id: 2,\n" +
                "                name: \"高等数学（下册）\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 201,\n" +
                "                        name: \"高等数学（下册）- 数学系\",\n" +
                "                        matchRate: 95,\n" +
                "                        courseCode: \"MATH202\",\n" +
                "                        teacher: \"刘教授\",\n" +
                "                        isActive: true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        id: 202,\n" +
                "                        name: \"高等数学（下）- 工学院\",\n" +
                "                        matchRate: 88,\n" +
                "                        courseCode: \"MATH203\",\n" +
                "                        teacher: \"孙教授\",\n" +
                "                        isActive: false\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: true\n" +
                "            },\n" +
                "            {\n" +
                "                id: 3,\n" +
                "                name: \"线性代数\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 301,\n" +
                "                        name: \"线性代数 - 数学系\",\n" +
                "                        matchRate: 99,\n" +
                "                        courseCode: \"MATH105\",\n" +
                "                        teacher: \"郑教授\",\n" +
                "                        isActive: true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        id: 302,\n" +
                "                        name: \"线性代数（工科版）- 工学院\",\n" +
                "                        matchRate: 82,\n" +
                "                        courseCode: \"MATH106\",\n" +
                "                        teacher: \"马教授\",\n" +
                "                        isActive: false\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: true\n" +
                "            },\n" +
                "            {\n" +
                "                id: 4,\n" +
                "                name: \"概率论与数理统计\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 401,\n" +
                "                        name: \"概率论与数理统计 - 数学系\",\n" +
                "                        matchRate: 92,\n" +
                "                        courseCode: \"MATH301\",\n" +
                "                        teacher: \"林教授\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: true\n" +
                "            },\n" +
                "            {\n" +
                "                id: 5,\n" +
                "                name: \"大学英语（四级）\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 501,\n" +
                "                        name: \"大学英语（四级）- 外国语学院\",\n" +
                "                        matchRate: 94,\n" +
                "                        courseCode: \"\",\n" +
                "                        teacher: \"\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: false\n" +
                "            },\n" +
                "            {\n" +
                "                id: 6,\n" +
                "                name: \"计算机基础\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 601,\n" +
                "                        name: \"计算机基础 - 计算机学院\",\n" +
                "                        matchRate: 96,\n" +
                "                        courseCode: \"\",\n" +
                "                        teacher: \"\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: false\n" +
                "            },\n" +
                "            {\n" +
                "                id: 7,\n" +
                "                name: \"电路原理\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 701,\n" +
                "                        name: \"电路原理 - 电子信息学院\",\n" +
                "                        matchRate: 91,\n" +
                "                        courseCode: \"\",\n" +
                "                        teacher: \"\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: false\n" +
                "            },\n" +
                "            {\n" +
                "                id: 8,\n" +
                "                name: \"模拟电子技术\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 801,\n" +
                "                        name: \"模拟电子技术 - 电子学院\",\n" +
                "                        matchRate: 93,\n" +
                "                        courseCode: \"\",\n" +
                "                        teacher: \"\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: false\n" +
                "            },\n" +
                "            {\n" +
                "                id: 9,\n" +
                "                name: \"数字电子技术\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 901,\n" +
                "                        name: \"数字电子技术 - 自动化学院\",\n" +
                "                        matchRate: 90,\n" +
                "                        courseCode: \"\",\n" +
                "                        teacher: \"\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: false\n" +
                "            },\n" +
                "            {\n" +
                "                id: 10,\n" +
                "                name: \"工程制图\",\n" +
                "                similarCourses: [\n" +
                "                    {\n" +
                "                        id: 1001,\n" +
                "                        name: \"工程制图 - 机械学院\",\n" +
                "                        matchRate: 89,\n" +
                "                        courseCode: \"\",\n" +
                "                        teacher: \"\",\n" +
                "                        isActive: true\n" +
                "                    }\n" +
                "                ],\n" +
                "                hasManualInput: false\n" +
                "            }\n" +
                "        ]";

        System.out.println(ParamTools.getEnParam(a));
    }
    public static String sendPost(String url, String param, String contentType) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            // 设置通用请求头
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", contentType); // 动态设置 Content-Type
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 发送请求参数
            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
                writer.write(param);
                writer.flush();
            }

            // 读取响应
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }
}


