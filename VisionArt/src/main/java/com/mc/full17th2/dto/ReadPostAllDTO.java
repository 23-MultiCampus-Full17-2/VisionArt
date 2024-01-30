package com.mc.full17th2.dto;

import java.util.List;

public class ReadPostAllDTO {
	private List<PostDTO> posts;
	private List<MemberDTO> members;
	private List<ArtFieldDTO> arts;

	public ReadPostAllDTO() {
		
	}
	
	public ReadPostAllDTO(List<PostDTO> posts, List<MemberDTO> members, List<ArtFieldDTO> arts) {
        this.posts = posts;
        this.members = members;
        this.arts = arts;
    }

	
	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public List<ArtFieldDTO> getArts() {
		return arts;
	}

	public void setArts(List<ArtFieldDTO> arts) {
		this.arts = arts;
	}

	
	
	
}
