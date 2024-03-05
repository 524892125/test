package org.example;

import okhttp3.*;
import org.tools.Quartz;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        String result = "<p>标题</p><h1>一级标题</h1><h2>二级标题</h2><h3>三级标题</h3><h4>四级标题</h4><h5>五级标题</h5><p>正文</p><p><br></p><p>引用</p><p>A man who stands&nbsp;<a href=\"http://www.baidu.com\" target=\"_blank\" style=\"background-color: rgb(255, 255, 255);\">for</a>&nbsp;nothing will fall&nbsp;<a href=\"http://www.baidu.com\" target=\"_blank\" style=\"background-color: rgb(255, 255, 255);\">for</a>&nbsp;anything.</p><p><br></p><p>文字加粗、斜体</p><p><span style=\"font-weight: bold;\">bold</span></p><p><span style=\"font-style: italic;\">italic</span></p><p><span style=\"font-style: italic;\"><br></span></p><p>链接</p><p><a href=\"https://www.dingtalk.com\" target=\"_blank\">this is a link</a><span style=\"font-style: italic;\"><br></span></p><p><br></p><p>图片</p><p><img src=\"http://127.0.0.1:9000/public/\" style=\"max-width:100%;\"><br></p>";
//        String markdown = Markdown.toMarkdown(result);
//        System.out.println(markdown);
//        System.out.println(Markdown.flexToMarkdown(result));

//        request();
        Quartz.start();
    }

    public static void request() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("sId","132")
                .addFormDataPart("openid","oEnSP5rWXPlhga8PzeHNRNYgVh8Y")
                .addFormDataPart("sessionKey","374510ac14f60b0d9b45cb3bc9906212")
                .addFormDataPart("tokentp","1")
                .build();
        Request request = new Request.Builder()
                .url("https://www.learnprompt.pro/article/gptStandard")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}