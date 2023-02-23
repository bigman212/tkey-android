package com.web3auth.tkey;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.web3auth.tkey.ThresholdKey.Common.PrivateKey;
import com.web3auth.tkey.ThresholdKey.GenerateShareStoreResult;
import com.web3auth.tkey.ThresholdKey.ServiceProvider;
import com.web3auth.tkey.ThresholdKey.StorageLayer;
import com.web3auth.tkey.ThresholdKey.ThresholdKey;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class tkeyGenerateShareStoreResultTest {
    static {
        System.loadLibrary("tkey-native");
    }

    private static GenerateShareStoreResult details;

    @BeforeClass
    public static void setupTest() {
        try {
            PrivateKey postboxKey = PrivateKey.generate();
            StorageLayer storageLayer = new StorageLayer(false, "https://metadata.tor.us", 2);
            ServiceProvider serviceProvider = new ServiceProvider(false, postboxKey.hex);
            ThresholdKey thresholdKey = new ThresholdKey(null, null, storageLayer, serviceProvider, null, null, false, false);
            PrivateKey key = PrivateKey.generate();
            thresholdKey.initialize(key.hex, null, false, false);
            tkeyGenerateShareStoreResultTest.details = thresholdKey.generateNewShare();
        } catch (RuntimeError e) {
            fail();
        }
    }

    @Test
    public void index() {
        try {
            assertNotEquals(details.getIndex().length(), 0);
        } catch (RuntimeError e) {
            fail();
        }
    }

    @Test
    public void shareStoreMap() {
        try {
            details.getShareStoreMap();
        } catch (RuntimeError e) {
            fail();
        }
    }
}