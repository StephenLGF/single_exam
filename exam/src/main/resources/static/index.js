$(document).ready(function () {
    $("#show-feedback").click(function () {
        tableClear();
        $.ajax({
            url: "/api/feedback", success: function (result) {
                tableClear();
                $("#title").text("反馈列表");
                $("thead").append("<tr><th>用户</th><th>反馈</th><th >时间</th></tr>");
                result.forEach(function (item) {
                    var tds = "";
                    tds += "<td>" + item.userName + "</td>";
                    tds += "<td>" + item.content + "</td>";
                    tds += "<td>" + getLocalTime(item.time) + "</td>";
                    $("tbody").append("<tr>" + tds + "</tr>")
                })
            }
        });
    });

    $("#show-news").click(function () {
        tableClear();
        $.ajax({
            url: "/api/news/all", success: function (result) {
                tableClear();
                $("#title").text("新闻列表");
                $("#th1").text("新闻标题");
                $("#th2").text("新闻类型");
                $("#th3").text("删除操作");
                $("thead").append("<tr><th>新闻标题</th><th>新闻类型 0-文字，1-图片，2-视频</th><th>发布者</th><th>时间</th><th>操作</th></tr>");
                result.forEach(function (item) {
                    var newsDelete = "newsDelete(" + item.newsId + ")";
                    var tds = "";
                    tds += "<td>" + item.title + "</td>";
                    tds += "<td>" + item.type + "</td>";
                    tds += "<td>" + item.provider + "</td>";
                    tds += "<td>" + getLocalTime(item.createTime) + "</td>";
                    tds += "<td><button onclick=" + newsDelete + ">删除</button></td>";
                    $("tbody").append("<tr id= 'news-" + item.newsId + "'>" + tds + "</tr>")
                })
            }
        });
    });

    $("#show-user").click(function () {
        tableClear();
        $.ajax({
            url: "/api/users", success: function (result) {
                tableClear();
                $("#title").text("用户列表");
                $("thead").append("<tr><th>手机号码</th><th>用户名</th><th >创建时间</th></tr>");
                result.forEach(function (item) {
                    var tds = "";
                    tds += "<td>" + item.telNum + "</td>";
                    tds += "<td>" + item.name + "</td>";
                    tds += "<td>" + getLocalTime(item.time) + "</td>";
                    $("tbody").append("<tr>" + tds + "</tr>")
                })
            }
        });
    });

    $("#send-notice").click(function () {
        tableClear();
        $("#title").text("系统通知发布");
        $("#input-form").append("<input type='text' placeholder='请输入要发布消息的内容' id='notice-content'></input>");
        $("#input-form").append("<button id='content-submit' onclick='contentSubmit()'>提交</button>");
    });

    $("#send-news").click(function () {
        tableClear();
        $("#title").text("新闻发布");
        $("#input-form").append("<p>请选择新闻的类型</p>");
        $("#input-form").append("<input type='text' placeholder='请输入要发布新闻的标题' id='news-title'></input>");
        $("#input-form").append("<button id='send-news-text' onclick='newsContent(0)'>文字</button>");
        $("#input-form").append("<button id='send-news-image' onclick='newsContent(1)'>图片</button>");
        $("#input-form").append("<button id='send-news-video' onclick='newsContent(2)'>视频</button>");
        $("#input-form").append("<div id='news-content-form'></div>");
    });
});

function newsDelete(index) {
    $.ajax({
        url: "/api/news/" + index,
        type: "DELETE",
        contentType: "application/json",
        dataType: "json", success: function (result) {
            $("#news-" + index).remove();
            alert("删除成功");
        }
    });
}

function newsSubmit(index) {
    var title = $("#news-title").val();
    if (!title) {
        alert("请输入新闻标题");
        return;
    }
    var contents = [];
    $(".news-content").each(function (index) {
        var content = $(".news-content").eq(index).val();
        if (!!content) {
            contents.push(content)
        }
    });
    if (contents.length === 0) {
        alert("新闻的内容不能为空");
        return;
    }
    $.ajax({
        url: "/api/news",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({type: index, contents: contents, title: title}),
        dataType: "json", success: function (result) {
            $("#input-form").empty();
            alert("发布成功");
        }
    });
}

function newsContent(index) {
    $("#news-content-form").empty();
    $("#content-submit").remove();
    var handleClick = "addNewsContent(" + index + ")";
    var handleSubmit = "newsSubmit(" + index + ")";
    switch (index) {
        case 0:
            $("#input-form").append("<button id='content-submit' onclick=" + handleSubmit + ">提交</button>");
            $("#news-content-form").append("<button id='content-submit' onclick=" + handleClick + ">添加段落</button>");
            $("#news-content-form").append("<textarea placeholder='请输入段落文字' class='news-content'></textarea>");
            break;
        case 1:
            $("#input-form").append("<button id='content-submit' onclick=" + handleSubmit + ">提交</button>");
            $("#news-content-form").append("<button id='content-submit' onclick=" + handleClick + ">添加图片</button>");
            $("#news-content-form").append("<input type='text' placeholder='请输入图片链接' class='news-content'></input>");
            break;
        case 2:
            $("#input-form").append("<button id='content-submit' onclick=" + handleSubmit + ">提交</button>");
            $("#news-content-form").append("<input type='text' placeholder='请输入视频链接' class='news-content'></input>");
            break;
    }
}

function addNewsContent(index) {
    switch (index) {
        case 0:
            $("#news-content-form").append("<textarea placeholder='请输入段落文字' class='news-content'></textarea>");
            break;
        case 1:
            $("#news-content-form").append("<input type='text' placeholder='请输入图片链接' class='news-content'></input>");
            break;
    }
}

function contentSubmit() {
    var inputContent = $("#notice-content").val();
    if (!inputContent) {
        alert("请输入消息内容");
        return;
    }
    $.ajax({
        url: "/api/notice",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({content: inputContent}),
        dataType: "json", success: function (result) {
            alert("发布成功");
            $("#input-form").empty();
        }
    });
}

function tableClear() {
    $("thead").empty();
    $("tbody").empty();
    $("#input-form").empty();
}

function getLocalTime(nS) {
    var time = new Date(nS);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
}

function add0(num) {
    if (num < 10) {
        return "0" + num;
    }
    return num
}