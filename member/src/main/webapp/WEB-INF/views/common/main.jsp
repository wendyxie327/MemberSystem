<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <c:import url="/style/common/header.jsp"/>
    <link href="<c:url value="/style/index/css/main.css"/> " rel="stylesheet">
</head>

<body class="gray-bg">
<div class="row  border-bottom dashboard-header">

    <div class="col-sm-6">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h4>会员推广</h4>
            </div>
            <div class="ibox-content show_content">
                <h2>尊贵的 <span class="color_red">${sessionScope.userInfo.realUserName} </span>您好！</h2>
                <small>推广二维码：</small>
                <br>
                <br>
                <div id="qrcode"></div>

                <div>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" required="" id="qrcode_url" readonly>
                    </div>
                    <div class="col-sm-4">
                        <button class="btn btn-primary" onclick="main.copyQRCode()" >复制二维码链接</button>
                    </div>

                </div>

                <br>
            </div>
        </div>
    </div>

    <div class="col-sm-6">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h4>新闻公告</h4>
            </div>
            <div class="ibox-content">
                <p>
                    <strong>立能&gt;生物醇新能源燃料(可代替汽油)并且有效清理发动机积碳提高发动机热效率明显增强动力！</strong>
                </p>
                <p>
                    <strong>产品37项科研成果，22项国家专利证书，及所有检测报告！</strong>
                </p>
                <p>
                    <br/>
                </p>
                <p>
                    <strong>&nbsp;&nbsp;&nbsp;&nbsp;使用说明</strong>
                </p>
                <p>
                    <strong>初期按3:7的比例，对清除积碳、增强动力的效果明显;后期可以逐步增加到5:5，省油省钱非常明显。</strong>具体使用方法如下
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;1)&nbsp; 3-5万公里，或3-5年车辆，每100元汽油添加一桶，行驶30-50公里即可见效：发动机噪音减小，提速加快。之后逐步增大添加比例。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;2)&nbsp; 5-10万公里，或5-8年车辆，可按每200元汽油添加1桶，过量添加可能造成被清除的积碳堵塞油路。按这个量添加两次之后，逐步增加到100元汽油1桶、最后增加到每100元汽油2-3桶。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;3)&nbsp; 10万公里以上、或8年以上的车辆，每200元汽油添加1桶的3分之2.添加3次后，再逐步加大比例。
                </p>
                <p>
                    生物醇新能源清洁燃料，每个人都在问我加的是什么，现在告诉大家我加的是什么，《生物醇高效清洁动力》它的好处是什么？那么就一条一条的列出来：
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第一：前期按照比例使用，五箱比例以后全部使用生物醇动力水，完全代替汽油
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第二：可以提升发动机动力。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第三：可以清洗燃油路。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第四：可以清洗喷油嘴。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第五：可以清洗进气门。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第六：可以清洗气缸积碳。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第七：可以清洗火花塞。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第八：可以清洗排气门。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第九：可以清洗三元催化器，防止堵塞。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第十：在油品质不好的情况下可以修复油产品。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第十一：可以延迟更换火花塞周期。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第十二：可以延长更换汽油滤清器周期。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第十三：可以延长更换汽油箱碳罐更换周期；
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第十四：以上的都可以了那不是维修保养更经济省钱了。
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;第十五：你车做一次大保养要一千多，而你生物醇动力好了，开着顺了，心情也好了，心情好了路就更宽了。零污染零排放，响应习大大宁要绿水青山也不要金山银山，自己车子零污染零排放，身边朋友车子都用生物醇动力水，我们蓝天白云还远吗，省钱同时又给地球环保做了贡献，何乐而不为，全国市场一片空白，让我们一起约起来超过97号汽油品质，价格便宜一半，自用省钱推广赚钱
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </p>
                <p>
                    &nbsp;&nbsp; 详情联系:13280699845
                </p>
                <p>
                    <br/>
                </p>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/style/common/js/jquery.qrcode.min.js"/>"></script>
<script src="<c:url value="/style/index/js/main.js"/>"></script>
<script>
    // 生成url,并展示
    main.createQRCode('${sessionScope.userInfo.userCode}');
</script>

</body>

</html>
