# news

## 목적
* 네이버 뉴스 서비스의 백엔드 시스템을 직접 구현해보며  
스프링 부트 개념(의존성 관리, IoC 컨테이너, AOP, PSA, 설정파일 등),  
표준 스펙 개념(유효성 검사, RESTful API 등),  
서비스 관련 개념(세션, 캐시, 메일 등),  
단위 테스트 (JUnit5),  
운영환경의 앱 배포(AWS)를 이해  
* 설정파일 요소들의 카테고리화,  가독성과 유지보수성이 높은 코드를 작성
* 코드와 연관된 기술의 등장배경, 목적, 특징, 사용법, 주의사항에 대해 공부하고 코드로 익힘
* 개발간 발생하는 예외나 에러를 Issue로 등록하여 협업간 정보공유
* 유사 Issue 발생시 재사용 가능하도록 '해결을 위한 공부 내용-시도 과정-결과+성과'로 정리
* 반복적인 Pull Request와 피드백 반영을 통해 협업간 커뮤니케이션 능력 향상 

## 기획
* 사용자에게 여러 언론사의 기사를 헤드라인, 조회수, 섹션, 언론사와 같은 기준으로 구분 제공하여
사용자의 성향과 관심사를 고려한 뉴스 서비스를 이용할 수 있게 해줌
* 사용자에게 검색창을 통해서 원하는 기사를 단어로 검색할 수 있도록 제공하여
시간적, 통계적 측면에서 효율적인 뉴스 서비스를 이용할 수 있게 해줌
* 기자들에게는 기사를 관리할 수 있도록 별도의 기자 전용 웹 페이지를 제공
* 네이버, 다음 등 포털 기반의 뉴스사이트에서 접하기 어려운 지역 언론사의 기사들은 공공데이터를 활용해 보완

* 이 서비스는 한국을 대상으로 함
* 헤드라인
  - 우선 각 언론사별 기사 중 조회수가 가장 높은 기사만 취합
  - 그 중 기사 1개를 무작위로 선택해 헤드라인 중에서도 가장 크게 표시
  - 나머지 남은 기사 중에서도 3개를 무작위로 선택해 표시
* 섹션은 총 7개로 뉴스홈, 정치, 경제, 사회, 생활/문화, 세계, IT/과학이 있음
* 언론사는 총 12개로 AA, BB, CC, DD, EE, FF, GG, HH, II, JJ, KK, LL가 있음
* 회원수 5만명, 최대 트래픽 규모는 분단위 액세스 약 1000회, DB 서버(MySQL) 1대, 로그인 세션 클러스터링 처리를 위한 Redis 서버(서버 다중화인 Replication도 고려)


[공공데이터 참고 사이트](https://www.data.go.kr/data/15034926/openapi.do)

[프로토타입 (ovenapp 활용)](https://ovenapp.io/view/wp8c3hZx9oYXGnwD4AWbaX0Zz3NKWFxw/)

## Specification
- Java 8
- Spring Boot 2.4.1
- MyBatis 3.5.6
- MySQL 8.0.22
- Redis _? (AWS EC2에 설치 후 기입)_
- 이메일 송신 서버 gmail SMTP
- AWS EC2 t2.micro 인스턴스 1대
- Lombok 1.18.16

## Use Case
https://github.com/HwangWonGyu/news/wiki/Use-Case

## Git

### Convention
[git commit 메시지 컨벤션 참고 사이트](https://meetup.toast.com/posts/106)

* 제목과 본문을 한 줄 띄워 분리하기
* 제목은 영문 기준 50자 이내로
* 제목 첫글자를 대문자로
* 제목 끝에 . 금지
* 제목은 명령조로
* 본문은 영문 기준 72자마다 줄 바꾸기
* 본문은 어떻게보다 무엇을, 왜에 맞춰 작성하기

[git pull request 컨벤션 참고 사이트](https://github.blog/2015-01-21-how-to-write-the-perfect-pull-request/)

PR을 시작하는 법
* PR의 목적을 한문장으로 요약하기
* PR을 생성하게된 맥락이 있는데 이를 리뷰어가 알아야 한다면 함께 명시
* 피드백 받기를 원하는 시점을 명시
* 요청한 PR이 작업중이라면 리뷰어들이 알 수 있도록 '작업중' 혹은 'WIP(Work In Progress)' 라고 기재
* 원하는 피드백의 방향과 내용을 리뷰어가 알 수 있도록 명시
* 짧은 답변이라도 어조를 명확히 하기 위해 이모지 사용

피드백에 응답하는 방법
* 피드백에 대한 감사의 표현
* 이해가 안됐을 경우 리뷰어에게 명확히 표현 할 수 있도록 이끌어내기
* 문제 해결법 피드백에 대한 응답이라면 그 해결법에 도달하기 위해 내린 결정에 대해 설명
* 최대한 모든 피드백에 대해 응답
* 혼란이나 논쟁이 증가하고 있다면 쓰여진 단어가 의사소통에 좋은건지 검토
* 항상 코멘트로 해결하기 보다는 화상회의나 오프라인 토론 후 요약글을 게시하는 것도 고려

## DB ERD
![ERD](https://user-images.githubusercontent.com/15853498/102389829-02c7b500-4017-11eb-8fbd-775686c1af80.PNG)
