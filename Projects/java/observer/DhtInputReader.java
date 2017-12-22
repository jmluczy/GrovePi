package grovepi.observer;
/*
 * **********************************************************************
 * PROJECT       :  GrovePi Java Library
 *
 * This file is part of the GrovePi Java Library project. More information about
 * this project can be found here:  https://github.com/DexterInd/GrovePi
 * **********************************************************************
 * 
 * ## License
 * 
 * The MIT License (MIT)
 * GrovePi for the Raspberry Pi: an open source platform for connecting Grove Sensors to the Raspberry Pi.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.dexterind.grovepi.sensors.DHTDigitalSensor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * An instance of this class reads sensor data from a GrovePi digital sensor and
 * updates an observer with this data.
 * 
 * @author James Luczynski
 * @author Jeff Blankenship
 */
public class DhtInputReader extends InputSensorReader implements Runnable{
    private DHTDigitalSensor sensor;    
    
    /**
     * Constructor
     * @param pin the GrovePi port number the sensor is plugged into
     * @param moduleType
     * @param scale
     * @throws IOException
     * @throws InterruptedException
     * @throws Exception 
     */
    public DhtInputReader(int pin, int moduleType, int scale) throws IOException, InterruptedException, Exception{
        this.pin = pin;
        sensor = new DHTDigitalSensor(pin,moduleType,scale);
        readDelay = 125;
    }    
    /**
     * Reads the values of the sensor, and updates this object's observers
     * if the values have changed from the last reading.
     */
    @Override
    public void run() {
        byte[] bytes = new byte[8];
        System.out.println("Trying to read:");
        float[] newData = sensor.read();
        System.out.println( newData[0] + ":" + newData[1] );
        
        //create byte[] from the two floats
        //public void convertFloatToByteArray(float f, byte[] b, int offset) 
        ByteBuffer.wrap(bytes, 0, 4).order(ByteOrder.LITTLE_ENDIAN).putFloat( newData[0] );
        ByteBuffer.wrap(bytes, 4, 4).order(ByteOrder.LITTLE_ENDIAN).putFloat( newData[0] );

        
        System.out.println("Hello from in the run");
        
        System.out.println("byte array length: " + bytes.length);
        notifyObservers(bytes);
         
    }
    /**
     * Begins reading sensor values.
     */
    public void startReading(){
        System.out.println("Hello startReading. FROM INSIDE DHTInputReader");
        super.startReading(this);
    }    
}
