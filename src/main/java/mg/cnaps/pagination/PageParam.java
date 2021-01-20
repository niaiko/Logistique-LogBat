package mg.cnaps.pagination;

public class PageParam {
	private Object param;
	private int page;
	private int size;
	
	public Object getParam() {
		return param;
	}
	public int getPage() {
		return page;
	}
	public int getSize() {
		return size;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
