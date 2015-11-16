package com.test.util;

import java.io.Serializable;

public class Token implements Serializable {
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -754659525548951914L;
	private String signature;
	private long timestamp;
	
	public Token(String signature, long timestamp) {
		if (signature == null)
			throw new IllegalArgumentException("signature can not be null");
		
		this.timestamp = timestamp;
		this.signature = signature;
	}
	
	public Token(String signature) {
		if (signature == null)
			throw new IllegalArgumentException("signature can not be null");
		
		this.signature = signature;
	}
	
	/**
	 * Returns a string containing the unique signatureentifier assigned to this token.
	 */
	public String getSignature() {
		return signature;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * timestamp 不予考虑, 因为就算 timestamp 不同也认为是相同的 token.
	 */
	public int hashCode() {
		return signature.hashCode();
	}
	
	public boolean equals(Object object) {
		if (object instanceof Token)
			return ((Token)object).signature.equals(this.signature);
		return false;
	}

	@Override
	public String toString() {
		return "Token [signature=" + signature + ", timestamp=" + timestamp
				+ "]";
	}
	
	
}

