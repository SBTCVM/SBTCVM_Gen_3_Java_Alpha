package vmsystem;

import disk.TROM;
import io_bus.IOBus;
import libcalc.TernUtil;
import memory.RAM;

public class FrontEndSBTCVM {

	public static void main(String[] args) throws Exception {
		RAM mem = new RAM(100000);
		IOBus bus = new IOBus((int) TernUtil.mpi());
		bus.initIO();
		mem.loadROM(new TROM("C:\\Users\\s650355\\Documents\\CompSci1\\SBTCVM-Gen2-9-master\\SBTCVM-Gen2-9-master\\demos\\tritmap_heavy\\auto_crtsplash.trom"));
		CPU cpu = new CPU(mem, bus);
		cpu.run();
	}

}
