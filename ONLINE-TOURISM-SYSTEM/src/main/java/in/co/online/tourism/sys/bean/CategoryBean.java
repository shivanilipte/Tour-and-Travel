package in.co.online.tourism.sys.bean;

import java.io.InputStream;


public class CategoryBean extends BaseBean {

	private String name;
	private String description;
	private String images;
	

	
	




	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}


	public CategoryBean() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "CategoryBean [name=" + name + ", description=" + description + ", images=" + images + ", id=" + id
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDatetime=" + createdDatetime
				+ ", modifiedDatetime=" + modifiedDatetime + "]";
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getKey() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}


	

}
