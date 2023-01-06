const boardWriteCheck = () => {
    if(document.form.title.value.trim() === '') {
        alert('글 제목을 적어주세요');
        return false;
    } else if(document.form.content.value.trim() === '') {
        alert('글 내용을 적어주세요');
        return false;
    } else if(document.form.password.value.trim() === '') {
        alert('비밀번호를 입력하세요');
        return false;
    } else if(document.form.content.value.length > 2000) {
        alert('글 내용이 너무 깁니다');
        return false;
    } else {
        return true;
    }
}