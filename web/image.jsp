<%@ page contentType="image/jpeg" import="javax.imageio.ImageIO,
                                          java.awt.*,
                                          java.awt.image.BufferedImage,
                                          java.util.Random" %>
<%!
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
%><%
    out.clear();//????resin???????tomacat??????
    out = pageContext.pushBody();
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    int width = 100, height = 44;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


    Graphics g = image.getGraphics();
    Random random = new Random();

    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);

    g.setFont(new Font("Times New Roman", Font.PLAIN, 18));


    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(12);
        int yl = random.nextInt(12);
        g.drawLine(x, y, x + xl, y + yl);
    }


    String sRand = "";
    for (int i = 0; i < 4; i++) {
        String rand = String.valueOf(random.nextInt(10));
        sRand += rand;
        g.setFont(new Font("Times New Roman",Font.BOLD,30));
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        g.drawString(rand, 25 * i + 6, 30);
    }
// ??????SESSION
    session.setAttribute("rand", sRand);

    g.dispose();

    ImageIO.write(image, "JPEG", response.getOutputStream());
%>