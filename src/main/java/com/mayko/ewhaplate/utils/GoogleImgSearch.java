package com.mayko.ewhaplate.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class GoogleImgSearch {
    public Map<String, String> getUrl(String name){
        Map<String, String> map = new HashMap<>();
        String imageUrl = "";
        String url = "";

        try{
            Connection.Response res = Jsoup.connect(
                    "https://www.googleapis.com/customsearch/v1?key=AIzaSyClNu9WR-T9rzvkrnRqmp9FwaREl1CRgrI&cx=a52ef0dacaa9e1fb3&q=" + name + "이대")
                    //"https://cse.google.com/cse?key=AIzaSyClNu9WR-T9rzvkrnRqmp9FwaREl1CRgrI&cx=a52ef0dacaa9e1fb3#gsc.tab=1&gsc.q=이대+" + name + "&gsc.sort=&gsc.page=1")
                    .ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").execute();
            JSONObject json = null;

            json = new JSONObject(res.body());

            url = json.getJSONArray("items").getJSONObject(0).getString("link");
            imageUrl =
                    json.getJSONArray("items").getJSONObject(0).getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("src");

            map.put("imageUrl", imageUrl);
            map.put("url", url);

        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}