![image](https://cdn.discordapp.com/attachments/986931212041977916/1007082261868458034/32x-100.jpg)

<br />

서비스명 : Wefit   
팀명 : 친력사무소   
개발기간 : 2022.07.04 ~ 2022.08.19   
서비스 개요 : 친구랜덤매칭 서비스   
[Notion](https://well-trampoline-c3b.notion.site/we-fit-a1eadec4b9da4668bb2a6d8130987749) 


---

<br />

# 목차
1. [서비스 소개](#1)
2. [기술 스택](#2)
3. [시스템 아키텍처](#3)
4. [주요 기능](#4)
5. [팀원 소개](#5)
6. [프로젝트 산출물](#6)
7. [기술 회고](#7)
   
<br />

<div id='1'> </div>

# 서비스 소개

## **매일 스쳐 지나가는 인연이 아닌 공통점을 매개로 이어지는 인연 찾기**
> 나와 맞는 MBTI, 나와 같은 관심사를 갖는 인연을 찾아보세요

<br />

## **첫 만남에 외모는 중요하지 않아요**
> 자기소개와 미션 먼저, 목소리와 얼굴공개는 천천히

<br />

## **첫 만남이 어색한가요?**
> 대화카드로 시작하여 여러 게임을 통해 어색함을 풀어봐요

<br />

## **We Fit에서 친구를 만나봐요**


<br />

<div id='2'></div>

# 기술 스택

<p align="center">
<img src="https://img.shields.io/badge/-JAVA-brightgreen" /> 
<img src="https://img.shields.io/badge/-Spring Boot-green" /> 
<img src="https://img.shields.io/badge/-SPRING SECURITY-yellowgreen" /> 
<img src="https://img.shields.io/badge/-JWT-yellow" /> 
<img src="https://img.shields.io/badge/-STOMP-blue" /> 
<img src="https://img.shields.io/badge/-JPA-orange" /> 
<img src="https://img.shields.io/badge/-HIBERNATE-red" /> 
<img src="https://img.shields.io/badge/-QUERYDSL-lightgrey" /> 
<img src="https://img.shields.io/badge/-VUE-brightgreen" /> 
<img src="https://img.shields.io/badge/-HTML-blue" /> 
<img src="https://img.shields.io/badge/-CSS-lightgrey" /> 
<img src="https://img.shields.io/badge/-SOCKJS-orange" /> 
<img src="https://img.shields.io/badge/-JAVASCRIPT-yellow" /> 
<img src="https://img.shields.io/badge/-NGNIX-brightgreen" /> 
<img src="https://img.shields.io/badge/-JENKINS-green" /> 
<img src="https://img.shields.io/badge/-DOCKER-blue" /> 
<img src="https://img.shields.io/badge/-DOCKER COMPOSE-orange" /> 
<img src="https://img.shields.io/badge/-EC2-lightgrey" /> 
<img src="https://img.shields.io/badge/-OPENVIDU-brightgreen" /> 
<img src="https://img.shields.io/badge/-UBUNTU-yellow" /> 
<img src="https://img.shields.io/badge/-REDIS-red" /> 
<img src="https://img.shields.io/badge/-GRADLE-lightgrey" /> 
<img src="https://img.shields.io/badge/-JIRA-blue" />
<img src="https://img.shields.io/badge/-GITLAB-yellow" />  
</p>
<br />

## 상세 기술스택 및 버전

| 구분       | 기술스택                    | 상세내용                 | 버전          |
| -------- | ----------------------- | -------------------- | ----------- |
| 공통     | 형상관리                    | Gitlab               | \-          |
|          | 이슈관리                    | Jira                 | \-          |
|          | 커뮤니케이션                  | Mattermost   | \-          |
|          | 커뮤니케이션                  | Notion   | \-          |
| Server   | 서버                      | AWS EC2              | \-          |
|          | 플랫폼                     | Ubuntu               | \-          |
|          | 배포                      | Docker               | \-          |
|          | 배포                      | Docker Compose              | \-          |
|          | 배포                      | Jenkins              | \-          |
|          | 배포                      | Nginx              | \-          |
| BackEnd  | DB                      | MySQL                | 8.0.29         |
|          |                         | JPA                  | \-          |
|          |                         | QueryDSL             | 5.0.0         |
|          | Java                    | Zulu                 | 8   |
|          | Spring                  | Spring               | \-          |
|          |                         | Spring Boot          | \-          |
|          |                         | Spring Security          | \-          |
|          | IDE                     | Intellij             | \-          |
|          | Cloud Storage           | AWS S3               | \-          |
|          | Cache Storage           | Redis              | \-          |
|          | Build                   | Gradle               | 7.5        |
|          | WebRTC                  | Openvidu              | 2.22.0          |
|          | WebSocket                  | Stomp            | 2.7.2          |
| FrontEnd | HTML5                   |                      | \-          |
|          | CSS3                    |                      | \-          |
|          | JavaScript(ES6)         |                      |\-           |
|          | Vue         |                      |\-           |
|          | IDE                     | Visual Studio Code   | \-          |
|          | WebSocket               | @stomp/stompjs       | \-          |
|          | WebSocket               | stompjs              | \-          |
|          | WebSocket               | sockjs-client        | \-          |
|          | WebSocket               | SockJs        | \-          |

<br />

<div id="3"></div>

<br />

# 시스템 아키텍처

## 시스템 구성
![시스템아키텍쳐](https://user-images.githubusercontent.com/53360337/185049818-fdb906f7-7009-4e75-a085-25ed670b99c7.png)


<br />

## CI/CD
![image](https://user-images.githubusercontent.com/53360337/184604858-47769d77-a3ce-49b2-965f-34a512d5cbff.png)

<br />

<div id = "4"></div>

# 주요 기능
### MBTI 및 관심사 정보를 통한 랜덤 매칭
* 만나기 싫은 MBTI는 만나지 않아요
* 나와 관심사가 비슷한 사람을 찾아드려요

이미지 추가

### 목소리 및 얼굴 필터
* 목소리, 얼굴이 가려진 상태에서 게임을 해봐요
* 게임이 진행될수록 필터가 사라져요
  
이미지 추가

### 대화 카드
* 처음 만남에서 대화거리를 줄게요
  
이미지 추가

### 벨런스 게임
* 벨런스 게임을 통해서 상대와 대화해봐요

이미지 추가
  
### 캐치마인드
* 캐치마인드로 그림을 그리고 맞춰봐요

이미지 추가
  
<br />

<div id="5"></div>

# 팀원소개
<table>
    <tr>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/184502226-97b9774c-31f9-4a6f-bc60-cc1d8d1fb426.png" width="140px" /> </a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/184502242-5b3895ee-6eef-42d2-a68b-2ba7b45ebe16.png" width="140px" /></a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/184502252-8b64eaee-1e1b-48f3-86cc-056e228a2359.png" width="140px" /></a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/184502289-81e9a973-7d28-4eee-8537-5a6492a003e7.png" width="140px" /> </a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/184502297-00174606-1664-49de-b008-4307accf5578.png" width="140px" /> </a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/184502283-048ee421-1a7e-4507-b72a-8130c926c819.png" width="140px" /></a> <br></td>
    </tr>
    <tr>
        <td align="center">유영진</td>
        <td align="center">강승훈</td>
        <td align="center">김진산</td>
        <td align="center">윤영훈</td>
        <td align="center">조성환</td>
        <td align="center">서준희</td>
    </tr>
    <tr>
        <td align="center">Back-End</td>
        <td align="center">Back-End</td>
        <td align="center">Back-End</td>
        <td align="center">Front-End</td>
        <td align="center">Front-End</td>
        <td align="center">Front-End</td>
    </tr>
    <tr>
        <td align="center">1</td>
        <td align="center">1</td>
        <td align="center">1</td>
        <td align="center">1</td>
        <td align="center">1</td>
        <td align="center">1</td>
    </tr>
</table>

<br />

<div id="6"></div>

# 프로젝트 산출물

## 컨벤션
### **git commit**
![image](https://user-images.githubusercontent.com/53360337/184503246-50fdc64b-c21e-444b-80c0-c73ae9f9f9e8.png)

<br />

### **git flow**
![image](https://user-images.githubusercontent.com/53360337/184503290-072c65c7-7e4c-457a-9bc8-e395d37abc02.png)


<br />

### **code**
![image](https://user-images.githubusercontent.com/53360337/184523804-d2d5800a-d4bd-49df-ab00-ee0c1debea5f.png)

<br />

## 기능 명세서
![image](https://user-images.githubusercontent.com/53360337/184503157-153a42c1-b429-4d58-8839-cd9a68c34de9.png)

<br />

## API 명세서
![image](https://user-images.githubusercontent.com/53360337/184503190-c6abc6ba-f941-4382-89ee-813c6152ede3.png)

<br />

## 화면 설계서
[화면 설계서](https://www.figma.com/file/DaeQGfmCisSECkzLV5OYJa/%EC%9C%84%ED%95%8F?node-id=2%3A11)

<br />

## ER-Diagram
![image](https://user-images.githubusercontent.com/53360337/184503044-0cb2e131-2706-4aff-b963-ffa161b9cfb3.png)


<br />

## EC2 포트 정리

| 구분       | 포트번호                    | 
| -------- | ----------------------- |
| Vue         |  80, 443                   | 
| Spring         | 8080                    | 
| Jenkins         | 9090                    | 
| Openvidu         | 8443                 | 
| Redis         |   6379               | 
| MySQL         | 3306                 | 

<br />

# 배포

서버는 AWS EC2 ubuntu를 사용했습니다

## 1. Docker

- openvidu 서버 : 8443

- 서비스 포트 번호 : 443

서로 다른 도커 이미지로 저장되어 있어 각각의 이미지를 실행시킵니다.

필요한 이미지들의 설정은 docker-compose.yml 파일에 작성합니다.

docker 내부는 같은 네트워크로 묶어주기 위해 docker-compose로 실행합니다. 

각 이미지들을 필요에 따라 하나의 컨테이너 위에 띄웁니다.

### 1-1. Nginx

- conf/nginx.conf
```
server {
    listen 80;
    server_name i7b206.p.ssafy.io; # 도메인으로 변경

    location / {
        return 308 https://$host$request_uri;
    }
}

server {
    listen 443 ssl;
    server_name i7b206.p.ssafy.io;

    ssl_certificate /etc/letsencrypt/live/i7b206.p.ssafy.io/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/i7b206.p.ssafy.io/privkey;

    location  / {
        proxy_intercept_errors on;
        proxy_pass  http://3.36.88.140:8081;
        add_header      Content-Security-Policy "upgrade-insecure-requests";
        error_page 500 502 503 504 /50x.html;
        error_page 404 /404.html;
    }

    location /api/v1{
        proxy_intercept_errors on;

        proxy_hide_header Access-Control-Allow-Origin;

        add_header 'Access-Control-Allow-Origin' '*';
#        add_header 'Content-Type' 'application/json';

        proxy_pass  http://3.36.88.140:8080$request_uri;


        add_header  Content-Security-Policy "upgrade-insecure-requests";

        error_page 404 /404.html;
        error_page 500 502 503 504 /50x.html;
    }
    location /ws{
        #websocket
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;

        proxy_intercept_errors on;
        proxy_pass http://3.36.88.140:8080$request_uri;


        error_page 404 /404.html;
        error_page 500 502 503 504 /50x.html;
    }

    error_page 404 /404.html;
    location = /404.html{
        root /usr/share/nginx/html;
        internal;
    }
    error_page 500 502 503 504 /50x.html;
    location = /50x.html{
        root /usr/share/nginx/html;
        internal;
    }
}

```

- Nginx 설정을 위한 파일. 루트 디렉토리 아래에 해당 경로로 작성합니다.

- 모든 도메인으로의 요청을 ssl 인증서를 이용하여 Reverse Proxy 설정을 했습니다.

- 웹소켓 사용을 위한 헤더 설정, 에러 페이지 처리 등을 설정했습니다.

- 서버는 letsencrypt를 사용하여 ssl 설정했습니다. openvidu를 사용하기 위해 받은 key의 경로를 각각 ssl_certificate, ssl_certificate_key에 연결해 주었습니다.

### 1-2. Nginx docker-compose.yml
- docker-compose.yml
```
version: '3'
services:
  nginx:
    image: nginx:latest
    restart: unless-stopped
    container_name: nginx
    volumes:
      - ./conf:/etc/nginx/conf.d
      - /etc/letsencrypt:/etc/letsencrypt
      - ./error/404.html:/usr/share/nginx/html/404.html

    ports:
      - 80:80
      - 443:443
networks:
  default:
    external:
      name: nginx-proxy
```
- docker에 있는 nginx 이미지를 사용하여 빌드 및 컨테이너에 띄웠습니다.
- volumes는 빌드된 이미지와 로컬의 경로를 맞춰서 설정파일, ssl 키 등을 읽도록 했습니다.

### 1-3. Vue
- wefit/frontend/Dockerfile
```
FROM node:lts-alpine as builder

# install simple http server for serving static content
RUN npm install -g http-server

# make the 'app' folder the current working directory
WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

FROM nginx:stable-alpine as production
RUN rm -rf /etc/nginx/conf.d/default.conf
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 8081
CMD [ "nginx", "-g", "daemon off;"]
```
- alpine에 nodejs를 설치 후 app 디렉토리를 만들고 해당 위치에 package*.json을 복사 한 뒤 npm을 설치합니다.

- ignore로 등록된 파일 외에 모두 /app 으로 복사하고 빌드합니다.
- nginx를 설치 후 기존 default.conf 파일을 제거하고 설정한 경로에 로컬의 default.conf 를 복사합니다.
- html을 저장한 경로의 기존 파일을 삭제하고 build 해서 나온 dist 파일을 저장합니다.
<br>

- wefit/frontend/conf/default.conf

```
server {
    listen 8081;

    location / {
        alias /usr/share/nginx/html/;
        try_files $uri $uri/ /index.html;
    }

}
```
- Nginx로 빌드하여 이미지를 컨테이너에 띄웠습니다.

- docker-compose.yml
```
version: "3.8"
services:
  vue:
    container_name: vue
    build: ./
    restart: on-failure
    ports:
      - 8081:8081
    expose:
      - 8081

networks:
  default:
    external:
      name: nginx-proxy
```
<br>
<br>

### 1-4.Backend(Spring,MySQL,Redis)
- backend/Dockerfile
```
FROM openjdk:8-jdk-alpine
RUN addgroup -S seungh1024 && adduser -S seungh1024 -G seungh1024
USER seungh1024:seungh1024
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
- java openjdk 8버전을 사용하여 빌드

<br>

- /backend/docker-compose.yml
```
version: "3.8"
services:
  redis:
    container_name: redis
    image: redis:7-alpine
    ports:
      - 6379:6379
    volumes:
      - redis:/data

  mysqldb:
    container_name: mysql
    image: mysql:latest
    restart: unless-stopped
    environment:
      - MYSQL_ROOT_PASSWORD=clsfurtkanth
      - MYSQL_DATABASE=ssafy
      - MYSQL_USER=ssafy
      - MYSQL_PASSWORD=clsfurtkanth
    ports:
      - 3306:3306
    command:
      - --character-set-server=utf8
      - --collation-server=utf8_unicode_ci
    volumes:
      - ./mysqldata:/var/lib/mysql
  spring:
    container_name: spring
    depends_on:
      - mysqldb
      - redis
    build: ./
    restart: on-failure
    ports:
      - 8080:8080
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysqldb:3306/ssafy?useSSL=false&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: clsfurtkanth
mysqldb:3306/ssafy?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false",
hibernate.dialect.MySQL8InnoDBDialect",

    stdin_open: true
    tty: true

networks:
  default:
    external:
      name: nginx-proxy
volumes:
    redis:
      driver: local
```
- 같은 네트워크를 공유하기 위해 하나의 컴포즈 파일에 작성

<br>

<br/>
<br/>

## 2. DevOps

`Jenkins`로  CI/CD를 구축하여 배포용 브랜치에 `push` 이벤트 발생시 build하도록 구성했습니다.

build 후 docker-compose를 실행하도록 했습니다.

<br/>
<br/>

## 3. How to

### Jenkins 설정

1. Jenkins 관리 > 시스템 설정
- Jenkins가 실행중인 서버의 URL 작성
- Gitlab URL 작성
- ssh로 서버에 접속하기 위한 설정
- 프로젝트 빌드 설정(gradle, nodejs)

2. 프로젝트 생성 & 설정
- pipeline 생성
- 빌드트리거 설정(push,merge 했을 때 빌드유발)
- 소스코드 관리(pipeline, branch 설정)
- 빌드 환경설정(pipeline 코드 작성)
- 빌드 후 조치

### Gitlab Webhook 설정

Jenkins 프로젝트 > 구성 > 빌드유발에서 발급한 토큰을 Webhook 설정 페이지에 작성하고 빌드 트리거를 설정한다.


<br/>
<br/>
<br/>
