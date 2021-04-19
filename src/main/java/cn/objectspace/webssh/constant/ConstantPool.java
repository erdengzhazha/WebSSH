package cn.objectspace.webssh.constant;

/**
* @Description: 常量池
* @Author: NoCortY
* @Date: 2020/3/8
*/
public class ConstantPool {
    /**
     * 随机生成uuid的key名
     */
    public static final String USER_UUID_KEY = "user_uuid";
    /**
     * 发送指令：连接
     */
    public static final String WEBSSH_OPERATE_CONNECT = "connect";
    /**
     * 发送指令：命令
     */
    public static final String WEBSSH_OPERATE_COMMAND = "command";

    public static final String SFTP_REQ_HOST = "root";
    public static final String SFTP_REQ_PORT = "port";
    public static final String SFTP_REQ_USERNAME = "username";
    public static final String SFTP_REQ_PASSWORD = "password";
    public static final int SFTP_DEFAULT_PORT = 22;
    public static final String SFTP_REQ_LOC = "location";
}
