
let currentQuestionnaire = []

onload = () => {
  var id = getParameterByName('id');
  if (id) {
    // ID参数存在
    console.log('ID参数值为: ' + id);
    window.sessionStorage.setItem("currentPreviewQuestionnaireId",id)
  } else {
    // ID参数不存在
    console.log('URL中没有ID参数');
  }
  $.ajax({
    url: '/questionnaire/'+window.sessionStorage.getItem('currentPreviewQuestionnaireId'),
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res)
        $('.questionnaire-title').html('问卷标题:'+res.data.questionnaireName)
        $('.questionnaire-description').html('问卷说明:'+res.data.description)
    }
  })
  $.ajax({
    url: '/problem/'+window.sessionStorage.getItem('currentPreviewQuestionnaireId'),
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res)
      if (res.code==666){


        currentQuestionnaire = res.data

        console.log("currentQuestionnaire的数组为：")
        console.log(currentQuestionnaire)

        //1：单选，2：多选，3：填空，4：矩阵，5：量表
        for (let i = 0; i < currentQuestionnaire.length; i++) {
          let questionnaireHtml = ''
          let mustAnswer = ''
          if (currentQuestionnaire[i].mustAnswer){
            mustAnswer=' 必答题 '
          }
          let problemOptions = []
               switch (currentQuestionnaire[i].problemType){
                 case 1:
                   //单选题
                      questionnaireHtml+='<div class="question" id="question'+i+'" data-type="1" data-problemIndex="'+ (i+1) +'">'
                      questionnaireHtml+='<div class="top">' +
                          '<span class="question-title" id="questionTitle">'+(i+1)+'、'+currentQuestionnaire[i].problemName+'(单选题)</span>' +
                          '<span class="must-answer" id="mustAnswer">'+mustAnswer+'</span>' +
                          '</div>'
                     questionnaireHtml+='<div class="bottom">'
                     problemOptions = currentQuestionnaire[i].option
                     problemOptions.forEach((item)=>{
                       questionnaireHtml+='<div style="display: flex; align-items: center; margin-bottom: 3px;">' +
                                          '<label class="radio-inline">' +
                                          '<input type="radio"  value="'+item.id+'"  name="chooseTerm'+currentQuestionnaire[i].problemName+'">' + item.chooseTerm+
                                          '</label>' +
                                          '</div>'
                     })
                     questionnaireHtml+=' </div></div>'
                   break
                 case 2:
                   //多选题
                   questionnaireHtml+='<div class="question" id="question'+i+'" data-type="1" data-problemIndex="'+ (i+1) +'">'
                   questionnaireHtml+='<div class="top">' +
                       '<span class="question-title" id="questionTitle">'+(i+1)+'、'+currentQuestionnaire[i].problemName+'(多选题)</span>' +
                       '<span class="must-answer" id="mustAnswer">'+mustAnswer+'</span>' +
                       '</div>'
                   questionnaireHtml+='<div class="bottom">'
                   problemOptions = currentQuestionnaire[i].option
                   problemOptions.forEach((item)=>{
                     questionnaireHtml+='<div style="display: flex; align-items: center; margin-bottom: 3px;">' +
                         '<label class="checkbox-inline">' +
                         '<input type="checkbox" value="'+item.id+'" name="chooseTerm'+currentQuestionnaire[i].problemName+'">' + item.chooseTerm +
                         '</label>' +
                         '</div>'
                   })
                   questionnaireHtml+=' </div></div>'
                   break
                 case 3:
                   //填空题
                   questionnaireHtml+='<div class="question" id="question'+i+' " data-type="1" data-problemIndex="'+ (i+1) +'">'
                   questionnaireHtml+='<div class="top">' +
                       '<span class="question-title" id="questionTitle">'+(i+1)+'、'+currentQuestionnaire[i].problemName+'(填空题)</span>' +
                       '<span class="must-answer" id="mustAnswer">'+mustAnswer+'</span>' +
                       '</div>'
                   questionnaireHtml+='<div class="bottom">'
                   questionnaireHtml+='<textarea class="form-control'+currentQuestionnaire[i].problemName+'" placeholder="请输入" rows="4" style="width: 70%;"></textarea>'
                   questionnaireHtml+='</div>'
                   break
                 case 4:
                   //矩阵题
                   questionnaireHtml+='<div class="question" id="question'+i+' " data-type="1" data-problemIndex="'+ (i+1) +'">'
                   questionnaireHtml+='<div class="top">' +
                       '<span class="question-title" id="questionTitle">'+(i+1)+'、'+currentQuestionnaire[i].problemName+'(矩阵题)</span>' +
                       '<span class="must-answer" id="mustAnswer">'+mustAnswer+'</span>' +
                       '</div>'
                   questionnaireHtml+='<div class="bottom">  <table class="table"> <thead>'
                   questionnaireHtml+='<th>' + "   "+'</th>'
                   problemOptions = currentQuestionnaire[i].option
                   let leftTile = currentQuestionnaire[i].leftTitle
                   problemOptions.forEach((item)=>{
                     questionnaireHtml+=
                         '<th>' +
                         +item.chooseTerm
                         '</th>'
                   })
                     questionnaireHtml+='</thead>'
                     questionnaireHtml+='<tbody>' +
                         '<tr>' +
                         '<td>' +
                         leftTile+
                         '</td>'

                   problemOptions.forEach((item)=>{
                     questionnaireHtml+=
                         '<td>' +
                         '<input type="radio" name="chooseTerm'+currentQuestionnaire[i].problemName+'" />' +
                         '</td>'
                   })
                     questionnaireHtml+='</tr>' +
                         '</tbody></table></div></div>'
                   break
                 case 5:
                   //量表的渲染
                   questionnaireHtml+=        questionnaireHtml+='<div class="question" id="question'+i+'" data-type="1" data-problemIndex="'+ (i+1) +'">'
                   questionnaireHtml+='<div class="top">' +
                       '<span class="question-title" id="questionTitle">'+(i+1)+'、'+currentQuestionnaire[i].problemName+'(量表题)</span>' +
                       '<span class="must-answer" id="mustAnswer">'+mustAnswer+'</span>' +
                       '</div>'
                   questionnaireHtml+='<div className="bottom" style="display: flex; align-items: center; justify-content: space-between;">'
                   problemOptions = currentQuestionnaire[i].option
                     problemOptions.forEach((item)=>{
                       questionnaireHtml+=' ' +
                           '<label className="radio-inline" style="padding-left:150px;">' +
                           '    <input type="radio" name="fraction'+currentQuestionnaire[i].problemName+'"/>  ' +item.fraction
                           '</label>'
                     })
                   questionnaireHtml+='  </div>' +'</div>'
                   break
                 default:
                   break
               }
          $('#problem').append(questionnaireHtml)
        }
      }else {
        alert("加载出错")
      }
    }
  })

  document.getElementById("questionnaireSubmit").onclick = submitQuestionnaire

}

