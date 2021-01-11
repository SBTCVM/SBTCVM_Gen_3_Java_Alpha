package disk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class TROM {
	private final String file_loc;
	private final File file;
	
	private long[] trom;
	
	public TROM(String file_loc) {
		this.file_loc = file_loc;
		this.file = new File(this.file_loc);
		createTROM();
	}
	
	private void createTROM() {
		ArrayList<Long> t = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				t.add(Long.parseLong(arr[0]));
				t.add(Long.parseLong(arr[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		trom = new long[t.size()];
		for(int i = 0; i < trom.length; i++) trom[i] = t.get(i);
	}
	
	public long[] getTROM() {
		return trom;
	}
}
