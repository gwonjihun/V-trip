package com.ssafy.trip.dto;

import java.util.Comparator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfoDto implements Comparable<AttractionInfoDto>{
	private String content_id;
	private int content_type_id;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String first_image;
	private String first_image2;
	private int readcount;
	private int sido_code;
	private int gugun_code;
	private String latitude;
	private String longitude;
	private String mlevel;

	@Override
	public String toString() {
		return "AttractionInfoDto {content_id:" + content_id + ", content_type_id:" + content_type_id + ", title:"
				+ title + ", addr1:" + addr1 + ", addr2:" + addr2 + ", zipcode:" + zipcode + ", tel:" + tel
				+ ", first_image:" + first_image + ", first_image2:" + first_image2 + ", readcount:" + readcount
				+ ", sido_code:" + sido_code + ", gugun_code:" + gugun_code + ", latitude:" + latitude + ", longitude:"
				+ longitude + ", mlevel:" + mlevel + "}";
	}

	@Override
	public int compareTo(AttractionInfoDto o1) {
		
		if(this.readcount== o1.readcount) {
			if(this.sido_code==o1.sido_code) {
				return Integer.compare(Integer.parseInt(this.content_id), Integer.parseInt(o1.content_id));
			}else {
			return Integer.compare(this.sido_code, o1.sido_code);
			}
			}
		return Integer.compare(this.readcount, o1.readcount);
		
	
	}



}
