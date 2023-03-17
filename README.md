# java-chess

# 기능 목록

## 도메인

### 좌표

- [x] 좌표는 file과 rank를 필드로 가진다.
- [x] 좌표는 (A,1) ~ (H,8) 까지 가능
- [x] 좌표는 다른 좌표로의 방향을 구할 수 있다.

### 피스

- [x] 자신의 진영을 필드로 가지고 있다.
- [ ] 움직일 수 있는 위치 리스트를 반환

### 체스판

- [x] 빈 보드를 생성
- [x] 좌표를 입력으로 주었을때, 좌표의 피스를 반환(getPiece)
    - [x] 좌표에 피스가 존재하는지 확인
    - [x] 같은 색깔 피스 인지 확인.
- [x] 피스를 교체(replace)
- [x] 두 좌표를 주었을때, 두 좌표 사이에 피스가 있는지 검증(checkBetweenRoute)
- [x] 목적지에 같은 색깔의 피스가 있는지 확인(checkSameColor)
- [x] 폰이 움직일 수 있는지 확인(checkRestrictionForPawn)
    - [x] 대각선으로 움직일때, 상대편 피스가 있는지 확인
    - [x] 위,아래 방향으로 움직일때, 칸이 비어있지 않은지 확인

### 피스 팩토리

- [x] 색상에 따라 초기 피스들의 리스트를 반환

### 랭크 : 가로줄을 나타내는 enum

### 파일 : 세로줄을 나타내느 enum

### 게임

- [x] 체스판을 초기화
- [ ] 피스를 이동

### 명령

- [x] 입력받은 명령를 검증(start,end,move)

## UI

### 입력

- [x] 명령어를 입력

### 출력

- [x] 체스판을 출력