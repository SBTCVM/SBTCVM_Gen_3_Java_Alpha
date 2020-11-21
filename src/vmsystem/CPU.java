package vmsystem;

import java.util.HashMap;

import io_bus.IOBus;
import libcalc.TernUtil;
import memory.LongStack;
import memory.RAM;
import vmsystem.Opcodes.Op;

public class CPU {
	private HashMap<Long, Op> opcodes;
	
	public void initOpcodes() {
		opcodes = new HashMap<>();
		//Simple reg operations
		//setreg1
		opcodes.put(-9841L, 
				data -> {
					this.reg1 = data;
				}
		);
		//setreg2
		opcodes.put(-9840L, 
				data -> {
					this.reg2 = data;
				}
		);
		//reg1=reg2
		opcodes.put(-9839L, 
				data -> {
					this.reg1 = reg2;
				}
		);
		//reg2=reg1
		opcodes.put(-9838L, 
				data -> {
					this.reg2 = reg1;
				}
		);
		//swap reg1 reg2
		opcodes.put(-9837L, 
				data -> {
					long temp = reg1;
					this.reg1 = reg2;
					this.reg2 = temp;
				}
		);
		//invert reg1
		opcodes.put(-9836L, 
				data -> {
					this.reg1 = TernUtil.neg(this.reg1);
				}
		);
		//invert reg2
		opcodes.put(-9835L, 
				data -> {
					this.reg2 = TernUtil.neg(this.reg2);
				}
		);
		//abs1
		opcodes.put(-9834L, 
				data -> {
					this.reg1 = TernUtil.abs(this.reg1);
				}
		);
		//abs2
		opcodes.put(-9833L, 
				data -> {
					this.reg2 = TernUtil.abs(this.reg2);
				}
		);
		//nabs1
		opcodes.put(-9832L, 
				data -> {
					this.reg1 = TernUtil.nabs(this.reg1);
				}
		);
		//nabs2
		opcodes.put(-9831L, 
				data -> {
					this.reg2 = TernUtil.nabs(this.reg2);
				}
		);
		
		//ALU
		//reg1 = reg1+reg2
		opcodes.put(-9800L, 
				data -> {
					this.reg1 = TernUtil.add(this.reg1, this.reg2);
				}
		);
		//reg2 = reg1+reg2
		opcodes.put(-9799L, 
				data -> {
					this.reg2 = TernUtil.add(this.reg1, this.reg2);
				}
		);
		//re1+=data
		opcodes.put(-9798L, 
				data -> {
					this.reg1 = TernUtil.add(this.reg1, this.data);
				}
		);
		//reg2+=data
		opcodes.put(-9797L, 
				data -> {
					this.reg2 = TernUtil.add(this.reg2, this.data);
				}
		);
		
		//reg1 = reg1-reg2
		opcodes.put(-9796L, 
				data -> {
					this.reg1 = TernUtil.sub(this.reg1, this.reg2);
				}
		);
		//reg2 = reg1-reg2
		opcodes.put(-9795L, 
				data -> {
					this.reg2 = TernUtil.sub(this.reg1, this.reg2);
				}
		);
		//re1-=data
		opcodes.put(-9794L, 
				data -> {
					this.reg1 = TernUtil.sub(this.reg1, this.data);
				}
		);
		//reg2-=data
		opcodes.put(-9793L, 
				data -> {
					this.reg2 = TernUtil.sub(this.reg2, this.data);
				}
		);
		
		//reg1 = reg1*reg2
		opcodes.put(-9792L, 
				data -> {
					this.reg1 = TernUtil.mul(this.reg1, this.reg2);
				}
		);
		//reg2 = reg1*reg2
		opcodes.put(-9791L, 
				data -> {
					this.reg2 = TernUtil.mul(this.reg1, this.reg2);
				}
		);
		//re1*=data
		opcodes.put(-9790L, 
				data -> {
					this.reg1 = TernUtil.mul(this.reg1, this.data);
				}
		);
		//reg2*=data
		opcodes.put(-9789L, 
				data -> {
					this.reg2 = TernUtil.mul(this.reg2, this.data);
				}
		);
		
		//reg1 = reg1/reg2
		opcodes.put(-9788L, 
				data -> {
					this.reg1 = TernUtil.div(this.reg1, this.reg2);
				}
		);
		//reg2 = reg1/reg2
		opcodes.put(-9787L, 
				data -> {
					this.reg2 = TernUtil.div(this.reg1, this.reg2);
				}
		);
		//re1/=data
		opcodes.put(-9786L, 
				data -> {
					this.reg1 = TernUtil.div(this.reg1, this.data);
				}
		);
		//reg2/=data
		opcodes.put(-9785L, 
				data -> {
					this.reg2 = TernUtil.div(this.reg2, this.data);
				}
		);
		
		//remainder operation
		opcodes.put(-9784L, 
				data -> {
					long[] temp = TernUtil.rem(this.reg1, this.reg2);
					this.reg2 = temp[0];
					this.reg1 = temp[1];
				}
		);
		
		//gotos
		//goto data
		opcodes.put(-9600L, 
				data -> {
					this.execpoint = data;
				}
		);
		//goto ifequal
		opcodes.put(-9599L, 
				data -> {
					if(reg1 == reg2) {
						this.execpoint = data;
					}
				}
		);
		//if less
		opcodes.put(-9598L, 
				data -> {
					if(reg1 < reg2) {
						this.execpoint = data;
					}
				}
		);
		//if more
		opcodes.put(-9597L, 
				data -> {
					if(reg1 > reg2) {
						this.execpoint = data;
					}
				}
		);
		//goto reg1
		opcodes.put(-9596L, 
				data -> {
					this.execpoint = reg1;
				}
		);
		//goto reg2
		opcodes.put(-9595L, 
				data -> {
					this.execpoint = reg2;
				}
		);
		
		//data read/write
		
		//dataread1
		opcodes.put(-9500L, 
				data -> {
					this.reg1 = ram.readData(data);
				}
		);
		//dataread2
		opcodes.put(-9499L, 
				data -> {
					this.reg2 = ram.readData(data);
				}
		);
		//instread1
		opcodes.put(-9498L, 
				data -> {
					this.reg1 = ram.readInst(data);
				}
		);
		//instread2
		opcodes.put(-9497L, 
				data -> {
					this.reg2 = ram.readInst(data);
				}
		);
		
		//datawrite 1
		opcodes.put(-9496L, 
				data -> {
					ram.writeData(data, reg1);
				}
		);
		
		//datawrite 2
		opcodes.put(-9495L, 
				data -> {
					ram.writeData(data, reg2);
				}
		);
		
		//instwrite 1
		opcodes.put(-9494L, 
				data -> {
					ram.writeInst(data, reg1);
				}
		);
		//instwrite 2
		opcodes.put(-9493L, 
				data -> {
					ram.writeInst(data, reg2);
				}
		);

		//io write1
		opcodes.put(-9492L, 
				data -> {
					iobus.deviceWrite(data, reg1);
				}
		);
		//io write2
		opcodes.put(-9491L, 
				data -> {
					iobus.deviceWrite(data, reg2);
				}
		);
		
		//io read1
		opcodes.put(-9490L, 
				data -> {
					reg1 = iobus.deviceRead(data);
				}
		);
		//io read2
		opcodes.put(-9489L, 
				data -> {
					reg2 = iobus.deviceRead(data);
				}
		);
		
		//fopwrite1
		opcodes.put(-9460L, 
				data -> {
					iobus.deviceWrite(fop1, data);
				}
		);
		//fopset1
		opcodes.put(-9459L, 
				data -> {
					fop1 = data;
				}
		);
		
		//fopwrite2
		opcodes.put(-9458L, 
				data -> {
					iobus.deviceWrite(fop2, data);
				}
		);
		//fopset2
		opcodes.put(-9457L, 
				data -> {
					fop2 = data;
				}
		);
		
		//fopwrite3
		opcodes.put(-9456L, 
				data -> {
					iobus.deviceWrite(fop3, data);
				}
		);
		//fopset3
		opcodes.put(-9455L, 
				data -> {
					fop3 = data;
				}
		);
		
		
		//stack1
		opcodes.put(-9100L, 
				data -> {
					decodeStack(stacks[0], data);
				}
		);
		opcodes.put(-9101L, 
				data -> {
					decodeStack(stacks[1], data);
				}
		);
		opcodes.put(-9102L, 
				data -> {
					decodeStack(stacks[2], data);
				}
		);
		opcodes.put(-9103L, 
				data -> {
					decodeStack(stacks[3], data);
				}
		);
		opcodes.put(-9104L, 
				data -> {
					decodeStack(stacks[4], data);
				}
		);
		opcodes.put(-9105L, 
				data -> {
					decodeStack(stacks[5], data);
				}
		);
				
		opcodes.put(-9000L, 
				data -> {
					running = false;
				}
		);

		
		opcodes.put(0L, 
				data -> {
					//do nothing
				}
		);
		
		for(Long l : opcodes.keySet()) opArr[(int) (l + 9841)] = opcodes.get(l);
	}
	
