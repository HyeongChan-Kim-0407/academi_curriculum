/**
 * 
 */
 
 /* 회원가입 양식의 입력값 확인 - 아이디, 비밀번호 확인 form 태그의 event 속성 or 스크립트 이용 */
      /* form 요소에 submit 이벤트 발생 (= onsubmit) 
         form 요소의 아이디 입력값, 비밀번호 입력값 확인
         아이디 또는 비밀번호가 입력되지 않으면 submit 중단 */

      // 1. 요소(tag, element)의 이벤트 등록 - form 태그의 submit 이벤트
      // 문서의 form 요소 선택
      const MEMBERFORM_E1 = document.getElementById("memberForm");
      // 아이디 입력 태그
      const INPUTMID_E1 = MEMBERFORM_E1.mid; // MEMBERFORM_E1.name속성값;
      // 비밀번호 입력 태그
      const INPUTMPW_E1 = MEMBERFORM_E1.mpw; // MEMBERFORM_E1.name속성값;

      /* form요소.addEventListener( "이벤트" , 호출 함수명(해당 함수가 없을 시 정의가능 function(이벤트 발생 대상){
        // 이벤트 호출시 실행코드
      }) ); */
      MEMBERFORM_E1.addEventListener("submit", function (e) {
        console.log("submit 이벤트 발생");
        /* 아이디 입력 확인 */
        let idValue = INPUTMID_E1.value;
        let pwValue = INPUTMPW_E1.value;
        const CHECKMID_E1 = document.querySelector("#checkmid");
        const CHECKMPW_E1 = document.getElementById("checkmpw");
        if (idValue.length <= 0) {
          // alert("아이디를 입력해주십시오");
          // 아이디 관련 메시지 출력 태그에 경고메시지 출력
          /* getElementById 형식으로 checkmid 선택 */
          // const CHECKMID_E1 = document.getElementById("checkmid");
          /* css 형식으로 checkmid 선택 */
          CHECKMID_E1.innerHTML = "아이디를 입력해주십시오";
          // innerHTML="" : 태그 안에 "" 내용 삽입
          e.preventDefault(); // 이벤트 중단
          return;
        } else if (pwValue.length <= 0) {
          /* 비밀번호 입력 확인 */
          CHECKMID_E1.innerHTML = "";
          // alert("비밀번호를 입력해주십시오");
          CHECKMPW_E1.innerHTML = "비밀번호를 입력해주십시오";
          e.preventDefault(); // 이벤트 중단
          return;
        }
      });