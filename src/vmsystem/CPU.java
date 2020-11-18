package vmsystem;

import java.util.HashMap;

import libcalc.TernUtil;
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
		
		
		opcodes.put(0L, 
				data -> {
//					System.out.println("DEBUG: " + reg1 + ", " + reg2);
				}
		);
	}
	
	private long reg1, reg2, data, inst, opcode;
	private long execpoint;
	private RAM ram;
	private long[][] stacks;
	private int[] stackTop;
	
	public CPU(RAM ram) {
		initOpcodes();
		
		this.reg1 = 1;
		this.reg2 = 1;
		this.execpoint = -9841L;
		this.ram = ram;
		
		this.data = 0;
		this.inst = 0;
		this.opcode = 0;
		
		this.stacks = new long[6][9841];
		this.stackTop = new int[6];
	}
	
	public void cycle() {
		
		this.data = ram.readData(execpoint);
		this.inst = ram.readInst(execpoint);
		
		Op op = opcodes.get(inst);
		if(op != null) {
			op.calc(data);
		}
		execpoint++;
	}
	
	@Override
	public String toString() {
		return "SBTCVM_G3";
	}
}
