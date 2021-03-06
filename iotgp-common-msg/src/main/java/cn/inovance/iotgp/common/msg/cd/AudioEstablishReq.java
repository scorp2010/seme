package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class AudioEstablishReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	/** 安全码 */
	private StringPdu securityCode;
	/** 通道 1：单声道 2：立体音 */
	private ShortPdu channel = new ShortPdu();
	/** 采样配置信息 bit7-bit4:采用频率 bit3-bit0:采样位数 */
	private ShortPdu sampling = new ShortPdu();
	/** 通信方式 0 ：TCP，1：UDP */
	private ShortPdu connType = new ShortPdu();
	/** 是否广播 0：不广播1：广播 */
	private ShortPdu broadcast = new ShortPdu();
	/** MDDS服务器信息 */
	private ServerConnectionInfoPdu serverConnectionInfo = new ServerConnectionInfoPdu(
			"0.0.0.0", 0);

	public String getSecurityCode() {
		return securityCode.getValue();
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public ServerConnectionInfoPdu getServerConnectionInfo() {
		return serverConnectionInfo;
	}

	public void setServerConnectionInfo(
			ServerConnectionInfoPdu serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
	}

	public ShortPdu getChannel() {
		return channel;
	}

	public void setChannel(ShortPdu channel) {
		this.channel = channel;
	}

	public ShortPdu getSampling() {
		return sampling;
	}

	public void setSampling(ShortPdu sampling) {
		this.sampling = sampling;
	}

	public ShortPdu getConnType() {
		return connType;
	}

	public void setConnType(ShortPdu connType) {
		this.connType = connType;
	}

	public ShortPdu getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(ShortPdu broadcast) {
		this.broadcast = broadcast;
	}

	public void setSecurityCode(StringPdu securityCode) {
		this.securityCode = securityCode;
	}

	public AudioEstablishReq() {
		this.header.setMsgType(Commands.AUDIO_ESTABLISH_REQ);
	}
	
	public AudioEstablishReq(byte[] data) {
		super(data);
	}
	
	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, channel.getBytes(), index);
		index += channel.getLength();
		ByteOps.addByteArray(data, sampling.getBytes(), index);
		index += sampling.getLength();
		ByteOps.addByteArray(data, connType.getBytes(), index);
		index += connType.getLength();
		ByteOps.addByteArray(data, broadcast.getBytes(), index);
		index += broadcast.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		channel = new ShortPdu(index, data);
		index += channel.getLength();
		sampling = new ShortPdu(index, data);
		index += sampling.getLength();
		connType = new ShortPdu(index, data);
		index += connType.getLength();
		broadcast = new ShortPdu(index, data);
		index += broadcast.getLength();
		serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		index += serverConnectionInfo.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + SECURITY_LENGTH + channel.getLength()
				+ this.sampling.getLength() + connType.getLength()
				+ this.broadcast.getLength()
				+ this.serverConnectionInfo.getLength();
	}

	@Override
	public String toString() {
		return "AudioEstablishReq [securityCode=" + securityCode + ", channel="
				+ channel + ", sampling=" + sampling + ", connType=" + connType
				+ ", broadcast=" + broadcast + ", serverConnectionInfo="
				+ serverConnectionInfo + "]";
	}

}
