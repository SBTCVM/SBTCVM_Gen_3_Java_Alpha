package libcalc;

public class TernUtil {
	public static final long[] POW3 = new long[33];
	public static final long[] MPI = new long[33];
	
	static {
		long n = 1;
		for(int i = 0; i < POW3.length; i++) {
			POW3[i] = n;
			MPI[i] = (n-1)/2;
			n*=3;
		}
	}
	
	public static long threeToThePowerOf(int t) {
		return POW3[t];
	}
	
	public static long add(long a, long b) {
		return a + b;
	}
	public static long sub(long a, long b) {
		return a - b;
	}
	public static long mul(long a, long b) {
		return a * b;
	}
	public static long div(long a, long b) {
		return a / b;
	}
	
	public static long[] rem(long a, long b) {
		return new long[] {a/b, a%b};
	}
	
	public static long shr(long n, int t) {
		return (n-cast(n, t))/POW3[t];
	}
	public static long shl(long n, int t) {
		return n*POW3[t];
	}
	
	public static long cast(long n, int t) {
		long rem = abs(n) % POW3[t];
		if (rem > MPI[t]) {
		    return (rem - (POW3[t]))*signof(n);
		}
		else {
		    return rem * signof(n);
		}
	}
	
	public static long abs(long n) {
		long l = (n & 0b10000000000000000000000000000000)>>63;
		return (((~n+1) & l)) | ((n & (~l)));
	}
	public static long nabs(long n) {
		long l = ((~n) & 0b10000000000000000000000000000000)>>63;
		return (((~n+1) & l)) | ((n) & (~l));
	}
	public static long neg(long n) {
		long l = (n & 0b10000000000000000000000000000000)>>63;
		return (((~n+1) & l)) | (((~n+1) & (~l)));
	}
	public static long signof(long n) {
		return (((~n)>>>63)<<1)-1;
	}
	
	public static long from(String s) throws Exception {
		long out = 0;
		s = new StringBuilder(s).reverse().toString();
		for(int i = 0; i < s.length(); i++) {
			switch(s.charAt(i)) {
				case '+' : out+=POW3[i]; break;
				case '-' : out-=POW3[i]; break;
				case 'p' : out+=POW3[i]; break;
				case 'n' : out-=POW3[i]; break;
				case '0' : break;
				default : throw new Exception("Illegal character: " + s.charAt(i));
			}
		}
		return out;
	}
	
	public static long from(long inst, long data) {
		return (inst*POW3[9]) + data;
	}
	
	public static long mni() {
		return -9841L;
	}
	public static long mpi() {
		return  9841L;
	}

	public static long mpi(int n) {
		return MPI[n];
	}
	public static long mni(int n) {
		return-MPI[n];
	}
	public static int mostSigTrit(long n) {
		long k = abs(n);
		int sig = -1;
		for(int i = MPI.length-1; i > 0; i--) {
			if(k < MPI[i]) sig = i;
			else break;
		}
		return sig;
	}
	
	public static int log3(long n) {
		long k = abs(n);
		int sig = -1;
		for(int i = POW3.length-1; i > 0; i--) {
			if(k < POW3[i]) sig = i;
			else break;
		}
		return sig;
	}
	
	public static String toString(long n) {
		long num = abs(n);
		StringBuilder sb = new StringBuilder();
		int i = 0;

		while (num != 0 && i < 12) {
			if (num % 3 == 1) sb.append(n > 0 ? '+' : '-');
			else if (num % 3 == 2) sb.append(n > 0 ? '-' : '+');
			else sb.append('0');

			num = (num + ((num < 0) ? -1 : 1)) / 3; // automatically floored

			i++;
		}
		
		return sb.reverse().toString();
	}
}
