

 ## ERS 패키시 설치 교육


### 개요
- RAID1 : 디스크 2개에 셋팅 많이함
- RAID5 : 3개이상  디스크

- 600 * 5 / 300 * 2 일때 구성
    - 600 * 3 / RADI5 --> /data : 오라클
    - 600 * 2 / RAID1 --> /backup : 오라클 백업
    - 300 * 2 / RAID1 --> / : 운영체제

- 오라클 자동설치 쉘을 만들어둠 -> 현재는 리눅스 버젼을 타는지 잘안됨

- ERS 패키징 [ GitLab 으로 통합]
    - 어바야는 좀 안전한데 cisco 는 멈쳐있음
    
    - ers_packaging_v6.5 :
    - ers_server_v6.5 :
    - ers_web_v6.5 : 

- VM 셋팅 : [링크](10.0.55.53) 
    - root / R5Dev!@# 
    - Redhot8 : 설치 iso 는 data머시기1번에 있음
    - cpu 2 / 4096MB / 500G

## OS

- Redhat8.5 / 언어는 영어 / 시간은 우선 한국시간으로
- Server with GUI + Development Tools 조합
- 파티션 구성
    

 ### DB 테이블 구조

- 오라클 테이블 스페이스 조사하기
- 백업주기는 매주 일요일


| Prefix | 설명 | 테이블 예 |
|:---|---|:---|
| U_ | 	각종 정보가 있는 테이블들.. |	 U_AGTInfo, U_SKLInfo, U_IBGInfo … |
|M_ |	매칭 정보가 있는 테이블 (특정 2개의 정보가 Pair로 존재) |	M_SKL_AGT, M_AGTTEAM_AGT … |
|H_  |	Historical 통계 데이터 테이블 (15분, 30분, 시간, 일, 월별로 데이터 쌓임)	| H_IBG_I, H_SKL_H, H_AGT_D …  	시간단위별로 15분(I), 30분(I30), 시간(H), 일(D), 월(M)이 뒤에 붙음. 	I, I30, H는 테이블 뒤에 yyyymm 이 있음.
|C_ |	Call 단위 통계 데이터 테이블 (1콜당 1개의 Row) |	C_IB_yyyymm, C_OB_yyyymm … 
|E_ |	이벤트성 통계 데이터 테이블(1콜당 여러 개 Row.. ) |	E_TCD_yyyymm, E_AGT_LOGIN_yyyymm… 
|R_	| Realtime 통계 데이터 테이블 (모니터링을 위한 테이블) |	R_IBG, R_SKL, R_AGT … 
|T_x_ |	실시간 모니터링을 위해 당일 Historical 데이터만 모아놓은 테이블 | T_E_IVRSVC, T_C_IVRCALL …… |




 ### IPCC 기본교육

- 대표번호 DNIS -> 



rtd sym tcp 만 깐다.

