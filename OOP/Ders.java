//OSMAN KARABAÐ 
//19010011010
package osman_odev;

public class Ders {
	private int dersId;
	private String dersAd;
	
	public Ders() {
		
	}
	
	public Ders(int dersId, String dersAd) {
		this.dersId = dersId;
		this.dersAd = dersAd;
	}
	
	public int getDersId() {
		return dersId;
	}
	
	public void setDersId(int dersId) {
		this.dersId = dersId;
	}
	
	public String getDersAd() {
		return dersAd;
	}
	
	public void setDersAd(String dersAd) {
		this.dersAd = dersAd;
	}
	
}
