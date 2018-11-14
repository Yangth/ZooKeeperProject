package com.scme;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//##ServerScoekt服务端
public class ZkServerScoekt implements Runnable {
	private int port = 8080;

	private String zkRootNode="/member";

	public static void main(String[] args) throws IOException {
		int port = 8081;
		ZkServerScoekt server = new ZkServerScoekt(port);
		Thread thread = new Thread(server);
		thread.start();
	}

	public ZkServerScoekt(int port) {
		this.port = port;
	}

	public void zkregister(String NodeName,String NodeVule,String type){
		ZkClient zk=new ZkClient("127.0.0.1:2181", 60000, 1000);
		if (type.equals("eph")){
			if (zk.exists(NodeName))
				zk.delete(NodeName);
			zk.createEphemeral(NodeName,NodeVule);
		}else if (type.equals("per")){
			if (!zk.exists(NodeName))
			zk.createPersistent(NodeName,NodeVule);
			zk.close();
		}
	}

	public String getLocalhost() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString(); //获取本机ip
		return ip;
	}

	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			zkregister(zkRootNode,"member-server","per");
			zkregister(zkRootNode+"/server-"+port, getLocalhost()+":"+port,"eph");
			System.out.println("Server start port:" + port);
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e2) {

			}
		}
	}

}
