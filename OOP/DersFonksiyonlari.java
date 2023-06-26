//OSMAN KARABAÐ 
//19010011010
package osman_odev;
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
		System.out.println("Ders Id    Ders AdÄ±");

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
