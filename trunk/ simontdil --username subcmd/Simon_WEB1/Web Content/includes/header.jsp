<script type="text/javascript" src="./scripts/prototype.js" ></script>
<% 
	com.tdil.simon.data.model.SystemUser user = (com.tdil.simon.data.model.SystemUser)session.getAttribute("user");
	boolean isAdministrator = user.isAdministrator();
	boolean isModerator = user.isModerator();
	boolean isDelegate = user.isDelegate();
%>