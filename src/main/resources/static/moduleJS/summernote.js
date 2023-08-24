$(function() {
    $('#summernote').summernote({
        placeholder: '수정할 내용을 작성해주세요.',
        height: 500,            // 에디터 높이
        minHeight: 300,         // 최소 높이
        maxHeight: 700,         // 최대 높이
        focus: true,            // 에디터 로딩 후 포커스 설정
        lang: 'kr-KR',          // 언어 설정 (한국어)
        toolbar: [
            ['style', ['style']],           // 글자 스타일 설정
            ['fontname', ['fontname']],     // 글꼴 설정
            ['fontsize', ['fontsize']],     // 글꼴 크기 설정
            ['font', ['bold', 'underline', 'clear']],       // 글자 굵게, 밑줄, 포맷 제거
            ['color', ['color']],                           // 글자 색상
            ['para', ['ul', 'ol', 'paragraph']],            // 문단 스타일, 순서 없는 목록, 순서 있는 목록
            ['table', ['table']],                           // 테이블 삽입
            ['insert', ['link', 'picture', 'video']],       // 링크 삽입
            ['view', ['codeview']],                         // 코드보기
        ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    });
});
