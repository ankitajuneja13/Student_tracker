package com.luv2code.web.jdbc;
public class Sendemaill {

		public static void sendd(String msg)
		{
			String from="abc@gmail.com";
			String pwd1="123456789";
			String sub="Monthly report card";
			String user="xyz@gmail.com";
		
			System.out.println("msg "+msg);
			Mail.send(from, pwd1, user, sub, msg);
			
		}
		

		
	}

