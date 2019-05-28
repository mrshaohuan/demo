
package com.ty.util.codec;

public interface Decoder {

    public abstract Object decode(Object obj)
            throws DecoderException;
}
