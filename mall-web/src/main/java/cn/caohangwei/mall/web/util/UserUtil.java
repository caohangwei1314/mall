package cn.caohangwei.mall.web.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.caohangwei.mall.common.util.MD5Util;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserLoginInfo;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserUtil {

	private static AtomicInteger integer = new AtomicInteger(1);

	private static void createUser(int count) throws Exception{
//		List<UcenterUser> users = new ArrayList<UcenterUser>(count);
//		//生成用户
//		for(long i=1;i<=count;i++) {
//			UcenterUser user = new UcenterUser();
//			user.setId(i);
//			user.setPhone(String.valueOf(19941064000L + i));
//			users.add(user);
//		}
//		System.out.println("update user");
//		//插入数据库
//		Connection conn = DBUtil.getConn();
//		String sql = "update ucenter_user set phone = ? where id = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		for(int i=0;i<users.size();i++) {
//			UcenterUser user = users.get(i);
//			pstmt.setString(1, user.getPhone());
//			pstmt.setLong(2, user.getId());
//			pstmt.addBatch();
//		}
//		pstmt.executeBatch();
//		pstmt.close();
//		conn.close();
//		System.out.println("insert to db");
		//登录，生成token
		String urlString = "http://localhost:8989/ucenter/do_login";
		File file = new File("D:/tokens.txt");
		if(file.exists()) {
			file.delete();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		file.createNewFile();
		raf.seek(0);
		for(int i=1;i<=count;i++) {
			String phone = String.valueOf(19941064000L + i);
			URL url = new URL(urlString);
			HttpURLConnection co = (HttpURLConnection)url.openConnection();
			co.setRequestMethod("POST");
			co.setDoOutput(true);
			OutputStream out = co.getOutputStream();
			String params = "phone="+phone+"&password="+MD5Util.inputPassFromPass("asd88751685");
			out.write(params.getBytes());
			out.flush();
			InputStream inputStream = co.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte buff[] = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buff)) >= 0) {
				bout.write(buff, 0 ,len);
			}
			inputStream.close();
			bout.close();
			String response = new String(bout.toByteArray());
			JSONObject jo = JSON.parseObject(response);
			String token = jo.getString("data");
			System.out.println("create token : " + phone);

			String row = phone+","+token;
			raf.seek(raf.length());
			raf.write(row.getBytes());
			raf.write("\r\n".getBytes());
			System.out.println("write to file : " + phone);
		}
		raf.close();
		
		System.out.println("over");
	}
	
	public static void main(String[] args)throws Exception {
		createUser(5000);
	}
}
