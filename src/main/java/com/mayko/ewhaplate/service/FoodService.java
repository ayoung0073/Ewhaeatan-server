package com.mayko.ewhaplate.service;

import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.dto.request.FoodWantRequestDto;
import com.mayko.ewhaplate.entity.Menu;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService{

    private final FoodRepository foodRepository;
    private final MenuService menuService;
    private final MenuRepository menuRepository;

    // 랜덤 맛집 뽑기
    @Transactional(readOnly = true)
    public Food getRandomFood(FoodRandomRequestDto requestDto) {
        List<Food> foodList;
        if(requestDto.getCategories().size() == 0){
            foodList = foodRepository.findAllByEwhaType(requestDto.getEwhaType());
        }
        else {
            foodList = foodRepository.findAllByCategoryNotInAndEwhaType(requestDto.getCategories(), requestDto.getEwhaType());
        }
        // 난수를 foodList 개수로 나눈 나머지 -> 랜덤 음식
        if(foodList.size() == 0) throw new IllegalArgumentException("해당 맛집이 없습니다");
        else {
            int random = (int) ((Math.random() * 100) % (foodList.size()));
            return foodList.get(random);
        }
    }

    // 조건에 해당하는 맛집 리스트
    @Transactional(readOnly = true)
    public List<Food> getFoodList(FoodWantRequestDto requestDto){
        int categorySize = requestDto.getCategories().size();
        int ewhaTypeSize = requestDto.getEwhaTypes().size();
        // 카테고리만 요청 온 경우
        if(ewhaTypeSize == 0 && categorySize != 0)
            return foodRepository.findAllByCategoryIn(requestDto.getCategories());
        // ewhaType만 요청 온 경우
        else if(ewhaTypeSize != 0 && categorySize == 0)
            return foodRepository.findAllByEwhaTypeIn(requestDto.getEwhaTypes());
        // 요청 둘 다 없는 경우는 전체 리스트 return
        else if(ewhaTypeSize == 0 && categorySize == 0)
            return foodRepository.findAll();
        // 둘 다 요청 온 경우
        else
            return foodRepository.findAllByCategoryInAndEwhaTypeIn(requestDto.getCategories(), requestDto.getEwhaTypes());
    }

    // 조건에 해당하는 맛집 리스트
    @Transactional(readOnly = true)
    public Food getFood(Long foodId){
        return foodRepository.findById(foodId).orElseThrow(
                () -> new IllegalArgumentException("해당 맛집이 없습니다")
        );
    }

    // 맛집 등록
    @Transactional
    public void register(FoodRequestDto requestDto) throws Exception {

        if(foodRepository.findByName(requestDto.getName()).isPresent()) {
            throw new Exception("이미 존재하는 맛집입니다.");
        }

        String categoryUrl = "";
        String url = "https://www.siksinhot.com";
        if(requestDto.getEwhaType().equals("신촌")) {
            categoryUrl = url + "/search?keywords=" + requestDto.getName() + "%20신촌";
        }
        else {
            categoryUrl = url + "/search?keywords=" + requestDto.getName() + "%20이대";
        }

        Document doc = Jsoup.connect(categoryUrl).get();
        Element element = doc.selectFirst(".cont a");
        String href = element.attr("href");
        String clickUrl = url + href;

        doc = Jsoup.connect(clickUrl).get();

        String phone = doc.getElementsByClass("p_tel").text().replace("전화번호 ","");
        String address = doc.getElementsByClass("txt_adr").text();
        if(!phone.equals("02-499-0458")) { // 예외 제외 처리
            requestDto.setAddress(address);
            requestDto.setPhone(phone);
        }
        else{
            requestDto.setPhone("");
            requestDto.setAddress("");
        }
        Food food = new Food(requestDto);

        if(!phone.equals("02-499-0458")){
            menuService.registerMenu(food, doc); // 메뉴 등록하기
        }

        foodRepository.save(food);
    }

    @Transactional(readOnly = true)
    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }

    @Transactional
    public void deleteFood(Long id){
        foodRepository.deleteById(id);
    }

    @Transactional
    public void updateFood(Long id, FoodRequestDto requestDto){
        Food food = foodRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 맛집이 없습니다")
        );
        food.updateFood(requestDto);
        Menu menu = menuRepository.findByFoodId(food.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 메뉴 없습니다")
        );
        menu.updateMenu(requestDto.getMenuName(), requestDto.getPrice());
    }
}
