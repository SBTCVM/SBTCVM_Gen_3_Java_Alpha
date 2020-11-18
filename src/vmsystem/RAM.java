package vmsystem;

public class RAM {
	
	private long[] inst, data;
	
	public RAM(int ramSize) {
		inst = new long[ramSize];
		data = new long[ramSize];
		
		for(int i = 0; i < ramSize; i++) {
			inst[i] = -9800;
		}
	}
	
	public void writeData(long address, long data) {
		this.data[((int) address) + 9841] = data;
	}
	public void writeInst(long address, long data) {
		this.inst[((int) address) + 9841] = data;
	}
	
	
	public long readInst(long address) {
		return this.inst[((int) address) + 9841];
	}
	public long readData(long address) {
		return this.data[((int) address) + 9841];
	}
	
	public void loadROM(long... instructions) {
		for(int i = 0; i < instructions.length/2; i++) {
			inst[i] = instructions[i*2];
			data[i] = instructions[i*2+1];
		}
	}
}
