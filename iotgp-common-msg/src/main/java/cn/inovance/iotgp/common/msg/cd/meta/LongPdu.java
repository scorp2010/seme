package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class LongPdu extends PDU {

	private long value = 0;

	public LongPdu() {
		this.dataLenth = 4;
	}

	public LongPdu(long value) {
		this.dataLenth = 4;
		this.value = value;
	}

	public LongPdu(int startIndex, byte[] data) throws MessageParseException {
		this.dataLenth = 4;
		this.startIndex = startIndex;
		try {
			value = ByteOps.makeLong(data[startIndex], data[startIndex + 1],
					data[startIndex + 2], data[startIndex + 3]);
		} catch (Exception e) {
			throw new MessageParseException();
		}
	}

	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public int getLength() {

		return dataLenth;
	}

	@Override
	public byte[] getBytes() {

		return ByteOps.long2FourByte(value);
	}
}
