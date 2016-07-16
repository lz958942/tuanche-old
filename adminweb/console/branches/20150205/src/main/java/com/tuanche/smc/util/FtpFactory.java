package com.tuanche.smc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.tuanche.commons.util.Resources;



public class FtpFactory {
    
   /* public static FTPClient getConnection() throws Exception{
        FTPClient ftpClient = new ftpClient();
        System.out.println(Resources.getString("picFtpHost"));
        System.out.println(Integer.parseInt(Resources.getString("picFtpPort")));
        System.out.println(Resources.getString("picFtpAccount"));
        System.out.println(Resources.getString("picFtpPwd"));
        ftpClient.
        ftpClient.connect(Resources.getString("picFtpHost"),Integer.parseInt(Resources.getString("picFtpPort")));
        ftpClient.login(Resources.getString("picFtpAccount"), Resources.getString("picFtpPwd"));
        return ftpClient;
    }*/
    
    public static ChannelSftp connect() {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(Resources.getString("picFtpAccount"), Resources.getString("picFtpHost"), Integer.parseInt(Resources.getString("picFtpPort")));
            Session sshSession = jsch.getSession(Resources.getString("picFtpAccount"), Resources.getString("picFtpHost"), Integer.parseInt(Resources.getString("picFtpPort")));
            sshSession.setPassword(Resources.getString("picFtpPwd"));
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        return sftp;
    }
    
    public static void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            sftp.put(new FileInputStream(file), file.getName());
            fileInputStream.close();
            fileInputStream=null;
            file =null;
            sftp.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void upload(String directory, String uploadFile) {
        try {
            ChannelSftp sftp = connect();
            sftp .cd(directory);
            File file = new File(uploadFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            sftp.put(new FileInputStream(file), file.getName());
            fileInputStream.close();
            fileInputStream=null;
            file =null;
            sftp.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void close(FTPClient ftpClient){
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public static void main(String[] args){
    	try {
			FtpFactory.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
