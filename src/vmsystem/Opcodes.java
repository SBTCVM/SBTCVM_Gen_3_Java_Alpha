package vmsystem;

public class Opcodes {
	@FunctionalInterface
	public interface Op {
		public void calc(long data);
	}
}
