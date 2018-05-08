package util;

public class pdfToImg {
//    /**
//     * 上传pdf同时对pdf切图
//     * @param file
//     * @param image_url
//     * @param pageNum
//     * @param request
//     */
//    @RequestMapping("/uploadImage.form")
//    public void uploadImage(@RequestParam(value = "file", required = false) MultipartFile file, String image_url, String pageNum, HttpServletRequest request) {
//        Json json = new Json();
//        List<MultipartFile> list = new ArrayList<MultipartFile>();
//        //切割pdf的pageNum页面
//        if (pageNum != "" && pageNum != null) {
//            InputStream is = null;
//            try {
//                is = file.getInputStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //先写到到项目的文件目录下，执行完会删除
//            String tomPath = new File("").getAbsolutePath();
//            int endIndex = tomPath.lastIndexOf("\\")+1;
//            String basePath = new File("").getAbsolutePath().substring(0,endIndex)+"webapps";
//            String filePahtAndName=basePath+"\\temp.jpg";
//            File imagefile = pdf2multiImage(is,  filePahtAndName, Integer.parseInt(pageNum));
//            //将切割成的图片转成MultipartFile并添加到list中
//            MultipartFile multipartFile = null;
//            try {
//                FileInputStream imageInput = new FileInputStream(imagefile);
//                multipartFile = new MockMultipartFile("file", "temp.jpg", "text/plain", IOUtils.toByteArray(imageInput));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String fileId=uplodFile(file,image_url);
//            String fileId2 = uplodFile(multipartFile,image_url);
//            String fileId3 = "&reportImageURL="+fileId2;
//            json.setObj(fileId+fileId3);
//            imagefile.delete();//删除临时图片
//        }else{
//            String fileId = uplodFile(file,image_url);
//            json.setObj(fileId);
//        }
//        json.setMsg("上传成功");
//        json.setSuccess(true);
//        super.writeJson(json);
//    }
//
//
//    private String uplodFile(MultipartFile file,String image_url){
//        Json json = new Json();
//        StorageClient1 client = null;
//        FileInputStream input = null;
//        InputStreamReader in = null;
//        String fileId="";
//        try {
//            String fileName = file.getOriginalFilename(); // 获取上传文件名
//            String fileName_str = fileName.substring(0, fileName.lastIndexOf("."));// 获取文件名
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();// 文件类型
//            byte[] images = file.getBytes();
//            client = FdfsClientPool.getConnection(3000);
//            NameValuePair[] metaList = new NameValuePair[3]; // 设置元信息
//            metaList[0] = new NameValuePair("fileName", fileName_str);
//            metaList[1] = new NameValuePair("fileExtName", fileExt); // 扩展名
//            metaList[2] = new NameValuePair("fileLength", images.length + "");
//            fileId = client.upload_file1(images, fileExt, metaList);// 原图上传
//            fileId = fileId + "," + client.upload_file1(images, fileExt, metaList);
//            if (image_url != null && !image_url.equals("")) {
//                client.delete_file1(image_url);// 修改时换了图片，删除原来的图片
//            }
//        } catch (Exception e) {
//            FdfsClientPool.drop(client);
//            logger.error("UserController-upload-error", e);
//            fileId="上传失败，请重试。";
//        } finally {
//            if (client != null) {
//                FdfsClientPool.remove(client);
//            }
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return fileId;
//    }
//
//    /**
//     * pdf 转图片
//     *
//     * @param outpath
//     * @param maxPage
//     */
//    private File pdf2multiImage(InputStream is, String outpath, int maxPage) {
//        File file = null;
//        try {
//            PDDocument pdf = PDDocument.load(is, true);
//            List<PDPage> pages = pdf.getDocumentCatalog().getAllPages();
//            List<BufferedImage> piclist = new ArrayList<BufferedImage>();
//            int actSize = pages.size();
//            if (actSize < maxPage) {
//                maxPage = actSize;
//            }
//            for (int i = maxPage - 2; i < maxPage; i++) {
//                piclist.add(pages.get(i).convertToImage());
//            }
//            file = yPic(piclist, outpath);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//
//    /**
//     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
//     *
//     * @param piclist 文件流数组
//     * @param outPath 输出路径
//     */
//    public File yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
//        File outFile = null;
//        if (piclist == null || piclist.size() <= 0) {
//            System.out.println("图片数组为空!");
//            return null;
//        }
//        try {
//            int height = 0, // 总高度
//                    width = 0, // 总宽度
//                    _height = 0, // 临时的高度 , 或保存偏移高度
//                    __height = 0, // 临时的高度，主要保存每个高度
//                    picNum = piclist.size();// 图片的数量
//            File fileImg = null; // 保存读取出的图片
//            int[] heightArray = new int[picNum]; // 保存每个文件的高度
//            BufferedImage buffer = null; // 保存图片流
//            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
//            int[] _imgRGB; // 保存一张图片中的RGB数据
//            for (int i = 0; i < picNum; i++) {
//                buffer = piclist.get(i);
//                heightArray[i] = _height = buffer.getHeight();// 图片高度
//                if (i == 0) {
//                    width = buffer.getWidth();// 图片宽度
//                }
//                height += _height; // 获取总高度
//                _imgRGB = new int[width * _height];// 从图片中读取RGB
//                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
//                imgRGB.add(_imgRGB);
//            }
//            _height = 0; // 设置偏移高度为0
//            // 生成新图片
//            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            for (int i = 0; i < picNum; i++) {
//                __height = heightArray[i];
//                if (i != 0) {
//                    _height += __height; // 计算偏移高度
//                }
//                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
//            }
//            outFile = new File(outPath);
//            ImageIO.write(imageResult, "jpg", outFile);// 写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return outFile;
//    }
}
