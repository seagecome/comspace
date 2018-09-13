package com.ethan.gap.biz.pdf;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class PdfHelper {
	private static String FONTS_PATH="/app/pai/conf/fonts_file/";
	private static final Logger logger = LoggerFactory.getLogger(PdfHelper.class);
	
	public static ITextRenderer getRender() throws DocumentException, IOException {
		ITextRenderer render = new ITextRenderer();
		render.getFontResolver().addFont(FONTS_PATH.concat("ARIALUNI.TTF"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		render.getFontResolver().addFont(FONTS_PATH.concat("SIMSUN.TTC"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		render.getFontResolver().addFont(FONTS_PATH.concat("SIMHEI.TTF"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		return render;
	}

	/**
	 * 获取要写入PDF的内容
	 * @param ftlPath
	 * @param ftlName
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String getPdfContent(String ftlPath, String ftlName, Object o) throws Exception {
		return useTemplate(ftlPath, ftlName, o);
	}

	/**
	 * 使用freemarker得到html内容
	 * @param ftlPath
	 * @param ftlName
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String useTemplate(String ftlPath, String ftlName, Object o) throws Exception {
		String html = null;
		Template tpl = getFreemarkerConfig(ftlPath).getTemplate(ftlName, "UTF-8");
		StringWriter writer = new StringWriter();
		tpl.process(o, writer);
		writer.flush();
		html = writer.toString();
		return html;
	}

	/**
	 * 获取Freemarker配置
	 * @param templatePath
	 * @return
	 * @throws IOException
	 */
	private static Configuration getFreemarkerConfig(String templatePath) throws IOException {
		Configuration config = new Configuration(Configuration.VERSION_2_3_23);
		config.setDirectoryForTemplateLoading(new File(templatePath));
		config.setEncoding(Locale.CHINA, "utf-8");
		return config;
	}
	
	/**
	 * 设置生成文件的字体
	 * @param fontsPath
	 */
	public static void setFontsPath(String fontsPath){
		logger.info("===fontsPath:" + fontsPath);
		if(StringUtils.isNotBlank(fontsPath)){
			FONTS_PATH = fontsPath;
		}
	}
}
