package vmsystem;

public class FrontEndSBTCVM {

	public static void main(String[] args) {
//		MemSys mem = new MemSys(1000000);
		RAM mem = new RAM(1000000);
		mem.loadROM(new long[] {
//				TernUtil.from(-9841, 0), //reg1 = 0
//				TernUtil.from(-9840, 1), //reg2 = 1
//				TernUtil.from(-9800, 0), //add1
//				TernUtil.from(-9837, 0), //swap
//				TernUtil.from(0, 0), //debug out
//				TernUtil.from(-9600, -9839) //goto add1 instruction
		});
		
		CPU cpu = new CPU(mem);
		long t0 = System.currentTimeMillis();
		for(int i = 0; i < 1000000; i++) {
			cpu.cycle();
		}
		long t1 = System.currentTimeMillis();
		System.out.println((1000000.0/(t1-t0))*1000.0);
	}

}
