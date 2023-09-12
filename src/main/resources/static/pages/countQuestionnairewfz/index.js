
onload = () =>{

    let id = $util.getPageParam('countQuestionnaireId')
    fetchProblemInfo(id)
}

let questionnaireName = ''
let problem = [] //使用problem来存储问题和选项

const fetchProblemInfo = (id) =>{
    let params = {
        questionnaireId: id
    }
    $.ajax({
        url: '/analysis/getAllProblems',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            problem = res.data
            // console.log(problem)
            fetchQuestionListInfo(id)//这个函数需要对页面进行处理
        }
    })
}


const fetchQuestionListInfo = (id) =>{

    questionnaireName = $util.getPageParam('countQuestionnaireName')

    //拼接问题标题
    $('#content').append(`
        <h3 class = "questionnaire-title">${questionnaireName}</h3>
        <div id="problemContent"></div>
    `)
    // console.log("开始加载下方按钮样式")
    // console.log(problem)

    //以下代码负责拼接问题框
    problem.map((item, index) => {
        // console.log(item)
        $('#problemContent').append(`
                <div class="question-title-inline">
                    <div>
                        <span class="question-title" id="questionTitle">第${index + 1}题：</span>
                        <span class="question-text">${problem[index].problemName}</span>
                    </div>
                    <button type="button" class="btn btn-link" onclick="countSimilar('${index}')">同类问题统计</button>
                </div>
                <div id="paneContent${index}" class="fixed-height-container"></div>
                <div class="d-flex justify-content-end question-item">
                    <div class="btn-group" role="group" aria-label="Button Group" id = "buttonGroup${index}">
                        <button type="button" class="btn btn-sm btn-icon" id="tableIcon${index}" onclick="selectTable('${index}')">
                             表格
                        </button>
                        <button type="button" class="btn btn-sm btn-icon" id="pieIcon${index}" onclick="selectPie('${index}')">
                             饼状
                        </button>
                        <button type="button" class="btn btn-sm btn-icon" id="loopIcon${index}" onclick="selectLoop('${index}')">
                            循环
                        </button>
                        <button type="button" class="btn btn-sm btn-icon" id="columnIcon${index}" onclick="selectColumn('${index}')">
                            柱状
                        </button>
                        <button type="button" class="btn btn-sm btn-icon" id="barIcon${index}" onclick="selectBar('${index}')">
                            条形
                        </button>
                        <button type="button" class="btn btn-sm btn-icon" id="lineIcon${index}" onclick="selectLine('${index}')">
                            折线
                        </button>
                    </div>
                </div>
        `)
        // console.log("开始加载表格")
        selectTable(index)

    })
}

/**
 * 进入同类问题统计页面
 */
const countSimilar = (index) =>{
    $util.setItem('countProblem', problem[index])//想缓存中存入问题属性

    location.href = '/pages/countSimilarWfz/index.html'
}

/**
 * 选择表格展示
 */
const selectTable = (index) =>{

    //以下代码负责将按钮变蓝
    // 移除之前点击过的按钮和图标的"active-btn"类
    var previousActiveBtn = document.querySelector('#buttonGroup'+index+' .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }

    // console.log('tableIcon'+index)
    // 添加当前选中的按钮和图标的"active-btn"类
    var currentBtn = document.getElementById('tableIcon'+index);
    // console.log(currentBtn)
    currentBtn.classList.add('active-btn');
    // console.log(currentBtn)

    //加载表格数据, 获得问题数据
    let params = {
        questionId: problem[index].id
    }

    //发送HTTP请求，获取响应数据，响应数据包含选项id，选项被选择的次数，选项所占比例
    // console.log("问题的id为：")
    // console.log(problem[index].id)
    $.ajax({
        url: '/analysis/listById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let counts= res.data
            // console.log(counts)

            //加载表单
            $('#paneContent'+index).html('')
            $('#paneContent'+index).append(`
                
            <table id="table${index}" class="table table-hover custom-table">
                <thead>
                <tr>
                    <th>选项</th>
                    <th>小计</th>
                    <th>比例</th>
                </tr>
                </thead>
                <tbody id="tbody${index}"></tbody>
                <tfoot>
                <!-- 统计行 -->
                <tr>
                    <td>本题有效填写人次</td>
                    <td>${counts.total}</td>
                    <td></td>
                </tr>
                </tfoot>
            </table>
        `)
        // console.log("问题选项如下所示，我想要判断为什么出现了错误")
        // console.log(problem[index].option)

        //加载表单项
        problem[index].option.map(item => {
            // console.log(counts)

            //首先获取选项所对应的数据
            let count = "nothing"
            counts.analysisOptions.map(optionRecord =>{
                if(item.id === optionRecord.id){
                    // console.log("optionRecord的值为：")
                    // console.log(optionRecord)
                    count = optionRecord
                    // console.log(count)
                }
            })

            // console.log("count的值为：")
            // console.log(count)

            $('#tbody'+index).append(`
                <tr>
                    <td>${item.chooseTerm}</td>
                    <td>${count === "nothing" ? 0 : count.total}</td>
                    <td style="display: flex; justify-content: center; align-items: center;">                  
                            <div class="progress" style="height: 20px; width: 300px">
                                <div class="progress-bar" role="progressbar" aria-valuenow="${count.percentage}" aria-valuemin="0"
                                    aria-valuemax="100" style="width: ${count.percentage};">${count === "nothing" ? '0.00%' : count.percentage}</div>
                        </div>
                    </td>
                </tr>
              `)
            })

        }
    })
}


