package com.sh.lang.email;
public class MailEntity {
		private String mailAddress; // 邮箱地址
		private String nickName; // 昵称

		public MailEntity(String mailAddress, String nickName) {
			this.mailAddress = mailAddress;
			this.nickName = nickName;
		}

		public String getMailAddress() {
			return mailAddress;
		}

		public void setMailAddress(String mailAddress) {
			this.mailAddress = mailAddress;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
	}