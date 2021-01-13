package com.mayko.ewhaplate.utils;

import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.entity.Menu;
import com.mayko.ewhaplate.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class InfoSearch {
    public static void main(String[] args) throws IOException {

        String food = "이대 맛집";
        String url = "https://www.siksinhot.com";
        String categoryUrl = "https://www.siksinhot.com/taste?upHpAreaId=10&hpAreaId=90&isBestOrd=N";

        List<Food> foodList = new ArrayList<>();

        Document doc = Jsoup.connect(categoryUrl).get();
        System.out.println("연결 성공");
        for(int i = 1; i <33; i++) {
            System.out.println(i);
            Element element = doc.select(".cont a").get(i);
            String name = element.selectFirst(".box_tit").text();
            String href = element.attr("href");
            String clickUrl = url + href;

            Document doc2 = Jsoup.connect(clickUrl).get();

            Elements categories = doc.select(".menu_ul li");

            String phone = doc2.getElementsByClass("p_tel").text().replace("전화번호 ", "");
            String address = doc2.getElementsByClass("txt_adr").text();
            if (phone.equals("02-499-0458")) return;

            Food foodObj = new Food(address, name, phone, "", "", "");
            foodList.add(foodObj);

        }
        writeCsv(foodList);

    }
    public static void writeCsv(List<Food> foodList){
        //출력 스트림 생성
        BufferedWriter bufWriter = null;
        try{
            String makePath = "C:\\Users\\82103\\Desktop\\food.csv";
            bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(makePath), "MS949"));
            //csv파일 읽기

            for(Food food: foodList) {

                bufWriter.write(food.getName());
                bufWriter.write(",");

                bufWriter.write(food.getAddress());
                bufWriter.write(",");

                bufWriter.write(food.getCategory());
                bufWriter.write(",");

                bufWriter.write(food.getEwhaType());
                bufWriter.write(",");

                bufWriter.write(food.getPhone());
                bufWriter.write(",");

                bufWriter.write(food.getImageUrl());


                bufWriter.newLine();
            }

        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(bufWriter != null){
                    bufWriter.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}
