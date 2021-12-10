/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonFilter;
import Framework.CommonFilterImpl;

public class SourceFilter extends CommonFilterImpl {
	

	public SourceFilter(int totalOut, int totalIn) {
		super(totalOut, totalIn);
	}

	@Override
	public boolean specificComputationForFilter() throws IOException {
		int byte_read;
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(this.FilePath)));
		while (true) {
			byte_read = br.read();
			if (byte_read == -1) {
				return true;
			}
			out.get(NoOut).write(byte_read);

		}
	}

}
