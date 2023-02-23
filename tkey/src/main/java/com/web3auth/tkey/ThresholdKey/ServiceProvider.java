package com.web3auth.tkey.ThresholdKey;

import com.web3auth.tkey.RuntimeError;

public final class ServiceProvider {
    private native void jniServiceProviderFree();

    private native long jniServiceProvider(boolean enableLogging, String postboxKey, String curveN, RuntimeError error);

    final static String curveN = "fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141";

    final long pointer;

    public ServiceProvider(boolean enableLogging, String postboxKey) throws RuntimeError {
        RuntimeError error = new RuntimeError();
        long ptr = jniServiceProvider(enableLogging, postboxKey, curveN, error);
        if (error.code != 0) {
            throw error;
        }
        pointer = ptr;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        jniServiceProviderFree();
    }
}