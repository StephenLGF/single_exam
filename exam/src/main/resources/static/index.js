$(document).ready(function () {
    $("#show-feedback").click(function () {
        $.ajax({
            url: "http://127.0.0.1:8081/api/feedback", success: function (result) {
                tableClear();
                $("#title").text("反馈列表");
                $("#th1").text("用户");
                $("#th2").text("反馈");
                $("#th3").text("时间");
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

    $("#show-user").click(function () {
        $.ajax({
            url: "http://127.0.0.1:8081/api/users", success: function (result) {
                tableClear();
                $("#title").text("用户列表");
                $("#th1").text("手机号码");
                $("#th2").text("用户名");
                $("#th3").text("创建时间");
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

});

function tableClear() {
    $("th").text("");
    $("tbody").empty();
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