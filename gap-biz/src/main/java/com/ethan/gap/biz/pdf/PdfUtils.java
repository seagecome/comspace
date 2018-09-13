package com.ethan.gap.biz.pdf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class PdfUtils {
	private static final Logger logger = LoggerFactory.getLogger(PdfUtils.class);
	
	/**
	 * 生成pdf文件
	 * @param ftlPath     模板文件路径
	 * @param ftlName     模板文件名
	 * @param data        模板填充数据
	 * @param outputPath  输出文件路径
	 * @param outputName  输出文件名
	 * @return
	 * @throws Exception
	 */
	public static boolean generateToPdfFile(String ftlPath, String ftlName, Object data, String outputPath, String outputName) throws Exception {
		//把freemarker中变量用data中的数据进行填充，获取填充后的文件内容字符串
		String pdfContent=PdfHelper.getPdfContent(ftlPath, ftlName, data);
		logger.info("===pdfContent==null:" + StringUtils.isBlank(pdfContent));
		try {
			OutputStream out = null;
			ITextRenderer render = null;
			
			//判断文件路径是否已存在
			File file =new File(outputPath);  
			if(!file.exists()  && !file.isDirectory()){
			    file.mkdir();    
			}	
			out = new FileOutputStream(outputPath.concat(outputName).concat(".pdf"));
			render = PdfHelper.getRender();
			render.setDocumentFromString(pdfContent);
			render.layout();
			render.createPDF(out);
			render.finishPDF();
			render = null;
			out.close();
			logger.info("===pdf文件已成功生成===");
		} catch (Exception e) {
			logger.error("===generateToPdfFile="+e.getMessage(), e);
			return false;
		}	
		return true;
	}
	
	/**
	 * 生成html文件
	 * @param ftlPath     模板文件路径
	 * @param ftlName     模板文件名
	 * @param data        模板填充数据(ftl文件中的填充数据)
	 * @param outputPath  输出文件路径
	 * @param outputName  输出文件名
	 * @return
	 * @throws Exception
	 */
	public static boolean generateToHtmlFile(String ftlPath, String ftlName, Object data, String outputPath, String outputName) throws Exception {
		FileWriter fw =  null;
		BufferedWriter bw = null;
		try {
			String wholeHtmlFile = outputPath.concat(outputName).concat(".html");
			String htmlContent = PdfHelper.getPdfContent(ftlPath, ftlName, data);
			logger.info("===htmlContent==null:" + StringUtils.isBlank(htmlContent));
			
			File file = new File(wholeHtmlFile);
			if(!file.exists()  && !file.isDirectory()){
			    file.mkdir();    
			}
			
			file.createNewFile();
			fw = new FileWriter(wholeHtmlFile, false);
            bw = new BufferedWriter(fw);
            bw.write(htmlContent);
            bw.flush();
            logger.info("===html文件已成功生成===");
        } catch (Exception e) {
            logger.error("===generateToHtmlFile="+e.getMessage(), e);
            return false;
        } finally{
        	if(fw != null){
        		fw.close();
        	}
        	if(bw != null){
        		bw.close();
        	}
        }
		return true;
    }
	
	/**
	 * 生成PDF到输出流中（ServletOutputStream用于下载PDF）
	 * @param ftlPath   模板文件路径
	 * @param ftlName   模板文件名
	 * @param data      模板填充数据(ftl文件中的填充数据)
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static OutputStream generateToServletOutputStream(String ftlPath, String ftlName, Object data,HttpServletResponse response) throws Exception{
		String downloadContent=PdfHelper.getPdfContent(ftlPath, ftlName, data);
		logger.info("===downloadContent==null:" + StringUtils.isBlank(downloadContent));
		
		OutputStream out = null;
		ITextRenderer render = null;
		out = response.getOutputStream();
		render = PdfHelper.getRender();
		render.setDocumentFromString(downloadContent);
		render.layout();
		render.createPDF(out);
		render.finishPDF();
		render = null;
		logger.info("===pdf已成功写入输出流===");
		return out;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> sourceMap = new HashMap<String, String>();
		sourceMap.put("proNo","PAI20180101001");
		sourceMap.put("userName","令狐冲");
		sourceMap.put("certNo","HS0001");
		try {
			PdfHelper.setFontsPath("D:\\filegen\\fonts\\");
			generateToPdfFile("D:\\filegen\\ftlspace\\", "selfprotocol.ftl", sourceMap, "D:\\filegen\\createspace\\", "selfprotocol");
			generateToHtmlFile("D:\\filegen\\ftlspace\\", "selfprotocol.ftl", sourceMap, "D:\\filegen\\createspace\\", "selfprotocol");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
