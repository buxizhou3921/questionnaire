onload = ()=>{
    let projectName = window.sessionStorage.getItem("createQuestionnaireProjectName");
    let projectId = window.sessionStorage.getItem("createQuestionnaireProjectId");
    fetchRecordList(projectId)
    $('#projectName').html('项目名： '+projectName)
}

const fetchRecordList = (pId)=>{
    $.ajax({
        url: '/record/'+pId,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let recordList =  res.data
            let rows = ""
            rows +=`
                <table class="table table-bordered table-striped">
                <tr>
                <th></th>
                <th>问卷名</th>
                <th>答卷人</th>
                <th>答卷时间</th>
                <th>操作</th>
                </tr>`
            for (let i = 0; i < recordList.length; i++) {
                rows += "<tr>";
                rows += "<td>" + (i+1) + "</td>";
                rows += "<td>" + recordList[i].questionnaireName + "</td>";
                rows += "<td>" + recordList[i].respondent + "</td>";
                rows += "<td>" + recordList[i].answerTime + "</td>";
                rows +=
                    "<td> " +
                    `<button type=\"button\" class=\"btn btn-link\" onclick=\"mingxi('${recordList[i].id}')\">明细</button>` +
                    "</td>";
                rows += "</tr>";
            }
            rows+='</table>'

            $("#content").html(rows)
        }
    })
}
const mingxi=(id)=>{
    window.sessionStorage.setItem("currentRecordId",id)
    window.location.href='/pages/answerSheetRecord/index.html'
}