/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.CommonFilter;
import Framework.CommonFilterImpl;

public class SinkFilter extends CommonFilterImpl{


	public SinkFilter(int totalOut, int totalIn) {
		super(totalOut, totalIn);
	}
    
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;
        FileWriter fw = new FileWriter(this.FilePath);
        while(true) {
            byte_read = in.get(NoOut).read(); 
            if (byte_read == -1) {
            	 fw.close();
                 System.out.print( "::Filtering is finished; Output file is created." );  
                 return true;
            }
            fw.write((char)byte_read);
        }
    }

    
	
}
