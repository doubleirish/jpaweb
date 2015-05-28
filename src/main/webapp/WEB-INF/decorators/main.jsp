<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <title><decorator:title default="Book Nook" /></title>
    <link rel="stylesheet" href="<c:url value='/static/my.css' />"/>
    <link rel="stylesheet" href="<c:url value='/static/nav.css' />"/>
    <decorator:head />
</head>



<body>
<h1>Book Nook</h1>
<div class="nav">
    <ul>
        <li class="home"><a href="#">Home</a></li>
        <li class="tutorials"><a class="active" href="#">Tutorials</a></li>
        <li class="about"><a href="#">About</a></li>
        <li class="news"><a href="#">Newsletter</a></li>
        <li class="contact"><a href="#">Contact</a></li>
    </ul>
</div>


    <div class="wrapper">
        <p>
            <decorator:body/>
        </p>

        <div class="push"></div>
    </div>

    <div class="footer">
        <p>Copyright (c) <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %>
        </p>
    </div>
</body>
</html>