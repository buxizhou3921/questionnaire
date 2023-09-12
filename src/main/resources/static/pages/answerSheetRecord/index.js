onload = ()=>{
    const recordId = window.sessionStorage.getItem("currentRecordId")
    $.ajax({
        url: '/record/'+recordId,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success(res){
            console.log(res)
            const record = res.data
            let html = ''
            html+=`<h4>${record.questionnaireName}</h4>`
            html+=`<h5>答卷人： ${record.respondent}</h5>`
            html+=record.content

            $('.container').html(html)
        }}
    )
}