const getName = (id, index) =>{

    console.log(index)
    console.log("开始执行获取name操作")
    let name = ''
    problem[index].option.map(item =>{
        if(item.id === id){
            name = item.chooseTerm
            console.log(name)
        }
    });


    return name
}

/**
 * 选择饼状图展示
 */
const selectPie = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup'+index+' .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('pieIcon'+index);
    currentBtn.classList.add('active-btn');

    // console.log("问题的id为：")
    // console.log(problem[index].id)

    //加载表格数据, 获得问题数据
    let params = {
        questionId: problem[index].id
    }

    $.ajax({
        url: '/analysis/listById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let counts = res.data//获取统计量
            console.log(counts)
            problem[index].option.map(item => {
// 判断元素是否存在
                $('#paneContent' + index).html('');

                $('#paneContent' + index).append(`
                <div id="chartContainer${index}" class="chart-container"></div>
               `);
                // 获取容器元素
                let chartContainer = document.getElementById('chartContainer' + index);

                // 初始化 ECharts 实例
                let chart = echarts.init(chartContainer);


                //发送HTTP请求，获取响应数据，响应数据包含选项id，选项被选择的次数，选项所占比例
                // console.log("问题的id为：")
                // console.log(problem[index].id)

                console.log("counts的值为：")
                console.log(counts)
                // 定义饼图的配置项和数据
                let options = {
                    series: [{
                        type: 'pie',
                        data: counts.analysisOptions.map(item => {
                            return {
                                value: item.total,
                                name: getName(item.id, index)
                            };
                        })
                    }]
                };

                // 使用配置项和数据绘制饼图
                chart.setOption(options);
            })
        }
    })

}


/**
 * 选择循环图
 */
const selectLoop = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup'+index+' .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('loopIcon'+index);
    currentBtn.classList.add('active-btn');

    //加载表格数据, 获得问题数据
    let params = {
        questionId: problem[index].id
    }

    $.ajax({
        url: '/analysis/listById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let counts = res.data//获取统计量
            console.log(counts)
            problem[index].option.map(item => {

                $('#paneContent' + index).html('');

                $('#paneContent' + index).append(`
                <div id="chartContainer${index}" class="chart-container"></div>
               `);
                // 获取容器元素
                let chartContainer = document.getElementById('chartContainer' + index);

                // 初始化 ECharts 实例
                let chart = echarts.init(chartContainer);


                //发送HTTP请求，获取响应数据，响应数据包含选项id，选项被选择的次数，选项所占比例
                // console.log("问题的id为：")
                // console.log(problem[index].id)

                console.log("counts的值为：")
                console.log(counts)
                // 定义饼图的配置项和数据
                let options = {
                    series: [{
                        type: 'pie',
                        radius: ['50%', '70%'], // 设置内外半径，形成环状图
                        data: counts.analysisOptions.map(item => {
                            return {
                                value: item.total,
                                name: getName(item.id, index)
                            };
                        })
                    }]
                };

                // 使用配置项和数据绘制饼图
                chart.setOption(options);
            })
        }
    })

}

