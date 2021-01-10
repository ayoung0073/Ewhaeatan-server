package com.mayko.ewhaplate.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class GoogleImgSearch {
    public String getImgUrl(String name){
        String imageUrl = "";
        try{
            Connection.Response res = Jsoup.connect(
                    "https://www.googleapis.com/customsearch/v1?key=AIzaSyClNu9WR-T9rzvkrnRqmp9FwaREl1CRgrI&cx=a52ef0dacaa9e1fb3&q=" + name + "이대")
                    //"https://cse.google.com/cse?key=AIzaSyClNu9WR-T9rzvkrnRqmp9FwaREl1CRgrI&cx=a52ef0dacaa9e1fb3#gsc.tab=1&gsc.q=이대+" + name + "&gsc.sort=&gsc.page=1")
                    .ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").execute();
            JSONObject json = null;

            json = new JSONObject(res.body());
            System.out.println(res.body());
            imageUrl =
                    json.getJSONArray("items").getJSONObject(0).getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("src");
            System.out.println(imageUrl);


        }catch (Exception e){
            imageUrl = "";
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return imageUrl;
    }
}