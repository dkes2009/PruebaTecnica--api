package com.ApiPruebatecnica.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "BD_USER_APIS")
@Data
public class UserEntity {
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDUSER")
	private Integer idUser;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="APPNAME")
	private String appName;
	
	public UserEntity() {
		super();
	}
	@Override
	public String toString() {
		return "UserEntity [idUser=" + idUser + ", userName=" + userName + ", password= [PROTECTED]" + ", appName="
				+ appName + "]";
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
