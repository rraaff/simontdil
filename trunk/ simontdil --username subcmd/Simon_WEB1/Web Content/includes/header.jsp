<% 	if (session == null) { %>
	<jsp:forward page="./login.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
<%	}
	com.tdil.simon.data.model.SystemUser user = (com.tdil.simon.data.model.SystemUser)session.getAttribute("user");
	if (user == null) { %>
	<jsp:forward page="./login.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
<% 	
	}
	try {
	boolean isAdministrator = user.isAdministrator();
	boolean isModerator = user.isModerator();
	boolean isDesigner = user.isDesigner();
	boolean isDelegate = user.isDelegate();
	boolean eventMode = com.tdil.simon.data.model.Site.EVENT.equals(com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus());
	boolean inNegotiation = com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus());
	boolean isSign = com.tdil.simon.data.model.Site.IN_SIGN.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus());
%>
<% if (isAdministrator || isModerator || isDesigner) { %>
	<%@ include file="headerSystemUser.jsp" %>
<% } else { 
	if (isDelegate) { %>
	<%@ include file="headerDelegate.jsp" %>
<% 	}
} %>