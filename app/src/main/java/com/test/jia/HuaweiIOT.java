package com.test.jia;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public class HuaweiIOT {
    //请在下方完善信息
    String HUAWEINAME="hw067582697";           //华为账号名
    String IAMINAME="huang";             //IAM账户名
    String IAMPASSWORD="11223344abc";          //IAM账户密码
    String project_id="0715ee18d43f40f4b1c2c213410e4bda";           //产品ID
    String device_id="64830c9901554a59339fdf41_20230609";            //设备ID
    String service_id="BasicData";           //服务ID
    String commands="control";             //命令名称


    String token="";
    public HuaweiIOT()throws Exception
    {
        token=gettoken();
    }
    public String getAtt(String att,String mode) throws Exception{
        String strurl="";
        if(mode=="shadow")  strurl="https://iotda.cn-north-4.myhuaweicloud.com"+"/v5/iot/%s/devices/%s/shadow";
        if(mode=="status")  strurl="https://iotda.cn-north-4.myhuaweicloud.com"+"/v5/iot/%s/devices/%s";
        strurl = String.format(strurl, project_id,device_id);

        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json");
        urlCon.addRequestProperty("X-Auth-Token",token);
        urlCon.connect();

        InputStreamReader is = new InputStreamReader(urlCon.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuffer strBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        is.close();
        urlCon.disconnect();
        String result = strBuffer.toString();
        System.out.println(result);

        if(mode=="shadow")
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readValue(result, JsonNode.class);
            JsonNode tempNode = jsonNode.get("shadow").get(0).get("reported").get("properties").get(att);
            String attvaluestr = tempNode.asText();
            System.out.println(att+"=" + attvaluestr);
            return attvaluestr;
        }
        if(mode=="status")
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readValue(result, JsonNode.class);
            JsonNode statusNode = jsonNode.get("status");
            String statusstr = statusNode.asText();
            System.out.println("status = " + statusstr);
            return statusstr;
        }
        return "error";
    }
    public String setCom(String com,String value,String com2,String value2) throws Exception{
        String strurl="";
        strurl="https://iotda.cn-north-4.myhuaweicloud.com"+"/v5/iot/%s/devices/%s/commands";
        strurl = String.format(strurl, project_id,device_id);
        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json");
        urlCon.addRequestProperty("X-Auth-Token",token);
        urlCon.setRequestMethod("POST");
        urlCon.setDoOutput(true);
        urlCon.setUseCaches(false);
        urlCon.setInstanceFollowRedirects(true);
        urlCon.connect();

        //String body = "{\"paras\":{\""+com+"\""+":"+value+"},\"service_id\":\""+service_id+"\",\"command_name\":\""+commands+"\"}";
        String body = "{\"paras\":{\""+com+"\""+":\""+value+"\",\""+com2+"\""+":\""+value2+"\"},\"service_id\":\""+service_id+"\",\"command_name\":\""+commands+"\"}";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream(),"UTF-8"));
        writer.write(body);
        writer.flush();
        writer.close();

        InputStreamReader is = new InputStreamReader(urlCon.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuffer strBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        is.close();
        urlCon.disconnect();
        String result = strBuffer.toString();
        System.out.println(result);
        return result;
    }
    public String gettoken( )throws Exception
    {
        String strurl="";
        strurl="https://iam.cn-north-4.myhuaweicloud.com"+"/v3/auth/tokens?nocatalog=false";
        String tokenstr="{"+"\""+"auth"+"\""+": {"+"\""+"identity"+"\""+": {"+"\""+"methods"+"\""+": ["+"\""+"password"+"\""+"],"+"\""+"password"+"\""+": {"+"\""+"user"+"\""+":{"+"\""+"domain\": {\"name\": \""+HUAWEINAME+"\"},\"name\": \""+IAMINAME+"\",\"password\": \""+IAMPASSWORD+"\"}}},\"scope\": {\"project\": {\"name\": \"cn-north-4\"}}}}";
        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json;charset=utf8");

        urlCon.setDoOutput(true);
        urlCon.setRequestMethod("POST");
        urlCon.setUseCaches(false);
        urlCon.setInstanceFollowRedirects(true);
        urlCon.connect();


        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream(),"UTF-8"));
        writer.write(tokenstr);
        writer.flush();
        writer.close();
        Map headers = urlCon.getHeaderFields();
        Set<String> keys = headers.keySet();
        /*for( String key : keys ){
            String val = urlCon.getHeaderField(key);
            System.out.println(key+"    "+val);
        }*/
        String token = urlCon.getHeaderField("X-Subject-Token");
        System.out.println("X-Subject-Token"+"："+token);
        return  token;
    }
}
