package Components.AddFilter;

import java.io.IOException;

import Framework.CommonFilterImpl;

public class AddFilter extends CommonFilterImpl {

	@Override
	public boolean specificComputationForFilter() throws IOException {

		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		int byte_read = 0;
		int startidx = 0;
		boolean first = false;
		boolean second = false;
		int checkidx = 0;
		int checkidx2 = 0;

		while (true) {
			// check "CS" on byte_read from student information
			while (byte_read != '\n' && byte_read != -1) {
				byte_read = in.read();
				if (byte_read == ' ')
					numOfBlank++;
				if (byte_read != -1) {
					buffer[idx++] = (byte) byte_read;
				}
				if (numOfBlank == 4) {
					startidx = idx;
				}

			}

			for (int i = startidx - 5; i < idx; i = i + 6) {

				if ((char) buffer[i] == '1' && (char) buffer[i + 1] == '7' && (char) buffer[i + 2] == '6'
						&& (char) buffer[i + 3] == '5' && (char) buffer[i + 4] == '1') {
					checkidx = i;
					first = true;
				}

				if ((char) buffer[i] == '1' && (char) buffer[i + 1] == '7' && (char) buffer[i + 2] == '6'
						&& (char) buffer[i + 3] == '5' && (char) buffer[i + 4] == '2') {
					checkidx2 = i;
					second = true;
				}
			}

			if (idx != 0) {

				// idx = idx - 2;
				if (first == true) {
					for (int i = checkidx; i < idx - 6; i++) {
						buffer[i] = buffer[i + 6];
					}
					idx = idx-6;
				}
				
				if (second == true) {
					for (int i = checkidx; i < idx - 6; i++) {
						buffer[i] = buffer[i + 6];
					}
					idx = idx-6;
					
				}
				// idx=idx+1;
				// buffer[idx++] = ((byte) ('\n'));
				first = false;
				second = false;
			}
			for (int i = 0; i < idx; i++) {
				// System.out.println(idx)

				out.write((char) buffer[i]);
			}

			if (byte_read == -1)
				return true;
			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';

		}
	}
}
/*
 * 
 * }
 */