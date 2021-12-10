package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter2 extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank = 3; 
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isNotCS = false;    
        int byte_read = 0;
        int checkNum = 0;
        
        while(true) {          
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
               
                if(numOfBlank == checkBlank) {
                	checkNum = idx;
                }
                
            }      
            System.out.println((char)(buffer[checkNum-2]));
            if(buffer[checkNum-2] != 'C' && buffer[checkNum-1] != 'S') {
            	isNotCS = true;
            }
            
            if(isNotCS == true) {
                for(int i = 0; i<idx; i++) 
                	out.write((char)buffer[i]);
                isNotCS = false;
            }
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }  
}
