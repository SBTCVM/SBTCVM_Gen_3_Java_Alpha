package io_bus;

import graphics.SBTGA;

public class IOBus {
	private final ReadNotify[] io_read;
	private final WriteNotify[] io_write;
	private final long[] io_data;
	public IOBus(long size) {
		io_read = new ReadNotify[(int) size];
		io_write = new WriteNotify[(int) size];
		io_data = new long[(int) size];
	}
	
	public void readOverride(long address, ReadNotify read) {
		io_read[(int) address] = read;
	}
	public void writeOverride(long address, WriteNotify write) {
		io_write[(int) address] = write;
	}
	
	public void deviceWrite(long address, long data) {
		if(io_write[(int) address] != null) io_write[(int) address].writeNotify(data);
		else io_data[(int) address] = 0;
	}
	public long deviceRead(long address) {
		if(io_read[(int) address] != null) return io_read[(int) address].readNotify();
		return io_data[(int) address] = 0;
	}
	
	private Terminal terminal;
	private SBTGA sbtga;
	
	public void initIO() {
		this.terminal = new Terminal();
		this.sbtga = new SBTGA();
		
		writeOverride(2, terminal::tritWriteNotif);
		writeOverride(3, terminal::decWriteNotif);
		
		readOverride(4, () -> 1);
		
		writeOverride(501, sbtga::setx1);
		writeOverride(502, sbtga::sety1);
		writeOverride(503, sbtga::setx2);
		writeOverride(504, sbtga::sety2);
		
		writeOverride(505, sbtga::setColor);
		writeOverride(506, sbtga::plotLine);
		
		writeOverride(508, sbtga::drawRect);
		writeOverride(509, sbtga::rectWidth);
		writeOverride(510, sbtga::rectHeight);
		
		writeOverride(522, sbtga::setx3);
		writeOverride(523, sbtga::sety3);
	}
}
