package com.sh.lang.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

/**
 * 邮件服务
 * 1、配置发件人邮箱，发件人昵称；
 * 2、配置smtp
 * 3、添加收件人，抄送人，密送人
 * 4、添加邮件主题
 * 5、添加邮件正文
 */
public class EmailUtil {
	private Properties properties;
	private Session session;
	private MailEntity from; // 发件人
	private List<MailEntity> to = new ArrayList<MailEntity>(); // 收件人
	private List<MailEntity> copy = new ArrayList<MailEntity>(); // 抄送
	private List<MailEntity> bcc = new ArrayList<MailEntity>(); // 密送
	private List<File> attachMent = new ArrayList<File>();  // 附件

	private String authPwd; // 授权码

	private String mailTitle; // 邮件主题
	private String context; // 正文

	public EmailUtil(String smtpHost) {
		properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", smtpHost);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.connectiontimeout", "10000"); // 连接超时时间
		properties.setProperty("mail.smtp.timeout", "30000");	// 读超时时间
		

		session = Session.getInstance(properties);
	}

	/**
	 * 发邮件： 创建一个邮件对象（MimeMessage）； 设置发件人，收件人，可选增加多个收件人，抄送人，密送人； 设置邮件的主题（标题）；
	 * 设置邮件的正文（内容）； 设置显示的发送时间； 保存到本地。
	 */
	public void sendMail() throws Exception {
		if (StringUtils.isEmpty(from.getMailAddress())) {
			throw new Exception("发件人不能为空");
		}
		if (StringUtils.isEmpty(from.getNickName())) {
			throw new Exception("发件人昵称不能为空");
		}

		if (to == null || to.size() == 0) {
			throw new Exception("收件人不能为空");
		}

		if (StringUtils.isEmpty(mailTitle)) {
			throw new Exception("邮件标题不能为空");
		}

		// 创建邮件对象
		MimeMessage mimeMessage = createMimeMessage();

		// 获取邮件传输对象
		Transport transport = session.getTransport();
		transport.connect(from.getMailAddress(), authPwd);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
	}

	private MimeMessage createMimeMessage() throws Exception {
		MimeMessage mimeMessage = new MimeMessage(session);
		// 发件人
		if (StringUtils.isNotEmpty(from.getNickName())) {
			mimeMessage.setFrom(new InternetAddress(from.getMailAddress(), from
					.getNickName(), "UTF-8"));
		} else {
			mimeMessage.setFrom(new InternetAddress(from.getMailAddress()));
		}
		if (StringUtils.isEmpty(context)) {
			context = "";
		}

		// 收件人，
		InternetAddress[] addresses = new InternetAddress[to.size()];
		for (int i = 0; i < to.size(); i++) {
			MailEntity recEntity = to.get(i);
			addresses[i] = new InternetAddress(recEntity.getMailAddress(),
					recEntity.getNickName() == null ? ""
							: recEntity.getNickName());
		}
		mimeMessage.setRecipients(MimeMessage.RecipientType.TO, addresses);
		// 抄送人
		if (copy != null && copy.size() > 0) {
			InternetAddress[] copyAddresses = new InternetAddress[copy.size()];
			for (int i = 0; i < copy.size(); i++) {
				MailEntity copyEntity = copy.get(i);
				copyAddresses[i] = new InternetAddress(copyEntity.getMailAddress(),
						copyEntity.getNickName() == null ? ""
								: copyEntity.getNickName());
			}
			mimeMessage.setRecipients(MimeMessage.RecipientType.CC,
					copyAddresses);
		}

		// 密送人
		if (bcc != null && bcc.size() > 0) {
			InternetAddress[] bccAddresses = new InternetAddress[bcc.size()];
			for (int i = 0; i < bcc.size(); i++) {
				MailEntity bccEntity = bcc.get(i);
				bccAddresses[i] = new InternetAddress(bccEntity.getMailAddress(),
						bccEntity.getNickName() == null ? ""
								: bccEntity.getNickName());
			}
			mimeMessage.setRecipients(MimeMessage.RecipientType.BCC,
					bccAddresses);
		}
		
		// 主题
		mimeMessage.setSubject(mailTitle, "UTF-8");
		Multipart multipart = new MimeMultipart();
		// 正文
		if(StringUtils.isNotEmpty(context)){
			BodyPart contextBodyPart = new MimeBodyPart();
			contextBodyPart.setContent(context, "text/html; charset=utf-8");
			multipart.addBodyPart(contextBodyPart);
		}
		
		// 处理附件
		addAttachment(multipart);
		
		mimeMessage.setContent(multipart);
		mimeMessage.setSentDate(new Date());

		mimeMessage.saveChanges();
		return mimeMessage;
	}

	private void addAttachment(Multipart multipart) throws Exception
			 {
		if(attachMent != null && attachMent.size() > 0){
			for(File file : attachMent){
				// 拦截过大的文件
				if(file.length() > 50*1024*1024){
					throw new Exception("附件超过50M");
				}
				
				BodyPart attachmentBodyPart = new MimeBodyPart();
				FileDataSource dataSource = new FileDataSource(file);
				attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
				
				// 设置附件名称，解决中文乱码
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
				multipart.addBodyPart(attachmentBodyPart);
			}
		}
	}

	/**
	 * 添加收件人邮箱
	 */
	public void addReciverEmail(String mail, String nickName) {
		to.add(new MailEntity(mail, nickName));
	}

	/**
	 * 添加抄送人邮箱
	 */
	public void addCopyReciverEmail(String mail, String nickName) {
		copy.add(new MailEntity(mail, nickName));
	}

	/**
	 * 添加密送人邮箱
	 */
	public void addBccReciverEmail(String mail, String nickName) {
		bcc.add(new MailEntity(mail, nickName));
	}

	/**
	 * 添加附件
	 */
	public void addAttachment(String fileName) {
		attachMent.add(new File(fileName));
	}
	
	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	public MailEntity getFrom() {
		return from;
	}

	public void setFrom(MailEntity from) {
		this.from = from;
	}

	public String getAuthPwd() {
		return authPwd;
	}

	public void setAuthPwd(String authPwd) {
		this.authPwd = authPwd;
	}
	


	public static void main(String[] args) {

	}
}
