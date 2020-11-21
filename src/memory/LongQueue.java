package memory;

public class LongQueue {
	private long[] arr;
	private int head, tail, size, max;
	
	public LongQueue(int max) {
		this.max = max;
		this.arr = new long[max];
		this.head = 0;
		this.tail = 0;
		this.size = 0;
	}
	
	public void pushEnd(long n) {
		arr[tail%max] = n;
		tail = (tail+1)%max;
		size++;
	}
	public long popFront() {
		if(size > 0) {
			long ret = arr[head];
			head = (head+1)%max;
			size--;
			return ret;
		}
		return 0;
	}
	
	public int size() {
		return size;
	}
}
