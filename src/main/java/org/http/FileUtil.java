package org.http;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

public class FileUtil {
    public static String uploadFile(String hostUrl, String token, String filepath) {

        StringBuffer result = new StringBuffer();
        try {

            String BOUNDARY = "======" + UUID.randomUUID().toString();
            String PREFIX = "--";
            String NEWLINE = "\r\n";

            URL url = new URL(hostUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("connection", "keep-alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//            connection.setRequestProperty("Authorization", "Bearer " + token);

            OutputStream out = new DataOutputStream(connection.getOutputStream());


            File file = new File(filepath);
            StringBuffer sb = new StringBuffer();
            sb.append(PREFIX)
                    .append(BOUNDARY)
                    .append(NEWLINE);

            sb.append("Content-Disposition: form-data;name=\"file\"; filename=\"" + file.getName() + "\"" + NEWLINE);
            sb.append("Content-Type: application/octet-stream");

            sb.append(NEWLINE);
            sb.append(NEWLINE);
            //将参数头写入输出流中
            out.write(sb.toString().getBytes());

            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            sb.append(NEWLINE);

            int bytes = 0;
            while ((bytes = dataInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
            }

            out.write(NEWLINE.getBytes());
            out.write((PREFIX + BOUNDARY + PREFIX).getBytes());
            out.flush();
            out.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resultLine;
            while ((resultLine = bufferedReader.readLine()) != null) {
                result.append(resultLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void uploadFile(String fileName, String token) {
        try {

            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL("http://192.168.99.100:8089/wp-json/wp/v2/media");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            File file = new File(fileName);
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"photo\";filename=\"" + fileName
                    + "\"" + newLine);
            sb.append("Content-Type:application/octet-stream");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 数据输入流,用于读取文件数据
            DataInputStream in = new DataInputStream(new FileInputStream(
                    file));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
            out.write(newLine.getBytes());
            in.close();

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
                    .getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();

            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
    }

    public static void fileDownload(String hostUrl, String filename) {
        try {
            URL url = new URL(hostUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);


            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");

            //rang 的起始位置


            String filePath = "D:\\data\\upload\\client\\" + filename;
            long rang = readFileStartDownload(filePath);
            connection.setRequestProperty("RANGE", "bytes=" + rang + "-");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
            String content = "fileName=" + URLEncoder.encode(filename, "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
            out.writeBytes(content);
            //流用完记得关
            out.flush();
            out.close();

            connection.connect();

            InputStream in = connection.getInputStream();

            String contentRange = connection.getHeaderField("Content-Range");

            if (contentRange.contains(String.valueOf(rang))) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
                long startPos = rang;
                randomAccessFile.seek(startPos);
                byte[] buffer = new byte[1024];
                int nRead = 0;
                while ((nRead = in.read(buffer)) > 0) {
                    randomAccessFile.write(buffer, 0, nRead);
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static long readFileStartDownload(String filepath) throws IOException {
        long start = 0;
        File file = new File(filepath);
        if (file.exists()) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            start = randomAccessFile.getFilePointer();
        }
        return start;

    }


    public static void main(String[] args) {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4Ljk5LjEwMDo4MDg5IiwiaWF0IjoxNTQ0NzYyMjg0LCJuYmYiOjE1NDQ3NjIyODQsImV4cCI6MTU0NTM2NzA4NCwiZGF0YSI6eyJ1c2VyIjp7ImlkIjoiMSJ9fX0.-CIb00RaY4igRoaLCqnTPLjGN1RuZQkYMr2K0kr65kQ";
//        String result = FileUtil.uploadFile("http://localhost:8080/upload",  token, "D:\\docker-compose\\wordpress\\docker-compose.yml");
//        FileUtil.uploadFile("",token);
//        System.out.println(result);


        fileDownload("http://localhost:8080/download", "text.txt");
    }
}
