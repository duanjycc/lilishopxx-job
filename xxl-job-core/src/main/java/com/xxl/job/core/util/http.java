//package com.xxl.job.core.util;
//
//
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import com.alibaba.fastjson.JSONObject;
//
//public class http {
//	 /**
//     * 向目的URL发送post请求
//     * @param url       目的url
//     * @param params    发送的参数
//     * @return  ResultVO
//     */
//    public static String sendPostRequest(String url, MultiValueMap<String, Object> params){
//        RestTemplate client = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//        HttpMethod method = HttpMethod.POST;
//        // 以表单的方式提交
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        //将请求头部和参数合成一个请求
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//        //执行HTTP请求，将返回的结构使用ResultVO类格式化
//        ResponseEntity<JSONObject> response = client.exchange(url, method, requestEntity, JSONObject.class);
//
//        
//        return response.getBody().toJSONString();
//    }
//}
