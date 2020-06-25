package com.qfdmy.commons.tools;

import javax.activation.MimetypesFileTypeMap;

/**
 * 图片工具
 * @author Lusifer
 * @since v1.0.0
 */
public class ImageTool {

	private static MimetypesFileTypeMap mime;

	static {
		mime = new MimetypesFileTypeMap();
		// 不添加下面的类型会造成误判
		// 详见：http://stackoverflow.com/questions/4855627/java-mimetypesfiletypemap-always-returning-application-octet-stream-on-android-e
		mime.addMimeTypes("image png tif jpg jpeg bmp");
	}

	/**
	 * 判断是否为图片
	 * @param filename {@code String} 文件名
	 * @return {@code boolean} 通过后缀名判断
	 */
	public static boolean isImage(String filename) {
		String contentType = mime.getContentType(filename);
		return "image".equals(contentType);
	}

}
