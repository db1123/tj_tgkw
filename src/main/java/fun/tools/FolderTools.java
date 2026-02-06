package fun.tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FolderTools {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	//获取当前月份 
	public static String getNowMonth()
	{
		return df.format(new Date()).substring(0, 7);
	}
	
	//获取当前周 
	public static String getNowWeek()
	{
		Calendar c = Calendar.getInstance();
		int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周 
		return df.format(new Date()).substring(0, 7) + "_" + week + "W";
	}
	
	/**
	 * 按月份检查文件夹是否存在(不存在自动创建) 
	 * @param path (例如：Z:) 
	 * @param filename
	 * @param wm(w:星期 m:月份)
	 * @return
	 */
	public static String CheckMonthFolder(String path, String filename, String wm)
	{
		String temp = "";
		GB2Alpha gb2a = new GB2Alpha();
		//检查并判断是否创建索引文件夹 
		temp = path + gb2a.String2AlphaFirst(filename, "s");
		File indexFile =new File(temp);
		if(!indexFile .exists()  && !indexFile .isDirectory()) //如果文件夹不存在则创建 
		{
			indexFile.mkdir();
		}
		//检查并判断是否创建月度文件夹 
		if(wm.equals("m"))
		{
			temp += "//" + getNowMonth();
		}else{
			temp += "//" + getNowWeek();
		}
		File monthFile =new File(temp);
		if(!monthFile .exists()  && !monthFile .isDirectory()) //如果文件夹不存在则创建 
		{
			monthFile.mkdir();
		}
		return temp + "//";
	}
}
