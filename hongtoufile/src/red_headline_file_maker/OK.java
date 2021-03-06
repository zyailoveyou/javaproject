package red_headline_file_maker;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
public class OK {
	
	//定义初始的起始坐标
    public static int startXlocationTEXT1 = 471;
    public static int startYlocationTEXT1 = 300;
    public static int startXlocationTEXT2 = 471;
    public static int startYlocationTEXT2 = 533;
    
    
    public static int startXlocationTEXT1Single = 450;
    public static int startYlocationTEXT1Single = 400;
    
    
    public static int XareaForDoubleText = 2080;
    public static int XareaForSingleText = 2110;
    
    public static String CopTitle1;
    public static String CopTitle2;
    
    
    public static int wordheight;
    public static int wordwidth;
    
    public static BufferedImage bi;
    
    
	
	public static void main(String[] args) {
		
		
	    bi = new BufferedImage(3400, 4676, BufferedImage.TYPE_INT_BGR);
	    
	    final File file = new File("c:\\javaPic.png");
		
	    try {
	    	if(file.exists()) {
		    	file.delete();
		    	file.createNewFile();
	    	}
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	    
        System.out.println("初始化完毕，准备写入");
        
        
        //读取输入
        ReadInPutword();
        
        
        if (CopTitle2.equals("")) {
			
     	   //生成图片
    		writeImageSingle(bi, "png", file);
    		
    		//生成图片
        	
		}
        
        else {
			
        	//生成图片
        	writeImageDouble(bi, "png", file);
        	//生成图片
        	
		}
        
       
		System.out.println("绘图成功");


	}
	
	
	
	
	public static void ReadInPutword() {
		// TODO 自动生成的方法存根
    	
	    BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    	
    	  System.out.print("请输入第一个公司的名称（注意不要输入有限公司4个字） :" + "\n");
          
          
			try {
				CopTitle1 = bufferedReader.readLine();
				System.out.println("你输入的第一个公司的名称是 :" + CopTitle1 + "\n");
				
			} catch (IOException e) {
				
				System.out.println("第一个公司读写流错误" + "\n");
				e.printStackTrace();
			}
			
			System.out.println("请输入第二公司的名称（如没有请直接回车）" + "\n");
			
			try {
				
				CopTitle2 = bufferedReader.readLine();
				
				System.out.println("你输入的第二个公司的名称是:" + CopTitle2 + "\n");
				
			} catch ( IOException e) {
				
				System.out.println("第二个公司读写流错误"+ "\n");
				e.printStackTrace();
				// TODO: handle exception
			}
    	
    	
	}


    public static boolean writeImageSingle(BufferedImage bi, String picType, File file) {
		// TODO 自动生成的方法存根
    	Graphics2D graphics2d = (Graphics2D)bi.getGraphics();
    	
    	//设置背景
    	graphics2d.setColor(Color.white);
    	graphics2d.fillRect(0, 0, 3600, 4676);
    	
    	Color myColor = new Color(255, 0, 0);
    	graphics2d.setColor(myColor);
    	Font  myFont = new Font("新宋体",Font.BOLD, 230);
    	graphics2d.setFont(myFont);
    	
    	
  	//画文字
    	drawtext(CopTitle1,startXlocationTEXT1Single,startYlocationTEXT1Single,XareaForSingleText,graphics2d,myFont);
    	
//    	graphics2d.setFont(new Font("宋体",Font.BOLD, 210));
    	drawtext("有限公司",2295,400,2850,graphics2d,myFont);

    	drawline(graphics2d,427, 1208, 2685, 15);
    	
        graphics2d.dispose();
        
        boolean val = false;
        try {
            val = ImageIO.write(bi, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return val;
    	
    	
	}
	
	
	

	public static boolean writeImageDouble(BufferedImage bi, String picType, File file) {
        
    	Graphics2D graphics2d = (Graphics2D)bi.getGraphics();
    	
    	//设置背景
    	graphics2d.setColor(Color.white);
    	graphics2d.fillRect(0, 0, 3600, 4676);
    	
    	//设置文字
//    	graphics2d.setColor(Color.RED);
//    	Font myfonFont = new Font("宋体", Font.BOLD, 100);
//    	graphics2d.setFont(myfonFont);
    	
    	
    	
    	Color myColor = new Color(255, 0, 0);
    	Font  myFont = new Font("宋体",Font.BOLD, 175);
    	
    	

    	
    	
    	//画文字
    	StringDrawer(graphics2d,myFont,myColor,XareaForDoubleText);
    	
    	//画线
    	
    	drawline(graphics2d,427, 1208, 2685, 15);
    	
    	
    	   	
        graphics2d.dispose();
        
        boolean val = false;
        try {
            val = ImageIO.write(bi, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
        
    }
    
    
    public static void StringDrawer(Graphics2D graphics2d,Font myfonFont,Color mylColor,int Xarea) {
    	
    
    	graphics2d.setColor(mylColor);
    	graphics2d.setFont(myfonFont);
    	    	
    	drawtext(CopTitle1,startXlocationTEXT1,startYlocationTEXT1,Xarea,graphics2d,myfonFont);
    	drawtext(CopTitle2,startXlocationTEXT2,startYlocationTEXT2,Xarea,graphics2d,myfonFont);
    	
    	Font  coptextfont = new Font("新宋体",Font.BOLD, 230);
    	
    	drawtext("有限公司",2268,400,2830,graphics2d,coptextfont);
    	
    	
    	
	}
    
    public static void drawtext(String s,float x,float y,float wholeXarea,Graphics2D graphics2d,Font myfont) {
    	
    	
    	String NowDrawWord = null;
    	int WholeStringWidth = graphics2d.getFontMetrics().stringWidth(s);
    	float EveryWordWidth = WholeStringWidth/s.length();
    	float DrawDistenceInterpolator = (wholeXarea-x)/(s.length()-1);
    	float AverageDrawerBeginLocationX = wholeXarea/s.length();
    	
    	graphics2d.setFont(myfont);
    	
    	FontMetrics fontinformation = graphics2d.getFontMetrics();
    	
    	wordheight = fontinformation.getHeight();
    	wordwidth = (fontinformation.getWidths())[0];
    	
    	BufferedImage changeimagesize = new BufferedImage(wordwidth, wordheight, BufferedImage.TYPE_INT_BGR);
    	
    	Graphics2D changeimageword = (Graphics2D)(changeimagesize.getGraphics());
    	
    	changeimageword.setFont(myfont);
    	

    	
    	for (int i = 0; i < s.length(); i++) {
			
    		NowDrawWord = s.substring(i,i+1);
    			
    			double RescaleX = 0.6;
    			double Rescaley = 1.2;
    			
    			int ResizeX = (int)(wordwidth*RescaleX);
    			int ResizeY = (int)(wordheight*Rescaley);
    			
    			changeimageword.setColor(Color.white);
    			
    			changeimageword.fillRect(0, 0, wordwidth, wordheight);
    			
    			changeimageword.setColor(Color.RED);
    			
    			changeimageword.setFont(myfont);
    			
    			changeimageword.drawString(NowDrawWord, 0, (int)(wordheight*0.8));
    			
    			graphics2d.drawImage(changeimagesize, (int)(x+i*DrawDistenceInterpolator), (int)y, ResizeX, ResizeY, null);
    			
		}
    	
    	changeimageword.dispose();
    	
    }
    
    
    
    
    public static void drawline(Graphics2D graphics2d,int x,int y,int width,int height) {
    	
    	graphics2d.drawRect(x, y, width, height);
    	graphics2d.fillRect(x, y, width, height);
    
	}


}
