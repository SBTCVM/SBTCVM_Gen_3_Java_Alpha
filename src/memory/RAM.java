package memory;
import vmsystem.Opcodes.Op;

public class RAM {
	private long[] inst, data;
	public Op[] opcodes;
//	private final 
	private Op callDecoder = 
			x -> {
//				CPU.jit.update()
			};
	
	public RAM(int ramSize) {
		inst = new long[ramSize];
		data = new long[ramSize];
		opcodes = new Op[ramSize];
		
		for(int i = 0; i < opcodes.length; i++) {
			opcodes[i] = callDecoder;
		}
	}
	
	public void writeInst(long address, long data) {
		this.inst[(int) (address+9841)] = data;
		this.opcodes[(int) (address+9841)] = callDecoder;
	}
	public void writeData(long address, long data) {
		this.data[(int) (address+9841)] = data;
	}
	
	public long readInst(long address) {return inst[(int) (address+9841)];}
	public long readData(long address) {return inst[(int) (address+9841)];}
}
