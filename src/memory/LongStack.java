package memory;

public class LongStack {
	private final long[] stack;
	private int size = 0;
	public LongStack() {
		stack = new long[9841];
	}
	
	public LongStack(int max) {
		stack = new long[max];
	}
	public void push(long n) {
		stack[size] = n;
		size++;
	}
	public long pop() {
		if(size > 0) {
			size--;
			return stack[size];
		}
		return 0;
	}
	public long peek() {
		return stack[size-1];
	}
	
	public void invert() {
		if(size > 1) {
			for(int i = 0; i < size/2; i++) {
				long f = stack[i];
//				long l = stack[size-1-i];
				
				stack[i] = stack[size-1-i];
				stack[size-1-i] = f;
			}
		}
	}
	
//	public static void main(String args[]) {
//		LongStack s = new LongStack();
//		s.push(10);
//		s.push(20);
//		s.push(30);
//		s.push(40);
//		s.push(50);
//		s.push(60);
//		s.push(70);
//		
//		System.out.println(s.peek());
////		System.out.println(s.pop());
//		s.invert();
//		System.out.println(s.pop());
//		System.out.println(s.pop());
//		System.out.println(s.pop());
//		System.out.println(s.pop());
//	}
}
