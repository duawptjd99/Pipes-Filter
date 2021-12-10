/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank = 1; 
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean is2013 = false;    
        int byte_read = 0;
        
        while(true) {          
        	// check "2013" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkBlank && buffer[0] == '2' && buffer[1] == '0' &&buffer[2] == '1' &&buffer[3] == '3' )
                	is2013 = true;
            }      
            if(is2013 == true) {
                for(int i = 0; i<idx; i++) 
                	out.write((char)buffer[i]);
                is2013 = false;
            }
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }  
}
