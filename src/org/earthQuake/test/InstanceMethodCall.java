package org.earthQuake.test;

class InstanceMethodCall {
	private native void nativeMethod();
	static {
		System.loadLibrary("InstanceMethodCall");
	}
	public static void main(String[] args) {
//		InstanceMethodCall c = new InstanceMethodCall();
//		c.nativeMethod();
		(new InstanceMethodCall()).nativeMethod();
	}
	private void callback() {
		System.out.println("In Java: callback()");
	}
}
