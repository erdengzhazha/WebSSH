package com.ovopark.webssh;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import cn.objectspace.webssh.constant.ConstantPool;
import cn.objectspace.webssh.util.SFTPChannel;
import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFTPTest {

    public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    public static Logger log = LoggerFactory.getLogger(SFTPTest.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SFTPTest test = new SFTPTest();

        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(ConstantPool.SFTP_REQ_HOST, "172.16.22.36");
        sftpDetails.put(ConstantPool.SFTP_REQ_USERNAME, "root");
        sftpDetails.put(ConstantPool.SFTP_REQ_PASSWORD, "wdz!!2020");
        sftpDetails.put(ConstantPool.SFTP_REQ_PORT, "22");

        String src = "D:\\DevSoft\\HB-SnagIt1001.rar"; // 本地文件名
        String dst = "/home/omc/ylong/sftp/HB-SnagIt1001.rar"; // 目标文件名

        SFTPChannel channel = test.getSFTPChannel();
        System.out.println(channel);
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
        Vector v = chSftp.ls("/");
        // 列出服务器指定的文件列表
        for (int i = 0; i < v.size(); i++) {
            String FileNames = v.get(i).toString().substring(v.get(i).toString().lastIndexOf(" ") + 1);

            log.info("source:{},now :{}",v.get(i).toString(),FileNames);
//            String wasFilePath = WASPath + "/" + FileNames;
//            String localFilePath = LOCALPath + "/" + FileNames;
//            try {//如果可以cd说明是个文件目录，则递归遍历
//                sftp.cd(wasFilePath);
//                downFile(localFilePath,wasFilePath);
//                sftp.rmdir(wasFilePath);
//            } catch (SftpException e) {//如果出现异常则说明是个文件，直接下载
//                FileOutputStream out = new FileOutputStream(new File(localFilePath));
//                sftp.get(wasFilePath, out);
//                sftp.rm(wasFilePath);
//                out.close();
//                log.info("=============【下载文件:"+wasFilePath+"成功】===================");
//            }
        }
        /**
         * 代码段1
         OutputStream out = chSftp.put(dst, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
         byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
         int read;
         if (out != null) {
         System.out.println("Start to read input stream");
         InputStream is = new FileInputStream(src);
         do {
         read = is.read(buff, 0, buff.length);
         if (read > 0) {
         out.write(buff, 0, read);
         }
         out.flush();
         } while (read >= 0);
         System.out.println("input stream read done.");
         }
         **/

//        chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2

        // chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // 代码段3

        chSftp.quit();
        channel.closeChannel();
    }
}