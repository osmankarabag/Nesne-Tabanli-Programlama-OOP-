//OSMAN KARABAG
//19010011010

package osman_odev;

public class tum_1910 {

}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Anasayfa {

	public static void main(String[] args) throws IOException {
		List<Ogrenci> ogrenciler = new ArrayList<Ogrenci>();
		List<Ders> dersler = new ArrayList<Ders>();
		dosyaOku(ogrenciler, dersler);

		Scanner scn = new Scanner(System.in);
		DersFonksiyonlari dersFonksiyonlari = new DersFonksiyonlari();
		OgrenciFonksiyonlari ogrenciFonksiyonlari = new OgrenciFonksiyonlari();
		
		while(true) {
			int sec;
			System.out.println("İşlem Seçiniz");
			System.out.println("1- Ders Ekle");
			System.out.println("2- Ders Listele");
			System.out.println("3- Öğrenci Ekle");
			System.out.println("4- Öğrenci Ara");
			System.out.println("5- Öğrenci Sil");
			System.out.println("6- Öğrencileri Listele");
			System.out.println("7- Öğrencileri Ayrıntılı Listele");
			System.out.println("8- Öğrencilerin Odeyeceği Tutarı Hesapla");
			System.out.println("9- Çıkış");
			sec = scn.nextInt();
			scn.nextLine();
			
			if(sec == 1) {
				dersFonksiyonlari.dersEkle(dersler);
			}
			if(sec == 2) {
				dersFonksiyonlari.dersListele(dersler);
			}
			if(sec == 3) {
				ogrenciFonksiyonlari.ogrenciEkle(ogrenciler, dersler);
			}
			if(sec == 4) {
				ogrenciFonksiyonlari.ogrenciAra(ogrenciler);
			}
			if(sec == 5) {
				ogrenciFonksiyonlari.ogrenciSil(ogrenciler);
			}
			if(sec == 6) {
				ogrenciFonksiyonlari.ogrencileriListele(ogrenciler);
			}
			if(sec == 7) {
				ogrenciFonksiyonlari.ogrencileriAyrintiliListele(ogrenciler);
			}
			if(sec == 8) {
				ogrenciFonksiyonlari.ogrencilerinOdeyecegiTutariHesapla(ogrenciler);
			}
			if(sec == 9) {
				System.out.println("İyi günler.");
				break;
			}
		}
		dosyayaYaz(ogrenciler, dersler);
	}
	
	public static void dosyaOku(List<Ogrenci> ogrenciler, List<Ders> dersler) throws IOException {
		File ogr = new File("ogrenciler.txt");
		FileReader fReader = new FileReader(ogr);
		BufferedReader bReader = new BufferedReader(fReader);
		String line;
		String temp = "";

		while ((line = bReader.readLine()) != null) {
			temp = temp + line;
		}

		temp = temp.substring(1);
		String[] satirlar = temp.split("\\(");

		for (String satir : satirlar) {
			ogrenciSatirParcala(ogrenciler, satir);
		}

		File drs = new File("ders.txt");
		FileReader fReader2 = new FileReader(drs);
		BufferedReader bReader2 = new BufferedReader(fReader2);
		String line2;
		String temp2 = "";

		while ((line2 = bReader2.readLine()) != null) {
			temp2 = temp2 + line2;
		}

		temp2 = temp2.substring(1);
		String[] satirlar2 = temp2.split("%");

		for (String satir : satirlar2) {
			dersSatirParcala(dersler, satir);
		}
		
		bReader.close();
		bReader2.close();
	}

	public static void ogrenciSatirParcala(List<Ogrenci> ogrenciler, String line) {
		String ogrenci = line.split("\\)")[0];
		String ders = line.split("\\)")[1];
		List<Ders> dersler = new ArrayList();

		String[] array = ogrenci.split("-");
		ders = ders.substring(1);
		String[] array2 = ders.split("%");

		int ogrenciId = Integer.parseInt(array[0]);
		String ogrenciAdSoyad = array[1];
		int ogrenciYas = Integer.parseInt(array[2]);

		for (String string : array2) {
			String[] array3 = string.split("-");
			int dersId = Integer.parseInt(array3[0]);
			String dersAd = array3[1];
			dersler.add(new Ders(dersId, dersAd));
		}
		ogrenciler.add(new Ogrenci(ogrenciId, ogrenciAdSoyad, ogrenciYas, dersler));
	}

	public static void dersSatirParcala(List<Ders> dersler, String line) {
		String[] array = line.split("-"); 
		int dersId = Integer.parseInt(array[0]);
		String dersAd = array[1];
		
		dersler.add(new Ders(dersId, dersAd));
	}
	
	public static void dosyayaYaz(List<Ogrenci> ogrenciler, List<Ders> dersler) throws IOException {
		File ogr = new File("ogrenciler.txt");
		FileWriter fw = new FileWriter(ogr);
		
		for (Ogrenci ogrenci : ogrenciler) {
			String ogrSatir = "(" + ogrenci.getOgrenciId() + "-" + ogrenci.getOgrenciAdSoyad() + "-" + ogrenci.getOgrenciYas() + ")\n";
			fw.write(ogrSatir);
			for (Ders ders : ogrenci.getAlinanDersler()) {
				String drsSatir = "%" + ders.getDersId() + "-" + ders.getDersAd() + "\n";
				fw.write(drsSatir);
			}
		}
		
		File drs = new File("ders.txt");
		FileWriter fw2 = new FileWriter(drs);
		
		for (Ders ders : dersler) {
			String dersSatir = "%" + ders.getDersId() + "-" + ders.getDersAd() + "\n";
			fw2.write(dersSatir);
		}
		fw.close();
		fw2.close();
	}
}



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


import java.util.List;
import java.util.Scanner;

public class DersFonksiyonlari {
	Scanner scanner = new Scanner(System.in);
	
	public void dersEkle(List<Ders> dersler) {
		int dersId;
		String dersAd;
		
		System.out.println("Id giriniz");
		dersId = scanner.nextInt();
		scanner.nextLine();
		
		for (Ders ders : dersler) {
			if(ders.getDersId() == dersId) {
				System.out.println("Girdiginiz id kullanilmaktadir.");
				return;
			}
		}
		
		System.out.println("Ders ad: ");
		dersAd = scanner.nextLine();
		
		dersler.add(new Ders(dersId, dersAd));
	}
	
	public void dersListele(List<Ders> dersler) {
		System.out.println("Ders Id    Ders Adı");

		for (Ders ders : dersler) {
			System.out.println(ders.getDersId() + "    " + ders.getDersAd());
		}
	}
	
	public Ders dersAra(int id, List<Ders> dersler) {
		for (Ders ders : dersler) {
			if(ders.getDersId() == id) {
				return ders;
			}
		}
		return null;
	}
}


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
