package libcalc;

import java.util.stream.IntStream;

public class DivOp {
	private static Div[] ops = new Div[] {
			n -> n,
			n -> (n * 0x2aaaaaaaaL)>>35,
			n -> (n * 0xe38e38e3L)>>35,
			n -> (n * 0x4bda12f6L)>>35,
			n -> (n * 0x1948b0fcL)>>35,
			n -> (n * 0x86d9054L)>>35,
			n -> (n * 0x2cf301cL)>>35,
			n -> (n * 0xefbab4L)>>35,
			n -> (n * 0x4fe8e6L)>>35,
			n -> (n * 0x1aa2f7L)>>35,
			n -> (n * 0x8e0fdL)>>35,
			n -> (n * 0x2f5a9L)>>35,
			n -> (n * 0xfc8dL)>>35,
			n -> (n * 0x542fL)>>35,
			n -> (n * 0x1c0fL)>>35,
			n -> (n * 0x95aL)>>35,
			n -> (n * 0x31eL)>>35,
			n -> (n * 0x10aL)>>35,
			n -> (n * 0x58L)>>35,
	};
	
	
	interface Div {
		public long div(long n);
	}
	
	public static long divPow3(long n, int t) {
		return ops[t].div(n);
	}
	
	static String decimalToBinary(double num, int k_prec) 
    { 
        String binary = ""; 
  
        // Fetch the integral part of decimal number 
        int Integral = (int) num; 
  
        // Fetch the fractional part decimal number 
        double fractional = num - Integral; 
  
        // Conversion of integral part to 
        // binary equivalent 
        while (Integral > 0)  
        { 
            int rem = Integral % 2; 
  
            // Append 0 in binary 
            binary += ((char)(rem + '0')); 
  
            Integral /= 2; 
        } 
  
        // Reverse string to get original binary 
        // equivalent 
        binary = new StringBuilder(binary).reverse().toString(); 
  
        // Append point before conversion of 
        // fractional part 
        binary += ('.'); 
  
        // Conversion of fractional part to 
        // binary equivalent 
        while (k_prec-- > 0) 
        { 
            // Find next bit in fraction 
            fractional *= 2; 
            int fract_bit = (int) fractional; 
  
            if (fract_bit == 1) 
            { 
                fractional -= fract_bit; 
                binary += (char)(1 + '0'); 
            } 
            else
            { 
                binary += (char)(0 + '0'); 
            } 
        } 
  
        return binary; 
    } 
	
	private static void generateCode() {
		long k = 3;
		System.out.println("n -> n,");
		for(int i = 1; i < 20; i++) {
			String s = decimalToBinary(1.0/k, 35).substring(1);
			long n = Long.parseLong(s, 2);
			String hex = Long.toHexString(n);
			System.out.println("n -> (n * 0x"+hex+"L)>>35,");
			k*=3;
		}
	}
	
	public static void main(String args[]) {
		long t0 = System.currentTimeMillis();
		IntStream.range(0, 10000000).forEach(
					i -> {
					long k = 0;
					long j = 0;
//					k = 193710244L/TernUtil.threeToThePowerOf(17);
					k = divPow3(193710244L, 17);
					j+=k;
//					k = 193710243L/TernUtil.threeToThePowerOf(17);
					k = divPow3(193710243L, 17);
					j-=k;
					k+=j;
					}
				);
		long t1 = System.currentTimeMillis();
		System.out.println(t1-t0);
//		System.out.println(Integer.toBinaryString(193710244).length());
//		System.out.println(divPow3(193710244, 17));
//		System.out.println((193710244)/((long) Math.pow(3, 17)));
		
	}
}
