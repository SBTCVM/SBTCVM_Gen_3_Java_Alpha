package float_test;

import java.util.Stack;

public class FloatVM {
	private StaticFloat f0, f1, f2, f3;
	private Stack<StaticFloat> s0, s1, s2;
	
	
	public FloatVM() {
		f0 = new StaticFloat();
		f1 = new StaticFloat();
		f2 = new StaticFloat();
		f3 = new StaticFloat();
		
		s0 = new Stack<>();
		s1 = new Stack<>();
		s2 = new Stack<>();
	}
	
	
	public void add01() {
		f0.add(f1);
	}
	public void add02() {
		f0.add(f2);
	}
	public void add03() {
		f0.add(f3);
	}
	
	public void add10() {
		f1.add(f0);
	}
	public void add12() {
		f1.add(f2);
	}
	public void add13() {
		f1.add(f3);
	}
	
	public void add21() {
		f2.add(f1);
	}
	public void add20() {
		f2.add(f0);
	}
	public void add23() {
		f2.add(f3);
	}
	
	public void add31() {
		f3.add(f1);
	}
	public void add32() {
		f3.add(f2);
	}
	public void add30() {
		f3.add(f0);
	}
	
	
	
	public void mul01() {
		f0.mul(f1);
	}
	public void mul02() {
		f0.mul(f2);
	}
	public void mul03() {
		f0.mul(f3);
	}
	
	public void mul10() {
		f1.mul(f0);
	}
	public void mul12() {
		f1.mul(f2);
	}
	public void mul13() {
		f1.mul(f3);
	}
	
	public void mul21() {
		f2.mul(f1);
	}
	public void mul20() {
		f2.mul(f0);
	}
	public void mul23() {
		f2.mul(f3);
	}
	
	public void mul31() {
		f3.mul(f1);
	}
	public void mul32() {
		f3.mul(f2);
	}
	public void mul30() {
		f3.mul(f0);
	}
	
	
	
	public void sub01() {
		f0.sub(f1);
	}
	public void sub02() {
		f0.sub(f2);
	}
	public void sub03() {
		f0.sub(f3);
	}
	
	public void sub10() {
		f1.sub(f0);
	}
	public void sub12() {
		f1.sub(f2);
	}
	public void sub13() {
		f1.sub(f3);
	}
	
	public void sub21() {
		f2.sub(f1);
	}
	public void sub20() {
		f2.sub(f0);
	}
	public void sub23() {
		f2.sub(f3);
	}
	
	public void sub31() {
		f3.sub(f1);
	}
	public void sub32() {
		f3.sub(f2);
	}
	public void sub30() {
		f3.sub(f0);
	}
	
	public void div01() {
		f0.div(f1);
	}
	public void div02() {
		f0.div(f2);
	}
	public void div03() {
		f0.div(f3);
	}
	
	public void div10() {
		f1.div(f0);
	}
	public void div12() {
		f1.div(f2);
	}
	public void div13() {
		f1.div(f3);
	}
	
	public void div21() {
		f2.div(f1);
	}
	public void div20() {
		f2.div(f0);
	}
	public void div23() {
		f2.div(f3);
	}
	
	public void div31() {
		f3.div(f1);
	}
	public void div32() {
		f3.div(f2);
	}
	public void div30() {
		f3.div(f0);
	}
	
	public void set0(long f, long m) {
		f0.set(f, m);
	}
	public void set1(long f, long m) {
		f1.set(f, m);
	}
	public void set2(long f, long m) {
		f2.set(f, m);
	}
	public void set3(long f, long m) {
		f3.set(f, m);
	}
	
	public void push00() {
		s0.push(f0);
	}
	public void push10() {
		s0.push(f1);
	}
	public void push20() {
		s0.push(f2);
	}
	public void push30() {
		s0.push(f3);
	}
	
	public void push01() {
		s1.push(f0);
	}
	public void push11() {
		s1.push(f1);
	}
	public void push21() {
		s1.push(f2);
	}
	public void push31() {
		s1.push(f3);
	}
	
	public void push02() {
		s2.push(f0);
	}
	public void push12() {
		s2.push(f1);
	}
	public void push22() {
		s2.push(f2);
	}
	public void push32() {
		s2.push(f3);
	}
}
