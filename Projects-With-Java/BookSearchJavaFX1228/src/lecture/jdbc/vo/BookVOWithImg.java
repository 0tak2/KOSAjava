package lecture.jdbc.vo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookVOWithImg {
	private String bisbn;
	private String btitle;
	private int bprice;
	private String bauthor;
	private String bimgbase64;
	private ImageView bimgview;
	
	public BookVOWithImg() {
	}

	public BookVOWithImg(String bisbn, String btitle, String bauthor, int bprice, String bimgbase64) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bprice = bprice;
		this.bimgbase64 = bimgbase64;
		setBimgviewFromBase64(bimgbase64.split(",")[1]);
	}
	
	private void setBimgviewFromBase64(String base64) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] imageByte = decoder.decode(base64);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);

			ImageView imgview = new ImageView(new Image(bis));
			this.bimgview = imgview;
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBisbn() {
		return bisbn;
	}

	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	
	public ImageView getBimgview() {
		return bimgview;
	}

	public void setBimgview(ImageView bimgview) {
		this.bimgview = bimgview;
	}

	public String getBimgbase64() {
		return bimgbase64;
	}

	public void setBimgbase64(String bimgbase64) {
		this.bimgbase64 = bimgbase64;
	}
}
