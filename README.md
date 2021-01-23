# ewhaeatan 🍽
결정을 잘 못하는 이화인들을 위한<br>
이화여대, 신촌 주변 맛집 랜덤 추천 서비스


### 🔗 https://ewhaeatan.vercel.app/<br>


**FRONTEND [@eun-ko](https://github.com/eun-ko) ✨<br>**
**BACKEND [@ayoung0073](https://github.com/ayoung0073)**


### 🛠 기술 스택 & 라이브러리

**BackEnd**   ```Java 8``` ```Spring Boot 2.4.1``` ```JPA``` 

**Database**   ```ClearDB MySQL```

**Server** ```heroku```

**Library** ```Google Custom Search API``` 

**IDE** ```IntelliJ IDEA```

---
### FRONT
![c1](https://user-images.githubusercontent.com/69340410/105575548-c6bf0700-5daf-11eb-924c-5eabf2c231dd.jpg)
현재 위치와 먹고 싶지 않은 음식 카테고리를 선택 
=> **```[POST] /random```** 랜덤 맛집 추천

![c2](https://user-images.githubusercontent.com/69340410/105575547-c4f54380-5daf-11eb-9c17-3dd237577999.jpg)
1-1. 다시하기 => **```[POST] /random```** 랜덤 맛집 추천 <br>

1-2. 전체리스트 => **```[GET] /list/all```** 맛집 전체 리스트 <br>

2. list 화면 필터링 버튼 => **```[POST] /list```** 해당 리스트 <br>

3. 등록하기 => **```[POST] /register```** 추천 맛집 등록 : ```Google Custom Search API```와 크롤링을 통해 주소, 연락처, 이미지 설정

---

### 서버 ADMIN 페이지 (맛집리스트)
mustache, ajax 구현

![image](https://user-images.githubusercontent.com/69340410/105575783-6fba3180-5db1-11eb-9c05-132e8363232d.png)
전체 리스트 페이지
![image](https://user-images.githubusercontent.com/69340410/105575806-ab54fb80-5db1-11eb-8053-4b354d10aa8d.png)
수정 페이지
![image](https://user-images.githubusercontent.com/69340410/105575788-79439980-5db1-11eb-9768-a0ba48f62a0c.png)
**수정, 삭제 버튼 클릭 시**

**```[POST] /check```** 패스워드 체크 후,
<br>
1) 수정 : **```[PUT] /update/{foodId}```**
2) 삭제 : **```[DELETE] /delete/{foodId}```**