	private long reg1, reg2, data, inst;
	
	private long fop1, fop2, fop3;
	
	private long execpoint;
	private RAM ram;
	private IOBus iobus;
	private boolean running = true;
	private LongStack[] stacks;
	
	private final Op[] opArr;
	
	public CPU(RAM ram, IOBus iobus) {
		this.opArr = new Op[19683];
		initOpcodes();
		
		this.reg1 = 1;
		this.reg2 = 1;
		this.execpoint = -9841L;
		this.ram = ram;
		this.iobus = iobus;
		
		this.data = 0;
		this.inst = 0;
		this.stacks = new LongStack[] {
				new LongStack(), new LongStack(), new LongStack(), new LongStack(), new LongStack(), new LongStack()
		};
		
		this.fop1 = 0;
		this.fop2 = 0;
		this.fop3 = 0;
	}
	
	public void run() {
		System.out.println("VM start : " + System.currentTimeMillis());
		long t0 = System.currentTimeMillis();
		while(running) {
			cycle();
		}
		long t1 = System.currentTimeMillis();
		System.out.println("VM time   : " + (t1-t0));
		System.out.println("VM cycles : " + cycles);
	}
	long cycles = 0;
	public void cycle() {
		this.data = ram.readData(execpoint);
		this.inst = ram.readInst(execpoint);
		
		long e = execpoint;
		Op op = opArr[(int) (inst+9841)];
		if(op != null) {
			op.calc(data);
		}
		if(e==execpoint) execpoint++;
		cycles++;
	}
	
	private void decodeStack(LongStack s, long data) {
		if(data == 0) pop1(s);
		else if(data == 1) pop2(s);
		else if(data == 2) push1(s);
		else if(data == 3) push2(s);
		else if(data == 4) peek1(s);
		else if(data == 5) peek2(s);
		else if(data == 6) invertStack(s);
	}
	
	private void pop1(LongStack s) {
		this.reg1 = s.pop();
	}
	private void pop2(LongStack s) {
		this.reg2 = s.pop();
	}
	private void push1(LongStack s) {
		s.push(reg1);
	}
	private void push2(LongStack s) {
		s.push(reg2);
	}
	private void peek1(LongStack s) {
		this.reg1 = s.peek();
	}
	private void peek2(LongStack s) {
		this.reg2 = s.peek();
	}
	private void invertStack(LongStack s) {
		s.invert();
	}
	
	@Override
	public String toString() {
		return "SBTCVM_G3";
	}
}
