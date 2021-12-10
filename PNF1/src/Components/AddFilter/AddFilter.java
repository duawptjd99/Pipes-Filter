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

		while (true) {
			// check "12345,23456" on byte_read from student information
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

				if ((char) buffer[i] == '1' && (char) buffer[i + 1] == '2' && (char) buffer[i + 2] == '3'
						&& (char) buffer[i + 3] == '4' && (char) buffer[i + 4] == '5')
					first = true;

				else if ((char) buffer[i] == '2' && (char) buffer[i + 1] == '3' && (char) buffer[i + 2] == '4'
						&& (char) buffer[i + 3] == '5' && (char) buffer[i + 4] == '6')
					second = true;

			}
			
			
			if (idx != 0) {
				
				idx=idx-2;
				if (first == false) {

					buffer[idx++] = ((byte) (' '));
					buffer[idx++] = ((byte) ('1'));
					buffer[idx++] = ((byte) ('2'));
					buffer[idx++] = ((byte) ('3'));
					buffer[idx++] = ((byte) ('4'));
					buffer[idx++] = ((byte) ('5'));

				}
				if (second == false) {

					buffer[idx++] = ((byte) (' '));
					buffer[idx++] = ((byte) ('2'));
					buffer[idx++] = ((byte) ('3'));
					buffer[idx++] = ((byte) ('4'));
					buffer[idx++] = ((byte) ('5'));
					buffer[idx++] = ((byte) ('6'));

				}
			
				buffer[idx++] = ((byte)('\n'));
				first = false;
				second = false;
			}
			for (int i = 0; i < idx; i++) {
			
				 
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