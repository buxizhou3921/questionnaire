onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#QuesStartTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#QuesEndTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
}

const handleCreateNewQuestoinaire = () => {
  const questionnaireParams = {
    projectId: window.sessionStorage.getItem("newQuestionnaireId"),
    questionnaireName:$('#surveyName').val(),
    description: $('#surveyDescription').val(),
    type: window.sessionStorage.getItem("newQuestionnaireType"),
    startTime: $('#QuesStartTime').data('date'),
    stopTime:  $('#QuesEndTime').data('date'),
  }
  if (!questionnaireParams.questionnaireName)return alert('问卷名称不能为空')
  if (!questionnaireParams.description)return alert('问卷描述不能为空')
  console.log(questionnaireParams)

  $.post({
    url: '/questionnaire/add',      // 请求的URL
    data: JSON.stringify(questionnaireParams),  // 将JSON对象转换为字符串
    contentType: 'application/json;charset=UTF-8', // 设置请求的内容类型为JSON
    success: function(response) {
      console.log(response);
      window.sessionStorage.setItem("currentQuestionnaireId",response.data)
      alert("新增成功")
      window.location.href="/pages/designQuestionnaire/index.html"
    },
    error: function(xhr, status, error) {
      console.error(error);
    }
  });
}
