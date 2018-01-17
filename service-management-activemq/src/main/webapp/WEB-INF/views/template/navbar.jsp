<%-- 
    Document   : navbar
    Created on : 9 août 2017, 14:32:56
    Author     : fqlh0717
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header class="main-header">
    <!-- Logo -->
    <a href="<c:url value="/" />" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini">AMQ</span>
        <span 
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg">ActiveMQ</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="hidden-xs">Mon Compte</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <p>${user.firstName} ${user.lastName}</p>

                            <p><small>${user.entity}</small></p>
                            <p><small>${user.email}</small></p>
                            <p><small>${user.phoneNumber}</small></p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <a  href="<c:url value="/login?logout" />" class="btn btn-default btn-flat" >XX</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>

