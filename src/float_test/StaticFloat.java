package float_test;

import libcalc.TernUtil;

public class StaticFloat {
	public long f, m;
	
	public StaticFloat() {
		f = 0;
		m = 0;
	}
	
	public StaticFloat(long f, long m) {
		this.f = f;
		this.m = m;
		normalize(f, m);
	}
	public StaticFloat(long f, long m, boolean norm) {
		this.f = f;
		this.m = m;
		if(norm) normalize(f, m);
	}
	
	public void normalize(long f0, long m0) {
		if(f0 != 0) {
			int log3 = TernUtil.log3(TernUtil.mpi(18)/f0);
			if(log3 > 0) {
				f = f0*TernUtil.threeToThePowerOf(log3);
				m = m0-log3;
			}
		}
	}
	
	public void add(StaticFloat f0) {
		add(f, m, f0.f, f0.m);
	}
	public void sub(StaticFloat f0) {
		add(f, m, -f0.f, f0.m);
	}
	public void mul(StaticFloat f0) {
		mul(f, m, f0.f, f0.m);
	}
	public void div(StaticFloat f0) {
		div(f, m, f0.f, f0.m);
	}
	public void reciprocal() {
		reciprocal(f, m);
	}
	public void add(long fix0, long man0, long fix1, long man1) {
		long f0 = fix0;
		long f1 = fix1;
		long m0 = man0;
		long m1 = man1;
		if(m1 == m0) {
			f = fix0 + fix1;
			m = man0;
			normalize(f, m);
			return;
		}
		if(m1 > m0) {
			f0 = fix1;
			f1 = fix0;
			m0 = man1;
			m1 = man0;
		}
		long lf0 = TernUtil.log3(f0);
		long lf1 = TernUtil.log3(f0);
		long df = TernUtil.abs(lf0 - lf1);
		long diff = m0-m1;
		if(diff < 9) {
			f1 /= TernUtil.threeToThePowerOf((int) diff);
			f = f0 + f1;
			m = m0;
		}
		else if(diff > (df+1)/2) {
			f = f0;
			m = m0;
		}
		else {
			f0 /= TernUtil.threeToThePowerOf((int) ((df+1)/2));
			f1 *= TernUtil.threeToThePowerOf((int) (df/2));
			f = f0+f1;
			m = m1+df/2;
		}
		normalize(f, m);
	}
	
	public void sub(long fix0, long man0, long fix1, long man1) {
		add(fix0, man0, -fix1, man1);
	}
	
	public void reciprocal(long fix0, long man0) {
		if(TernUtil.abs(fix0) < TernUtil.threeToThePowerOf(18)) {
			f = TernUtil.threeToThePowerOf(18)/fix0;
			m = -18-man0;
			normalize(f, m);
		}
		else {
			f = fix0/TernUtil.threeToThePowerOf(18);
			m = -18-man0;
			normalize(f, m);
		}
	}
	
	public void mul(long fix0, long man0, long fix1, long man1) {
		long nf0 = TernUtil.log3(fix0);
		long nf1 = TernUtil.log3(fix1);
		
		if(nf0 + nf1 <= 17) {
			f = fix0*fix1;
			m = man0 + man1;
			normalize(f, m);
		}
		else if(nf0 > nf1) {
			long diff = nf0-nf1;
			long pow = TernUtil.threeToThePowerOf((int) diff);
			f = (fix0/pow) * fix1;
			m = man0 + man1 + diff;
			normalize(f, m);
		}
		else {
			long diff = nf1-nf0;
			long pow = TernUtil.threeToThePowerOf((int) diff);
			f = (fix1/pow) * fix0;
			m = man0 + man1 + diff;
			normalize(f, m);
		}
	}
	
	public void div(long fix0, long man0, long fix1, long man1) {
		reciprocal(fix1, man1);
		mul(fix0, man0, f, m);
	}
	
	public double getDouble() {
		return f * Math.pow(3, m);
	}
	
	public StaticFloat dupe() {
		return new StaticFloat(f, m, false);
	}
	
	public void set(long fix0, long man0) {
		f = fix0;
		m = man0;
		normalize(f, m);
	}
	
	@Override
	public String toString() {
		return Double.toString(getDouble());
	}
	
	public static void main(String args[]) {
		StaticFloat f = new StaticFloat(5, -1);
		StaticFloat g = new StaticFloat(1, 2);
		System.out.println(f);
		System.out.println(g);
		f.div(g);
		System.out.println(f);
	}
}
