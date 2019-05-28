package com.ty.util.codec;

public interface BinaryDecoder extends Decoder {

	public abstract byte[] decode(byte abyte0[]) throws DecoderException;
}
