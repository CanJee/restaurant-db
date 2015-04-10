<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:b="http://bootsfaces.net/ui"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:c="http://xmlns.jcp.org/jsp/jstl/core">

    <h:head>
        <title>View Restaurants</title>
        <meta name="CanJee" content="Can Jee"></meta>
    </h:head>
    <h:body>

        <ui:composition template="./template2.xhtml">

            <ui:define name="content">
                <f:view>
                    <h:form id="pagedResults" prependId="false" >
                        <h2 class="form-signin-heading">Menu items for restaurant #{viewMenuItemBean.restaurant.name}</h2>
                        <c:if test="#{viewMenuItemBean.menuItemCount == 0}">
                            <h:outputText value="There are no menu items to display." />
                        </c:if>
                        <c:if test="#{viewMenuItemBean.menuItemCount > 0}">   
                    <h:dataTable id="MenuItem_table" cellpadding="10" value="#{viewMenuItemBean.menuItems}"
                                  var="menuItem" border="1">
                         <h:column>
                            <f:facet name="header">Name</f:facet>
                            <h:outputText value="#{menuItem.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">Category</f:facet>
                            <h:outputText value="#{menuItem.category}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">Type</f:facet>
                            <h:outputText value="#{menuItem.type}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">Description</f:facet>
                            <h:outputText value="#{menuItem.description}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">Price</f:facet>
                            <h:outputText value="#{menuItem.price}"/>
                        </h:column>
                     </h:dataTable>
                    </c:if>
                </h:form>
                </f:view>
        </ui:define>

        </ui:composition>

    </h:body>
</html>
