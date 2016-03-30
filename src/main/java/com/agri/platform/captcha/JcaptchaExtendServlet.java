package com.agri.platform.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class JcaptchaExtendServlet extends SimpleImageCaptchaServlet {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		// Set to expire far in the past.
		httpServletResponse.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		httpServletResponse.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		httpServletResponse.addHeader("Cache-Control",
				"post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		httpServletResponse.setHeader("Pragma", "no-cache");

		// return a jpeg
		httpServletResponse.setContentType("image/jpeg");

		service = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), new Engine(),180,100000,75000);
		// create the image with the text
		BufferedImage bi = service.getImageChallengeForID(httpServletRequest
				.getSession(true).getId());

		ServletOutputStream out = httpServletResponse.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
}