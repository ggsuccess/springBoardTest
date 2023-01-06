const hiddenValue = document.getElementsByClassName('hidden');
const inputPassword = document.getElementsByClassName('inputpassword');
const hiddenPasswordCheck = () => {
    if(inputPassword[0].value.trim() === '') {
      alert('비밀번호를 입력해주세요.');
      return false;
    } else if(hiddenValue[0].value !== inputPassword[0].value) {
      alert('비밀번호가 틀립니다.');
      return false;
    } else if(hiddenValue[0].value === inputPassword[0].value) {
      return true;
    }
}