# Redis cacheing write 권한 서버

## 시스템 구조
<img width="1000" alt="구조3" src="https://user-images.githubusercontent.com/40568894/95047184-98de8580-0720-11eb-89a3-33bd2827b35f.PNG">

## 캐시 업데이트 하는 서버(앱)을 분리의 장점, 이유
* Redis의 쓰기 권한을 분리하기 위해
* access log 를 통해서 문제 생길시 tracking 이 용이
