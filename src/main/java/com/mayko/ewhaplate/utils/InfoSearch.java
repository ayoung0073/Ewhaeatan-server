package com.mayko.ewhaplate.utils;

import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.entity.Menu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoSearch {
    public static void main(String[] args) throws IOException {
        String food = "스탠바이키친";
        String url = "https://www.siksinhot.com";
        String categoryUrl = url + "/search?keywords=" + food + "%20이대";

        Document doc = Jsoup.connect(categoryUrl).get();
        Element element = doc.selectFirst(".cont a");
        String href = element.attr("href");
        String clickUrl = url + href;

        doc = Jsoup.connect(clickUrl).get();

        Elements categories = doc.select(".menu_ul li");

        String phone = doc.getElementsByClass("p_tel").text().replace("전화번호 ","");
        String address = doc.getElementsByClass("txt_adr").text();
        if(phone.equals("02-499-0458")) return;
        System.out.println(phone);
        System.out.println(address);
    }
}
