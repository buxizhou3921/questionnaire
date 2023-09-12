let counts  = {}
let problem = {}

onload = () =>{

    problem = $util.getItem('countProblem')
    $('#questionTitle').text(problem.problemName);
    count()
}

/**
 *
 */
const count = () =>{

    let projectId  = $util.getPageParam('seeProjectId')

    console.log(projectId)

    console.log($util.getPageParam('seeProjectId'))
    //加载表格数据, 获得问题数据
    let params = {
        questionName: problem.problemName,
        projectId: $util.getPageParam('seeProjectId')//获取项目id
    }

    $.ajax({
        url: API_BASE_URL + '/analysis/listByName',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            counts = res.data
            selectTable()//ajax请求是异步请求
        }
    })
}
/**
 * 选择表格展示
 */
const selectTable = () =>{

    //以下代码负责将按钮变蓝
    // 移除之前点击过的按钮和图标的"active-btn"类
    var previousActiveBtn = document.querySelector('#buttonGroup .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }

    // console.log('tableIcon'+index)
    // 添加当前选中的按钮和图标的"active-btn"类
    var currentBtn = document.getElementById('tableIcon');
    // console.log(currentBtn)
    currentBtn.classList.add('active-btn');
    // console.log(counts)

    //加载表单
    $('#paneContent').html('')
    $('#paneContent').append(`
                
            <table id="table" class="table table-hover custom-table">
                <thead>
                <tr>
                    <th>选项</th>
                    <th>小计</th>
                    <th>比例</th>
                </tr>
                </thead>
                <tbody id="tbody"></tbody>
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

    //加载表单项
    problem.option.map(item => {

        //首先获取选项所对应的数据
        let count = "nothing"


        counts.analysisOptions.map(optionRecord => {
            if (item.id === optionRecord.id) {
                count = optionRecord
            }
        })

        $('#tbody').append(`
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


const getName = (id) =>{

    let name = ''
    problem.option.map(item =>{
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
    var previousActiveBtn = document.querySelector('#buttonGroup .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('pieIcon');
    currentBtn.classList.add('active-btn');

    $('#paneContent').html('');

    $('#paneContent').append(`<div id="chartContainer" class="chart-container"></div>`);
    // 获取容器元素
    let chartContainer = document.getElementById('chartContainer');

    // 初始化 ECharts 实例
    let chart = echarts.init(chartContainer);


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

}


/**
 * 选择循环图
 */
const selectLoop = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('loopIcon');
    currentBtn.classList.add('active-btn');

    $('#paneContent').html('');

    $('#paneContent').append(`<div id="chartContainer" class="chart-container"></div>`);
    // 获取容器元素
    let chartContainer = document.getElementById('chartContainer');

    // 初始化 ECharts 实例
    let chart = echarts.init(chartContainer);


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

}

/**
 * 选择柱状图
 */
const selectColumn = (index) =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('columnIcon');
    currentBtn.classList.add('active-btn');

    $('#paneContent').html('');

    $('#paneContent').append(`<div id="chartContainer" class="chart-container"></div>`);
    // 获取容器元素
    let chartContainer = document.getElementById('chartContainer');

    // 初始化 ECharts 实例
    let chart = echarts.init(chartContainer);


    console.log("counts的值为：")
    console.log(counts)

    // 定义柱状图的配置项和数据
    let options = {
        xAxis: {
            type: 'category',
            data: counts.analysisOptions.map(item => getName(item.id))
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

}

/**
 * 选择条形图
 */
const selectBar = () =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('barIcon');
    currentBtn.classList.add('active-btn');


    $('#paneContent').html('');

    $('#paneContent').append(`<div id="chartContainer" class="chart-container"></div>`);
    // 获取容器元素
    let chartContainer = document.getElementById('chartContainer');

    // 初始化 ECharts 实例
    let chart = echarts.init(chartContainer);


    console.log("counts的值为：")
    console.log(counts)

    let options = {
        yAxis: {
            type: 'category',
            data: counts.analysisOptions.map(item => getName(item.id))
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
}

/**
 * 选择折线图
 */
const selectLine = () =>{

    //将按钮变蓝，负责对样式进行处理
    var previousActiveBtn = document.querySelector('#buttonGroup .btn.active-btn');
    if (previousActiveBtn) {
        previousActiveBtn.classList.remove('active-btn');
    }
    var currentBtn = document.getElementById('lineIcon');
    currentBtn.classList.add('active-btn');

    $('#paneContent').html('');

    $('#paneContent').append(`<div id="chartContainer" class="chart-container"></div>`);
    // 获取容器元素
    let chartContainer = document.getElementById('chartContainer');

    // 初始化 ECharts 实例
    let chart = echarts.init(chartContainer);


    console.log("counts的值为：")
    console.log(counts)

    let options = {
        xAxis: {
            type: 'category',
            data: counts.analysisOptions.map(item => getName(item.id))
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
}