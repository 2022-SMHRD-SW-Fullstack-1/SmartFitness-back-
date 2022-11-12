package com.smartfitness.demo.exception;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

/**
 * 에러 코드 정리
 * 400 BAD_REQUEST : 잘못된 요청, url오류
 * 401 UNAUTHORIZED : 인증되지 않은 사용자
 * 404 NOT_FOUND : Resource를 찾을 수 없음, 페이지나 파일을, 요청한 것을 찾을 수 없음
 * 409 CONFLICT : Resource의 현재 상태와 충돌. 보통 중복된 데이터 존재
 * **/
@Getter
@AllArgsConstructor
public enum ErrorCode {
	//전달 오류
	BAD_REQUEST(400,"REQUEST-ERR-400", "BAD REQUEST"),
	//멤버URL전달 오류
	MEM_BAD_REQUEST(400,"MEM-REQUEST-ERR-400", "MEM BAD REQUEST"),
	//운동기구URL전달 오류
	EM_BAD_REQUEST(400,"EM-REQUEST-ERR-400", "EM BAD REQUEST"),
	//프로그램URL전달 오류
	PG_BAD_REQUEST(400,"PG-REQUEST-ERR-400", "PG BAD REQUEST"),
	//QNA전달 오류
	QNA_BAD_REQUEST(400,"QNA-REQUEST-ERR-400", "QNA BAD REQUEST"),
	//URL오류
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    //멤버아이디 찾을 수 없음(로그인 등)
    MEMBER_NOT_FOUND(404,"MEMBER-ERR-404","MEMBER NOT FOUND"),
    //멤버 비번 잘못된 비밀번호
    WRONG_PW(404,"MEMBER-ERR-404","WRONG PW"),
    //운동기구 못찾음
    EM_NOT_FOUND(404,"EM-ERR-404","EM NOT FOUND"),
    //프로그램 못찾음
    PG_NOT_FOUND(404,"PG-ERR-404","PG NOT FOUND"),
    //기구 예약 못찾음
    EM_BK_NOT_FOUND(404,"EM-ERR-404","EM BOOK NOT FOUND"),
    //프로그램 예약 못찾음
    PG_BK_NOT_FOUND(404,"PG-ERR-404","PG BOOK NOT FOUND"),
    //회원 예약 내역이 없다
    MEM_BK_NOT_FOUND(404,"MEM-ERR-404","MEM BOOK NOT FOUND"),
    //QNA가 없다
    QNA_NOT_FOUND(404,"QNA-ERR-404","QNA NOT FOUND"),
    //누가 이미 예약했다(중복)
    BK_CONFLICT(409,"MEM-BK-ERR-409","ALREADY BOOKED"),
    //네가 이미 예약했다(중복)
    MEM_BK_CONFLICT(409,"MEM-BK-ERR-409","YOU ALREADY BOOKED"),
    //정원 초과했을 때 에러
    PG_MAX(409,"PG-MAX","FULLY BOOKED"),
    
    //한 사람이 트레이너에게 여러번 평점을 주려고 할 때
    TR_DU(409,"TR_DU","TR RATED"),
    
    //이미 존재하는 아이디다(회원정보 수정)
    MEM_CONFLICT(409,"MEM-ERR-409","MEMBER ID CONFLICT"),
    
    //서버 오류
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),    
    //서버에서 바디 값을 못받음
    BODY_MISSING(500,"REQUIRE-ERR-500","REQURED REQUEST BODY IS MISSING")
//    MEM_DUPLICATION(409,"MEMBER-ERR-409","DUPLICATED"),
    
    ;

    private int status;
    private String errorCode;
    private String message;
}