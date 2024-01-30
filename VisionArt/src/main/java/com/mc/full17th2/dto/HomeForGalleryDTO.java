package com.mc.full17th2.dto;

import java.util.List;

public class HomeForGalleryDTO {
	private List<PostDTO> postsForGallery;
	private List<MemberDTO> members;
	private List<ArtFieldDTO> arts;
	
	public HomeForGalleryDTO() {}

	public HomeForGalleryDTO(List<PostDTO> postsForGallery, List<MemberDTO> members, List<ArtFieldDTO> arts) {
		this.postsForGallery = postsForGallery;
		this.members = members;
		this.arts = arts;
	}

	public List<PostDTO> getPostsForGallery() {
		return postsForGallery;
	}

	public void setPostsForGallery(List<PostDTO> postsForGallery) {
		this.postsForGallery = postsForGallery;
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
