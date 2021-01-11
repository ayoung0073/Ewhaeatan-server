package com.mayko.ewhaplate.service;

import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.entity.Menu;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;


    public void registerMenu(Food food, Document doc) throws IOException {

        Elements categories = doc.select(".menu_ul li");

        List<Menu> menuList = new ArrayList<>();
        for(Element e : categories){
            String menuName = e.getElementsByClass("tit").text(); // 메뉴이름 가져오기
            if(menuName.contains("냉면 (물,비빔,매운)"))
                break;
            // 가격 가져오기
            int price = Integer.parseInt(e.getElementsByTag("label").text().replace(",", "").replace(" 원", ""));
            //System.out.println(e.getElementsByTag("label").text());
            menuList.add(new Menu(food, menuName, price));
        }

        menuRepository.saveAll(menuList);
    }

    public String addMenu(String name) throws IOException {
        String url = "https://www.siksinhot.com";
        Food food = foodRepository.findDistinctByName(name).orElseThrow(
                () -> new IllegalArgumentException("해당 음식점이 없습니다")
        );
        String categoryUrl = url + "/search?keywords=" + food.getName() + "%20이대";

        Document doc = Jsoup.connect(categoryUrl).get();
        Element element = doc.selectFirst(".cont a");
        String href = element.attr("href");
        String clickUrl = url + href;

        doc = Jsoup.connect(clickUrl).get();

        Elements categories = doc.select(".menu_ul li");
        List<Menu> menuList = new ArrayList<>();
        for(Element e : categories){
            String menuName = e.getElementsByClass("tit").text(); // 메뉴이름 가져오기
            if(menuName.contains("냉면 (물,비빔,매운)"))
                break;

            // 가격 가져오기
            int price = Integer.parseInt(e.getElementsByTag("label").text()
                    .replace(",", "").replace(" 원", "")
                    .split(" ")[0]);
            //System.out.println(e.getElementsByTag("label").text());
            menuList.add(new Menu(food, menuName, price));
        }

        menuRepository.saveAll(menuList);
        return categoryUrl;
    }
}
