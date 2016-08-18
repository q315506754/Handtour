package com.handtours.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

 class UpYun {
	protected String bucketname = null;
	protected String username = null;
	protected String password = null;
	protected int timeout = 30 * 1000;
	public boolean debug = false;
	
	protected String _iwidth = null;
	protected String _iheight = null;
	protected String _iframes = null;
	protected String _itype = null;
	
	protected String _file_type = null;
	protected String _file_size = null;
	protected String _file_date = null;
	
	protected String content_md5 = null;
	protected String file_secret = null;
	
	protected String api_domain = "v0.api.upyun.com";
	
	public String version(){return "1.0.1";}
	/**
	* ??? UpYun ????
	* @param bucketname ????
	* @param username ?????
	* @param password ??
	* return UpYun object
	*/
	public UpYun(String bucketname, String username, String password) {
		this.bucketname = bucketname;
		this.username = username;
		this.password = md5(password);
	}

	/**
	* ?? API ?????
	* @param domain {?? v0.api.upyun.com ????, v1.api.upyun.com ??, v2.api.upyun.com ??, v3.api.upyun.com ??}
	* return null;
	*/
	public void setApiDomain(String domain) {
		this.api_domain = domain;
	}

	/**
	* ????????
	* @param time ?
	* return null;
	*/
	public void setTimeout(int time) {
		this.timeout = time*1000;
	}

	/**
	* ?? GMT ?????
	* return String;
	*/
	public String getGMTDate() {
		SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		formater.setTimeZone(TimeZone.getTimeZone("GMT"));
		return formater.format(new Date());
	}

	/**
	* MD5 ????
	* @param str ??????
	* return ??????;
	*/
	public static String md5(String str) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		md5.update(str.getBytes());
		byte[] encodedValue = md5.digest();
		int j = encodedValue.length;
		char finalValue[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte encoded = encodedValue[i];
			finalValue[k++] = hexDigits[encoded >> 4 & 0xf];
			finalValue[k++] = hexDigits[encoded & 0xf];
		}

		return new String(finalValue);
	}

	public static String md5(File file) throws Exception {
		FileInputStream is = new FileInputStream(file);
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			int n = 0;
			byte[] buffer = new byte[1024];
			do {
				n = is.read(buffer);
				if (n > 0) {
					md5.update(buffer, 0, n);
				}
			} while (n != -1);
			is.skip(0);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			is.close();
		}

		byte[] encodedValue = md5.digest();

		int j = encodedValue.length;
		char finalValue[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte encoded = encodedValue[i];
			finalValue[k++] = hexDigits[encoded >> 4 & 0xf];
			finalValue[k++] = hexDigits[encoded & 0xf];
		}

		return new String(finalValue);
	}

	/**
	* ???????????
	* @param conn
	* return ???;
	*/
	private String getText(HttpURLConnection conn, Boolean is_head_method) throws IOException {
		String text = "";
		_file_type = null;
		InputStream is = null;
		InputStreamReader sr = null;
		BufferedReader br = null;
		try {
			//is = conn.getInputStream();
			if (conn.getResponseCode() >= 400) {
				is = conn.getErrorStream();
			} else {
				is = conn.getInputStream();
			}
			if(!is_head_method){
			sr = new InputStreamReader(is);
			br = new BufferedReader(sr);
			String line = br.readLine();
			while (line != null) {
				text += "\n" + line;
				line = br.readLine();
			}
			}
			if(conn.getResponseCode() == 200 && conn.getHeaderField("x-upyun-width") != null){
				_iwidth = conn.getHeaderField("x-upyun-width");
				_iheight = conn.getHeaderField("x-upyun-height");
				_iframes = conn.getHeaderField("x-upyun-frames");
				_itype = conn.getHeaderField("x-upyun-file-type");
			}else{
				_iwidth = null;
				_iheight = null;
				_iframes = null;
				_itype = null;
			}

			if(conn.getResponseCode() == 200 && conn.getHeaderField("x-upyun-file-type") != null){
				_file_type = conn.getHeaderField("x-upyun-file-type");
				_file_size = conn.getHeaderField("x-upyun-file-size");
				_file_date = conn.getHeaderField("x-upyun-file-date");
			}else{
				_file_type = null;
				_file_size = null;
				_file_date = null;
			}
		} finally {
			if (br != null) {
				br.close();
			}
			if (sr != null) {
				sr.close();
			}
			if (is != null) {
				is.close();
			}
		}
		if(is_head_method){
			if (conn.getResponseCode() >= 400)
				return null;
			return "";
		}
		if (conn.getResponseCode() >= 400)
			throw new IOException(text);
		return text;
	}

	/**
	* ??????
	* @param conn ??
	* @param uri ????
	* @param length ????Body????
	* return ?????
	*/
	private String sign(HttpURLConnection conn, String uri, long length){
		String sign = conn.getRequestMethod() + "&" + uri + "&" + conn.getRequestProperty("Date") + "&" + length + "&" + password;
		////System.out.println(sign);
		////System.out.println("UpYun " + username + ":" + md5(sign));
		return "UpYun " + username + ":" + md5(sign);
	}

	/**
	* ??????
	* @param method ???? {GET, POST, PUT, DELETE}
	* @param uri ????
	* @param datas ???????????? null?
	* @param out_file ???????? null?
	* @param auto ????????(??10?)
	* return ?????????? null
	*/
	private String HttpAction(String method, String uri, byte[] datas, File out_file, Boolean auto){
		String result = null;
		boolean is_folder = false;
		try{
			if(datas.length == 11 && (new String(datas,0,11,"GBK")).equals("folder:true")){
				is_folder = true;
				datas = null;
			}
		}catch(Exception e){}
		try {
			URL url = new URL("http://"+ api_domain + uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
			conn.setRequestProperty("Date", getGMTDate());
			if(is_folder){
				if(auto)conn.setRequestProperty("mkdir", "true");
				conn.setRequestProperty("Folder", "true");
				conn.setDoOutput(true);
			}
			long length = 0;
			if(datas == null)
				conn.setRequestProperty("Content-Length", "0");
			else{
				length = datas.length;
				conn.setRequestProperty("Content-Length", String.valueOf(datas.length));
				if(this.content_md5 != null)conn.setRequestProperty("Content-Md5", this.content_md5);
				if(this.file_secret != null)conn.setRequestProperty("Content-Secret", this.file_secret);
				if(auto)conn.setRequestProperty("mkdir", "true");
				conn.setDoOutput(true);

				this.content_md5 = null;
				this.file_secret = null;
			}
			conn.setRequestProperty("Authorization", sign(conn, uri, length));
			try
			{
				conn.connect();
				if(datas != null){
					OutputStream os = conn.getOutputStream();
					try {
						os.write(datas);
						os.flush();
					} finally {
						if (os != null) {
							os.close();
						}
					}
				}
				if(is_folder){
					OutputStream os = conn.getOutputStream();
					os.flush();
				}
				if(out_file == null)
					result = getText(conn, method.equals("HEAD"));
				else{
					FileOutputStream out_stream = new FileOutputStream(out_file);
					result = "";
					InputStream is = conn.getInputStream();
					byte[] data = new byte[4096];
					int wl = 0;
					try {
						int temp=0;
						while((temp=is.read(data))!=-1){
							out_stream.write(data, 0, temp);
						}
					} finally {
						out_stream.flush();
						out_stream.close();
						is.close();
					}
				}
			} finally {
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			}
		} catch (IOException e) {
			if(debug)e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	* ??????
	* @param method ???? {GET, POST, PUT, DELETE}
	* @param uri ????
	* return ?????????? null
	*/
	private String HttpAction(String method, String uri){
		return HttpAction(method, uri, null, null, false);
	}

	/**
	* ????????????
	* @param path ????
	* return ?????????? -1
	*/
	public long getFolderUsage(String path) throws Exception {
		String result = HttpAction("GET", "/"+ bucketname + path + "/?usage");
		try {
			return Long.parseLong(result.trim());
		}catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	* ???????????
	* @param path ????
	* return ?????????? -1
	*/
	public long getBucketUsage() throws Exception {
		return getFolderUsage("");
	}

	/**
	* ???????? Content-MD5 ??????????????MD5?????????????? 406 Not Acceptable ???
	* @param String str ??? MD5 ????
	* return null;
	*/
	public void setContentMD5(String str){
		this.content_md5 = str;
	}

	/**
	* ???????? ?????????????????????????????URL??????? URL ???? ?????????+??? ?????
	* ?????????? ! ???? bac???????? /folder/test.jpg ??????????????? http://????/folder/test.jpg!bac
	* @param $str ??? MD5 ????
	* return null;
	*/
	public void setFileSecret(String str){
		this.file_secret = str;
	}

	/**
	* ????
	* @param file ???????????
	* @param byte[] datas ????
	* return true or false
	*/
	public boolean writeFile(String file, byte[] datas) throws Exception {
		return HttpAction("PUT", "/"+bucketname+file, datas, null, false) != null;
	}

	/**
	* ????
	* @param file ???????????
	* @param byte[] datas ????
	* @param auto ????????(??10?)
	* return true or false
	*/
	public boolean writeFile(String file, byte[] datas, Boolean auto) throws Exception {
		return HttpAction("PUT", "/"+bucketname+file, datas, null, auto) != null;
	}

	/**
	* ????
	* @param file ???????????
	* @param String datas ????
	* return true or false
	*/
	public boolean writeFile(String file, String datas) throws Exception {
		return HttpAction("PUT", "/"+bucketname+file, datas.getBytes(), null, false) != null;
	}

	/**
	* ????
	* @param file ???????????
	* @param String datas ????
	* @param auto ????????(??10?)
	* return true or false
	*/
	public boolean writeFile(String file, String datas, Boolean auto) throws Exception {
		return HttpAction("PUT", "/"+bucketname+file, datas.getBytes(), null, auto) != null;
	}

	/**
	* ????
	* @param file ???????????
	* @param File _file ?????
	* return true or false
	*/
	public boolean writeFile(String file, File _file, Boolean auto) throws Exception {
		FileInputStream is = new FileInputStream(_file);
		String result = "";
		try {
			URL url = new URL("http://"+ api_domain + "/"+bucketname+file);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("PUT");
			conn.setUseCaches(false);
			conn.setRequestProperty("Date", getGMTDate());
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", is.available()+"");
			if(this.content_md5 != null)conn.setRequestProperty("Content-Md5", this.content_md5);
			if(this.file_secret != null)conn.setRequestProperty("Content-Secret", this.file_secret);
			conn.setRequestProperty("Authorization", sign(conn, "/"+bucketname+file, is.available()));
			if(auto)conn.setRequestProperty("mkdir", "true");
			this.content_md5 = null;
			this.file_secret = null;
			try
			{
				conn.connect();

				OutputStream os = conn.getOutputStream();
				byte[] data = new byte[4096];
				int wl = 0;
				try {
					int temp=0;
					while((temp=is.read(data))!=-1){
						os.write(data, 0, temp);
					}
				} finally {
					os.flush();
					if (os != null) {
						os.close();
					}
				}

				result = getText(conn, false);
			} finally {
				is.close();
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			}
			return true;
		} catch (IOException e) {
			if(debug)e.printStackTrace();
			return false;
		} finally {
			is.close();
		}
	}

	/**
	* ????
	* @param file ???????????
	* @param FileInputStream fis ?????
	* return true or false
	*/
	public boolean writeFile(String file, FileInputStream fis, Boolean auto) throws Exception {
		FileInputStream is = fis;
		String result = "";
		try {
			URL url = new URL("http://"+ api_domain + "/"+bucketname+file);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("PUT");
			conn.setUseCaches(false);
			conn.setRequestProperty("Date", getGMTDate());
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", is.available()+"");
			if(this.content_md5 != null)conn.setRequestProperty("Content-Md5", this.content_md5);
			if(this.file_secret != null)conn.setRequestProperty("Content-Secret", this.file_secret);
			conn.setRequestProperty("Authorization", sign(conn, "/"+bucketname+file, is.available()));
			if(auto)conn.setRequestProperty("mkdir", "true");
			this.content_md5 = null;
			this.file_secret = null;
			try
			{
				conn.connect();

				OutputStream os = conn.getOutputStream();
				byte[] data = new byte[4096];
				int wl = 0;
				try {
					int temp=0;
					while((temp=is.read(data))!=-1){
						os.write(data, 0, temp);
					}
				} finally {
					os.flush();
					if (os != null) {
						os.close();
					}
				}

				result = getText(conn, false);
			} finally {
				is.close();
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			}
			return true;
		} catch (IOException e) {
			if(debug)e.printStackTrace();
			return false;
		} finally {
			is.close();
		}
	}

	/**
	* ????
	* @param file ???????????
	* @param File _file ?????
	* return true or false
	*/
	public boolean writeFile(String file, File _file) throws Exception {
		return writeFile(file, _file, false);
	}


	/**
	* ??????????????????????
	* @param $key ??????x-upyun-width?x-upyun-height?x-upyun-frames?x-upyun-file-type?
	* return value or NULL
	*/
	public String getWritedFileInfo(String key){
		if(_iwidth == null)return null;
		if(key.equals("x-upyun-width"))return _iwidth;
		if(key.equals("x-upyun-height"))return _iheight;
		if(key.equals("x-upyun-frames"))return _iframes;
		if(key.equals("x-upyun-file-type"))return _itype;
		return null;
	}

	/**
	* ????
	* @param file ???????????
	* return ???? ? null
	*/
	public String readFile(String file) throws Exception {
		return HttpAction("GET", "/"+bucketname+file);
	}

	/**
	* ????
	* @param file ???????????
	* @param _file ?????
	* return true or false
	*/
	public boolean readFile(String file, File _file) throws Exception {
		return HttpAction("GET", "/"+bucketname+file, null, _file, false) == "";
	}

	/**
	* ??????
	* @param file ???????????
	* return ???? ? null
	*/
	public Map getFileInfo(String file) throws Exception {
		HttpAction("HEAD", "/"+bucketname+file);
		if(_file_type == null)return null;
		Map<String,String> mp=new HashMap<String, String>();
		mp.put("type", _file_type);
		mp.put("size", _file_size);
		mp.put("date", _file_date);
		return mp;
	}

	/**
	* ????
	* @param file ???????????
	* return true or false
	*/
	public boolean deleteFile(String file) throws Exception {
		return HttpAction("DELETE", "/"+bucketname+file) != null;
	}

	/**
	* ????
	* @param path ???????????
	* return true or false
	*/
	public boolean rmDir(String path) throws Exception {
		return HttpAction("DELETE", "/"+bucketname+path) != null;
	}

	/**
	* ????
	* @param path ????
	* return true or false
	*/
	public boolean mkDir(String path) throws Exception {
		String a = "folder:true";
		return HttpAction("PUT", "/"+bucketname+path, a.getBytes(), null, false) != null;
	}

	/**
	* ????
	* @param path ????
	* @param auto ????????(??10?)
	* return true or false

	*/
	public boolean mkDir(String path, Boolean auto) throws Exception {
		String a = "folder:true";
		return HttpAction("PUT", "/"+bucketname+path, a.getBytes(), null, auto) != null;
	}

	/**
	* ??????
	* @param path ????
	* return List<FolderItem> ??? null
	*/
	public List<FolderItem> readDir(String path) throws Exception {
		String result = HttpAction("GET", "/"+ bucketname + path + "/");
		if(result == null)return null;
		List<FolderItem> list = new LinkedList<FolderItem>();
		String[] datas = result.split("\n");
		for(int i =0; i < datas.length ; i++)
			if(datas[i].indexOf("\t")>0)
			list.add(new FolderItem(datas[i]));
		return list;
	}

	public class FolderItem{
		public String name; /// ???
		public String type; /// ???? {file, folder}
		public long	size; /// ????
		public Date date; /// ????
		public FolderItem(String data){
			String[] a = data.split("\t");
			if(a.length == 4){
				this.name = a[0];
				this.type = (a[1].equals("N")?"File":"Folder");
				try {
					this.size = Long.parseLong(a[2].trim());
				}catch (NumberFormatException e) {
					this.size = -1;
				}
				long da = 0;
				try {
					da = Long.parseLong(a[3].trim());
				}catch (NumberFormatException e) {
				}
				this.date = new Date(da* 1000);
			}
		}
	}
}
