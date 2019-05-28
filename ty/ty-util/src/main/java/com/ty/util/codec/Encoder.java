
package com.ty.util.codec;

public interface Encoder {

    public abstract Object encode(Object obj)
            throws EncoderException;
}
