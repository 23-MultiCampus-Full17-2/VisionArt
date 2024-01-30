package com.mc.full17th2.dto;

import java.util.List;

public class ReadSearchAllDTO {
	 private List<PostDTO> posts;
	 private List<MemberDTO> members;
	 private List<ArtFieldDTO> hitArts;
	 
	public ReadSearchAllDTO() {
		super();
		
	}

	public ReadSearchAllDTO(List<PostDTO> posts, List<MemberDTO> members, List<ArtFieldDTO> hitArts) {
		this.posts = posts;
		this.members = members;
		this.hitArts = hitArts;
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

	public List<ArtFieldDTO> getHitArts() {
		return hitArts;
	}

	public void setHitArts(List<ArtFieldDTO> hitArts) {
		this.hitArts = hitArts;
	}
	
	 
}
