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
7. [배포](#7)
   
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
|         | 서버                      | FireBase             | 8.0.1         |
|          | 플랫폼                     | Ubuntu               | 20.04         |
|          | 배포                      | Docker               | 20.10.17         |
|          | 배포                      | Docker Compose              |  2.6.0         |
|          | 배포                      | Jenkins              | 2.346.2          |
|          | 배포                      | Nginx              | 1.23.1         |
| BackEnd  | DB                      | MySQL                | 8.0.29         |
|          |                         | JPA                  | 5.0.0          |
|          |                         | QueryDSL             | 5.0.0         |
|          | Java                    | Zulu                 | 8   |
|          | Spring                  | Spring               | 5.3.22         |
|          |                         | Spring Boot          | 2.7.2          |
|          |                         | Spring Security          | 2.7.2          |
|          | IDE                     | Intellij             | 2022.2          |
|          | Cache Storage           | Redis              | 7-alpine         |
|          | Build                   | Gradle               | 7.5        |
|          | WebRTC                  | Openvidu              | 2.22.0          |
|          | WebSocket                  | Stomp            | 2.7.2          |
| FrontEnd | HTML5                   |                      | \-          |
|          | CSS3                    |                      | \-          |
|          | JavaScript(ES6)         |                      |\-           |
|          | Vue         |                      |  3.2.13        |
|          | IDE                     | Visual Studio Code   |1.70.0          |
|          | WebSocket               | webstomp-client       | 1.2.6          |
|          | WebSocket               | sockjs-client       | 1.6.1          |

<br />

<div id="3"></div>

<br />

# 시스템 아키텍처

## 시스템 구성
![image](https://user-images.githubusercontent.com/53360337/185145709-d2519b71-e565-442b-bf1a-cae2323fef7b.png)

<br />

## CI/CD
![image](https://user-images.githubusercontent.com/53360337/185145852-3c1ea708-df03-457e-8e70-a77644ec27e8.png)

<br />

## 디렉토리 구조
![image](https://user-images.githubusercontent.com/53360337/185148535-3ae41e28-9d5c-4665-bd8e-a682b66bf204.png)

<br />

<div id = "4"></div>

# 주요 기능
### MBTI 및 관심사 정보를 통한 랜덤 매칭
* 만나기 싫은 MBTI는 만나지 않아요
* 나와 관심사가 비슷한 사람을 찾아드려요

![i7b206 p ssafy io_randomvideo](https://user-images.githubusercontent.com/53360337/185414241-54f53b97-a8e8-471c-869e-092b3e093eac.png)
![i7b206 p ssafy io_randomvideo (1)](https://user-images.githubusercontent.com/53360337/185414353-44555f77-3812-4ed2-9245-22fb1000cf7d.png)



### 목소리 및 얼굴 필터
* 목소리, 얼굴이 가려진 상태에서 게임을 해봐요
* 게임이 진행될수록 필터가 사라져요

<img src="https://user-images.githubusercontent.com/53360337/185411425-a9bea897-db92-4b8c-b0e6-2e39a877396c.png" width="160px" />
<img src="https://user-images.githubusercontent.com/53360337/185411477-db8e509a-9990-495d-a0a5-ecd67a742454.png" width="160px" />
<img src="https://user-images.githubusercontent.com/53360337/185411554-5abd8ece-e665-4aa0-84d7-6cd6df57154f.png" width="160px" />

<br />

### 대화 카드
* 처음 만남에서 대화거리를 줄게요
  
![대화카드](https://user-images.githubusercontent.com/53360337/185390652-81322dba-2560-4def-8d81-bbb2eb39ca03.gif)


### 벨런스 게임
* 벨런스 게임을 통해서 상대와 대화해봐요

![밸런스게임](https://user-images.githubusercontent.com/53360337/185412935-b2b367eb-ad8f-4f58-86d4-07b7460d758a.gif)
  
### 캐치마인드
* 캐치마인드로 그림을 그리고 맞춰봐요

![캐치마인드](https://user-images.githubusercontent.com/53360337/185413323-f7404297-32a4-479e-90ce-c1fc5c6ed90f.gif)


### 고요속의 외침
* 입모양을 보고 말하는 것을 맞춰봐요


  
<br />

<div id="5"></div>

# 팀원소개
<table>
    <tr>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/185133988-eb3716cf-a87c-4a45-905a-0603d3466ee7.png" width="140px" /> </a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/185134234-fd849d9d-ff45-42c9-9971-0f75902687ed.png" width="140px" /></a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/185134422-a4f0821d-b2b0-4cc0-ae98-2cd6c9683652.png" width="140px" /></a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/185134769-c0801c1b-48cd-4c86-b0f6-1ef68c15380b.png" width="140px" /> </a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/185135010-0e5c4ff6-54c7-4a12-add0-30193b32ba6b.png" width="140px" /> </a> <br></td>
        <td height="140px" align="center"> <a href="">
            <img src="https://user-images.githubusercontent.com/53360337/185134598-e16bc8a7-6cc4-4467-be12-3c684d70a091.png" width="140px" /></a> <br></td>
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

<div id="7"></div>

<br />

## EC2 포트 정리

| 구분       | 포트번호                    | 
| -------- | ----------------------- |
| Vue         |  8081               | 
| Jenkins         |  80, 443                 | 
| Spring         | 8080                    | 
| Jenkins         | 9090                    | 
| Openvidu         | 8443                 | 
| Redis         |   6379               | 
| MySQL         | 3306                 | 

<br />

# 배포

서버는 AWS EC2 ubuntu를 사용했습니다


## Openvidu

### Openvidu On premises
 1. 방화벽 설정   
```
ufw allow ssh   
ufw allow 80/tcp   
ufw allow 443/tcp   
ufw allow 3478/tcp   
ufw allow 3478/udp   
ufw allow 40000:57000/tcp   
ufw allow 40000:57000/udp   
ufw allow 57001:65535/tcp   
ufw allow 57001:65535/udp   
ufw enable   
```

<br />

2. Openvidu 파일 설치

```
sudo su   
cd /opt   
curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | bash     
```

<br />

3. 설정

```
cd openvidu   
nano .env   
/*
DOMAIN_OR_PUBLIC_IP= '주어진 도메인 이름'
OPENVIDU_SECRET= '비밀키로 사용할 아무 문자열'
CERTIFICATE_TYPY= letsencrypt
LETSENCRYPT_EMAIL= '아무 이메일'
HTTP= '포트번호'
HTTPS= '포트번호'
*/
```

<br />

4. 실행
```
./openvidu start
```  

<br />

## Docker

- openvidu 서버  8443

- 서비스 포트 번호  443

서로 다른 도커 이미지로 저장되어 있어 각각의 이미지를 실행시킵니다.

필요한 이미지들의 설정은 docker-compose.yml 파일에 작성합니다.

docker 내부는 같은 네트워크로 묶어주기 위해 docker-compose로 실행합니다. 

각 이미지들을 필요에 따라 하나의 컨테이너 위에 띄웁니다.

<br />

### Nginx

- confnginx.conf
```
server {
    listen 80;
    server_name i7b206.p.ssafy.io; # 도메인으로 변경

    location  {
        return 308 https$host$request_uri;
    }
}

server {
    listen 443 ssl;
    server_name i7b206.p.ssafy.io;

    ssl_certificate etcletsencryptlivei7b206.p.ssafy.iofullchain.pem;
    ssl_certificate_key etcletsencryptlivei7b206.p.ssafy.ioprivkey;

    location   {
        proxy_intercept_errors on;
        proxy_pass  http3.36.88.1408081;
        add_header      Content-Security-Policy upgrade-insecure-requests;
        error_page 500 502 503 504 50x.html;
        error_page 404 404.html;
    }

    location apiv1{
        proxy_intercept_errors on;

        proxy_hide_header Access-Control-Allow-Origin;

        add_header 'Access-Control-Allow-Origin' '';
#        add_header 'Content-Type' 'applicationjson';

        proxy_pass  http3.36.88.1408080$request_uri;


        add_header  Content-Security-Policy upgrade-insecure-requests;

        error_page 404 404.html;
        error_page 500 502 503 504 50x.html;
    }
    location ws{
        #websocket
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection upgrade;
        proxy_set_header Host $host;

        proxy_intercept_errors on;
        proxy_pass http3.36.88.1408080$request_uri;


        error_page 404 404.html;
        error_page 500 502 503 504 50x.html;
    }

    error_page 404 404.html;
    location = 404.html{
        root usrsharenginxhtml;
        internal;
    }
    error_page 500 502 503 504 50x.html;
    location = 50x.html{
        root usrsharenginxhtml;
        internal;
    }
}

```

- Nginx 설정을 위한 파일. 루트 디렉토리 아래에 해당 경로로 작성합니다.

- 모든 도메인으로의 요청을 ssl 인증서를 이용하여 Reverse Proxy 설정을 했습니다.

- 웹소켓 사용을 위한 헤더 설정, 에러 페이지 처리 등을 설정했습니다.

- 서버는 letsencrypt를 사용하여 ssl 설정했습니다. openvidu를 사용하기 위해 받은 key의 경로를 각각 ssl_certificate, ssl_certificate_key에 연결해 주었습니다.

<br />

### Nginx docker-compose.yml
- docker-compose.yml
```
version '3'
services
  nginx
    image nginxlatest
    restart unless-stopped
    container_name nginx
    volumes
      - .confetcnginxconf.d
      - etcletsencryptetcletsencrypt
      - .error404.htmlusrsharenginxhtml404.html

    ports
      - 8080
      - 443443
networks
  default
    external
      name nginx-proxy
```
- docker에 있는 nginx 이미지를 사용하여 빌드 및 컨테이너에 띄웠습니다.
- volumes는 빌드된 이미지와 로컬의 경로를 맞춰서 설정파일, ssl 키 등을 읽도록 했습니다.

<br />

### Vue
- wefitfrontendDockerfile
```
FROM nodelts-alpine as builder

# install simple http server for serving static content
RUN npm install -g http-server

# make the 'app' folder the current working directory
WORKDIR app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package.json .

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

FROM nginxstable-alpine as production
RUN rm -rf etcnginxconf.ddefault.conf
COPY .nginxdefault.conf etcnginxconf.ddefault.conf

RUN rm -rf usrsharenginxhtml
COPY --from=builder appdist usrsharenginxhtml

EXPOSE 8081
CMD [ nginx, -g, daemon off;]
```
- alpine에 nodejs를 설치 후 app 디렉토리를 만들고 해당 위치에 package.json을 복사 한 뒤 npm을 설치합니다.

- ignore로 등록된 파일 외에 모두 app 으로 복사하고 빌드합니다.
- nginx를 설치 후 기존 default.conf 파일을 제거하고 설정한 경로에 로컬의 default.conf 를 복사합니다.
- html을 저장한 경로의 기존 파일을 삭제하고 build 해서 나온 dist 파일을 저장합니다.
  
<br />

- wefitfrontendconfdefault.conf

```
server {
    listen 8081;

    location  {
        alias usrsharenginxhtml;
        try_files $uri $uri index.html;
    }

}
```
- Nginx로 빌드하여 이미지를 컨테이너에 띄웠습니다.

- docker-compose.yml
```
version 3.8
services
  vue
    container_name vue
    build .
    restart on-failure
    ports
      - 80818081
    expose
      - 8081

networks
  default
    external
      name nginx-proxy
```

<br />

### Backend(Spring,MySQL,Redis)
- backendDockerfile
```
FROM openjdk8-jdk-alpine
RUN addgroup -S seungh1024 && adduser -S seungh1024 -G seungh1024
USER seungh1024seungh1024
ARG JAR_FILE=buildlibs.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [java,-jar,app.jar]
```
- java openjdk 8버전을 사용하여 빌드

<br />

- backenddocker-compose.yml
```
version 3.8
services
  redis
    container_name redis
    image redis7-alpine
    ports
      - 63796379
    volumes
      - redisdata

  mysqldb
    container_name mysql
    image mysqllatest
    restart unless-stopped
    environment
      - MYSQL_ROOT_PASSWORD=clsfurtkanth
      - MYSQL_DATABASE=ssafy
      - MYSQL_USER=ssafy
      - MYSQL_PASSWORD=clsfurtkanth
    ports
      - 33063306
    command
      - --character-set-server=utf8
      - --collation-server=utf8_unicode_ci
    volumes
      - .mysqldatavarlibmysql
  spring
    container_name spring
    depends_on
      - mysqldb
      - redis
    build .
    restart on-failure
    ports
      - 80808080
    environment
      SPRING_DATASOURCE_URL jdbcmysqlmysqldb3306ssafyuseSSL=false&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false
      SPRING_DATASOURCE_USERNAME root
      SPRING_DATASOURCE_PASSWORD clsfurtkanth
mysqldb3306ssafyallowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false,
hibernate.dialect.MySQL8InnoDBDialect,

    stdin_open true
    tty true

networks
  default
    external
      name nginx-proxy
volumes
    redis
      driver local
```
- 같은 네트워크를 공유하기 위해 하나의 컴포즈 파일에 작성

<br />

## DevOps

`Jenkins`로  CICD를 구축하여 배포용 브랜치에 `push` 이벤트 발생시 build하도록 구성했습니다.

build 후 docker-compose를 실행하도록 했습니다.

<br />

## How to

<br />

### Jenkins 설정

1. Jenkins 관리  시스템 설정
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

<br />

### Gitlab Webhook 설정

Jenkins 프로젝트  구성  빌드유발에서 발급한 토큰을 Webhook 설정 페이지에 작성하고 빌드 트리거를 설정한다.