package jlibrtpTest;

import java.net.DatagramSocket;

import jlibrtp.Participant;
import jlibrtp.RTPReceiverThread;
import jlibrtp.RTPSession;

public class test {
public RTPSession rtpSession = null;
	
	test() {
		DatagramSocket rtpSocket = null;
		DatagramSocket rtcpSocket = null;
		
		try {
			rtpSocket = new DatagramSocket(7002);
			rtcpSocket = new DatagramSocket(7003);
		} catch (Exception e) {
			System.out.println("RTPSession failed to obtain port");
		}
		
		
		rtpSession = new RTPSession(rtpSocket, rtcpSocket);
		
//		rtpSession.RTPSessionRegister(this,null,null);
		
		
		Participant p = new Participant("127.0.0.1", 7004, 7005);
		
		rtpSession.addParticipant(p);
	}
	
	public static void main(String[] args) {
		test t = new test();
		RTPReceiverThread rrt = new RTPReceiverThread(t.rtpSession);
		new Thread(rrt).start();
	}
}
