package com.mc.full17th2.dto;

import java.util.List;

public class ReadSearchAllCategoryDTO {
	 private List<PostDTO> posts;
	 private List<MemberDTO> members;
	 private List<ArtFieldDTO> arts;
	 
	public ReadSearchAllCategoryDTO() {
		super();
		
	}
	public ReadSearchAllCategoryDTO(List<PostDTO> posts, List<MemberDTO> members, List<ArtFieldDTO> arts) {
		this.posts = posts;
		this.members = members;
		this.arts = arts;
	}
	public List<PostDTO> getPosts() {
		return posts;
	}
	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}
	public List<MemberDTO> getMembers() {
		return members;
	}
	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}
	public List<ArtFieldDTO> getArts() {
		return arts;
	}
	public void setArts(List<ArtFieldDTO> arts) {
		this.arts = arts;
	}
	 
	 
}
