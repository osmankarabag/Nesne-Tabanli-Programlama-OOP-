//OSMAN KARABAÐ 
//19010011010
package osman_odev;
import java.util.List;

public class Ogrenci {
	private int ogrenciId;
	private String ogrenciAdSoyad;
	private int ogrenciYas;
	private List<Ders> alinanDersler;
	
	public Ogrenci() {
		
	}
	
	public Ogrenci(int ogrenciId, String ogrenciAdSoyad, int ogrenciYas, List<Ders> alinanDersler) {
		this.ogrenciId = ogrenciId;
		this.ogrenciAdSoyad = ogrenciAdSoyad;
		this.ogrenciYas = ogrenciYas;
		this.alinanDersler = alinanDersler;
	}

	public int getOgrenciId() {	
		return ogrenciId;
	}

	public void setOgrenciId(int ogrenciId) {
		this.ogrenciId = ogrenciId;
	}

	public String getOgrenciAdSoyad() {
		return ogrenciAdSoyad;
	}

	public void setOgrenciAdSoyad(String ogrenciAdSoyad) {
		this.ogrenciAdSoyad = ogrenciAdSoyad;
	}

	public int getOgrenciYas() {
		return ogrenciYas;
	}

	public void setOgrenciYas(int ogrenciYas) {
		this.ogrenciYas = ogrenciYas;
	}

	public List<Ders> getAlinanDersler() {
		return alinanDersler;
	}

	public void setAlinanDersler(List<Ders> alinanDersler) {
		this.alinanDersler = alinanDersler;
	}
	
	
	
}
