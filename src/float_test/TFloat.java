package float_test;

import libcalc.TernUtil;

public class TFloat {
	private long fixed, mantissa;
	
	public TFloat(long fixed, long mantissa) {
		this.fixed = fixed;
		this.mantissa = mantissa;
		normalize();
	}
	public TFloat(long fixed, long mantissa, boolean norm) {
		this.fixed = fixed;
		this.mantissa = mantissa;
		if(norm) normalize();
	}
	
	public double getDouble() {
		return fixed * Math.pow(3, mantissa);
	}
	
	public void normalize() {
		if(fixed != 0) {
			int log3 = TernUtil.log3(TernUtil.mpi(18)/fixed);
			if(log3 > 0) {
				fixed *= TernUtil.threeToThePowerOf(log3);
				mantissa -= log3;
			}
		}
	}
	
	public TFloat add(TFloat f) {
		long f0 = fixed;
		long f1 = f.fixed;
		long m0 = mantissa;
		long m1 = f.mantissa;
		if(m1 == m0) {
			return new TFloat(fixed + f.fixed, mantissa);
		}
		if(m1 > m0) {
			f0 = f.fixed;
			f1 = fixed;
			m0 = f.mantissa;
			m1 = mantissa;
		}
		long lf0 = TernUtil.log3(f0);
		long lf1 = TernUtil.log3(f0);
		long df = TernUtil.abs(lf0 - lf1);
		long diff = m0-m1;
		if(diff < 9) {
			f1 /= TernUtil.threeToThePowerOf((int) diff);
			return new TFloat(f0 + f1, m0);
		}
		if(diff > (df+1)/2) {
			return new TFloat(f0, m0);
		}
		else {
			f0 /= TernUtil.threeToThePowerOf((int) ((df+1)/2));
			f1 *= TernUtil.threeToThePowerOf((int) (df/2));
			return new TFloat(f0+f1, m1 + df/2);
		}
	}
	
	public TFloat sub(TFloat f) {
		return add(f.neg());
	}
	
	public TFloat neg() {
		return new TFloat(-fixed, mantissa, false);
	}
	
	public TFloat mul(TFloat f) {
		long nf0 = TernUtil.log3(fixed);
		long nf1 = TernUtil.log3(f.fixed);
		
		if(nf0 + nf1 <= 17) {
			return new TFloat(fixed*f.fixed, mantissa + f.mantissa);
		}
		if(nf0 > nf1) {
			long diff = nf0-nf1;
			long pow = TernUtil.threeToThePowerOf((int) diff);
			return new TFloat((fixed/pow) * f.fixed, mantissa + f.mantissa + diff);
		}
		long diff = nf1-nf0;
		long pow = TernUtil.threeToThePowerOf((int) diff);
		return new TFloat((f.fixed/pow) * fixed, mantissa + f.mantissa + diff);
	}
	
	public TFloat div(TFloat f) {
		return mul(f.reciprocal());
	}
	
	public TFloat reciprocal() {
		if(TernUtil.abs(fixed) < TernUtil.threeToThePowerOf(17)) {
			System.out.println("OI");
			return new TFloat(TernUtil.threeToThePowerOf(17)/fixed, -17 - mantissa);
		}
		else
			return new TFloat(fixed/TernUtil.threeToThePowerOf(17), -17 + mantissa);
	}
	
	@Override
	public String toString() {
		return Double.toString(getDouble());
	}
	
	public static void main(String args[]) {
		TFloat f = new TFloat(200,-1);
		TFloat g = new TFloat(100, 2);
		
//		System.out.println(f + " + " + g + " = " + f.add(g));
//		System.out.println(f + " - " + g + " = " + f.sub(g));
//		System.out.println(f + " * " + g + " = " + f.mul(g));
//		System.out.println(f + " / " + g + " = " + f.div(g));
		
		System.out.println(g + " 1/g " + g.reciprocal());
		
//		System.out.println("f = " + f);
//		System.out.println("1/" + f + " = " + f.reciprocal());
	}
}
