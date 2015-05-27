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
    <title>Book Nook</title>
</head>
<body>
<h2>Book Nook</h2>

<table border="1">
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>description</th>
        <th>genre</th>
    </tr>

    <c:forEach items="${books}" var="book">
      <tr>
          <td>${book.isbn}</td>
          <td>${book.title}</td>
          <td>${book.description}</td>
          <td>${book.genre}</td>
      </tr>
    </c:forEach>

</table>

</body>
</html>
