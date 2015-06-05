<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="pageId" content="tableau_reports"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Book Nook</title>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
</head>
<body>
<h2>Book Nook home</h2>

<table class="pure-table">


    <c:forEach items="${books}" var="book">
      <tr>
          <td>${book.genre}</td>
          <td>${book.isbn}</td>

          <td> <b> ${book.title}</b></td>
          <td><fmt:formatNumber value="${book.price}" type="currency"/> </td>
          <td><fmt:formatDate value="${book.releaseDate}"   pattern="MM/dd/yyyy"/></td>

      </tr>


        <tr class="pure-table-odd"   >
            <td colspan="2"></td>
            <td colspan="3">${book.description}</td>
        </tr>



    </c:forEach>

</table>

</body>
</html>
