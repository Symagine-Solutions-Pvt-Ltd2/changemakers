package com.lms.changemaker.utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Random;


import com.lms.changemaker.entity.Program;
import org.dom4j.DocumentException;
import com.lms.changemaker.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import static com.lms.changemaker.constants.IConstants.THYMLEAF_CERTIFICATE_TEMPLATE;
import static com.lms.changemaker.controller.AjaxRestController.certifyPath;


public class PDFUtil {


/*	private static String convertToXhtml(String html) throws UnsupportedEncodingException {
		Tidy tidy = new Tidy();
		tidy.setInputEncoding(UTF_8);
		tidy.setOutputEncoding(UTF_8);
		tidy.setXHTML(true);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parseDOM(inputStream, outputStream);
		return outputStream.toString(UTF_8);
	}*/

	private static String convertToXhtml(String html) throws UnsupportedEncodingException {
		Tidy tidy = new Tidy();
		tidy.setXHTML(true);
		tidy.setIndentContent(true);
		tidy.setPrintBodyOnly(true);
		tidy.setInputEncoding("UTF-8");
		tidy.setOutputEncoding("UTF-8");
		tidy.setSmartIndent(true);
		tidy.setShowWarnings(false);
		tidy.setQuiet(true);
		tidy.setTidyMark(false);

		Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

		OutputStream out = new ByteArrayOutputStream();
		tidy.pprint(htmlDOM, out);
		return out.toString();
	}


	public  File generatePdfFromHtml(Student student, Program program,int countModule, SpringTemplateEngine springTemplateEngine) throws IOException, DocumentException {
		//String outputFolder = certifyPath + File.separator + "thymeleaf.pdf";


		Context context = new Context();
		context.setVariable("name", student.getStudentFirstName()+" "+student.getStudentLastName());
		context.setVariable("programName", program.getProgramName());
		context.setVariable("countModule",countModule);
		context.setVariable("bg_image","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/certificate_background.jpg");
		// context.setVariable("footer","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/footer.png");
		// context.setVariable("line","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/line.png");
		context.setVariable("logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/logo.png");
		context.setVariable("certfied_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/certified_logo.PNG");
		context.setVariable("dev_sup_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/dev_sup_logo.PNG");
		context.setVariable("implemented_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/implemented_logo.PNG");
		String xHtml=springTemplateEngine.process(THYMLEAF_CERTIFICATE_TEMPLATE, context);

		ITextRenderer renderer = new ITextRenderer();
		String outputFolder=certifyPath+"Program_"+new Random().nextInt(999)+program.getProgId()+".pdf";

		renderer.setDocumentFromString(xHtml, FileSystems.getDefault()
				.getPath("")
				.toUri().toURL().toString());

		renderer.layout();

		OutputStream outputStream = new FileOutputStream(outputFolder);
		try {
			renderer.createPDF(outputStream);
		} catch (com.itextpdf.text.DocumentException e) {
			e.printStackTrace();
		}
		outputStream.close();

		return new File(outputFolder);
	}

/*
	public  File generatestaticPdfFromHtml(SpringTemplateEngine springTemplateEngine) throws IOException, DocumentException {
		//String outputFolder = certifyPath + File.separator + "thymeleaf.pdf";


		Context context = new Context();
		context.setVariable("name", "Anirudra Choudhury");
		context.setVariable("programName", "Test Programme");
		context.setVariable("countModule",10);
		context.setVariable("bg_image","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/certificate_background.jpg");
		context.setVariable("footer","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/footer.png");
		context.setVariable("line","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/line.png");
		context.setVariable("logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/logo.png");
		context.setVariable("certfied_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/certified_logo.PNG");
		context.setVariable("dev_sup_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/dev_sup_logo.PNG");
		context.setVariable("implemented_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/implemented_logo.PNG");
		String xHtml=springTemplateEngine.process(THYMLEAF_CERTIFICATE_TEMPLATE, context);

		ITextRenderer renderer = new ITextRenderer();
		String outputFolder="./Output/Program_"+new Random().nextInt(999)+".pdf";

		renderer.setDocumentFromString(xHtml, FileSystems.getDefault()
				.getPath("")
				.toUri().toURL().toString());

		renderer.layout();

		OutputStream outputStream = new FileOutputStream(outputFolder);
		try {
			renderer.createPDF(outputStream);
		} catch (com.itextpdf.text.DocumentException e) {
			e.printStackTrace();
		}
		outputStream.close();

		return new File(outputFolder);
	}
*/
}
