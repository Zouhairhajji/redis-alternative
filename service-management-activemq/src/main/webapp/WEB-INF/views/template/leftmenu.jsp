<%-- 
    Document   : leftmenu
    Created on : 9 août 2017, 14:09:37
    Author     : fqlh0717
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside class="main-sidebar">

    <section class="sidebar">

        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-ravelry"></i> <span>Utilisateurs</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>

                <ul class="treeview-menu">
                    <li>
                        <a href="<c:url value="/users/" />">
                            <i class="fa fa-circle"></i>Liste des utilisateurs
                        </a>
                    </li>
                </ul>
            </li>
            
            
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-ravelry"></i> <span>Liens</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>

                <ul class="treeview-menu">
                    <li>
                        <a href="http://gitlab.silber.inside.esiag.info" target="_blank">
                            <i class="fa fa-circle"></i>Git portail
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
