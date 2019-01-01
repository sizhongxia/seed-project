package com.company.project.unit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成和读的工具类
 *
 */
public class QRCodeUtil {

	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param outputStream
	 *            文件输出流路径
	 * @param content
	 *            二维码携带信息
	 * @param qrCodeSize
	 *            二维码图片大小
	 * @param imageFormat
	 *            二维码的格式
	 * @throws WriterException
	 * @throws IOException
	 */
	public static byte[] createQrCodeBytes(String content, int qrCodeSize) throws WriterException, IOException {
		// 设置二维码纠错级别ＭＡＰ
		Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.MARGIN, 2);
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q); // 矫错级别
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
		int CrunchifyWidth = byteMatrix.getWidth();
		// BufferedImage的主要作用就是将一副图片加载到内存中
		BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < CrunchifyWidth; i++) {
			for (int j = 0; j < CrunchifyWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "gif", os);
		return os.toByteArray();
	}

}
