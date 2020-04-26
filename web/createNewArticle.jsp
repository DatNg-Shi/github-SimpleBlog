<%-- 
    Document   : createNewArticle
    Created on : Jan 13, 2020, 4:33:46 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Article</title>
        <style>
            .wapper {
                line-height: 50px;
                font-size: 20px;
                border: solid black 1px;
            }
            .chill {
                width: 25%;
                background-color: none;
                height: auto;
            }
/*            body {
                margin-left: 15px;
            }*/
            .input {
                float: right;
                width: 50%;
                margin-top: 4%;
            }
        </style>
    </head>
    <body>
        <div style="border: solid black 1px">
            <font color="black">
            <c:if test="${not empty sessionScope.EMAIL}">
                <h2>Welcome, ${sessionScope.NAME} ; ${sessionScope.EMAIL}</h2>
                <a href="DispatchController?btAction=Logout">Logout</a><br/>
            </c:if>
            </font>
        </div>
        <div class="wapper">

            <form action="DispatchController" method="POST">
                <c:set var="errors" value="${requestScope.POSTERRORS}"/>
                <h1>Create A New Article</h1>
                <div class="chill">
                    Tittle: <input class="input" type="text" name="txtInputTittle" value="${param.txtInputTittle}" /><br/>
                    <c:if test="${not empty errors.tittleEmptyErr}">
                        <font color="red">
                        ${errors.tittleEmptyErr}
                        </font><br/>
                    </c:if>
                    Short Description: <input class="input" type="text" name="txtInputShortDes" value="${param.txtInputShortDes}" /><br/>
                    <c:if test="${not empty errors.shortDescriptionEmptyErr}">
                        <font color="red">
                        ${errors.shortDescriptionEmptyErr}
                        </font><br/>
                    </c:if>
                    Content: <input class="input" type="text" name="txtInputContent" value="${param.txtInputContent}" /><br/>
                    <c:if test="${not empty errors.contentEmptyErr}">
                        <font color="red">
                        ${errors.contentEmptyErr}
                        </font><br/>
                    </c:if>
                    <input type="hidden" name="txtStatus" value="New" />
                </div>
                    <input style="margin-left: 20px; width: 5%;" type="submit" value="Post" name="btAction"/>
                <input style="margin-left: 20px; width: 5%;" type="reset" value="Reset" />
                <input style="margin-left: 20px; width: 5%;" type="submit" value="Cancel" name="btAction" />
            </form><br/>

            <c:if test="${not empty errors.tittleIsExisted}">
                <font color="red">
                ${errors.tittleIsExisted}
                </font><br/>
            </c:if>
        </div>
    </body>
</html>
