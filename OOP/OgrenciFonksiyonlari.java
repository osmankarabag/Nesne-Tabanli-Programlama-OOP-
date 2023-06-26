//OSMAN KARABA� 
//19010011010

package osman_odev;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OgrenciFonksiyonlari {
	Scanner scanner = new Scanner(System.in);
	
	public void ogrenciEkle(List<Ogrenci> ogrenciler, List<Ders> dersler) {
		int dersSayisi;
		int ogrenciId, ogrenciYas;
		String ogrenciAdSoyad;
		List<Ders> alinanDersler = new ArrayList<Ders>();
		
		System.out.println("Id giriniz");
		ogrenciId = scanner.nextInt();
		scanner.nextLine();
		
		for (Ogrenci ogrenci : ogrenciler) {
			if(ogrenci.getOgrenciId() == ogrenciId) {
				System.out.println("Girdiginiz id kullanilmaktadir.");
				return;
			}
		}
		
		System.out.println("Ad/Soyad giriniz");
		ogrenciAdSoyad = scanner.nextLine();
		System.out.println("Yaş giriniz");
		ogrenciYas = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Kaç ders girmek istiyorsunuz: ");
		dersSayisi = scanner.nextInt();
		scanner.nextLine();

		DersFonksiyonlari dersFonksiyonlari = new DersFonksiyonlari();
		for (int i = 0; i < dersSayisi; i++) {
			alinanDersler.add(dersEkle(dersler));
		}
	
		ogrenciler.add(new Ogrenci(ogrenciId, ogrenciAdSoyad, ogrenciYas, alinanDersler));
		System.out.println("Öğrenci eklendi");
	}
	
	public Ders dersEkle(List<Ders> dersler) {
		int dersId;
		
		System.out.println("Id giriniz");
		dersId = scanner.nextInt();
		scanner.nextLine();
		
		DersFonksiyonlari dersFonksiyonlari = new DersFonksiyonlari();
		while(dersFonksiyonlari.dersAra(dersId, dersler) == null) {
			System.out.println("Böyle bir ders yok. Doğru id giriniz.");
			dersId = scanner.nextInt();
			scanner.nextLine();
		}
		
		return dersFonksiyonlari.dersAra(dersId, dersler);
	}
	public void ogrenciAra(List<Ogrenci> ogrenciler) {
		System.out.println("Ad/Soyad giriniz");
		String ogrenciAdSoyad = scanner.nextLine();
		
		for (Ogrenci ogrenci : ogrenciler) {
			if(ogrenci.getOgrenciAdSoyad().equals(ogrenciAdSoyad)) {
				System.out.println("Id: " + ogrenci.getOgrenciId() + " Ad - Soyad: " + ogrenci.getOgrenciAdSoyad() + " Yaş: " + ogrenci.getOgrenciYas());
				return;
			}
		}
		System.out.println("Öğrenci bulunamadı");
	}
	
	public void ogrenciSil(List<Ogrenci> ogrenciler) {
		System.out.println("Öğrenci silmek için id giriniz");
		int ogrenciId = scanner.nextInt();
		scanner.nextLine();
		
		for (Ogrenci ogrenci : ogrenciler) {
			if(ogrenci.getOgrenciId() == ogrenciId) {
				ogrenciler.remove(ogrenci);
				System.out.println("Öğrenci silindi");
				return;
			}
		}
		System.out.println("Öğrenci bulunamadı");
	}
	
	public void ogrencileriListele(List<Ogrenci> ogrenciler) {
		System.out.println("Tüm öğrenciler");
		for (Ogrenci ogrenci : ogrenciler) {
			System.out.println(
					ogrenci.getOgrenciId() + " " + ogrenci.getOgrenciAdSoyad() + " " + ogrenci.getOgrenciYas());
		}
	}
	
	public void ogrencileriAyrintiliListele(List<Ogrenci> ogrenciler) {
		System.out.println("Tüm Öğrenciler ve Aldıkları Dersler");
		
		for (Ogrenci ogrenci : ogrenciler) {
			System.out.println(ogrenci.getOgrenciId() + " " + ogrenci.getOgrenciAdSoyad() + " " + ogrenci.getOgrenciYas());
			
			for (Ders ders : ogrenci.getAlinanDersler()) {
				System.out.println("    " + ders.getDersId() + " " + ders.getDersAd());
			}
		}
	}
	
	public void ogrencilerinOdeyecegiTutariHesapla(List<Ogrenci> ogrenciler) {
		System.out.println("Tutar hesaplamak için id giriniz");
		int ogrenciId = scanner.nextInt();
		scanner.nextLine();
		
		for (Ogrenci ogrenci : ogrenciler) {
			if(ogrenci.getOgrenciId() == ogrenciId) {
				hesapla(ogrenci);
				return;
			}
		}
		System.out.println("Öğrenci bulunamadı");
	}
	
	public void hesapla(Ogrenci ogrenci) {
		
		if(ogrenci.getAlinanDersler().size() > 3) {
			System.out.println("Kampanya 3'ten faydalanıyor.");
			System.out.println("Tutar: " + ogrenci.getAlinanDersler().size() * (250 * 0.88)); 
		}
		else if(ogrenci.getAlinanDersler().size() == 3) {
			System.out.println("Kampanya 2'den faydalanıyor.");
			System.out.println("Tutar: " + (2 * 250) + (250 * 0.79));
		}
		else if(ogrenci.getAlinanDersler().size() == 2) {
			System.out.println("Kampanya 1'den faydalanıyor.");
			System.out.println("Tutar: " + 250 + (250 * 0.83));
		}
		else {
			System.out.println("1 ders için kampanya bulunmuyor.");
			System.out.println("Tutar: " + 250);
		}
	}
}
