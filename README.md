# 1. 프로젝트 텅 빈 

## 주제

- 프레스 기능과 재활용 분류 모델을 이용한 스마트 쓰레기통

## 기간

- 2024.07.09 ~ 2024.08.16(6주)

## 팀원 역할 및 담당

| 지예찬 | 황우성 | 김민아 | 오혜성 | 전기철 | 여현대 |
| ----- | ------ | ----- | ------ | ----- | ------ |
| <a href="https://github.com/dev-yesam"><img src="https://avatars.githubusercontent.com/u/156279309?v=4" alt="dev-yesam" width="100" height="100"></a> | <a href="https://github.com/WOOSEONG-1"><img src="https://avatars.githubusercontent.com/u/156279287?v=4" alt="WOOSEONG-1" width="100" height="100"></a> | <a href="https://github.com/alsdk9349"><img src="https://avatars.githubusercontent.com/u/156388712?v=4" alt="alsdk9349" width="100" height="100"></a> | <a href="https://github.com/gemutsruhe"><img src="https://avatars.githubusercontent.com/u/44457864?v=4" alt="gemutsruhe" width="100" height="100"></a> | <a href="https://github.com/jbs3047"><img src="https://avatars.githubusercontent.com/u/57222722?v=4" alt="jbs3047" width="100" height="100"></a> |<a href="https://github.com/duguseo"><img src="https://avatars.githubusercontent.com/u/50838264?v=4" alt="duguseo" width="100" height="100"></a> | 

| Contributors | Role | Position |
| ------------ | ---- | -------- |
| 지예찬 | 팀장, <br /> Backend  | - 숏컷 기능 개발 <br> - 백엔드 오류 수정 <br> |
| 황우성  | 팀원, <br /> Backend,<br />Infra | - WebSocket 채팅 서버 <br> - 롤 API 담당하여 명세서 작성 <br> - 롤 LOCAL API 이용한 크롤링 <br> - socket s3를 이용한 이미지 업로드 |
| 김민아 | 팀원, <br /> Backend  | - 쓰레기통 관련 기능 개발 <br> - 카테고리, 잔량 관련 기능 개발 <br> - 이메일 인증, 비밀번호 변경 기능 개발 <br> - DB 조회 성능 최적화, 에러 수정 |
| 오혜성 | 팀원, <br /> Frontend | - 일렉트론 포팅 및 개발 & PWA 포팅 <br> - 클라이언트 & 라이브 채팅창 연동 <br> - 선행기술 연구 <br> - UCC 제작 |
| 전기철 | 팀원, <br /> Embedded, <br /> AI| - 3D 프린팅 및 하드웨어 제작<br> - YOLO 이용 GPU서버에서 재활용 이미지 분류 모델 제작<br>- YOLO와 GPIO 연결을 통한 인식 결과에 맞춘 LED 제어<br> - Jetson Orin nano 환경 세팅 및 코드 결합 <br>   |
| 여현대 | 팀원, <br /> Embedded, <br /> | - WebSocket 채팅 프론트 <br> - 웹 UI/UX <br> - PWA 반응형 모바일 UI |



# 1. 프로젝트 컨셉 및 주요 기능

## 목적

- 직접 가지 않아도 쓰레기통 잔량을 확인할 수 있고, 쓰레기 압축 기능을 통해 쓰레기통을 효율적으로 관리할 수 있다.

## 예상 사용자

- 여러 쓰레기통을 관리하는 관리 업체

## 주요 기능

- 쓰레기 압축 프레스 기능
- AI를 이용한 재활용 쓰레기 분류 및 안내 기능


## 기능 설명

- 쓰레기 압축 프레스 기능
    - 쓰레기를 버리고 버튼을 누를 시 자동 압축 기능을 통해 쓰레기통을 효율적으로 관리할 수 있음.
- AI를 이용한 재활용 쓰레기 분류 및 안내 기능
    - 재활용 쓰레기 분류 모델을 이용하여 쓰레기 카테고리에 맞는 적절한 쓰레기통 안내

## 기대 효과

- 직접 쓰레기통 위치로 가서 확인하지 않아도 잔량을 알 수 있어 효율적인 쓰레기통 관리 가능
- 재활용 쓰레기 분류 안내를 통해 쓰레기가 섞이는 것을 방지하여 시간 및 인력 절약 가능

# 2. 개발 환경

## ✏ 기술 스택

### Backend

<img src="https://img.shields.io/badge/SpringBoot-6DB33F.svg?style=for-the-badge&logo=SpringBoot&logoColor=white"><img src="https://img.shields.io/badge/JWT-FBBA00?style=for-the-badge&logo=JWT&logoColor=white"><br>
<img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=MariaDB&logoColor=white"><img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=Redis&logoColor=white">

### Frontend

<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white"><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"><br>
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">

### Embedded

<img src="https://img.shields.io/badge/RaspberryPI-A22846?style=for-the-badge&logo=RaspberryPi&logoColor=white"><img src="https://img.shields.io/badge/Jetson Orin Nano-000000?style=for-the-badge&logo=&logoColor=white">


### AI

<img src="https://img.shields.io/badge/OpenCV-5C3EE8?style=for-the-badge&logo=OpenCV&logoColor=white"><img src="https://img.shields.io/badge/YOLO-2081E2?style=for-the-badge&logo=&logoColor=white">


### CI/CD

<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white"><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">


### Other Tools

<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white"><br>
<img src="https://img.shields.io/badge/jira-0052cc?style=for-the-badge&logo=jira&logoColor=white"><img src="https://img.shields.io/badge/postman-ff6c37?style=for-the-badge&logo=postman&logoColor=white">

## 서비스 아키텍쳐

서비스 아키텍쳐 그림

## ERD

![ERD](readmeImage/ERD.png)

## API 명세

<a href="https://www.notion.so/API-f0c30b2ab305425b81e39f51b29f47ce">기능 및 API 명세</a>

# 3. 주요 기능

### 메인 화면

![main](readmeImage/main.png)

### 회원가입 페이지

![signup](readmeImage/signup.png)

### 잔량 표시 페이지

![remain](readmeImage/remain.png)

### 숏컷 페이지

![shortcut](readmeImage/shortcut.png)

### 마이페이지

![mypage](readmeImage/mypage.png)

### 제품 등록 페이지

![submit](readmeImage/submit.png)

### 프레스 기능

<img src="readmeImage/trashcan.jpg" alt="trashcan" width="400" height="300">
<img src="readmeImage/press.jpg" alt="press" width="400" height="300">


### 재활용 분류 모델

- ![Yoloimage](readmeImage/Yolo.png)

# 4. UCC

UCC 링크
