package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserVo {
	
	
	@Id //primary key 지정
	@GeneratedValue // seq처럼 1씩 자동증가
	private Long id;
	
	@Column(nullable=false, length=20, unique=true) // null값이 들어갈 수 없다. default : true  f3로 속성을 볼 수 있다.
	private String userId;
	
	private String name;
	private String password;
	private String email;
	
	
	public boolean matchId(Long newId) {
		if(newId == null){
			return false;
		}
		
		return newId.equals(id);
	}
	
	public boolean matchPw(String newPw) {
		if(newPw == null){
			return false;
		}
		
		return newPw.equals(password);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", name=" + name + ", password=" + password + "]";
	}
	public void update(UserVo updateUser) {
		this.userId = updateUser.userId;
		this.password = updateUser.password;
		this.name = updateUser.name;
		this.email = updateUser.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVo other = (UserVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