/**
 * 选择柱状图
 */
const selectColumn = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup'+index+' .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('columnIcon'+index);
    currentBtn.classList.add('active-btn');

    //加载柱状图
    $('#paneContent'+index).html('')


    //加载表格数据, 获得问题数据
    let params = {
        questionId: problem[index].id
    }

    $.ajax({
        url: '/analysis/listById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let counts = res.data//获取统计量
            console.log(counts)
            problem[index].option.map(item => {

                $('#paneContent' + index).html('');

                $('#paneContent' + index).append(`
                <div id="chartContainer${index}" class="chart-container"></div>
               `);
                // 获取容器元素
                let chartContainer = document.getElementById('chartContainer' + index);

                // 初始化 ECharts 实例
                let chart = echarts.init(chartContainer);


                //发送HTTP请求，获取响应数据，响应数据包含选项id，选项被选择的次数，选项所占比例
                // console.log("问题的id为：")
                // console.log(problem[index].id)

                console.log("counts的值为：")
                console.log(counts)
                // 定义柱状图的配置项和数据
                let options = {
                    xAxis: {
                        type: 'category',
                        data: counts.analysisOptions.map(item => getName(item.id, index))
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        type: 'bar',
                        data: counts.analysisOptions.map(item => item.total)
                    }]
                };

                // 使用配置项和数据绘制饼图
                chart.setOption(options);
            })
        }
    })

}

/**
 * 选择条形图
 */
const selectBar = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup'+index+' .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('barIcon'+index);
    currentBtn.classList.add('active-btn');

    //加载条形图
    $('#paneContent'+index).html('')

    //加载表格数据, 获得问题数据
    let params = {
        questionId: problem[index].id
    }

    $.ajax({
        url: '/analysis/listById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let counts = res.data//获取统计量
            console.log(counts)
            problem[index].option.map(item => {

                $('#paneContent' + index).html('');

                $('#paneContent' + index).append(`
                <div id="chartContainer${index}" class="chart-container"></div>
               `);
                // 获取容器元素
                let chartContainer = document.getElementById('chartContainer' + index);

                // 初始化 ECharts 实例
                let chart = echarts.init(chartContainer);


                //发送HTTP请求，获取响应数据，响应数据包含选项id，选项被选择的次数，选项所占比例
                // console.log("问题的id为：")
                // console.log(problem[index].id)

                console.log("counts的值为：")
                console.log(counts)
// 定义横向条形图的配置项和数据
                let options = {
                    yAxis: {
                        type: 'category',
                        data: counts.analysisOptions.map(item => getName(item.id, index))
                    },
                    xAxis: {
                        type: 'value'
                    },
                    series: [{
                        type: 'bar',
                        data: counts.analysisOptions.map(item => item.total)
                    }]
                };

                // 使用配置项和数据绘制饼图
                chart.setOption(options);
            })
        }
    })
}

/**
 * 选择折线图
 */
const selectLine = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup'+index+' .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('lineIcon'+index);
    currentBtn.classList.add('active-btn');


    //加载表格数据, 获得问题数据
    let params = {
        questionId: problem[index].id
    }

    $.ajax({
        url: '/analysis/listById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let counts = res.data//获取统计量
            console.log(counts)
            problem[index].option.map(item => {
// 判断元素是否存在

            $('#paneContent' + index).html('');

            $('#paneContent' + index).append(`
                <div id="chartContainer${index}" class="chart-container"></div>
              `);


            // 获取容器元素
            let chartContainer = document.getElementById('chartContainer' + index);

            // 初始化 ECharts 实例
            let chart = echarts.init(chartContainer);


            //发送HTTP请求，获取响应数据，响应数据包含选项id，选项被选择的次数，选项所占比例
            // console.log("问题的id为：")
            // console.log(problem[index].id)

            let options = {
                xAxis: {
                    type: 'category',
                    data: counts.analysisOptions.map(item => getName(item.id, index))
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    type: 'line',
                    data: counts.analysisOptions.map(item => item.total)
                }]
            };

            // 使用配置项和数据绘制饼图
            chart.setOption(options);
        })
        }
    })
}

