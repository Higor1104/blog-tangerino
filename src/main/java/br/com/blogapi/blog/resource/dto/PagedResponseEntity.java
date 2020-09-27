package br.com.blogapi.blog.resource.dto;

import java.util.List;

public class PagedResponseEntity<T> {

	private List<T> objects;
	private Integer page;
	private Integer size;

	public List<T> getObjets() {
		return objects;
	}

	public void setObjects(List<T> objects) {
		this.objects = objects;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer index) {
		this.size = index;
	}

}
