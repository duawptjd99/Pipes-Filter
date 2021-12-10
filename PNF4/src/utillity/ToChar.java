package utillity;

import java.util.ArrayList;

public class ToChar {
	ArrayList<Byte> buffer = new ArrayList<Byte>();
	char[] line = new char[64];

	public ToChar(ArrayList<Information> list) {
		for (int i = 0; i < list.size(); i++) {
			line = list.get(i).toString().toCharArray();
			for (int j = 0; j < line.length; j++) {

				buffer.add((byte)line[j]);
			}buffer.add((byte)10);
		
		}
	}
	
	public ArrayList<Byte> getbuffer() {
		return this.buffer;
	}
}
