package net.dasong.common;

import java.util.Map;

public class Constants {

	// 程序根目录
	public static final String ROOT_DIR = System.getProperty("user.dir");

	// 配置文件目录
	public static final String CFG_DIR = ROOT_DIR + "/cfg";

	// 配置文件目录
	public static final String DATA_DIR = ROOT_DIR + "/data";

	// 参数文件
	public static final String PARAM_FILENAME = CFG_DIR + "/params.properties";

	// sql文件
	public static final String SQL_FILENAME = CFG_DIR + "/sql.xml";

	public static final String SNIPPETS_FILE = DATA_DIR + "/UserSnippets.xml";

	// jdbc:oracle:thin:@172.16.26.116:1521:rac4
	public static String DB_URL;
	public static String DB_USER;
	public static String DB_PWD;

	// 数据库登录用户角色
	public static String DB_ROLE;

	// 预查询条数
	public static String DB_PREFETCH;

	// 邮件服务器主机名
	public static String MAIL_HOST_NAME;

	// 发送人邮箱地址
	public static String MAIL_SENDER;

	// 发件人别名
	public static String MAIL_SENDER_NAME;

	// 发送人邮箱用户名
	public static String MAIL_USER_NAME;

	// 发送人邮箱密码
	public static String MAIL_PASSWORD;

	// 收件人邮件地址
	public static String MAIL_RECIPIENT;

	// 收件人别名
	public static String MAIL_RECIPIENT_NAME;

	// sql语句map
	public static Map<String, String> SQL_MAP;
}
