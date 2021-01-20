package mg.cnaps.models;

import java.util.List;

public class ParamActivite {

	private int page;
	private int size = 10;
	private int nbrPage;
	List<ActiviteMod> activite;

	public ParamActivite() {
		super();
	}

	public int getNbrPage() {
		return nbrPage;
	}

	public void setNbrPage(int nbrPage) {
		this.nbrPage = nbrPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<ActiviteMod> getActivite() {
		return activite;
	}

	public void setActivite(List<ActiviteMod> activite) {
		this.activite = activite;
	}

}
