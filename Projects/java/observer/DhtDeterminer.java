package grovepi.observer;

import com.dexterind.grovepi.sensors.DHTDigitalSensor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import static java.util.Arrays.copyOfRange;

// <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
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
/**
 * RotaryAngleDeterminer. This GrovePi sensor demonstration is part of
 * a coursework project for CS505 Design Patterns, at Central
 * Connecticut State University, Fall 2017, with Dr. Chad Williams.
 *
 * @author James Luczynski
 * @author Jeff Blankenship
 * @author Chad Williams
 */
// </editor-fold>

public class DhtDeterminer implements InputSensorObserver{
    //For scale:  C=0, F=1
    
    private DHTDigitalSensor dhtSensor;
    private DhtInvoker invoker;
    private int scale;
    private double temperature;
    private double humidity;
    
    
    public DhtDeterminer(DhtInvoker invoker){
        this.invoker = invoker;
    }

    @Override
    public void update(byte[] dhtData) {
        System.out.println("hello from inside update");
        
        ByteBuffer buffer = ByteBuffer.wrap(dhtData);
        
        
        System.out.println("chugga chugga");
        this.temperature = buffer.getFloat();
        //this.temperature = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();   
        
        System.out.println("temp: " + temperature);
        //bytes = copyOfRange(dhtData,4,7);
        //this.humidity = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();  
        System.out.println("humidity: " + humidity);
        invoker.DhtInvoker(this.temperature, this.humidity);
        
    }
	
    

    /**
     * Changes this instance's Invoker object to the one in the argument provided.
     * @param invoker 
     */
    public void setInvoker(DhtInvoker invoker){
        this.invoker = invoker;
    }

    
}