let userTrueName = ''
const submitQuestionnaire = ()=>{
  $('input').prop('disabled', true);
  $('textarea').prop('disabled', true);
  $('#questionnaireSubmit').prop('disabled',true)
  let name = prompt("请输入你的姓名");

  userTrueName = name

  if (name==null)name='匿名'
  $('#answer').html("答卷人："+name)

  $('textarea').each(function() {
    let value = $(this).val();
    console.log(value)
    $(this).attr('placeholder',value)
  });

  $('input[type="radio"]').each(function() {
    if ($(this).is(':checked')) {
      $(this).attr('checked', true);
    }
  });

  $('input[type="checkbox"]').each(function() {
    if ($(this).is(':checked')) {
      $(this).attr('checked', true);
    }
  });




  const html = $('#problem').html();
  const param = {
    projectId : sessionStorage.getItem('seeProject')  ,
    questionnaireName : $('.questionnaire-title').html(),
    respondent : name,
    content: html
  }
  $.ajax({
    url: '/record/add',
    type: "POST",
    data: JSON.stringify(param),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if (res.code == 666){
        console.log(res)

        //以下是信息新加功能用来获取Record
        handleSubmit()

      }
    }
  })


}

// 获取URL参数ID的函数
function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
      results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}


//以下是新加内容，用于数据分析
let params = []


/**
 * 将选中情况存入analysis表中
 */
const handleSubmit = () =>{

  var fullAnswer = true;
  currentQuestionnaire.map((item, index) => {

    $(`#question${index} .bottom`).each(function() {

      var questionType = $(this).find('input[type="radio"]').length > 0 ? 'radio' : '';//发现单选题
      if(questionType === ''){
        console.log("按钮类型不是单选框")
        questionType = $(this).find('input[type="checkbox"]').length > 0 ? 'checkbox' : '';
      }//发现多选题
      var isChecked = false;

      // 根据问题类型进行相应操作
      if (questionType === 'radio') {
        // 单选框逻辑
        $(this).find('input[type="radio"]:checked').each(function() {
          console.log('单选题')
          console.log($(this).val())
          addRecord($(this).val(), item.id, item.problemName);
          isChecked = true
        });
      } else if(questionType === 'checkbox'){
        // 复选框逻辑
        $(this).find('input[type="checkbox"]:checked').each(function() {
          console.log('多选题')
          console.log($(this).val())

          addRecord($(this).val(), item.id, item.problemName);
          isChecked = true
        });
      }

      //如果未选择并且必答
      if (!isChecked && item.mustAnswer) {
        alert("第"+index+"题未作答，请至少选一个选项");
        fullAnswer = false
      }

    });
  })

  if(fullAnswer){
    console.log(params)

    $.ajax({
      url: '/analysis/add',
      type: "POST",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        if (res.code === "666") {//成功作答跳转到成功页面，可以不写
          alert("提交问卷成功")
          window.location.href="/pages/seeProject/index.html"
        }else{
          alert(res.message)
        }
      }
    })
  }
}

const addRecord = (id, qId, problemName) =>{
  //设置record对象
  let record = {
    respondent: userTrueName,
    optionId: id,
    problemId: qId,
    problemContent: problemName,
    questionnaireId: $util.getPageParam('seeQuestionnaireId'),
    projectId:  $util.getPageParam('seeProjectId')
  }
  console.log("问卷id为：")
  console.log(record.questionnaireId)

  params.push(record)

}

