<%-- 
    Document   : articleSearch
    Created on : Jan 12, 2020, 11:53:17 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DatNT.articles.ArticlesDTO" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article</title>
        <style>
            .tittleLink {
                color: black;
            } 
            .wapper {
                border: solid black 1px;
            }
        </style>
    </head>
    <body>
        <div style="border: solid black 1px">
            <font color="black">
            <c:if test="${not empty sessionScope.EMAIL}">
                <h3>Welcome, ${sessionScope.NAME} ; ${sessionScope.EMAIL}</h3>
                <a href="DispatchController?btAction=Logout">Logout</a><br/>
            </c:if>
            <c:if test="${empty sessionScope.EMAIL}">
                <h3>Welcome, Quest.</h3>
                <a href="DispatchController?btAction=LoginPage">Login</a><br/>
                <a href="DispatchController?btAction=Sign Up">Sign Up</a><br/>
            </c:if>
            </font> 
        </div><br/>
        <div class="wapper">
            <div style="border: solid black 1px;">
                <c:url var="CreateNewArticle" value="DispatchController?btAction=CheckLogin"/>
                <a href="${CreateNewArticle}">Create New Article</a><br/>
                <form action="DispatchController">
                    <h1>Search Article</h1>
                    Input the text <input style="width: 20%" type="text" name="txtSearchArticle" value="${param.txtSearchArticle}" />
                    <input type="submit" value="Search" name="btAction" />
                    <input type="hidden" name="txtStatusAr" value="Active" />
                    <input type="hidden" name="txtSearchTittle" value="${param.txtSearchTittle}" />
                </form><br/>
            </div>
            <div style="border: solid black 1px; height: auto">
                <a href="DispatchController?btAction=showAll&txtStatusAr=${param.txtStatusAr}">Show All</a><br/>
                <c:set var="searchArticle" value="${param.txtSearchArticle}"/>
                <c:set var="show" value="${requestScope.SHOWARTICLE}"/>

                <c:if test="${not empty searchArticle or not empty show}">
                    <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
                    <c:if test="${not empty result}">
                        <c:set var="page" value="${requestScope.PAGE}"/>
                        <h3>Page ${page}</h3>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No. </th>
                                    <th>Tittle</th>
                                    <th>Author </th>
                                    <th>Content </th>
                                    <th>Date </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                <form action="DispatchController">

                                    <tr>
                                        <td> ${counter.count}</td>
                                        <td> 
                                            <c:url var="urlRewriting" value="DispatchController">
                                                <c:param name="btAction" value="tittle" />
                                                <c:param name="pk" value="${dto.tittle}"/>
                                                <c:param name="author" value="${dto.author}"/>
                                                <c:param name="txtStatusAr" value="${dto.status}"/>
                                            </c:url>
                                            <a href="${urlRewriting}" style="text-decoration: none;" ><span class="tittleLink">${dto.tittle}</span></a>
                                        </td>
                                        <td> ${dto.author} </td>
                                        <td> ${dto.content} </td>
                                        <td> ${dto.dateOfPost} </td>

                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:url var="previous" value="DispatchController?btAction=changePage&page=${page}&option=previous&txtSearchArticle=${searchArticle}&txtStatusAr=Active&txtSearchTittle=${param.txtSearchTittle}"/>
                        <c:url var="next" value="DispatchController?btAction=changePage&page=${page}&option=next&txtSearchArticle=${searchArticle}&txtStatusAr=Active&txtSearchTittle=${param.txtSearchTittle}"/>
                        <a href="${previous}">Previous page</a> <d>----------</d> <a href="${next}">Next Page</a>
                        </c:if>

                    <c:if test="${empty result}">
                        <h2>No record is matched</h2>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>
