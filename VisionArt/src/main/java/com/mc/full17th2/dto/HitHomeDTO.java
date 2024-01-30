package com.mc.full17th2.dto;

import java.util.List;

public class HitHomeDTO {
	private List<PostDTO> hitPosts;
	private List<MemberDTO> members;
	private List<ArtFieldDTO> hitArts;
	
	public HitHomeDTO() {}

	public HitHomeDTO(List<PostDTO> hitPosts, List<MemberDTO> members, List<ArtFieldDTO> hitArts) {
		this.hitPosts = hitPosts;
		this.members = members;
		this.hitArts = hitArts;
	}

	public List<PostDTO> getHitPosts() {
		return hitPosts;
	}

	public void setHitPosts(List<PostDTO> hitPosts) {
		this.hitPosts = hitPosts;
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
