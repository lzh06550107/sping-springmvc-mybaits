<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/12
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<div id="moods">
    <b>说说列表：</b><br/>
    <c:forEach items="${moods}" var="mood">
        -----------------------------------
        <br>
        <b>用户：</b><span id="account">${mood.userName}</span><br>
        <b>说说内容：</b><span id="content">${mood.content}</span><br>
        <b>发表时间：</b>

        <span id="publish_time">
            ${mood.publishTime}
        </span><br>
        <b>点赞数：</b><span id="praise_num">${mood.praiseNum}</span><br>
        <div style="margin-left: 350px">
            <a id="praise" href="/springspringmvcmybaitsdemo_war_exploded/mood/${mood.id}/praise?userId=${mood.userId}">赞</a>
            <a id="praise1" href="/springspringmvcmybaitsdemo_war_exploded/mood/${mood.id}/praiseForRedis?userId=${mood.userId}">Redis点赞</a>
            <a id="praise2" href="/springspringmvcmybaitsdemo_war_exploded/mood/${mood.id}/praiseForQueue?userId=${mood.userId}">Queue点赞</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
