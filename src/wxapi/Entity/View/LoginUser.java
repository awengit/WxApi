package wxapi.Entity.View;

public class LoginUser extends wxapi.Entity.LoginUser {

	private String userroletitle;

	private String userrights;

	public String getUserroletitle() {
		return userroletitle;
	}

	public void setUserroletitle(String userroletitle) {
		this.userroletitle = userroletitle;
	}

	public String getUserrights() {
		return userrights;
	}

	public void setUserrights(String userrights) {
		this.userrights = userrights;
	}

}
