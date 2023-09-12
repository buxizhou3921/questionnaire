onload = () => {
  $('#headerDivB').text('项目详情')

  let projectId = sessionStorage.getItem('seeProject')
  console.log(projectId, 'projectId')
  fetchProjectInfo(projectId)
  fetchQuestionnaireList(projectId)
}

let formatter = new Intl.DateTimeFormat('zh-CN', { year: 'numeric', month: '2-digit', day: '2-digit' , hour: '2-digit', minute: '2-digit' });
const fetchProjectInfo = (id) => {
  let params = {
    id
  }
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data[0]
      $('#projectName').text(info.projectName)
      $('#createTime').text(formatter.format(new Date(info.creationDate)))
      $('#projectDescription').text(info.projectContent)
      $('#personInCharge').text(info.createdBy)
    }
  })
}
function fetchQuestionnaireList(id){
  $.ajax({
    url: API_BASE_URL + '/questionnaire/list/'+id,
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res.data)
      let questionnaireList =  res.data
      let rows = ""
      for (let i = 0; i < questionnaireList.length; i++) {
        rows += "<tr>";
        rows += "<td>" + (i+1) + "</td>";
        rows += "<td>" + questionnaireList[i].questionnaireName + "</td>";
        rows += "<td>" + questionnaireList[i].startTime + "</td>";
        rows +=
            "<td> " +
            `<button type=\"button\" class=\"btn btn-link\" onclick=\"preview('${questionnaireList[i].id}')\">预览</button>` +
            `<button type=\"button\" class=\"btn btn-link\" onclick="release('${questionnaireList[i].id}')">发布</button>` +
            `<button type="button" class="btn btn-link" onclick="deleteQuestionnaireById('${questionnaireList[i].id}','${id}')">删除</button>` +
            `<button type=\"button\" class=\"btn btn-link\" onclick="countQuestionnaire('${questionnaireList[i].id}', '${questionnaireList[i].questionnaireName}')">统计</button>`+
            "</td>";
        rows += "</tr>";
      }
      $("#questionnaireListBody").html(rows)
    }
  })
}

function deleteQuestionnaireById(id,projectId){
  $.ajax({
    url: API_BASE_URL + '/questionnaire/'+id,
    type: "DELETE",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      fetchQuestionnaireList(projectId)
      alert("删除成功")
    }
  })
}
function preview(id){

  console.log('将问卷id加入缓存')
  $util.setPageParam('seeQuestionnaireId', id)
  window.sessionStorage.setItem("currentPreviewQuestionnaireId",id);



  window.location.href= '/pages/answerSheet/index.html'
}

function release(id){
  $.ajax({
    url:  '/questionnaire/fabu/'+id,
    type: "POST",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res)
      alert(`发布成功！以下是该问卷的填写界面网址:
             ${res.data}/pages/answerSheet/index.html?id=${id}`)
    }
  })

}

//统计问卷
const countQuestionnaire = (id, name) =>{


  $util.setPageParam('countQuestionnaireId', id)
  // console.log($util.setPageParam('countQuestionnaireId', id))

  // window.sessionStorage.setItem("countQuestionnaireId",id)
  $util.setPageParam('countQuestionnaireName', name)
  location.href = "/pages/countQuestionnaireWfz/index.html"

}