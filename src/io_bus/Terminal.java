package io_bus;

import libcalc.TernUtil;

public class Terminal {
	
	public void decWriteNotif(long data) {
		System.out.println(data);
	}
	public void tritWriteNotif(long data) {
		System.out.println(TernUtil.toString(data));
	}
	
}
