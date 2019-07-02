package com.web06.entity;

import java.util.List;

public class Page {
	private Integer pageNo=1;   //��ǰҳ��,��ǰ�ڼ�ҳ
	private Integer pageCount;  //��ҳ��
	private Integer pageSize;   //ÿҳ��ʾ�ļ�¼��
	private Integer rowCount;   //�ܼ�¼��
	private List data;          //�洢��ѯ���ķ�ҳƬ������
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageCount() {
		if (rowCount%pageSize==0) {
			this.pageCount=rowCount/pageSize;
		}else {
			this.pageCount=rowCount/pageSize+1;
		}
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Integer getFirstRecord() {
		return (pageNo-1)*pageSize;
	}
	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageCount=" + pageCount + ", pageSize=" + pageSize + ", rowCount="
				+ rowCount + ", data=" + data + "]";
	}
}
