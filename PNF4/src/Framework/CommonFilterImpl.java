/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public abstract class CommonFilterImpl implements CommonFilter {
	protected ArrayList<PipedInputStream> in = new ArrayList<PipedInputStream>();
	protected ArrayList<PipedOutputStream> out = new ArrayList<PipedOutputStream>();

	protected int NoIn;
	protected int NoOut;

	protected String FilePath = "";

	public CommonFilterImpl(int totalOut, int totalIn) {
		for (int i = 0; i < totalIn; i++) {
			in.add(i, new PipedInputStream());
		}
		for (int i = 0; i < totalOut; i++) {
			out.add(i, new PipedOutputStream());
		}
	}
	
	public void setFilePath(String string) {
		this.FilePath = string;
	}
	
	public void connectOutputTo(int noOut, CommonFilter nextFilter, int noIn) throws IOException {
		this.NoIn = noIn;
		this.NoOut = noOut;
		out.get(noOut).connect(nextFilter.getPipedInputStream().get(noIn));
	}

	public void connectInputTo(int noIn, CommonFilter previousFilter, int noOut) throws IOException {
		this.NoIn = noIn;
		this.NoOut = noOut;
		in.get(noIn).connect(previousFilter.getPipedOutputStream().get(noOut));
	}

	public ArrayList<PipedInputStream> getPipedInputStream() {
		return in;
	}

	public ArrayList<PipedOutputStream> getPipedOutputStream() {
		return out;
	}

	abstract public boolean specificComputationForFilter() throws IOException;

	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			closePorts();
		}
	}

	public void closePorts() {
		try {
			for (int i = 0; i < in.size(); i++) {
				in.get(i).close();
			}
			for (int i = 0; i < out.size(); i++) {
				out.get(i).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
