/**
 * www.algorytm.org
 * Algorytm Hornera - obliczanie wspolczynnikow z 
 * dzielenia wielomianu przez (x-a)^b
 * Tomasz Lubinski (c)2005
 */
public class Hornerd {

	private static int n;
	private static double a[],b[],c[];
	private static double x;

	 //wspolczynniki wielmianu po podzieleniu
	private static double wspol(int k)	{
		if (k==n) {
			c[k-1]=b[k]; 
			return b[k];
		}
		else {
			if (k>0) {
				c[k-1]=wspol(k+1)*x+b[k];
				} 
			return wspol(k+1)*x+b[k];
			}
	}


	public static void main(String[] args) {
		int i,j,s;
		double tmp;
		
		System.out.println("Algorytm Hornera - obliczanie wartosci z dzielenia wielomianu przez (x-a)^b\nPodaj stopien wielomianu");
		n = Console.readInt("?");
		a = new double[n+1];
		b = new double[n+1];
		c = new double[n+1];

		System.out.println("Podaj teraz kolejne wspolczynniki wielomianu.\nZaczynij od tego z najwieksza potega.");
		for(i=n; i>=0; i--) {
			a[i] = Console.readDouble("a[" + i +"] = ");
		}
		
		System.out.println("Podaj parametr a (liczba rzeczywista)");
		x = Console.readInt("?");
		System.out.println("Podaj parametr b (liczba calkowita dodatnia)");
		s = Console.readInt("?");
		if ((s<0)||(s>n)) {
			System.out.println("Nieprawidlowy parametr b"); 
			return;
		}

		for (i=0; i<=n; i++) {
			b[i]=a[i];
		}
		for (j=1; j<=s; j++) {
			tmp=wspol(0);
			for (i=0; i<=n; i++)
				b[i]=c[i];
		}

		System.out.println("Rozwiazanie:");
		for (i=n; i>=0; i--) {
			System.out.println("b["+ i + "]="+b[i]);
		}

		return; 		
	}
}
