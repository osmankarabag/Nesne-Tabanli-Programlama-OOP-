//OSMAN KARABA� 
//19010011010

package osman_odev;
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
			System.out.println("3- Ders Ara");
			System.out.println("4- Öğrenci Ekle");
			System.out.println("5- Öğrenci Ara");
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
