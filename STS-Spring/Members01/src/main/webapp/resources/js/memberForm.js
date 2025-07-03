/**
 * 
 */
      /* 회원가입 양식의 입력값 확인 - 아이디, 비밀번호 */
      /* 1. form요소에 submit 이벤트가 발생되면 
    	 2. form요소의 아이디 입력값, 비밀번호 입력값 확인 
    	 3. 아이디 또는 비밀번호가 입력 되지 않으면 submit 중단 */

      // 1. 태그의 이벤트 등록 - form 태그의 submit 이벤트
      // 문서의 form 요소 선택
      const memberFormEl = document.getElementById("memberForm");
      // 아이디 입력 태그
      const inputMidEl = memberFormEl.mid;
      // 비밀번호 입력 태그
      const inputMpwEl = memberFormEl.mpw;

      memberFormEl.addEventListener("submit", function (e) {
        /* 이벤트 호출시 실행 코드 */
        console.log("submit 이벤트 호출");
        /* 아이디 입력 확인 */
        let idValue = inputMidEl.value;
        if (idValue.length < 1) {
          //alert("아이디를 입력 해주세요!");
          // 아이디 관련 메세지 출력태그 선택 - id 속성으로 선택
          const idMsgEl = document.querySelector("#idMsg");
          // 선택된 요소에 메세지 작성
          idMsgEl.innerHTML = "아이디를 입력 해주세요!";
          // 선택된 요소에 글자색 red
          idMsgEl.style.color = "red";
          e.preventDefault(); // 이벤트 중단
        }
        /* 비밀번호 입력 확인 */
        let pwValue = inputMpwEl.value;
        // inputMpwEl : 비밀번호입력태그
        // inputMpwEl.nextElementSibling : 비밀번호입력태그의 다음 요소
        let pwMsgEl = inputMpwEl.nextElementSibling;
        if (pwValue.length < 1) {
          //alert("비밀번호를 입력 해주세요!");
          pwMsgEl.innerHTML = "비밀번호를 입력 해주세요!";
          // 선택된 요소에 글자색 red
          pwMsgEl.style.color = "red";
          e.preventDefault(); // 이벤트 중단
        }
      });