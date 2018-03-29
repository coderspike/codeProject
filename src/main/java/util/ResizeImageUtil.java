package util;

/**
 * 图片压缩工具类
 *
 * @author DAGONG
 */

public class ResizeImageUtil {

//    /**
//     * 开启调用压缩图片方法
//     */
//    public static byte[] startImageResize(MultipartFile file, String fileName, byte[] images) throws Exception {
//        CommonsMultipartFile cf = (CommonsMultipartFile) file;
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//        File f = fi.getStoreLocation();
//        ResourceBundle bundle = ResourceBundle.getBundle("config");
//        float resizeRate = Float.parseFloat(bundle.getString("resizeRate"));
//        File sf = imgResize(images, f.getPath(), fileName, resizeRate);
//        byte[] img = ResizeImageUtil.File2byte(sf);
//        sf.delete();
//        return img;
//    }
//
//    /**
//     * 开启调用压缩图片方法 针对普通File
//     */
//    public static byte[] startImageResizeForFile(File f, String fileName, byte[] images) throws Exception {
//        ResourceBundle bundle = ResourceBundle.getBundle("config");
//        float resizeRate = Float.parseFloat(bundle.getString("resizeRate"));
//        File sf = imgResize(images, f.getPath(), fileName, resizeRate);
//        byte[] img = ResizeImageUtil.File2byte(sf);
//        sf.delete();
//        return img;
//    }
//
//    /**
//     * jdk压缩图片-质量压缩
//     * gwb
//     */
//    private static File imgResize(byte[] images, String filePath, String fileName, float quality) throws Exception {
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//        File file = null;
//        try {
//            File dir = new File(filePath.substring(0, filePath.lastIndexOf("\\") + 1));
//            if (!dir.exists() && dir.isDirectory()) {
//                dir.mkdirs();
//            }
//            file = new File(filePath.substring(0, filePath.lastIndexOf("\\") + 1) + File.separator + fileName);
//            fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos);
//            bos.write(images);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Image targetImage = ImageIO.read(file);
//        int width = targetImage.getWidth(null);
//        int height = targetImage.getHeight(null);
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.createGraphics();
//        g.drawImage(targetImage, 0, 0, width, height, null);
//        g.dispose();
//        String ext = getFileType(file.getName());
//        if (ext.equals("jpg") || ext.equals("jpeg")) {
//            FileOutputStream out = new FileOutputStream(file);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
//            jep.setQuality(quality, false);
//            encoder.setJPEGEncodeParam(jep);
//            encoder.encode(image);
//            out.close();
//        } else {
//            ImageIO.write(image, ext, file);
//        }
//        return file;
//    }
//
//    /**
//     * file and byte[] transfer
//     *
//     * @param file
//     * @return
//     */
//    private static byte[] File2byte(File file) {
//        byte[] buffer = null;
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return buffer;
//    }
//
//    /**
//     * 创建目录
//     *
//     * @param path
//     * @return path
//     */
//    private static String createDirectory(String path) {
//        File file = new File(path);
//        if (!file.exists())
//            file.getParentFile().mkdirs();
//        return path;
//    }
//
//    /**
//     * 通过文件名获取文件类型
//     *
//     * @param fileName 文件名
//     */
//    private static String getFileType(String fileName) {
//        if (fileName == null || "".equals(fileName.trim()) || fileName.lastIndexOf(".") == -1) {
//            return null;
//        }
//        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
//        return type.toLowerCase();
//    }
}
