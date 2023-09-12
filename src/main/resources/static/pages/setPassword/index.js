onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('重置密码')

  let user = $util.getPageParam('user')
  // console.log('--- 用户信息 ---');
  // console.log(user);
  if (user) {
    $('#username').val(user.username)
    $('#password').val(user.password)
  }
}

const handleCreateUser = () => {

  if (!$('#username').val()) return alert('账号不能为空！')
  if (!$('#password').val()) return alert('密码不能为空！')

  let user = $util.getPageParam('user');
  console.log('--- user ---')
  console.log(user);
  if(!user) {
    user = {};
  }
 
  user.username = $('#username').val();
  user.password = $('#password').val();



      $.ajax({
        url: API_BASE_URL + '/admin/modifyUserInfo',
        type: 'POST',
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
          if (res.code === "666") {
            location.href = '/pages/user/index.html'
          } else {
            alert(res.message)
          }
        }
      })

}
