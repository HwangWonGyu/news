# news

## 목적
* 뉴스 서비스를 직접 구현해보며 ***백엔드***를 이해
* ***의미 있고*** 가독성 높은 코드를 작성
* ***사용하는 기술***에 대해 깊이있고 정확히 이해
* ***객체지향 프로그래밍을 코드에 반영***

## 기획
* 사용자에게 여러 언론사의 기사를 헤드라인, 조회수, 섹션, 언론사와 같은 기준으로 구분하고 정리해서 제공하여
***좀 더 편하게*** 뉴스 서비스를 이용할 수 있게 해줌
* 사용자에게 검색창을 통해서 원하는 기사를 단어로 검색할 수 있도록 제공하여
***좀 더 편하게*** 뉴스 서비스를 이용할 수 있게 해줌
* 기자들에게는 기사를 관리할 수 있도록 별도의 기자 전용 웹 페이지를 제공
* 네이버, 다음 등 포털 기반의 뉴스사이트에서 접하기 어려운 지역 언론사의 기사들은 공공데이터를 활용해 보완

* 이 서비스는 한국을 대상으로 함
* 헤드라인은 우선 각 언론사별 기사 중 조회수가 가장 높은 기사만 모아둠
그 중 기사 1개를 무작위로 선택해 헤드라인 중에서도 가장 크게 표시하고
나머지 남은 기사 중에서도 3개를 무작위로 선택해 표시
* 섹션은 총 7개로 뉴스홈, 정치, 경제, 사회, 생활/문화, 세계, IT/과학이 있음
* ***언론사는 총 N개로 ~가 있음***
* ***총 등록 사용자수 N명, 트래픽량, 초단위 혹은 월단위 엑세스 X~Y개, 서버 하드웨어 대수 등 현재 기대하는 서비스 규모에 따른 구체적인 명세가 필요한 상황


[공공데이터 참고 사이트](https://www.data.go.kr/data/15034926/openapi.do)

[프로토타입 (ovenapp 활용)](https://ovenapp.io/view/wp8c3hZx9oYXGnwD4AWbaX0Zz3NKWFxw/)

[서비스화면 이미지](https://github.com/HwangWonGyu/news#%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%B0%B8%EA%B3%A0-%EC%9D%B4%EB%AF%B8%EC%A7%80)

## Use Case
https://github.com/HwangWonGyu/news/wiki/Use-Case

## DB ERD
![news_ERD](https://user-images.githubusercontent.com/15853498/102218071-cca80980-3f20-11eb-86e7-18c4eb7ac0eb.PNG)

## 서비스화면 이미지
<div>
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102216864-20b1ee80-3f1f-11eb-9e26-ddab43e8b7bb.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217022-5a82f500-3f1f-11eb-8c3d-3ef4e4852259.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102216823-109a0f00-3f1f-11eb-91cc-69e6c9979f48.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217064-68387a80-3f1f-11eb-9998-d11f3197b1d9.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217097-6f5f8880-3f1f-11eb-99b1-018039869b9c.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217120-77b7c380-3f1f-11eb-9e9f-6d6f64434d75.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217149-7eded180-3f1f-11eb-91b1-9ca23351cd48.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217154-81412b80-3f1f-11eb-8923-447d90bfc1a4.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217165-869e7600-3f1f-11eb-89cd-9cb64c492e78.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217169-8900d000-3f1f-11eb-93c4-1597d97fddc5.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217177-8bfbc080-3f1f-11eb-8093-b3bcd5a67f58.PNG">
  <img width="400" src="https://user-images.githubusercontent.com/15853498/102217183-8dc58400-3f1f-11eb-944a-ab1fdb867631.PNG">
</div>
