package com.ournews.commons.utils;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 邮箱工具类
 * @author ljs
 * @time 2018-10-28
 */
public class SendEmailUtil {
//    //邮箱 授权码
    private String username = null;
    private String password = null;
    private Authenticator auth = null;

    private MimeMessage mimeMessage =null;
    //配置邮箱的键值对
    private Properties pros = null;
    //发送附件部分
    private Multipart multipart = null;
    private BodyPart bodypart= null;
    /**
     * 初始化账号密码并验证
     * 创建MimeMessage对象
     * 发送邮件必须的步骤:1
     * @param username  邮箱号
     * @param password  授权码
     */
    public SendEmailUtil(String username, String password){
        this.username = username;
        this.password = password;
    }
    /**
     * 初始化MimeMessage对象
     * 发送邮件必须的步骤:3
     */
    public void initMessage(){
        this.auth = new Email_Autherticator();
        Session session = Session.getDefaultInstance(pros,auth);
        session.setDebug(true); //设置获取 debug 信息
        mimeMessage = new MimeMessage(session);
    }
    /**
     * 设置email系统参数
     * 接收一个map集合key为string类型，值为String
     * 发送邮件必须的步骤:2
     * @param map
     */
    public void setPros(Map<String,String> map){
        pros = new Properties();
        for(Map.Entry<String,String> entry:map.entrySet()){
            pros.setProperty(entry.getKey(), entry.getValue());
        }
    }
    /**
     * 验证账号密码
     * 发送邮件必须的步骤
     * 重写Authenticator中的验证密码的getPasswordAuthentication()的方法
     * @author Administrator
     *
     */
    public class Email_Autherticator extends Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(username, password);
        }
    }
    /**
     * 设置发送邮件的基本参数(去除繁琐的邮件设置)
     * @param sub 设置邮件主题
     * @param text 设置邮件文本内容
     * @param rec 设置邮件接收人
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void setDefaultMessagePros(String sub,String text,String rec) throws MessagingException, UnsupportedEncodingException {
        mimeMessage.setSubject(sub);
        mimeMessage.setText(text);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
        mimeMessage.setSentDate(new Date());
        mimeMessage.setFrom(new InternetAddress(username,username));
    }
    /**
     * 设置主题
     * @param subject
     * @throws MessagingException
     */
    public void  setSubject(String subject) throws MessagingException{
        mimeMessage.setSubject(subject);
    }
    /**
     * 设置日期
     * @param date
     * @throws MessagingException
     */
    public void  setDate(Date date) throws MessagingException{
        mimeMessage.setSentDate(new Date());
    }
    /**
     * 设置邮件文本内容
     * @param text
     * @throws MessagingException
     */
    public void setText(String text) throws MessagingException{
        mimeMessage.setText(text);
    }
    /**
     * 设置邮件头部
     * @param arg0
     * @param arg1
     * @throws MessagingException
     */
    public void setHeader(String arg0,String arg1) throws MessagingException{
        mimeMessage.setHeader(arg0, arg1);
    }
    /**
     * 设置邮件接收人地址 <单人发送>
     * @param recipient
     * @throws MessagingException
     */
    public void setRecipient(String recipient) throws MessagingException{
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }
    /**
     * 设置邮件接收人地址 <多人发送>
     * @param
     * @throws MessagingException
     * @throws AddressException
     */
    public String setRecipients(List<String> recs) throws AddressException, MessagingException{
        if(recs.isEmpty()){
            return "接收人地址为空!";
        }
        for(String str:recs){
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(str));
        }
        return "加入接收人地址成功!";
    }
    /**
     * 设置邮件接收人地址 <多人发送>
     * @param
     * @throws MessagingException
     * @throws AddressException
     */
    @SuppressWarnings("static-access")
    public String setRecipients(StringBuffer sb) throws AddressException, MessagingException{
        if(sb==null||"".equals(sb)){
            return "字符串数据为空!";
        }
        Address []address = new InternetAddress().parse(sb.toString());
        mimeMessage.addRecipients(Message.RecipientType.TO, address);
        return "收件人加入成功";
    }
    /**
     * 设置邮件发送人的名字
     * @param from
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void setFrom(String from) throws UnsupportedEncodingException, MessagingException{
        mimeMessage.setFrom(new InternetAddress(username,from));
    }
    /**
     * 发送邮件<单人发送>
     * return 是否发送成功
     * @throws MessagingException
     */
    public String sendMessage() throws MessagingException{
        Transport.send(mimeMessage);
        return "success";
    }
    /**
     * 设置附件
     * @param file 发送文件的路径
     */
    public void setMultipart(String file) throws MessagingException, IOException {
        if(multipart==null){
            multipart = new MimeMultipart();
        }
        multipart.addBodyPart(writeFiles(file));
        mimeMessage.setContent(multipart);
    }
    /**
     * 设置附件<添加多附件>
     * @param fileList<接收List集合>
     * @throws MessagingException
     * @throws IOException
     */
    public void setMultiparts(List<String> fileList) throws MessagingException, IOException{
        if(multipart==null){
            multipart = new MimeMultipart();
        }
        for(String s:fileList){
            multipart.addBodyPart(writeFiles(s));
        }
        mimeMessage.setContent(multipart);
    }
    /**
     * 发送文本内容，设置编码方式
     * <方法与发送附件配套使用>
     * <发送普通的文本内容请使用setText()方法>
     * @param s
     * @param type
     * @throws MessagingException
     */
    public void setContent(String s,String type) throws MessagingException{
        if(multipart==null){
            multipart = new MimeMultipart();
        }
        bodypart = new MimeBodyPart();
        bodypart.setContent(s, type);
        multipart.addBodyPart(bodypart);
        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();
    }
    /**
     * 读取附件
     * @param filePath
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    public BodyPart writeFiles(String filePath)throws IOException, MessagingException{
        File file = new File(filePath);
        if(!file.exists()){
            throw new IOException("文件不存在!请确定文件路径是否正确");
        }
        bodypart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(file);
        bodypart.setDataHandler(new DataHandler(dataSource));
        //文件名要加入编码，不然出现乱码
        bodypart.setFileName(MimeUtility.encodeText(file.getName()));
        return bodypart;
    }

    public static String getCode(){
        String[] beforeShuffle= new String[] { "1","2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString().substring(3, 9);
    }
    public static String SendEmail(String email) throws MessagingException, UnsupportedEncodingException {
        Map<String,String> map= new HashMap<String,String>();
        SendEmailUtil mail = new SendEmailUtil("252048917@qq.com","lospnsbmluivbgif");
        // 设置邮箱发送的基本配置
        map.put("mail.smtp.host", "smtp.qq.com");
        map.put("mail.smtp.auth", "true");
        //是否SSL加密
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //qq邮箱端口号
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        mail.setRecipient(email );
        mail.setSubject("注册验证");
        String code = getCode();
        mail.setText("欢迎注册我的网站,你的邮箱已经被验证成功,验证码为 ："+ code + ";\n 使用该验证码即可实现我的网站的注册");
        mail.setDate(new Date());
        mail.setFrom("www.lijunsai.cn");
        mail.sendMessage();
        return code;
    }

}
